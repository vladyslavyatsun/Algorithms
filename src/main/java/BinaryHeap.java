import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dog on 7/16/17.
 */
public abstract class BinaryHeap {
    protected List<Integer> elements;

    protected abstract void heapify(int i);
    public abstract void add(int element);

    protected BinaryHeap () {
        elements = new LinkedList<Integer>();
    }

    public static BinaryHeap maxHeap() {
        return new MaxHeap();
    }

    public static BinaryHeap minHeap() {
        return new MinHeap();
    }

    public int size() {
        return elements.size();
    }

    public int top() {
        return elements.get(0);
    }

    public int extractTop() {
        int result = elements.get(0);
        int lastElementIndex = size() - 1;
        elements.set(0, elements.get(lastElementIndex));
        elements.remove(lastElementIndex);
        heapify(0);
        return result;
    }

    public void build(Integer [] array) {
        elements = new LinkedList<Integer>(Arrays.asList(array));
        for (int i = size() / 2; i >= 0 ; i--) {
            heapify(i);
        }
    }

    public void sort(Integer [] array) {
        build(array);
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = extractTop();
        }
    }

    public void print() {
        for (int i = 0; i < elements.size() - 1; i++) {
            System.out.print(elements.get(i) + " ");
            if (i == 0  || i == 2 || i == 6 || i == 14 ) {
                System.out.println();
            }
        }
    }
}

class MaxHeap extends BinaryHeap {

    public void add(int element) {
        elements.add(element);

        int i = size() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && elements.get(parent) < elements.get(i)) {
            int temp = elements.get(i);
            elements.set(i, elements.get(parent));
            elements.set(parent, temp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void heapify(int i) {
        int leftChild;
        int rightChild;
        int largestChild;

        for ( ; ; ) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < size() && elements.get(leftChild) > elements.get(largestChild)) {
                largestChild = leftChild;
            }

            if (rightChild < size() && elements.get(rightChild) > elements.get(largestChild)) {
                largestChild = rightChild;
            }

            if (largestChild == i) {
                return;
            }

            int temp = elements.get(i);
            elements.set(i, elements.get(largestChild));
            elements.set(largestChild, temp);
            i = largestChild;
        }
    }
}

class MinHeap extends BinaryHeap {

    public void add(int element) {
        elements.add(element);

        int i = size() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && elements.get(parent) > elements.get(i)) {
            int temp = elements.get(i);
            elements.set(i, elements.get(parent));
            elements.set(parent, temp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void heapify(int i) {
        int leftChild;
        int rightChild;
        int smallestChild;

        for ( ; ; ) {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            smallestChild = i;

            if (leftChild < size() && elements.get(leftChild) < elements.get(smallestChild)) {
                smallestChild = leftChild;
            }

            if (rightChild < size() && elements.get(rightChild) < elements.get(smallestChild)) {
                smallestChild = rightChild;
            }

            if (smallestChild == i) {
                return;
            }

            int temp = elements.get(i);
            elements.set(i, elements.get(smallestChild));
            elements.set(smallestChild, temp);
            i = smallestChild;
        }
    }
}
