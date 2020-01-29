package com.company;

public class Main {

    public static void main(String[] args) {
	MinHeap test = new MinHeap(100);
	int incrementer = 1;

	while(incrementer < 20){
        test.add(incrementer);
        incrementer++;
    }
        System.out.println("\nPRINTING HEAP: ");
	    test.print();

	    test.remove(10);
	    test.remove(19);
	    test.extractMin();
	    test.add(2);

        System.out.println("\nPRINTING HEAP: ");
        test.print();
    }
}
