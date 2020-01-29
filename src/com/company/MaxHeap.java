package com.company;

import java.util.Arrays;

public class MaxHeap {
    private int[] maxHeap;
    private int size = 1;
    private int capacity;

    public MaxHeap(int capacity){
        this.capacity = capacity;
        this.maxHeap = new int[this.capacity + 1];
        maxHeap[0] = Integer.MAX_VALUE;
    }

    private int getParentIndex(int childIndex) {
        return childIndex / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private boolean hasParent(int childIndex) {
        if(getParentIndex(childIndex) > 0)
            return true;
        else
            return false;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getParent(int childIndex) {
        return maxHeap[getParentIndex(childIndex)];
    }

    private int getLeftChild(int parentIndex) {
        return maxHeap[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex) {
        return maxHeap[getRightChildIndex(parentIndex)];
    }

    private void capacityCheck() {
        if (size == capacity) {
            maxHeap = Arrays.copyOf(maxHeap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    private int getMax() {
        if(maxHeap.length > 1)
            return maxHeap[1];
        else
            return -1;
    }

    public void add(int itemToBeAdded) {
        capacityCheck();
        maxHeap[size] = itemToBeAdded;
        heapifyUp();
        size++;
    }

    private void swap(int index1, int index2) {
        int tempVal = maxHeap[index1];
        maxHeap[index1] = maxHeap[index2];
        maxHeap[index2] = tempVal;
    }

    private void heapifyUp() {
        int index = size;

        while (hasParent(index) && getParent(index) < maxHeap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void print() {
        for(int i = 1; i < this.size; i++){
            System.out.print(maxHeap[i] + " ");
        }
    }

    public void remove(int itemToBeRemoved){
        int counter = 1;
        boolean isSearching = true;
        boolean wasReplaced = false;

        while(counter < size && isSearching){
            if(maxHeap[counter] == itemToBeRemoved){
                isSearching = false;
                int tempItem = maxHeap[size-1];
                size--;
                maxHeap = replaceArray(maxHeap, tempItem);
                maxHeap[counter] = tempItem;
                wasReplaced = true;
            }
            else {
                counter++;
            }
        }

        int replacedVal = maxHeap[counter];
        int currentIndex = counter;

        if(wasReplaced){
            while((hasLeftChild(currentIndex) || hasRightChild(currentIndex))) {
                if(getLeftChild(currentIndex) > replacedVal || getRightChild(currentIndex) > replacedVal){
                    currentIndex = sink(currentIndex);
                }
            }
        }
    }

    public int extractMax(){
        if(maxHeap.length > 1){
            remove(maxHeap[1]);
            return(maxHeap[1]);
        }
        return -1;
    }


    private int sink(int index){
        int maxChildIndex = 0;
        int newIndex = 0;

        if(hasRightChild(index) && hasLeftChild(index)){
            if(getLeftChild(index) > getRightChild(index)){
                maxChildIndex = getLeftChildIndex(index);
                newIndex = maxChildIndex;
                swap(index, maxChildIndex);
                return newIndex;
            }

            else if(getRightChild(index) > getLeftChild(index)){
                maxChildIndex = getRightChildIndex(index);
                newIndex = maxChildIndex;
                swap(index, maxChildIndex);
                return newIndex;
            }
        }

        else if(hasRightChild(index)){
            maxChildIndex = getRightChildIndex(index);
            newIndex = maxChildIndex;
            swap(index, maxChildIndex);
            return newIndex;
        }

        else if(hasLeftChild(index)){
            maxChildIndex = getLeftChildIndex(index);
            newIndex = maxChildIndex;
            swap(index, maxChildIndex);
            return newIndex;
        }

        return 0;
    }

    public int[] replaceArray(int[] originalArray, int itemToBeRemoved){
        int[] newArray = new int[originalArray.length];

        for(int i = 0; i < size; i++)
            if(itemToBeRemoved != originalArray[i])
                newArray[i] = originalArray[i];

            return newArray;
    }
}

    /* Removal method
    Replace the element being removed with the rightmost node on the lowest level.
    If the nodes below are higher, swap with the higher parent
     */

    /*
    Insert the element at the highest leftmost position in the tree
    If the tree is broken, swap up with parents until correct
     */

    /*
    STORAGE
    Store the tree in an array/ArrayList with index 0 blank in breadth-first order
     */

    /*
    FORMULAS:
        Index of PARENT of node @ index [n] = floor([n]/2)
        Index of LEFT CHILD of node @ index [n] = (2 * [n])
        Index of RIGHT CHILD of node @ index [n] = (2 * [n]) + 1
     */
