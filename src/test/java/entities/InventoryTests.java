package entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class InventoryTests {

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
    public void UpdateInvalidUnitPrice(){
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        assertFalse(inventory.updateUnitPrice("Pen",-1.5));
        assertFalse(inventory.updateUnitPrice("Pen",0));
        assertFalse(inventory.updateUnitPrice("Pencil", 4.5));
        assertEquals(9.99,inventory.getUnitPriceForItem("Pen"));
    }


    @Test
    public void UpdateValidUnitPrice(){
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        assertFalse(inventory.updateUnitPrice("Pen",-1.5));
        assertFalse(inventory.updateUnitPrice("Pen",0));

        assertEquals(9.99,inventory.getUnitPriceForItem("Pen"));
    }

    @Test
    public void InvalidDeleteItem(){
        Inventory inventory = Inventory.getInstance();
        assertFalse(inventory.deleteItem("Pen"));
    }


}
