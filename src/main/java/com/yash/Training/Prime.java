package com.yash.Training;

public class Prime {

    public static void main(String[] args) {
        int n = 21;

        int count = 0;
        int i = 0;
        for(i = 1; i<n; i++) {
            if(n%i == 0) {
                count++;
            }
        }
        System.out.println(count);
        if(count < 2) {
            System.out.println("This is com.yash.Training.Prime");
        }
        else {
            System.out.println("This is not prime");
        }
    }
}
