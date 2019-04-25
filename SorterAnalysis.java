import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.Object;


public class SorterAnalysis {

    Integer[] size10_1;
    Integer[] size10_2;
    Integer[] size10_3;
    Integer[] size10_4;
    Integer[] size10_5;
    Integer[] size10_6;

    public SorterAnalysis() {
        size10_1 = new Integer[10];
        size10_2 = new Integer[100];
        size10_3 = new Integer[1000];
        size10_4 = new Integer[10000];
        size10_5 = new Integer[100000];
        size10_6 = new Integer[1000000];


    }

    public void setData(String type) {
        switch (type) {
            case "random":

                this.generateRandom(size10_1, 10);
                this.generateRandom(size10_2, 100);
                this.generateRandom(size10_3, 1000);
                this.generateRandom(size10_4, 10000);
                this.generateRandom(size10_5, 100000);
                this.generateRandom(size10_6, 1000000);
                break;
            case "sorted":
                this.generateSorted(size10_1, 10);
                this.generateSorted(size10_2, 100);
                this.generateSorted(size10_3, 1000);
                this.generateSorted(size10_4, 10000);
                this.generateSorted(size10_5, 100000);
                this.generateSorted(size10_6, 1000000);
                break;
            case "almostSort":
                this.generateAlmostSorted(size10_1, 10);
                this.generateAlmostSorted(size10_2, 100);
                this.generateAlmostSorted(size10_3, 1000);
                this.generateAlmostSorted(size10_4, 10000);
                this.generateAlmostSorted(size10_5, 100000);
                this.generateAlmostSorted(size10_6, 1000000);
                break;
            case "sortedReverse":
                this.generatesortedRevers(size10_1, 10);
                this.generatesortedRevers(size10_2, 100);
                this.generatesortedRevers(size10_3, 1000);
                this.generatesortedRevers(size10_4, 10000);
                this.generatesortedRevers(size10_5, 100000);
                this.generatesortedRevers(size10_6, 1000000);
                break;
            default:
                throw new IllegalArgumentException("Invalid type  of sorting: " + type);
        }

    }

