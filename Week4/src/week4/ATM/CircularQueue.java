package week4.ATM;

import java.util.Arrays;

public class CircularQueue<T>{

   private T list[];
   private int capacity;
   private int index;

    public CircularQueue(T[] list) {
        this.list = list;
        capacity = list.length;
        index=-1;
    }

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
        index=-1;
    }

    public void add(T value){
        index = (index+1) % capacity;
        list[index] = value;
    }

    public int size(){
        return capacity;
    }

    public T get(int index){
        return list[index];
    }

    public void clear(){
        for (int i = 0; i < capacity; i++) {
            list[i]=null;
        }
    }

    @Override
    public String toString() {
        return "CircularQueue{" +
                "list=" + Arrays.toString(list) +
                ", capacity=" + capacity +
                ", index=" + index +
                '}';
    }
}
