/**
 * Created by dog on 7/17/17.
 */
public class MedianCounter {
    private BinaryHeap maxHeap;
    private BinaryHeap minHeap;

    public MedianCounter () {
        maxHeap = BinaryHeap.maxHeap();
        minHeap = BinaryHeap.minHeap();
    }

    public void addElement(int element) {
        if (maxHeap.size() == 0 || maxHeap.top() > element)
        {
            maxHeap.add(element);
        } else {
            minHeap.add(element);
        }

        if (maxHeap.size() - minHeap.size() == 2) {
            minHeap.add(maxHeap.extractTop());
        } else if (minHeap.size() - maxHeap.size() == 2) {
            maxHeap.add(minHeap.extractTop());
        }
    }

    public int [] getMedian() {
        int [] result = new int[0];
        if (maxHeap.size() > minHeap.size() && maxHeap.size() > 0) {
            result = new int[] {maxHeap.top()};
        } else if (maxHeap.size() < minHeap.size() && minHeap.size() > 0) {
            result = new int[] {minHeap.top()};
        } else if (maxHeap.size() > 0 && minHeap.size() > 0){
            result = new int[]{maxHeap.top(), minHeap.top()};
        }
        return result;
    }
}
