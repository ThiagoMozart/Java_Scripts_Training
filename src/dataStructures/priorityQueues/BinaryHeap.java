package dataStructures.priorityQueues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private int heapSize = 0;
    private int heapCapacity = 0;
    private List<T> heap = null;

    public BinaryHeap(){
        this(1);
    }

    public BinaryHeap(int sz){
        heap = new ArrayList<>(sz);
    }

    public BinaryHeap(T[] elems){
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<T>(heapCapacity);

        for (int i = 0; i < heapSize; i++) heap.add(elems[i]);

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);

    }

    public BinaryHeap(Collection<T> elems){
        this(elems.size());
        for (T elem : elems) add(elem);
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public void clear(){
        for (int i = 0; i < heapCapacity; i++) heap.set(i, null);
        heapSize = 0;
    }

    public int size(){
        return heapSize;
    }

    public T peek(){
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public T poll(){
        return removeAt(0);
    }

    public boolean contains(T elem){
        for (int i = 0; i < heapSize; i++) if (heap.get(i).equals(elem)) return true;
        return false;
    }

    public void add(T elem){
        if (elem == null) throw new IllegalArgumentException();

        if (heapSize < heapCapacity){
            heap.set(heapSize, elem);
        }
        else{
            heap.add(elem);
            heapCapacity++;
        }

        swim(heapSize);
        heapSize++;
    }

    private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    private void swim(int k){
        int parent = (k - 1) / 2;

        while (k > 0 && less(k, parent)){
            swap(parent, k);
            k = parent;

            parent = (k - 1) / 2;
        }
    }

    private void sink(int k){
        while(true) {
            int left = 2 * k + 1; // left node
            int right = 2 * k + 2; // right node
            int smallest = left;

            if (right < heapSize && less(right, left)) smallest = right;

            if (left >= heapSize || less(k, smallest)) break;

            swap(smallest, k);
            k = smallest;
        }
    }

    private void swap(int i, int j){
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    public boolean remove(T element){
        if (element == null) return false;

        for (int i = 0; i < heapSize; i++){
            if (element.equals(heap.get(i))){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private T removeAt(int i){
        if (isEmpty()) return null;

        heapSize--;
        T remove_data = heap.get(i);
        swap(i, heapSize);

        heap.set(heapSize, null);

        if (i == heapSize) return remove_data;
        T elem = heap.get(i);

        sink(i);

        if (heap.get(i).equals(elem)) swim(i);
        return remove_data;
    }

    public boolean isMinHeap(int k){
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}