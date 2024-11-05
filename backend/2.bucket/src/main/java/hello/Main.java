package hello;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Product> productSet = new HashSet<>();

        Product product1 = new Product("11", "우유",2000);
        Product product2 = new Product("11", "사과",1000);
        productSet.add(product1);
        productSet.add(product2);
        System.out.println("==Products==");
        for(Product product: productSet) {
            System.out.println(product.getName() + "-" + product.getPrice()+"won");
        }

        // Cart에 상품 담기
        Cart cart = new Cart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 1);

        System.out.println("add");
        cart.showItems();

        System.out.println("add again");
        cart.addProduct(product1, 3);
        cart.showItems();

        // 삭제
        System.out.println("remove");
        cart.removeProduct(product1, 1);
        cart.showItems();

        // 예외 확인
//        cart.removeProduct(product1, 10);
//        toString() 이미 구현되어있음
        System.out.println(cart.showItemsStream());
    }
}