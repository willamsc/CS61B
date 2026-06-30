package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> defaultComparator;
    public T max(){
        return max(defaultComparator);
    }
    public MaxArrayDeque(Comparator<T> c){
        this.defaultComparator = c;
    }
    public T max(Comparator<T> c){

        if (isEmpty()){
            return null;
        }

        T MaxItem = get(0);
        for (int i = 0; i < this.size(); i++) {

           if (c.compare(MaxItem,get(i))<0){
                MaxItem = get(i);
           }
        }
        return MaxItem;
    }
}
