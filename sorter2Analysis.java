import java.util.Random;

public class sorter2Analysis {


    static  void insertionSort(Integer A[], int p, int q) {
        for (int i = p; i < q; i++) {
            int tempVal = A[i + 1];
            int j = i + 1;
            while (j > p && A[j - 1] > tempVal) {
                A[j] = A[j - 1];
                j--;
            }
            A[j] = tempVal;
        }
        /*int temp[] = Arrays.copyOfRange(A, p, q + 1);*/

    }
    static   void merge(Integer arr[], int l, int m, int r)
    {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        /* create temp arrays */
        Integer L [] = new Integer[n1];
        Integer R [] = new Integer[n2];

        /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = l; // Initial index of merged subarray
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

	/* Copy the remaining elements of L[], if there
	are any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

	/* Copy the remaining elements of R[], if there
	are any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    static  void mergeSort(Integer arr[], int l, int r)
    {
        if (r > l)
        {
            // Same as (l+r)/2, but avoids overflow for
            // large l and h
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    static  void Modified_mergeSort(Integer arr[], int l, int r,int k)
    {
        if (r>l && r - l > k)
        {
            int m = l + (r - l) / 2;
            Modified_mergeSort(arr, l, m,k);
            Modified_mergeSort(arr, m + 1, r,k);
            merge(arr, l, m, r);
        }
        else if(r-l<=k)
        {
            insertionSort(arr, l, r);
        }
    }
    static void Randomdata(Integer [] a1, Integer [] a2, Integer [] a3, Integer[] a4)
    {
        Random rand = new Random();

        for (int i = 0; i < 1000; i++)
        {
            a1[i] = rand.nextInt(1000);
        }
        for (int i = 0; i < 10000; i++)
        {
            a2[i] = rand.nextInt(10000);
        }
        for (int i = 0; i < 50000; i++)
        {
            a3[i] = rand.nextInt(50000);
        }
        for (int i = 0; i < 100000; i++)
        {
            a4[i] = rand.nextInt(100000);

        }
    }
    public static void main(String[] args) {

        Integer array1[] = new Integer[1000];
        Integer array2[] = new Integer[10000];
        Integer array3[] = new Integer[50000];
        Integer array4[] = new Integer[100000];
        Randomdata(array1, array2, array3, array4);

        for (int i = 0; i < 10; i++)
        {
            System.out.print( array1[i] );
        }
        System.out.println();
       long t = System.nanoTime();

        //mergeSort(array1, 0, 999);

        //mergeSort(array2, 0, 9999);
        //mergeSort(array3, 0, 49999);
        //mergeSort(array4, 0, 99999);
        Modified_mergeSort(array1, 0, 999,10);

        //Modified_mergeSort(array2, 0, 9999,1260);
        //Modified_mergeSort(array3, 0, 49999,1561);
        //Modified_mergeSort(array4, 0, 99999,1550);
        t = System.nanoTime() - t;

        System.out.println("mean time for modified merg sort = " + t/1000000000+" sec");

        for (int i = 0; i < 10; i++)
        {
            System.out.print( array1[i] );
        }






    }
}
