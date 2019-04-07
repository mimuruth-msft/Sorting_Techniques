/**
 * Java Class to Implement Quick Sort
 */

package sorting;

/**
 *
 * @author Michael
 */

public class QuickSort
{

    int comparsionCount, copyCount;

    int[] array;

    public void quickSort(int[] array)
    {
        comparsionCount = 0;
        copyCount = 0;
        this.array = array;
        QuickSort.this.quickSort(0, array.length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex)
    {

        int i = lowerIndex;
        int j = higherIndex;

        // calculate pivot number,
        exchangeNumbers(lowerIndex, lowerIndex + (higherIndex - lowerIndex) / 2);
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j)
        {
            /*
             In each iteration, we will identify a number from left side which
             is greater then the pivot value, and also we will identify a number   * from right side which is less then the pivot value. Once the search   * is done, then we exchange both numbers.
             from right side which is less then the pivot value. Once the search   * is done, then we exchange both numbers.
             is done, then we exchange both numbers.
             */
            comparsionCount++;
            while (array[i] < pivot)
            {
                comparsionCount++;
                i++;
            }
            comparsionCount++;
            while (array[j] > pivot)
            {
                j--;
                comparsionCount++;
            }
            if (i <= j)
            {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively 
        if (lowerIndex < j)
        {
            QuickSort.this.quickSort(lowerIndex, j);
        }
        if (i < higherIndex)
        {
            QuickSort.this.quickSort(i, higherIndex);
        }
    }

    private void exchangeNumbers(int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        copyCount += 3;
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