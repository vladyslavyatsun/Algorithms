import java.util.Random;

/**
 * Created by dog on 5/9/17.
 */
public class Sorter {
    public static int count = 0;

    enum PartitionType {
        LAST_BASIC_ELEMENT {
            public int partition(int [] array, int start, int end) {
                int basicElement = array[end];
                int i = start - 1;
                for (int j = start; j < end; j++) {
                    count++;
                    if (array[j] < basicElement) {
                        int buf = array[++i];
                        array[i] = array[j];
                        array[j] = buf;
                    }
                }
                array[end] = array[++i];
                array[i] = basicElement;
                return i;
            }
        },
        FIRST_BASIC_ELEMENT {
            public int partition(int [] array, int start, int end) {
                int basicElement = array[start];
                array[start] = array[end];
                int i = start - 1;
                for (int j = start; j < end; j++) {
                    count++;
                    if (array[j] < basicElement) {
                        int buf = array[++i];
                        array[i] = array[j];
                        array[j] = buf;
                    }
                }
                array[end] = array[++i];
                array[i] = basicElement;
                return i;
            }
        },
        MEDIANA_BASIC_ELEMENT {
            public int partition(int [] array, int start, int end) {
                int indexOfBasicElement = (end - start)/2 + start;

                if (array[start] > array[end] && array[end] > array[indexOfBasicElement] || array[indexOfBasicElement] > array[end] && array[end] > array[start]) {
                    indexOfBasicElement = end;
                } else if (array[end] > array[start] && array[start] > array[indexOfBasicElement] || array[indexOfBasicElement] > array[start] && array[start] > array [end]) {
                    indexOfBasicElement = start;
                }

                int basicElement = array[indexOfBasicElement];
                array[indexOfBasicElement] = array[end];
                int i = start - 1;
                for (int j = start; j < end; j++) {
                    count++;
                    if (array[j] < basicElement) {
                        int buf = array[++i];
                        array[i] = array[j];
                        array[j] = buf;
                    }
                }
                array[end] = array[++i];
                array[i] = basicElement;
                return i;
            }
        },
        RANDOM_BASIC_ELEMENT {
            public int partition(int [] array, int start, int end) {
                Random random = new Random();
                int indexOfBasicElement = random.nextInt(end - start + 1) + start;
                int basicElement = array[indexOfBasicElement];
                array[indexOfBasicElement] = array[end];
                int i = start - 1;
                for (int j = start; j < end; j++) {
                    if (array[j] < basicElement) {
                        int buf = array[++i];
                        array[i] = array[j];
                        array[j] = buf;
                    }
                }
                array[end] = array[++i];
                array[i] = basicElement;
                return i;
            }
        };

        public abstract int partition(int [] array, int start, int end);
    }

    public void quickSort(int [] array, int start, int end, PartitionType partitionType) {
        if (start < end)
        {
            int indexOfBasicElement = partitionType.partition(array, start, end);
            quickSort(array, start, indexOfBasicElement - 1, partitionType);
            quickSort(array, indexOfBasicElement + 1, end, partitionType);
        }
    }

    /**
     * @param array - string array. Allow use symbols: abcdefghijklmnopqrstuvwxyz.
     *             Elements must have equal length.
     */
    public String [] radixSort(String [] array) {
        String [] result = array;
        for (int i =  array[0].length() - 1; i >= 0; i--) {
            result = lineSort(result, i);
        }
        return result;
    }

    private String [] lineSort(String [] array, int index) {
        // number of first symbol(a) is 97, number of last symbol(z) is 122
        int [] countArray = new int[122 - 97 + 1];
        String [] resultArray = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i].charAt(index) - 97]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i-1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            resultArray[--countArray[array[i].charAt(index) - 97]] = array[i];
        }
        return resultArray;
    }
}
