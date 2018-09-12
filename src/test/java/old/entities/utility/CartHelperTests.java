package old.entities.utility;

import old.entities.Inventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CartHelperTests {
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        Inventory.getInstance().deleteAllItems();
    }

    @Test
    public void getEffectiveItemPriceTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Dove Soap", 39.99);
        assertEquals(199.95000000000002, CartHelper.getEffectiveItemPrice("Dove Soap", 5));
    }

    @Test
    public void formatAmountValueTest() {
        assertEquals(199.95, CartHelper.formatAmountValue(199.95000000000002));
    }


}
