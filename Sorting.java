/**
 * Java Program to Implement Sorting
 */

package sorting;

/**
 * @author Michael
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Sorting
{

    static int comparsionCount, copyCount;

    public static void InsertionSort(int[] a)
    {
        comparsionCount = 0;
        copyCount = 0;
        for (int i = 1; i < a.length; i++)
        {
            int temp = a[i];
            copyCount++;
            int j;
            for (j = i - 1; j >= 0 && temp < a[j]; j--, comparsionCount++)
            {
                a[j + 1] = a[j];
                copyCount++;
            }
            copyCount++;
            a[j + 1] = temp;
        }
    }

    public static int[] doSelectionSort(int[] arr)
    {
        comparsionCount = 0;
        copyCount = 0;
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                comparsionCount++;
                if (arr[j] < arr[index])
                {
                    index = j;
                }
            }
            copyCount += 3;
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }

    public static int[] generateRandom(int size)
    {
        int[] result = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            result[i] = random.nextInt(size);
        }
        return result;
    }

    //
    public static int[] generateInOrder(int size)
    {
        int[] result = generateRandom(size);
        Arrays.sort(result);
        return result;
    }

    public static int[] generateReverseOrder(int size)
    {
        int[] result = generateRandom(size);
        Arrays.sort(result);
        for (int i = 0; i < result.length; i++)
        {
            int temp = result[i];
            result[i] = result[result.length - 1 - i];
            result[result.length - 1 - i] = temp;
        }
        return result;
    }

    public static int[] generateSomeOutofOrder(int size)
    {
        int[] result = generateInOrder(size);
        Random random = new Random();
        for (int i = 0; i < size / 10; i++)
        {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            int temp = result[index1];
            result[index1] = result[index2];
            result[index2] = temp;
        }
        return result;
    }

    public static void radixSort(int[] array, int maxPowerOf10)
    {
        comparsionCount = 0;
        copyCount = 0;
        Queue<Integer>[] queueArray = new Queue[10];

        for (int queueNum = 0; queueNum < 10; queueNum++)
        {
            queueArray[queueNum] = new LinkedList<Integer>();
        }

        for (int powerOf10 = 1; powerOf10 <= maxPowerOf10; powerOf10 = powerOf10 * 10)
        {
            for (int item = 0; item < array.length; item++)
            {
                int digit = getDigit(array[item], powerOf10);
                comparsionCount++;
                copyCount++;
                queueArray[digit].add(new Integer(array[item]));
            }
            int item = 0;
            for (int queueNum = 0; queueNum < 10; queueNum++)
            {
                while (!queueArray[queueNum].isEmpty())
                {
                    copyCount++;
                    array[item] = ((Integer) queueArray[queueNum].poll().intValue());
                    item++;
                }
            }
        }
    }

    public static int getDigit(int number, int powerOf10)
    {
        return (number / powerOf10) % 10;
    }

    public static void main(String[] args)
    {
        // Choose how the elements are ordered from these choices:
        // InOrder: Array elements are already in sorted order, i.e., from smallest to
        // largest.
        // ReverseOrder: Array elements are in the exact opposite order from sorted,
        // i.e., from largest to smallest.
        // AlmostOrder: Array elements are almost in sorted order, but a few
        // elements are in the wrong place.
        // Random: The initial order of the elements is chosen at random, so it is
        // probably far from being sorted (or reverse-sorted).
        Scanner input = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("1. InOrder");
            System.out.println("2. ReverseOrder");
            System.out.println("3. AlmostOrder");
            System.out.println("4. Random");
            choice = input.nextInt();
        } while (choice < 1 || choice > 4);
        //2. Specify the array size by user input
        System.out.println("Specify the array size:");

        int size = input.nextInt();
        int[] arr = null;
        switch (choice)
        {
            case 1:
                arr = Sorting.generateInOrder(size);
                break;
            case 2:
                arr = Sorting.generateReverseOrder(size);
                break;
            case 3:
                arr = Sorting.generateSomeOutofOrder(size);
                break;
            case 4:
                arr = Sorting.generateRandom(size);
                break;
        }
        long endTime,startTime;
        int[] tempArray = Arrays.copyOf(arr, size);
        HeapSort heapSort = new HeapSort();
        QuickSort quickSort = new QuickSort();
        MergeSort mergeSort = new MergeSort();        
        tempArray = Arrays.copyOf(arr, size);
        //first sort is slow due unknown reason
        startTime = System.nanoTime();
        Arrays.sort(tempArray);
        endTime = System.nanoTime() - startTime;
        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        Arrays.sort(tempArray);
        endTime = System.nanoTime() - startTime;

        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        radixSort(tempArray, (int) Math.pow(10, Math.log10(size) + 1));
        endTime = System.nanoTime() - startTime;
        System.out.println("Radix Sort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + comparsionCount);
        System.out.println("Copies: " + copyCount);
        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        quickSort.quickSort(tempArray);
        endTime = System.nanoTime() - startTime;
        System.out.println("Quick Sort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + quickSort.getComparsionCount());
        System.out.println("Copies: " + quickSort.getCopyCount());
        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        InsertionSort(tempArray);
        endTime = System.nanoTime() - startTime;
        System.out.println("InsertionSort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + comparsionCount);
        System.out.println("Copies: " + copyCount);
        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        doSelectionSort(tempArray);
        endTime = System.nanoTime() - startTime;
        System.out.println("SelectionSort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + comparsionCount);
        System.out.println("Copies: " + copyCount);

        tempArray = Arrays.copyOf(arr, size);
        startTime = System.nanoTime();
        mergeSort.sort(tempArray);
        endTime = System.nanoTime() - startTime;
        System.out.println("MergeSort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + mergeSort.getComparsionCount());
        System.out.println("Copies: " + mergeSort.getCopyCount());
        startTime = System.nanoTime();
        heapSort.heapSort(tempArray);
        endTime = System.nanoTime() - startTime;
        System.out.println("Heap Sort: " + ((double) endTime) / (1000000.0));
        System.out.println("Comparstions: " + heapSort.getComparsionCount());
        System.out.println("Copies: " + heapSort.getCopyCount());
    }

}