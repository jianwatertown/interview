package hackerrank.dataStructure.arrays;

import java.io.*;
import java.util.*;

/**
 * Created by jian.wang on 5/7/16.
 */
public class ArrayDS {

    public static void main(String[] args) {
	

        Scanner scan = new Scanner(System.in);
        int[] array = new int[scan.nextInt()];
        for(int i = 0; i < array.length; i++){
            array[i] = scan.nextInt();
        }
        scan.close();
	
        //int size = Integer.parseInt(args[0]);
        String[] splited = args[1].split("\\s+");
        for(int i=splited.length-1;i>=0;i--){
            System.out.print(splited[i]+" ");
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}
