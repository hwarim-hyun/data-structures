package ds;

import java.util.Arrays;

/**
 * General Tree class implementation for UNION/FIND
 */
public class ParPtrTree {
    private Integer[] array; // Node array
    private Integer[] size; // groupsize array. Consider only values of root nodes. We don't need to care others.
    private int maxSize;

    public ParPtrTree(int maxsize) {
        this.maxSize = maxsize;
        array = new Integer[this.maxSize]; // Create node array
        size = new Integer[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            array[i] = null;
            size[i] = 1;
        }
    }

    public void clear() {
        array = new Integer[this.maxSize]; // Create node array
        size = new Integer[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            array[i] = null;
            size[i] = 1;
        }
    }

    /**
     * Determine if nodes are in different trees
     */
    public boolean differ(Integer a, Integer b) {
        return find(a) != find(b);
        //TODO: fill your code
    }

    /**
     * Merge two subtrees using weighted union rule
     */
    public void union(Integer a, Integer b) {
        //TODO: fill your code
        // follow "weighted union rule"
        // if group size of two values are equal, hang b's root to a's.
        if(groupSize(a)>=groupSize(b)){
            if(find(a) == null && find(b) == null){
                array[b] = a;
            }else if(find(a) == null){
                array[find(b)] = a;
            }else if(find(b) == null){
                array[b] = find(a);
            }else {
                //array[b] = find(a);
                array[find(b)] = find(a);
            }
        }else{
            if(find(a) == null && find(b) == null){
                array[a] = b;
            }else if(find(b) == null){
                array[find(a)] = b;
            }else if(find(a) == null){
                array[a] = find(b);
            }else {
                //array[a] = find(b);
                array[find(a)] = find(b);
            }
        }
        int sizeA = size[a];
        int sizeB = size[b];
        for(int i=0; i<getArray().length; i++){
            if(find(a) != null && find(b) != null){
                if(find(i) == find(a)){
                    size[i] = sizeA + sizeB;
                } else if(i == find(a)){
                    size[i] = sizeA + sizeB;
                }
            }else if(find(a) == null){
                if(find(i) == find(b)){
                    size[i] = sizeA + sizeB;
                }else if(i==a){
                    size[i] = sizeA + sizeB;
                }
            }else{
                if(find(i) == find(a)){
                    size[i] = sizeA + sizeB;
                }else if(i==b){
                    size[i] = sizeA + sizeB;
                }
            }
        }
    }

    /**
     * Find the root of the node using path compression
     */
    public Integer find(Integer curr) {
        //TODO: fill your code
        // use "path compression"
        int temp = curr;
        if(array[curr]==null) return null;

        while(array[curr] != null){
            curr = array[curr];
        }
        array[temp] = curr;
        return curr;
    }

    /**
     * Return the size of the graph that the node belongs to
     */
    public Integer groupSize(Integer curr) {
        //TODO: fill your code
        return size[curr];
    }

    public Integer[] getSize() {
        return this.size;
    }

    public Integer[] getArray() {
        return this.array;
    }

    public void print() {
        System.out.println(Arrays.toString(array).replace("null", "N"));
    }


}
