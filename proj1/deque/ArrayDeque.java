package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{

   /**
    * 定义类所需要的变量
    */
   private T [] item = (T[]) new Object[8];
   private int size;
   private int nextFirst;
   private int nextLast;


   /**
    * ArrayDeque 的无参构造方法。
    */
   public ArrayDeque(){
      size = 0;
      nextFirst = (this.item.length/2)-1;
      nextLast = this.item.length/2;

   }

   /**
    * 添加元素到第一位
    * @param item
    */
   @Override
   public void addFirst(T item) {

      if (size == this.item.length){
         Resizing(this.item.length*2);
      }
      this.item[nextFirst] = item;
      this.nextFirst = (nextFirst -1 + this.item.length) % this.item.length;
      size +=1;
   }

   /**
    *本次
    * @param capacity
    */
   private void Resizing(int capacity){
      T [] newArray = (T[]) new Object[capacity];
      int oldIndex = (nextFirst +1) % this.item.length;

      for (int i = 0 ; i<size;i++){
         newArray[i] = this.item[oldIndex];
         oldIndex = (oldIndex +1)%this.item.length;

      }
      this.item = newArray;
      this.nextFirst = this.item.length -1;
      this.nextLast = size;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void printDeque() {
      int index = 0;
      while (index<size){
         System.out.print(get(index)+" ");
         index +=1;
      }
      System.out.println();
   }

   @Override
   public T get(int index) {

      if (index>size-1 || index<0){
         return null;
      }
      int tempt = (nextFirst +1 +index) % this.item.length;
      T item = this.item[tempt];
      return item;
   }


   @Override
   public T removeFirst() {
      if (size == 0){
         return null;
      }
      int lastFirst = (nextFirst +1) % this.item.length;
      T item = this.item[lastFirst];
      this.item[lastFirst] = null;
      this.nextFirst = lastFirst;
      size -=1;

      return item;
   }

   @Override
   public T removeLast() {
      if (size == 0){
         return null;
      }
      int lastLast = (nextLast -1 + this.item.length) % this.item.length;
      T item = this.item[lastLast];
      this.item[lastLast] = null;
      nextLast = lastLast;
      size -=1;
     return item;
   }

   @Override
   public void addLast(T item) {

      if (size == this.item.length){
         Resizing(this.item.length*2);
      }

      this.item[nextLast] = item;
      nextLast = (nextLast +1) % this.item.length;
      size +=1;
   }

   @Override
   public Iterator<T> iterator() {
      return new ArrayDequeIterator();
   }

   private class ArrayDequeIterator implements Iterator<T>{
      int index = 0;
      @Override
      public T next() {
         T item = get(index);
         index +=1;
         return item;
      }

      @Override
      public boolean hasNext() {
         return index<size;

      }
   }
}
