package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testUtil.testData.TestProducts;

import static junit.framework.TestCase.*;

public class CartHelperTests {
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void qualityValidationTest() {
        assertTrue(CartHelper.isQuantityValid(4));
        assertFalse(CartHelper.isQuantityValid(0));
        assertFalse(CartHelper.isQuantityValid(-4));

    }

    @Test
    public void getEffectiveProductPriceTest() {
        assertEquals(199.95000000000002, CartHelper.getEffectiveProductPrice(TestProducts.getDoveSoap(), 5));
    }

    @Test
    public void amountFormattingTest() {
        assertEquals(199.95, CartHelper.formatAmountValue(199.95000000000002));
    }
}
