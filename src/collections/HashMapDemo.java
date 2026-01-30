package collections;
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
  public static void main(String[] args) {
    HashMap<String, Integer> height=new HashMap<>();
    height.put("Alice", 167);
    height.put("Bob", 187);
    height.put("Charlie", 187);
    height.put("David", 178);

    for (String key: height.keySet()){
      System.out.println(key+": "+height.get(key));
    }

    height.put("David", 200);
    System.out.println(height);
    System.out.println(height.containsKey("David"));
    System.out.println(height.containsKey("david"));
    height.remove("Bob");

    for (Map.Entry<String, Integer> e: height.entrySet()){
      System.out.println(e);
    }
  }
}