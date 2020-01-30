package com.company;

import java.util.Arrays;

public class MinHeap {
    private int[] minHeap;
    private int size = 1;
    private int capacity;

    public MinHeap(int capacity){
        this.capacity = capacity;
        this.minHeap = new int[this.capacity + 1];
        minHeap[0] = Integer.MAX_VALUE;
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
        return minHeap[getParentIndex(childIndex)];
    }

    private int getLeftChild(int parentIndex) {
        return minHeap[getLeftChildIndex(parentIndex)];
    }

    private int getRightChild(int parentIndex) {
        return minHeap[getRightChildIndex(parentIndex)];
    }

    private void capacityCheck() {
        if (size == capacity) {
            minHeap = Arrays.copyOf(minHeap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    public int getMin() {
        if(minHeap.length > 1)
            return minHeap[1];
        else
            return -1;
    }

    public void add(int itemToBeAdded) {
        capacityCheck();
        minHeap[size] = itemToBeAdded;
        heapifyUp();
        size++;
    }

    private void swap(int index1, int index2) {
        int tempVal = minHeap[index1];
        minHeap[index1] = minHeap[index2];
        minHeap[index2] = tempVal;
    }

    private void heapifyUp() {
        int index = size;

        while (hasParent(index) && getParent(index) > minHeap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void print() {
        for(int i = 1; i < this.size; i++){
            System.out.print(minHeap[i] + " ");
        }
    }

    public void remove(int itemToBeRemoved){
        int counter = 1;
        boolean isSearching = true;
        boolean wasReplaced = false;

        while(counter < size && isSearching){
            if(minHeap[counter] == itemToBeRemoved){
                isSearching = false;
                int tempItem = minHeap[size-1];
                size--;
                minHeap = replaceArray(minHeap, tempItem);
                minHeap[counter] = tempItem;
                wasReplaced = true;
            }
            else {
                counter++;
            }
        }

        if(wasReplaced){
            int replacedVal = minHeap[counter];
            int currentIndex = counter;

            while((hasLeftChild(currentIndex) || hasRightChild(currentIndex))) {
                if(getLeftChild(currentIndex) < replacedVal || getRightChild(currentIndex) < replacedVal){
                    currentIndex = sink(currentIndex);
                }
            }
        }
    }

    public int extractMin(){
        if(minHeap.length > 1){
            int extracted = minHeap[1];
            remove(minHeap[1]);
            return extracted;
        }
        return -1;
    }


    private int sink(int index){
        int maxChildIndex = 0;
        int newIndex = 0;

        if(hasRightChild(index) && hasLeftChild(index)){
            if(getLeftChild(index) < getRightChild(index)){
                maxChildIndex = getLeftChildIndex(index);
                newIndex = maxChildIndex;
                swap(index, maxChildIndex);
                return newIndex;
            }

            else if(getRightChild(index) < getLeftChild(index)){
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

    private int[] replaceArray(int[] originalArray, int itemToBeRemoved){
        int[] newArray = new int[size];
        int newLength = size;
        boolean isSearching = true;

        for(int i = 0; i < newLength; i++){
            if(isSearching && itemToBeRemoved != originalArray[i]){
                newArray[i] = originalArray[i];
            }
            else {
                isSearching = false;
                newArray[i] = originalArray[i];
            }
        }
        return newArray;
    }
}

    /* Removal method
    Replace the element being removed with the rightmost node on the lowest level.
    If the nodes below are higher, swap with the lower parent
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

