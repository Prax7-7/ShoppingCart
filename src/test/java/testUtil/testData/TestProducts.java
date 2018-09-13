package testUtil.testData;

import entities.Product;

public class TestProducts {
    public static Product getDoveSoap() {
        return new Product.ProductBuilder("Dove Soap", 39.99).build();
    }

    public static Product getPen() {
        return new Product.ProductBuilder("Pen", 9.99).build();
    }


}
