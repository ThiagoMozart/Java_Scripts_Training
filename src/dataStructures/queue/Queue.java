package dataStructures.queue;

public class Queue<T> implements Iterable<T> {

    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    public Queue(){}

    public Queue(T firstElement){
        offer(firstElement);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T peek(){
        if (isEmpty()) throw new RuntimeException("Queue empty");
        return list.peekFirst();
    }

    public T poll(){
        if (isEmpty()) throw new RuntimeException("Queue empty");
        return list.removeFirst();
    }

    public void offer(T element){
        if (element == null) throw new RuntimeException("Element null");
        list.addLast(element);
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
