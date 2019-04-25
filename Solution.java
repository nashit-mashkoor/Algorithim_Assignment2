import java.util.*;
import java.io.*;

public class Solution {


    public static void main(String[] args) {

       SorterAnalysis sorter =  new SorterAnalysis();



            System.out.println("\t\t\t\t Random");
           sorter.setData("random");
           ArrayList<SorterAnalysis.timeLog> random = sorter.allSort();
           for (SorterAnalysis.timeLog e : random) {
               e.print();

           }

        System.out.println("\t\t\t\t Sorted");
        sorter.setData("sorted");
        random = sorter.allSort();
        for (SorterAnalysis.timeLog e : random) {
            e.print();

        }

        System.out.println("\t\t\t\t Inverse Sorted");
        sorter.setData("sortedReverse");
         random = sorter.allSort();
        for (SorterAnalysis.timeLog e : random) {
            e.print();

        }
        System.out.println("\t\t\t\t Partially Sorted");
        sorter.setData("almostSort");
         random = sorter.allSort();
        for (SorterAnalysis.timeLog e : random) {
            e.print();

        }



    }


}
