import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T extends Comparable<T>> {
    private List<T> arraySet;

    /**
     * default constructor
     */
    public ArraySet()  {
        arraySet = new ArrayList<T>();
    }

    /**
     * returns the size
     * @return int
     */
    public int size() {
        return arraySet.size();
    }

    /**
     * returns shallow copy of list
     * @return
     */
    public List<T> asList() {
        return new ArrayList<T>(arraySet);
    }

    /**
     * Returns true if the query item is present in the set and false otherwise
     * @param query - item in list
     * @return boolean
     */
    public boolean contains(T query) {
        return Collections.binarySearch(arraySet, query) >= 0;
    }

    /**
     * adds item if it unique, does not if duplicate
     * @param item - item to be added
     * @return boolean
     */
    public boolean add(T item) {
        if(item == null) {
            throw new RuntimeException("ArraySet does not support null items");
        }

        if(arraySet.contains(item)) {
            return false;
        }

        if(arraySet.size() == 0) {
            arraySet.add(item);
        }
        else {
            int index = 0;
            for (T arrayItem : arraySet) {
                if (item.compareTo(arrayItem) < 0) {
                    arraySet.add(index, item);
                    break;
                }
                index++;
            }
            if(index == arraySet.size()) {
                arraySet.add(item);
            }
        }
        return true;
    }

    /**
     * Retrieves an item in the set that is equal to the query item
     * @param query - query item
     * @return T
     */
    public T get(T query) {
        int index = Collections.binarySearch(arraySet, query);
        if(index >= 0) {
            return arraySet.get(index);
        }
        return null;
    }

    /**
     * formats string
     * @return String
     */
    @Override
    public String toString() {
        return arraySet.toString();
    }

    /**
     * iterator
     * @return Iterator<T>
     */
    public Iterator<T> iterator() {
        return arraySet.iterator();
    }

}
