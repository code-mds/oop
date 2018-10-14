package e1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Dictionary<V> implements  Iterable<Dictionary.Association<V>> {
    private final ArrayList<Association<V>> assocList = new ArrayList<>();

    public static class Association<V> implements Comparable<String> {
        private final String key;
        private final V val;

        Association(String key, V val) {
            this.key = key;
            this.val = val;
        }

        public String getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }

        @Override
        public String toString() {
            return "Association{" +
                    "key='" + key + '\'' +
                    ", val=" + val +
                    '}';
        }

        @Override
        public int compareTo(String key) {
            return this.getKey().compareTo(key);
        }
    }

    private int indexOf(String key) {

        int cidx = Collections.binarySearch(assocList, key);
        return cidx;
//        int idx = -1;
//
//        for (int i=0; i<assocList.size(); i++) {
//            int comparison = assocList.get(i).getKey().compareTo(key);
//            if(comparison == 0) {
//                idx = i;
//                break;
//            } else if(comparison > 0) {
//                break;
//            } else {
//                idx--;
//            }
//        }
//        return idx;
    }

    public int size() {
        return assocList.size();
    }

    public boolean isEmpty(){
        return assocList.isEmpty();
    }

    public V get(String key){
        int idx = indexOf(key);
        return idx >=0 ? assocList.get(idx).getValue() : null;
    }

    public V put(String key, V value){
        if(key == null || value == null)
            throw new IllegalArgumentException();

        V oldValue = null;
        Association<V> newAssoc = new Association<>(key, value);

        int idx = indexOf(key);
        if(idx >= 0) {
            oldValue = assocList.get(idx).getValue();
            assocList.set(idx, newAssoc);
        } else {
            assocList.add(Math.abs( idx + 1), newAssoc);
        }

        return oldValue;
    }

    public boolean containsKey(String key){
        int idx = indexOf(key);
        return idx >= 0;
    }

    public boolean containsValue(Object value){
        for (Association<V> assoc : assocList) {
            if (assoc.getValue().equals(value))
                return true;
        }

        return false;
    }

    public V remove(String key){
        if(key == null)
            throw new IllegalArgumentException();

        V oldValue = null;
        int idx = indexOf(key);
        if(idx >= 0) {
            oldValue = assocList.remove(idx).getValue();
        }

        return oldValue;
    }

    public void clear(){
        assocList.clear();
    }

    public Iterator<Association<V>> iterator() {
        return assocList.iterator();
    }

}
