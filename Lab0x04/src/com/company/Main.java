package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	long start = getCpuTime();
	long stop = getCpuTime();
	long time;
        double doubleRatio;
        double expDoubleRatio;
        long pFibRecur= 0;
        long pFibCache =0;
        long pFibloop = 0;
        long pFibMatrix =0;
        boolean mTime = false;
    boolean condition = true;
    int maxNum = 10000;

        System.out.printf("%12s%24s%19s%19s%24s%19s%19s%24s%19s%19s%24s%19s%19s\n", "", "Fibonacci recursive", "", "", "fibonacci Cache ", "", "", "Fibonacci Loop", "", "", "Fibonacci Matrix", "", "");
        System.out.printf("%12s%24s%19s%19s%24s%19s%19s%24s%19s%19s%24s%19s%19s\n","N", "Time", "DblRatio", "ExpDblRatio", "Time", "DblRatio", "ExpDblRatio", "Time", "DblRatio", "ExpDblRatio", "Time", "DblRatio", "ExpDblRatio");

        for( int i = 0; i< maxNum;i++){
            System.out.printf("%12s", i);

            start =getCpuTime();
            fibRecur(i);
            stop=getCpuTime();
            time = stop-start;
            System.out.printf("%24s", time);
            if(pFibRecur==0){
                System.out.printf("%19s%19s", "N/a","N/a");
            }
            else{
                doubleRatio= time/pFibRecur;
                expDoubleRatio = (double)Math.pow(time,3)/Math.pow(pFibRecur,3);
                System.out.printf("%19f%19f", doubleRatio,expDoubleRatio);
            }
            pFibRecur = time;
            if(time>maxNum){
                mTime =true;
            }


            start =getCpuTime();
            fibCache(i);
            stop=getCpuTime();
            time = stop-start;
            System.out.printf("%24s", time);
            if(pFibCache==0){
                System.out.printf("%19s%19s", "N/a","N/a");
            }
            else{
                doubleRatio = time/pFibCache;
                expDoubleRatio = (double)Math.pow(time,3)/Math.pow(pFibCache,3);
                System.out.printf("%19f%19f", doubleRatio,expDoubleRatio);
            }
            pFibCache = time;
            if(time>maxNum){
                mTime =true;
            }

            start =getCpuTime();
            fibLoop(i);
            stop=getCpuTime();
            time = stop-start;
            System.out.printf("%24s", time);
            if(pFibloop==0){
                System.out.printf("%19s%19s", "N/a","N/a");
            }
            else{
                doubleRatio = time/pFibloop;
                expDoubleRatio = (double)Math.pow(time,3)/Math.pow(pFibloop,3);
                System.out.printf("%19f%19f", doubleRatio,expDoubleRatio);
            }
            pFibloop = time;
            if(time>maxNum){
                mTime =true;
            }
            start =getCpuTime();
            fibMatrix(i);
            stop=getCpuTime();
            time = stop-start;
            System.out.printf("%24s", time);
            if(pFibMatrix==0){
                System.out.printf("%19s%19s", "N/a","N/a");
            }
            else{
                doubleRatio = time/pFibMatrix;
                expDoubleRatio = (double)Math.pow(time,3)/Math.pow(pFibMatrix,3);
                System.out.printf("%19f%19f", doubleRatio,expDoubleRatio);
            }
            pFibMatrix = time;
            if(time>maxNum){
                mTime =true;
            }
            System.out.println("");
        }

    }


    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }
    public static long fibRecur(long x){
        //if the x is 0 or 1 then due to the fib equation it will return the value given
        if(x ==0|| x ==1){
            return x;
        }
        //else it will add the values of the fibRecur(x-1)+ fibRecur(x-2).
        else{
            x = fibRecur(x-1)+fibRecur(x-2);
        }
        //return the value.
        return x;
    }
    public static long fibLoop(long x){

        if(x==0||x==1){
            return x;
        }
        else{
            long first = 0;
            long answer = 1;
            long value;
            //will start at 2 since 0 and 1 will already be accounted for.

            //when i reaches x, then answer will return to the variable.
            for(int i = 2;i<(int)x;i++){
                //the value is a place holder to get the first and answer to add to each other
                value = first+answer;
                //first will then hold the value of the original answer
                first = answer;
                //answer will hold the current value.
                answer = value;
            }
            return answer;
        }

    }

    public static long fibCache(long x){
        //checks to see if x is either 0 or 1
        //returns if so
        if(x==0||x==1){
            return x;
        }
        //else will call a helper function
        else {
            return helper((int)x);
        }
    }
    //helper function is to create an array cache, and run through the
    //algorithm
    public static long helper(int x){
        long[] cache =new long[x];
        if (cache[x] == 0) {
            cache[x] = helper(x - 1) + helper(x - 2);
        }
        return cache[x];

    }

    public static long fibMatrix(long x){

        long matrix[][] = new long[][]{{1,1},{1,0}};
        //will not incorporate 1 in this if statement
        if(x ==0){
            return 0;
        }
        //using the power function to get the matrix value;
        pow(matrix, x-1);
        return matrix[0][0];

    }

    public static void pow(long matrix[][], long x){
        long powerMatrix[][] = new long[][]{{1,1},{1,0}};

        //creates a for loop to distribute
        for(int i =2; i<=x;i++){
            //calculates the corners of the matrix
            //top left
            long a = matrix[0][0]*powerMatrix[0][0]+matrix[0][1]*powerMatrix[1][0];
            //top right
            long b = matrix[0][0]*powerMatrix[0][1]+matrix[0][1]*powerMatrix[1][1];
            // bottom left
            long c = matrix[1][0]*powerMatrix[0][0]+matrix[1][1]*powerMatrix[1][0];
            // bottom right
            long d = matrix[1][0]*powerMatrix[0][1]+matrix[1][1]*powerMatrix[1][1];

            //puts the values into the matrix.
            matrix[0][0]=a;
            matrix[0][1]=b;
            matrix[1][0]=c;
            matrix[1][1]=d;
        }
    }
}
