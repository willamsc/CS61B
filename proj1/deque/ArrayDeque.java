package deque;

public class ArrayDeque<T> implements Deque<T>{
   private T [] item = (T[]) new Object[8];
   private int size;

   public ArrayDeque(){
      size = 0;
   }

   @Override
   public void addFirst(T item) {

   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void printDeque() {

   }

   @Override
   public T get(int index) {
      return null;
   }

   @Override
   public boolean isEmpty() {

      if (size == 0){
         return true;
      }else {
         return false;
      }
   }

   @Override
   public T removeFirst() {
      return null;
   }

   @Override
   public T removeLast() {
     return null;
   }

   @Override
   public void addLast(T item) {

   }
}
