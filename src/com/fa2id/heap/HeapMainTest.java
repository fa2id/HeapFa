package com.fa2id.heap;

/**
 * Created by Farid Ariafard
 * www.fa2id.com
 * on 12/26/2017.
 */
public class HeapMainTest {

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        System.out.println(heap);
    }
}
