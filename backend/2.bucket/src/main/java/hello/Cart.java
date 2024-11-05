package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int amount){
        // 있으면 기존의 개수에서 더하고, 처음 나왔으면 추가한다.
//        if(items.containsKey(product)) {
//            items.put(product, items.get(product) + amount);
//        } else {
//            items.put(product, amount);
//        }
        items.merge(product, amount, Integer::sum);
    }

    public void removeProduct(Product product, int amount){
        // 기존에 있으면 갯수를 빼서 map을 업데이트한다.
        if(items.containsKey(product) && items.get(product)-amount >=0) {
            items.put(product, items.get(product)-amount);
        } else {
            throw new IllegalStateException("제거할 수 없음");
        }
    }

    public void showItems(){
        System.out.println("=== cart ===");
        for(Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue() + "개");
        }
    }

    public Map<String, Integer> showItemsStream(){
//        System.out.println("=== cart ===");
        return items.entrySet().stream()
                .collect(Collectors.toMap(e-> e.getKey().getName(), e-> e.getValue()));
    }


}
