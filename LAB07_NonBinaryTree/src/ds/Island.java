package ds;

public class Island {
    private ParPtrTree ppt;
    private Integer[][] map;
    private int map_size;

    public Island(Integer[][] m, int ms) {
        this.map = m;
        this.map_size = ms;
        ppt = new ParPtrTree(map_size * map_size);
    }

    /**
     * Segment map using union and find function in ppt (point pointer tree)
     * Hint: you can represents 2-d array as 1-d array by flattening.
     * For row r and col c -> 1-d index = row * (row_size) + col
     */
    public void segmentMap() {
        //TODO: fill your code
        for(int r=0; r<map_size; r++){
            for(int c=0; c<map_size; c++){
                int curr = r*map_size + c;
                if(map[r][c] == 0) continue;
                if(c+1 < map_size && map[r][c+1] ==1 ){
                    int neighbor = r*map_size + c+1;
                    ppt.union(curr, neighbor);
                }
                if(r+1 < map_size && map[r+1][c] ==1 ){
                    int neighbor = (r+1)*map_size + c+1;
                    ppt.union(curr, neighbor);
                }
            }
        }
    }

    /**
     * get the number of the islands using array of ppt
     */
    public int getIslandNum() {
        // TODO: fill your code
        int count =0;
        Integer[] roots = ppt.getArray();

        for(int r=0; r<map_size; r++){
            for(int c=0; c<map_size; c++){
                int curr = r*map_size + c;
                if(map[r][c] == 1 && roots[curr] == null) count++;
            }
        }
        return count;
    }

    public int[] getIslandSize() {
        // TODO: optional problem, fill your code if you want
        // Hint: use Arrays.sort() to sort array
        return null;
    }
}
