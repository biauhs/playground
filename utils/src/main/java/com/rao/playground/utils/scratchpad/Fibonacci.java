package com.rao.playground.utils.scratchpad;

/**
 * Created by shuaibrao on 24/04/2017.
 */
public class Fibonacci {

    public static long fibRec(int n) {
        if (n <= 1) return n;
        else return fibRec(n-1) + fibRec(n-2);
    }

    public static int fib(int n) {
        if (n==0) return 0;
        if (n==1) return 1;
        if (n==2) return 1;

        int current = 0;
        int aaa = 1;
        int bbb = 1;

        for (int i = 3; i<=n; i++) {
            current = aaa + bbb;
            aaa = bbb;
            bbb = current;
        }

        return current;

    }

    public static void main(String[] args) {

        System.out.println(fibRec(4));
        System.out.println(fib(4));

    }

}
