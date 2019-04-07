/**
 * Java Class to Implement Heap Sort
 */
package sorting;

/**
 *
 * @author Michael
 */

public class HeapSort
{

    int comparsionCount = 0, copyCount = 0;

    private int size;
    private int[] arr;

    private void buildHeap()
    {
        for (int i = (size - 1) / 2; i >= 0; i--)
        {
            int val = arr[i];
            copyCount+=2;
            int hole = percolateDown(i, val);
            arr[hole] = val;
        }
    }

    int percolateDown(int hole, int val)
    {
        while ((2 * (hole + 1)) - 1 < size - 1)
        {
            int target;
            int left = (2 * (hole + 1)) - 1;
            int right = left + 1;
            comparsionCount++;
            if (arr[left] > arr[right]
                    || right >= size)
            {
                target = left;
            } else
            {
                target = right;
            }
            comparsionCount++;
            if (arr[target] > val)
            {
                copyCount++;
                arr[hole] = arr[target];
                hole = target;
            } else
            {
                break;
            }
        }
        return hole;
    }

    int deleteMax()
    {
        int ans = arr[0];
        int hole = percolateDown(0, arr[size - 1]);
        copyCount+=2;
        arr[hole] = arr[size - 1];
        size--;
        return ans;
    }

    public void heapSort(int[] arr)
    {
        this.arr = arr;
        size = arr.length;
        buildHeap();
        int lastIndex = arr.length - 1;
        while (size > 0)
        {
            arr[lastIndex] = deleteMax();
            lastIndex--;
        }
    }

    public int getComparsionCount()
    {
        return comparsionCount;
    }

    public int getCopyCount()
    {
        return copyCount;
    }

}