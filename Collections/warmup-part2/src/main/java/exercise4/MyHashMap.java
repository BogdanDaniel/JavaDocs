package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {

        // Initialize buckets list


      this.buckets = new ArrayList<LinkedList<MyEntry>>(BUCKET_ARRAY_SIZE);

            for(int i=0;i<BUCKET_ARRAY_SIZE;i++){

             buckets.add(new LinkedList<MyEntry>());

                   // System.out.println(buckets.get(i));

            }


    }

    public String get(String key) {
        //

      for(MyEntry aux : buckets.get(key.hashCode()%BUCKET_ARRAY_SIZE)){
         if(aux.getKey().equals(key))
             return aux.getValue();
      }
        return null;
    }

    public void put(String key, String value) {
        // TODO
        int pus = 0;
        MyEntry a = new MyEntry(key, value);

        if (key == null)
            buckets.get(0).add(a);

        else {


            for (MyEntry b : buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE))) {
                if (b.getKey().equals(a.getKey())) {
                    b.setValue(a.getValue());
                    // buckets.get(Math.abs(key.hashCode()%BUCKET_ARRAY_SIZE)).add(b);
                    pus = 1;
                    break;

                }
            }


            if (pus == 0)
                buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE)).add(a);


        }
    }

    public Set<String> keySet() {
        // TODO
        Set<String> aux= new TreeSet<String>();

        for(int i=0;i<buckets.size();i++){
            for(int j=0;j<buckets.get(i).size();j++) {
                if(buckets.get(i).get(j).getKey()== null)
                    aux.add("null");
                else aux.add(buckets.get(i).get(j).getKey());
            }

        }

        return aux;
    }

    public Collection<String> values() {
        // TODO
        Collection<String> aux= new ArrayList<String>();


        for(int i=0;i<buckets.size();i++){
            for(int j=0;j<buckets.get(i).size();j++) {
                aux.add(buckets.get(i).get(j).getValue());
            }

        }
        return aux;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int size =0;
        for(int i=0;i<buckets.size(); i++){
           size += buckets.get(i).size();
        }
        return size;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        return null;
    }

    public boolean isEmpty() {
        // TODO
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString(){
            return "[" +key+","+value+"]";
        }

    }

}
