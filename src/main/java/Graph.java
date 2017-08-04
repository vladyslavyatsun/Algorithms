import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

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
            int weight;
            while (s.hasNextInt()) {
               from = s.nextInt();
               to = s.nextInt();
               weight = s.nextInt();
               addLink(from, to, weight);
            }

            s.close();
        } catch (IOException i) {
            System.out.println("Error : " + i);
        }
    }

    public void addLink(int from, int to) {
        nodes.get(from).links.add(new Link(nodes.get(to)));
    }

    public void addLink(int from, int to, int weight) {
        nodes.get(from).links.add(new Link(nodes.get(to), weight));
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

            for (Link link: node.links) {
                Node linkedNode = link.node;
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
            for (Link link : node.links) {
                Node linkedNode = link.node;
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
            for (Link link : node.links) {
                Node linkedNode = link.node;
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
        for (Link link : node.links) {
            Node linkedNode = link.node;
            if (linkedNode.marked == false) {
                DFSR(linkedNode);
            }
            node.time = ++time;
        }
    }

    public int dijkstra(int start, int end) {
        ArrayList<Node> bufNodes = (ArrayList<Node>) nodes.clone();
        ArrayList<Node> nodesQueue = new ArrayList<Node>();
        ArrayList<Node> nodesForRemove;

        Node startNode = nodes.get(start);
        Node endNode = nodes.get(end);
        startNode.time = 0;
        startNode.marked = true;

        nodesQueue.add(startNode);
        bufNodes.remove(start);

        while (!bufNodes.isEmpty()) {
            int time = Integer.MAX_VALUE;
            Node nearestNode = null;

            nodesForRemove = new ArrayList<Node>();
            for (Node node : nodesQueue) {
                boolean removeNode = true;
                for (Link link : node.links) {
                    if (!link.node.marked) {
                        removeNode = false;

                        if ((time >= (node.time + link.weight))) {
                            nearestNode = link.node;
                            time = node.time + link.weight;
                            nearestNode.time = time;
                            nearestNode.previousNodes.add(node);
                        }
                    }
                }

                if (removeNode) {
                    nodesForRemove.add(node);
                }

                if (nodesQueue.size() == 0) {
                    break;
                }
            }

            nodesQueue.removeAll(nodesForRemove);
            nearestNode.marked = true;
            nodesQueue.add(nearestNode);
            bufNodes.remove(nearestNode);
        }

        System.out.println(endNode.previousNodes.size());
        return endNode.time;
    }


    private class Node  {
        ArrayList<Link> links;
        Integer time;
        Integer number;
        boolean marked;
        ArrayList<Node> previousNodes;

        Node(Integer number) {
            this.links = new ArrayList<Link>();
            this.number = number;
            marked = false;
            time = Integer.MAX_VALUE;
            previousNodes = new ArrayList<Node>();
        }

        protected Node copy() {
            Node node = new Node(number);
            node.marked = false;
            node.time = time;
            return node;
        }
    }

    private class Link {
        Integer weight;
        Node node;

        Link(Node node, Integer weight) {
            this.node = node;
            this.weight = weight;
        }

        Link(Node node) {
            this(node, 1);
        }
    }
}