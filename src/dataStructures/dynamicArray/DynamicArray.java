package dataStructures.dynamicArray;

import java.util.Iterator;
@SuppressWarnings("unchecked")

public class DynamicArray<T> implements Iterable<T>{

    private T[] array;
    private int len = 0;
    private int capacity = 0;

    public DynamicArray(){
        this(16);
    }

    public DynamicArray(int capacity){
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size(){
        return len;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T get(int index){
        return array[index];
    }

    public void set(int index, T element){
        array[index] = element;
    }

    public void clear(){
        for (int i = 0; i < len; i++)
            array[i] = null;
        len = 0;
    }

    public void add(T element){
        if (len + 1 >= capacity){

            if(capacity == 0)
                capacity = 1;

            else capacity *= 2;

            T[] new_array = (T[]) new Object[capacity];

            for (int i = 0; i < len; i++)
                new_array[i] = array[i];

            array = new_array;
        }
        array[len++] = element;
    }

    public T removeAt(int rm_index){
        if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();

        T data = array[rm_index];
        T[] new_array = (T[]) new Object[len - 1];

        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == rm_index) j--;
            else new_array[j] = array[i];
        array = new_array;
        capacity= --len;
        return data;
    }

    public boolean remove(Object obj){
        int index = indexOf(obj);

        if (index == -1) return false;
        removeAt(index);
        return true;
    }

    public int indexOf(Object obj){
        for (int i = 0; i < len; i++){
            if (obj == null){
                if (array[i] == null) return i;
            }
            else{
                if(obj.equals(array[i])) return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}