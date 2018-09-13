package core.carts;

import entities.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.testData.TestProducts;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertSame;

public class CartTests {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    /**
     * Step 1 Test as per the instruction
     */
    @Test
    public void addProductsToShoppingCart() {
        ICart cart = new Cart();

        Product doveSoap = TestProducts.getDoveSoap();

        cart.addProduct(doveSoap, 5);
        assertEquals(5, cart.getProductQuantity(doveSoap));

        assertEquals(doveSoap.getUnitPrice(), cart.getAllProducts().get(0).getUnitPrice());

        assertEquals(199.95, cart.getTotalAmount());

    }

    /**
     * Step 2 Test as per the instruction
     */
    @Test
    public void addAdditionalProductsOfTheSameTypeToTheShoppingCart() {
        ICart cart = new Cart();

        Product doveSoap = TestProducts.getDoveSoap();

        cart.addProduct(doveSoap, 5);
        cart.addProduct(doveSoap, 3);

        assertEquals(8, cart.getProductQuantity(doveSoap));

        assertEquals(doveSoap.getUnitPrice(), cart.getAllProducts().get(0).getUnitPrice());

        assertEquals(319.92, cart.getTotalAmount());
    }

    /**
     * Step 3 Test as per the instruction
     */
    @Test
    public void calculateTheTaxRateOfShoppingCartWithMultipleItems() {
        ICart cart = new Cart();

        Product doveSoap = TestProducts.getDoveSoap();
        Product axeDeo = TestProducts.getAxeDeo();
        cart.setTaxRate(12.5);

        cart.addProduct(doveSoap, 2);
        cart.addProduct(axeDeo, 2);

        assertEquals(2, cart.getProductQuantity(doveSoap));
        assertEquals(2, cart.getProductQuantity(axeDeo));

        assertEquals(doveSoap.getUnitPrice(), cart.getAllProducts().get(0).getUnitPrice());
        assertEquals(axeDeo.getUnitPrice(), cart.getAllProducts().get(1).getUnitPrice());

        assertEquals(35.00, cart.getTaxAmount());

        assertEquals(314.96, cart.getTotalAmount());
    }

    //------------------------------------- Remaining unit tests-----------------------------------------------

    @Test
    public void invalidAddProductsTest() {
        ICart cart = new Cart();
        Product doveSoap = TestProducts.getDoveSoap();
        try {
            cart.addProduct(null, 2);
            fail();
        } catch (Exception e) {
            assertEquals("Product to be added is null", e.getMessage());
        }

        try {
            cart.addProduct(doveSoap, 0);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid quantity value, quantity cannot be zero or negative", e.getMessage());
        }

        try {
            cart.addProduct(doveSoap, -2);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid quantity value, quantity cannot be zero or negative", e.getMessage());
        }

    }

    @Test
    public void getAllProductsTests() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 3);

        assertEquals(2, cart.getAllProducts().size());

        assertSame(product1, cart.getAllProducts().get(0));
        assertSame(product2, cart.getAllProducts().get(1));
    }

    @Test
    public void getProductQuantityForValidData() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 3);

        assertEquals(2, cart.getProductQuantity(product1));
        assertEquals(3, cart.getProductQuantity(product2));
    }

    @Test
    public void getProductQuantityForInValidData() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 2);
        try {
            cart.getProductQuantity(null);
            fail();
        } catch (Exception e) {
            assertEquals("Input Product is null or doesn't exist in cart", e.getMessage());
        }

        try {
            cart.getProductQuantity(product2);
            fail();
        } catch (Exception e) {
            assertEquals("Input Product is null or doesn't exist in cart", e.getMessage());
        }
    }

    @Test
    public void validProductUpdateTest() {
        Product doveSoap = TestProducts.getDoveSoap();
        ICart cart = new Cart();

        cart.addProduct(doveSoap, 10);
        cart.updateProductQuantity(doveSoap, 2);
        assertEquals(2, cart.getProductQuantity(doveSoap));
    }

    @Test
    public void invalidProductUpdateTest() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 2);
        try {
            cart.updateProductQuantity(null, 2);
            fail();
        } catch (Exception e) {
            assertEquals("Product to be added is null", e.getMessage());
        }

        try {
            cart.updateProductQuantity(product2, 2);
            fail();
        } catch (Exception e) {
            assertEquals("Product doesn't exist in cart, update failed", e.getMessage());
        }

        try {
            cart.updateProductQuantity(product1, 0);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid quantity value, quantity cannot be zero or negative", e.getMessage());
        }

        try {
            cart.updateProductQuantity(product1, -3);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid quantity value, quantity cannot be zero or negative", e.getMessage());
        }
    }

    @Test
    public void cartEmptyTest() {
        Product product1 = TestProducts.getDoveSoap();
        ICart cart = new Cart();
        assertTrue(cart.isCartEmpty());
        cart.addProduct(product1, 2);
        assertFalse(cart.isCartEmpty());
        cart.clear();
        assertTrue(cart.isCartEmpty());
    }

    @Test
    public void cartClearTest() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 2);
        cart.clear();
        assertTrue(cart.isCartEmpty());
    }

    @Test
    public void deleteProductTest() {
        Product product1 = TestProducts.getDoveSoap();
        Product product2 = TestProducts.getPen();
        ICart cart = new Cart();
        cart.addProduct(product1, 3);
        cart.addProduct(product2, 5);
        cart.deleteProduct(product1);
        assertEquals(1, cart.getAllProducts().size());


        try {
            cart.deleteProduct(null);
            fail();
        } catch (Exception e) {
            assertEquals("Input Product is null or doesn't exist in cart", e.getMessage());
        }

        try {
            cart.deleteProduct(product1);
            fail();
        } catch (Exception e) {
            assertEquals("Input Product is null or doesn't exist in cart", e.getMessage());
        }
    }







}
