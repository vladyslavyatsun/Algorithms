/**
 * Created by dog on 4/30/17.
 */
public class InversionCounter {
    public int inversionCount(int [] a, int [] b) {
        int [] array = sortArrayToIndexArray(a, b);
        return sortAndCountInversion(array, 0, array.length-1);
    }

    // merge sort
    private int sortAndCountInversion(int [] array, int start, int end) {
        int mid;
        int x = 0;
        int y = 0;
        int z = 0;
        if (start < end) {
            mid = (start + end)/2;
            x = sortAndCountInversion(array, start, mid);
            y = sortAndCountInversion(array, mid + 1, end);
            z = mergeAndCountInversion(array, start, mid, end);
        }
        return x + y + z;
    }

    private int mergeAndCountInversion(int [] array, int start, int mid, int end) {
        int lengthLeftArray = mid - start + 1;
        int lengthRightArray = end - mid;
        int [] leftArray = new int [lengthLeftArray + 1];
        int [] rightArray = new int [lengthRightArray + 1];
        // last elements -> infinity
        leftArray[lengthLeftArray] = Integer.MAX_VALUE;
        rightArray[lengthRightArray] = Integer.MAX_VALUE;

        for (int i = 0; i < lengthRightArray; i++) {
            leftArray[i] = array[start + i];
            rightArray[i] = array[mid + i + 1];
        }
        if (lengthLeftArray > lengthRightArray) {
            leftArray[lengthLeftArray - 1] = array[mid];
        }
        leftArray[lengthLeftArray] = Integer.MAX_VALUE;
        rightArray[lengthRightArray] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int inversion = 0;

        for (int k = start; k < end + 1; k++) {
            if (leftArray[i] < rightArray[j])
            {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
                inversion += lengthLeftArray - i;
            }
        }
        return inversion;
    }

    private int [] sortArrayToIndexArray(int [] indexArray, int [] array) {
        int [] result = new int[indexArray.length];
        int index;
        for (int i = 0; i < indexArray.length; i++) {
            index = indexArray[i] - 1;
            result[index] = array[i];
        }
        return result;
    }
}
