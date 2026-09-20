package bstmap;





import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private BSTNode root;
    private int size;

    /**BSTNode**/
    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key);
    }

    private boolean containsKey(BSTNode node , K key){

        if(node == null){
            return false;
        }

        int cmp = key.compareTo(node.key);

        if (cmp<0){
            return containsKey(node.left,key);
        } else if (cmp>0) {
            return containsKey(node.right,key);
        }else {
            return true;
        }

    }

    @Override
    public V get(K key) {

        return get(root,key);
    }

    private V get(BSTNode node, K key){
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp<0){
          return   get(node.left,key);
        } else if (cmp>0) {
            return get(node.right,key);
        }else {
            return node.value;
        }


    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(K key, V value) {

        root = put(root,key,value);

    }

    private BSTNode put(BSTNode node, K key, V value){
        if (node == null){
            BSTNode newNode = new BSTNode(key,value);
            size ++;
            return newNode;
        }

        int cmp = key.compareTo(node.key);

        if (cmp<0){
            node.left =  put(node.left,key,value);
        } else if (cmp>0) {
            node.right= put(node.right,key,value);
        }else {
            node.value = value;

        }

        return node;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        printInOrder(root);
    }



    private void printInOrder(BSTNode node){
        if (node == null) {
            return;
        }

        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
    }
}
