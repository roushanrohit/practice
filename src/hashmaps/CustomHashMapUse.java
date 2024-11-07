package hashmaps;

public class CustomHashMapUse {

    public static void main(String[] args) {

        CustomHashMap<Integer, Integer> hmap = new CustomHashMap<>();
        System.out.println(hmap.size());
        System.out.println(hmap.loadFactor());
        for(int i = 0; i < 10; i++){
            hmap.insert(i, 1+1);
        }
        System.out.println(hmap.size());
        System.out.println(hmap.loadFactor());
    }
}
