package hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main2 {
    public static void main(String[] args) {
        Set<Product> productSet = new HashSet<>();

        String filePath = "/Users/yeongjae/Desktop/goorm/backend/data.csv";// CSV 파일 경로

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // 쉼표를 기준으로 각 줄을 분할
                String[] values = line.split(",");

                // 분할된 데이터 출력
                System.out.println("ID: " + values[0] + ", 상품: " + values[1] + ", 가격: " + values[2]);
                Product product = new Product(values[0], values[1], Integer.parseInt(values[2]));
                productSet.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cart cart = new Cart();
        System.out.println("==Products==");
        for (Product product : productSet) {
            System.out.println(product.getName() + "-" + product.getPrice() + "won");
            cart.addProduct(product, 2);
            cart.removeProduct(product, 1);
        }

        cart.showItems();
        System.out.println(cart.showItemsStream());
    }
}