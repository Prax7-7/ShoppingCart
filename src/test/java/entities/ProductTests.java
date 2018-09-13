package entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.testData.TestProducts;

import static junit.framework.TestCase.*;
import static org.junit.Assert.fail;


public class ProductTests {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getNameTest() {
        Product doveSoap = TestProducts.getDoveSoap();
        assertEquals("Dove Soap", doveSoap.getName());
    }

    @Test
    public void getUnitPriceTest() {
        Product doveSoap = TestProducts.getDoveSoap();
        assertEquals(39.99, doveSoap.getUnitPrice());
    }

    @Test
    public void hashCodeTest() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = new Product.ProductBuilder("Dove Soap", 39.98).build();
        Product product3 = new Product.ProductBuilder("Dove soap", 39.99).build();

        assertFalse(product1.hashCode() == product2.hashCode());
        assertFalse(product1.hashCode() == product3.hashCode());
    }

    @Test
    public void objectEqualsTest() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = new Product.ProductBuilder("Dove Soap", 39.98).build();
        Product product3 = new Product.ProductBuilder("Dove soap", 39.99).build();
        Product product4 = new Product.ProductBuilder("Dove Soap", 39.99).build();

        assertFalse(product1.equals(product2));
        assertFalse(product1.equals(product3));

        assertTrue(product1.equals(product4));


    }

    @Test
    public void invalidFieldsTests() {
        try {
            new Product.ProductBuilder(null, 39.98).build();
            fail();
        } catch (Exception e) {
            assertEquals("Product name cannot be null", e.getMessage());
        }

        try {
            new Product.ProductBuilder("Pen", 0.0).build();
            fail();
        } catch (Exception e) {
            assertEquals("Invalid unit-price, unit-price should be greater than zero", e.getMessage());
        }

        try {
            new Product.ProductBuilder("Pen", -39.98).build();
            fail();
        } catch (Exception e) {
            assertEquals("Invalid unit-price, unit-price should be greater than zero", e.getMessage());
        }
    }
}