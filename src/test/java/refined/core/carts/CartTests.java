package refined.core.carts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import refined.entities.Product;
import testUtility.testData.TestProducts;

import static junit.framework.TestCase.assertEquals;

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

        cart.addItem(doveSoap, 5);
        assertEquals(5, cart.getProductQuantity(doveSoap));

        assertEquals(doveSoap.getUnitPrice(), cart.getAllProducts().get(0).getUnitPrice());

        assertEquals(199.95, cart.getTotalAmount());

    }

}
