import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by dog on 7/30/17.
 */
public class Graph {
    private ArrayList<Node> nodes;
    private int time;
    private ArrayList<Integer> counter;

    public Graph(int size) {
        createNodes(size);
    }

    public Graph(String filePath) {
        try {
            Scanner s = new Scanner(new File(filePath));
            int size = s.nextInt();
            createNodes(size);

            int from;
            int to;
            while (s.hasNextInt()) {
               from = s.nextInt();
               to = s.nextInt();
               addLink(from, to);
            }

            s.close();
        } catch (IOException i) {
            System.out.println("Error : " + i);
        }
    }

    public void addLink(int from, int to) {
        nodes.get(from).links.add(nodes.get(to));
    }

    public ArrayList<Integer> strongConnectedComponent() {
        DFSLoop();
        transpose();
        Collections.sort(nodes, new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return node2.time.compareTo(node1.time);
            }
        });
        DFSLoop();
        return counter;
    }

    private void BFS(Node firstNode) {
        firstNode.marked = true;
        LinkedList<Node> nodesQueue = new LinkedList<Node>();
        nodesQueue.add(firstNode);

        while (!nodesQueue.isEmpty()) {
            Node node = nodesQueue.poll();

            for (Node linkedNode : node.links) {
                if (linkedNode.marked == false) {
                    linkedNode.marked = true;
                    nodesQueue.add(linkedNode);
                }
            }
        }
    }

    private void DFS(Node firstNode) {
        firstNode.marked = true;
        Stack<Node> nodesStack = new Stack<Node>();
        nodesStack.push(firstNode);


        while (!nodesStack.isEmpty()) {
            Node node = nodesStack.peek();

            boolean noNextNode = true;
            for (Node linkedNode : node.links) {
                if (linkedNode.marked == false) {
                    linkedNode.marked = true;
                    nodesStack.push(linkedNode);
                    noNextNode = false;
                    break;
                }
            }

            if (noNextNode) {
                node.time = ++time;
                nodesStack.remove(node);
            }
        }
    }

    public void transpose () {
        ArrayList<Node> buf = nodes;
        nodes = new ArrayList<Node>(buf.size());

        for (Node node : buf) {
            nodes.add(node.copy());
        }

        for (int i = 1; i < buf.size(); i++) {
            Node node = buf.get(i);
            for (Node linkedNode : node.links) {
                addLink(linkedNode.number, i);
            }
        }
    }

    private void createNodes(int size) {
        nodes = new ArrayList<Node>(size);
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(i));
        }
    }

    private void DFSLoop () {
        counter = new ArrayList<Integer>();
        time = 0;
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            int oldTime = time;
            if (node.marked == false) {
                DFS(node);
                counter.add(time - oldTime);
            }
        }
    }

    private void DFSR (Node node) {
        node.marked = true;
        for (Node linkedNode : node.links) {
            if (linkedNode.marked == false) {
                DFSR(linkedNode);
            }
            node.time = ++time;
        }
    }

    private class Node  {
        ArrayList<Node> links;
        Integer time;
        Integer number;
        boolean marked;

        Node(Integer number) {
            this.links = new ArrayList<Node>();
            this.number = number;
            marked = false;
            time = 0;
        }

        protected Node copy() {
            Node node = new Node(number);
            node.marked = false;
            node.time = time;
            return node;
        }
    }
}