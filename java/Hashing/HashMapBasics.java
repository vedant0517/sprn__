import java.util.*;
public class HashMapBasics {
    public static void main(String[] args) {
        //Create
        HashMap<String,Integer> hm=new HashMap<>();

        //Insert - O(1)
        hm.put("India",100);
        hm.put("China",150);
        hm.put("USA",50);
        hm.put("Nepal",5);
        hm.put("Indonesia",6);
        // System.out.println(hm);

        // //Get - O(1)
        // int population=hm.get("India");
        // System.out.println(population);

        // System.out.println(hm.get("Italy"));

        // //ContainsKey - O(1)
        // System.out.println(hm.containsKey("India"));
        // System.out.println(hm.containsKey("Bhutan"));

        // //remove - O(1)
        // hm.remove("China");
        // System.out.println(hm);

        // //size
        // System.out.println(hm.size());

        // //isEmpty
        // hm.clear();
        // System.out.println(hm.isEmpty());

        Set<String> keys= hm.keySet();
        System.out.println(keys);
        
        for (String k : keys) {
            System.out.println("Key="+k+","+"value="+hm.get(k));
        }
    }
}
