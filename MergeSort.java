/**
 * Java Class to Implement Merge Sort
 */

package sorting;

/**
 *
 * @author Michael
 */

public class MergeSort
{

    int comparsionCount=0, copyCount=0;
    private int[] array;
    private int length;
    private int[] tempMergArr;

    public void sort(int inputArr[])
    {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerindex, int higherindex)
    {

        if (lowerindex < higherindex)
        {
            int middle = lowerindex + (higherindex - lowerindex) / 2;
            // Below step sorts the left side of the array 
            doMergeSort(lowerindex, middle);
            // Below step sorts the right side of the array 
            doMergeSort(middle + 1, higherindex);
            // Now merge both sides
            mergeParts(lowerindex, middle, higherindex);

        }

    }

    private void mergeParts(int lowerindex, int middle, int higherindex)
    {

        for (int i = lowerindex; i <= higherindex; i++)
        {
            tempMergArr[i] = array[i];
            copyCount++;
        }
        int i = lowerindex;
        int j = middle + 1;
        int k = lowerindex;
        while (i <= middle && j <= higherindex)
        {
            comparsionCount++;
            if (tempMergArr[i] <= tempMergArr[j])
            {
                array[k] = tempMergArr[i];
                i++;
            } else
            {
                array[k] = tempMergArr[j];
                j++;
            }
            copyCount++;
            k++;
        }

        while (i <= middle)
        {
            array[k] = tempMergArr[i];
            copyCount++;
            k++;
            i++;

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