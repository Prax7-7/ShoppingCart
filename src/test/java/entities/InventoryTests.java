package entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class InventoryTests {
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        Inventory.getInstance().deleteAllItems();
    }

    @Test
    public void addInvalidItemTest(){
        Inventory inventory = Inventory.getInstance();

        assertFalse(inventory.addItem("Pen",0.0));
        assertFalse(inventory.addItem("Pen",-1.43));
    }

    @Test
    public void addValidItemTest(){
        Inventory inventory = Inventory.getInstance();

        assertTrue(inventory.addItem("Pen",9.99));
        assertTrue(inventory.addItem("Pencil",4.99));

        assertEquals(2,inventory.getItemsList().size());

    }

    @Test
    public void updateInvalidUnitPrice() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        assertFalse(inventory.updateUnitPrice("Pen",-1.5));
        assertFalse(inventory.updateUnitPrice("Pen",0));
        assertFalse(inventory.updateUnitPrice("Pencil", 4.5));
        assertEquals(9.99,inventory.getUnitPriceForItem("Pen"));
    }


    @Test
    public void updateValidUnitPrice() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        assertFalse(inventory.updateUnitPrice("Pen",-1.5));
        assertFalse(inventory.updateUnitPrice("Pen",0));

        assertEquals(9.99,inventory.getUnitPriceForItem("Pen"));
    }

    @Test
    public void invalidDeleteItem() {
        Inventory inventory = Inventory.getInstance();
        assertFalse(inventory.deleteItem("Pen"));
    }

    @Test
    public void validDeleteItem() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen", 9.99);

        assertTrue(inventory.deleteItem("Pen"));
    }

    @Test
    public void deleteAllTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen", 9.99);
        inventory.deleteAllItems();
        assertEquals(0, inventory.getItemsList().size());
    }

    @Test
    public void itemPresentTest() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen", 9.99);
        assertTrue(inventory.isItemPresent("Pen"));
        assertFalse(inventory.isItemPresent("Pencil"));
    }

    @Test
    public void getUnitPriceTestForValidItem() {
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen", 9.99);
        assertEquals(9.99, inventory.getUnitPriceForItem("Pen"));
    }

    @Test
    public void getUnitPriceTestForInvalidItem() {
        Inventory inventory = Inventory.getInstance();
        assertEquals(-1.0, inventory.getUnitPriceForItem("Pen"));
    }


}
