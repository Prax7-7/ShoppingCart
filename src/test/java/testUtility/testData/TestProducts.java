package testUtility.testData;

import refined.entities.Product;

public class TestProducts {
    public static Product getDoveSoap() {
        return new Product.ProductBuilder("Dove Soap", 39.99).build();
    }


}
