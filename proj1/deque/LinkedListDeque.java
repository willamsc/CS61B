package deque;

import java.util.Iterator;

public class  LinkedListDeque <T> implements Deque<T>,Iterable<T> {
    /**
     *对 LinkedListDeque 进行变量和内嵌类的定义
     */

    private class Node  {
        T item;
        Node prev;
        Node next;
        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private Node sentinel;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{

        Node curr = sentinel.next;
        @Override
        public T next() {

            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            T item = curr.item;
            curr = curr.next;

            return item;
        }

        @Override
        public boolean hasNext() {
            return curr != sentinel;
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item){
       Node temptNode = new Node(item,sentinel,sentinel.next);
       sentinel.next = temptNode;
       temptNode.next.prev = temptNode;
        size +=1;
    }

    @Override
    public void addLast(T item){
        Node temptNode = new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next = temptNode;
        sentinel.prev = temptNode;
        size +=1;
    }

    @Override
    public T removeFirst(){
        if (this.size <= 0){
            return null;
        }
        Node firstNode = sentinel.next;
        firstNode.next.prev = sentinel;
        sentinel.next = firstNode.next;

        T i = firstNode.item;

        firstNode.next = null;
        firstNode.prev = null;
        firstNode.item = null;
        size -=1;
        return i;
    }

    @Override
    public T removeLast(){
        if (this.size<=0){
            return null;
        }

        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;

        T i = lastNode.item;

        lastNode.item = null;
        lastNode.prev = null;
        lastNode.next = null;

        size -=1;

        return i;
    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index>=size || index<0){
            return null;
        }

        Node p = sentinel.next;


        for (int i = 0;i<index;i++){

            p = p.next;
        }

        return p.item;
    }

    public T getRecursive(int index){
        if (index>=size || index<0){
            return null;
        }

        Node p = sentinel.next;
        T i =  getRecursiveHelper(p,index);
        return i;
    }

    private T getRecursiveHelper(Node p, int index) {
        // 基线条件 (Base Case)：如果 index 降到了 0，说明当前节点 p 就是目标，直接返回 p.item
        if (index == 0){
            return p.item;
        }

        // 递归步骤 (Recursive Step)：如果还没到 0，就去问 p 的下一个节点要 (index - 1) 的宝贝
       return getRecursiveHelper(p.next,index-1);

    }
}