    private void generateRandom(Integer[] a, Integer N) {
        if (N < 0) throw new IllegalArgumentException();
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            a[i] = rand.nextInt(N * 10);
        }

    }

    private void generateSorted(Integer[] a, Integer N) {
        if (N < 0) throw new IllegalArgumentException();

        for (int i = 0; i < N; i++) {
            a[i] = i;
        }

    }


    private void generateAlmostSorted(Integer[] a, Integer N) {
        if (N < 6) throw new IllegalArgumentException("array too small");
        this.generateSorted(a, N);
        Random rand = new Random();
        int totalDistance = 0; // this is an approximation of the number of inversions
        int range = (int) (Math.sqrt(N));

        for (int i = 0; i < N / 2; i++) {
            if (rand.nextInt(10) == 0) {
                int temp = a[i];
                a[i] = a[N - 1 - i];
                a[N - i - 1] = temp;
            }
        }


    }

    private void generatesortedRevers(Integer[] a, Integer N) {

        if (N < 0) throw new IllegalArgumentException();

        for (int i = 0; i < N; i++) {
            a[i] = N - 1 - i;
        }

    }


    //////////////////////////Insertion Sort

    void sortInsertion(Integer arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    //////////////////////////// Merge Sort///////////////////////////////////
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(Integer arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sortMerge(Integer arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sortMerge(arr, l, m);
            sortMerge(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    ////////////////////////// Heap Sort//////////////////////////
    public void sortHeap(Integer arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(Integer arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /////// Quick Sort ////////////
    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(Integer arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sortQuick(Integer arr[], int l, int h) {
        // Create an auxiliary stack
        int[] stack = new int[h-l+1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0)
        {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }


    /////////////////////////// Quick Sort Median///////////////////////////////

    // Generates Random Pivot, swaps pivot with
// end element and calls the partition function
    int partition_r(Integer a[], int low, int high) {
        // Generate a random number in between
        // low .. high
        Random rand = new Random();
        int m = low + rand.nextInt(high - low);
        int n = low + rand.nextInt(high - low);
        int o = low + rand.nextInt(high - low);
        int mid = 0;

        if (a[o] <= a[n] && a[o] >= a[m])
            mid = o;
        else if (a[o] >= a[n] && a[o] <= a[m])
            mid = o;
        else if (a[o] >= a[n] && a[m] <= a[n])
            mid = n;
        else if (a[o] <= a[n] && a[n] <= a[m])
            mid = n;
        else if (a[o] >= a[m] && a[n] <= a[m])
            mid = m;
        else if (a[o] <= a[m] && a[n] >= a[m])
            mid = m;

        // Swap A[mid] with A[high]

        Integer temp = a[high];
        a[high] = a[mid];
        a[mid] = temp;

        return partition(a, low, high);
    }

    void quickSort2(Integer arr[], int l, int h) {
        // Create an auxiliary stack
        int[] stack = new int[h-l+1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0)
        {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition_r(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    class timeLog {

        String name;
        Long insertion;
        Long heap;
        Long merge;
        Long quick;
        Long quick2;

        void print() {
            System.out.println("Size of Array: " + name);
            System.out.println("Time for Insertion sort: " + insertion);
            System.out.println("Time for Heap Sort : " + heap);
            System.out.println("Time for Merge Sort: " + merge);
            System.out.println("Size of Quick Sort : " + quick);
            System.out.println("Size of Quick Sort (Median): " + quick2);


        }

        public timeLog(String name) {
            this.name = name;
        }

        public void setInsertion(Long insertion) {
            this.insertion = insertion;
        }

        public void setHeap(Long heap) {
            this.heap = heap;
        }

        public void setMerge(Long merge) {
            this.merge = merge;
        }

        public void setQuick(Long quick) {
            this.quick = quick;
        }

        public void setQuick2(Long quick2) {
            this.quick2 = quick2;
        }
    }

    private void generateTimes  (timeLog tl, Integer [] a ) {


        Long start;
        Long end;
//        // Insertion Sort
//        start = System.nanoTime();
//
//        this.sortInsertion(a);
//
//        end = System.nanoTime();
//        tl.insertion = end - start;

        // Merge Sort
        start = System.nanoTime();

        this.sortMerge(a,0,a.length - 1);

        end = System.nanoTime();
        tl.merge =  end - start;

        // Heap Sort
        start = System.nanoTime();

        this.sortHeap(a);

        end = System.nanoTime();
       tl.heap = end - start;

        // Quick Sort
        start = System.nanoTime();
        this.sortQuick(a,0,a.length - 1);
        end = System.nanoTime();
        tl.quick = end - start;
        // Quick Sort 2
        start = System.nanoTime();
        this.quickSort2(a,0,a.length - 1);
        end = System.nanoTime();
        tl.quick2 = end - start;

    }

    public ArrayList<timeLog> allSort() {

        ArrayList<timeLog> list = new ArrayList<>();
        timeLog size_1 = new timeLog("Size 10");
        timeLog size_2 = new timeLog("Size 100");
        timeLog size_3 = new timeLog("Size 1000");
        timeLog size_4 = new timeLog("Size 10000");
        timeLog size_5 = new timeLog("Size 100000");
        timeLog size_6 = new timeLog("Size 1000000");



//        generateTimes(size_1,size10_1);
//        generateTimes(size_2,size10_2);
//        generateTimes(size_3,size10_3);
//        generateTimes(size_4,size10_4);
//        generateTimes(size_5,size10_5);
        generateTimes(size_6,size10_6);




        list.add(size_1);
        list.add(size_2);
        list.add(size_3);
        list.add(size_4);
        list.add(size_5);
        list.add(size_6);

        return list;

    }

}
