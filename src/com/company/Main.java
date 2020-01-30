package com.company;

public class Main {

    public static void main(String[] args) {
        MinHeap test = new MinHeap(100);
        int incrementer = 1;

        while(incrementer < 20){
            test.add(incrementer);
            incrementer++;
        }

        test.add(11);
        test.add(30);
        test.add(3);

        System.out.println("\nPRINTING HEAP: ");
        test.print();

        test.remove(30);
        test.remove(0);
        test.extractMin();

        System.out.println("\nPRINTING HEAP: ");
        test.print();
        }

}
