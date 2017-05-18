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

    public void quickSort(int [] array, int start, int end, PartitionType partitionType)
    {
        if (start < end)
        {
            int indexOfBasicElement = partitionType.partition(array, start, end);
            quickSort(array, start, indexOfBasicElement - 1, partitionType);
            quickSort(array, indexOfBasicElement + 1, end, partitionType);
        }
    }
}
