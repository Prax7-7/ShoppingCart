package entities.carts;

import entities.Inventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utility.CartHelper;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class CartTests {

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){
        Inventory.getInstance().deleteAllItems();
    }

    @Test
    public void  addProductsToShoppingCart(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();

        inventory.addItem("Dove Soap",39.99);

        cart.addItem("Dove Soap",5);

        System.out.println("Expected Quantity of Dove Soap item in cart = 5");
        System.out.println("Actual Quantity of Dove Soap item in cart = "+cart.getItemQuantiy("Dove Soap"));
        assertEquals(5, cart.getItemQuantiy("Dove Soap"));

        System.out.println("Expected Unit price of Dove Soap = 39.99");
        System.out.println("Actual Unit price of Dove Soap = "+ inventory.getUnitPriceForItem("Dove Soap"));
        assertEquals(39.99,inventory.getUnitPriceForItem("Dove Soap"));


        System.out.println("Expected Total Amount = "+ cart.getTotalAmount());
        System.out.println("Actual Total Amount = 199.95");
        assertEquals(199.95,cart.getTotalAmount());

    }

    @Test
    public void addAdditionalProductsOfTheSameTypeToTheShoppingCart(){
        ICart cart  = new Cart();
        Inventory inventory = Inventory.getInstance();

        inventory.addItem("Dove Soap",39.99);

        cart.addItem("Dove Soap",5);

        cart.addItem("Dove Soap",3);

        System.out.println("Expected Quantity of Dove Soap item in cart = 8");
        System.out.println("Actual Quantity of Dove Soap item in cart = "+cart.getItemQuantiy("Dove Soap"));
        assertEquals(8, cart.getItemQuantiy("Dove Soap"));

        System.out.println("Expected Unit price of Dove Soap = 39.99");
        System.out.println("Actual Unit price of Dove Soap = "+ inventory.getUnitPriceForItem("Dove Soap"));
        assertEquals(39.99,inventory.getUnitPriceForItem("Dove Soap"));

        System.out.println("Expected Total Amount = "+ cart.getTotalAmount());
        System.out.println("Actual Total Amount = 319.92");
        assertEquals(319.92,cart.getTotalAmount());

    }

    @Test
    public void calculateTheTaxRateOfShoppingCartWithMultipleItems(){
        ICart cart = new Cart(12.50);
        Inventory inventory = Inventory.getInstance();

        inventory.addItem("Dove Soap",39.99);
        inventory.addItem("Axe Deo",99.99);

        cart.addItem("Dove Soap",2);
        cart.addItem("Axe Deo",2);

        System.out.println("Expected Quantity of Dove Soap item in cart = 2");
        System.out.println("Actual Quantity of Dove Soap item in cart = "+cart.getItemQuantiy("Dove Soap"));
        assertEquals(2, cart.getItemQuantiy("Dove Soap"));

        System.out.println("Expected Unit price of Dove Soap = 39.99");
        System.out.println("Actual Unit price of Dove Soap = "+ inventory.getUnitPriceForItem("Dove Soap"));
        assertEquals(39.99,inventory.getUnitPriceForItem("Dove Soap"));

        System.out.println("Expected Quantity of Axe Item item in cart = 2");
        System.out.println("Actual Quantity of Dove Soap item in cart = "+cart.getItemQuantiy("Axe Deo"));
        assertEquals(2, cart.getItemQuantiy("Axe Deo"));

        System.out.println("Expected Unit price of Axe Soap = 99.99");
        System.out.println("Actual Unit price of Axe Deo = "+ inventory.getUnitPriceForItem("Axe Deo"));
        assertEquals(99.99,inventory.getUnitPriceForItem("Axe Deo"));

        System.out.println("Expected Tax amount for rate 12.5% = 35.00");
        System.out.println("Expected Tax amount for rate 12.5% = "+cart.getTaxAmount());
        assertEquals(35.00,cart.getTaxAmount());


        System.out.println("Expected Total Amount = "+ cart.getTotalAmount());
        System.out.println("Actual Total Amount = 314.96");
        assertEquals(314.96,cart.getTotalAmount());

    }

    @Test
    public void shouldNotAddItemToCart(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();

        //Item not present in inventory, should return false
        assertFalse(cart.addItem("Dove Soap"));

        inventory.addItem("Dove Soap",39.99);

        //Item added to inventory but quantity is zero, should return false
        assertFalse( cart.addItem("Dove Soap",0));


        //Item added to inventory but quantity is negative, should return false
        assertFalse(cart.addItem("Dove Soap",-1));


    }

    @Test
    public void shouldNotUpdateItemQuantity(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Dove Soap",39.99);

        //Item not present in the cart, should return false
        assertFalse(cart.updateItemQuantity("Dove Soap",2));

        cart.addItem("Dove Soap",2);
        //Item quantity is negative, should return false
        assertFalse(cart.updateItemQuantity("Dove Soap",-1));


    }

    @Test
    public void shouldUpdateCartItemSuccessfully(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);
        cart.addItem("Pen",2);
        //update quantity to 5
        assertTrue(cart.updateItemQuantity("Pen",5));
        //verify the item quantity
        assertEquals(5,cart.getItemQuantiy("Pen"));
    }

    @Test
    public void shouldDeleteItemFromCart(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Dove Soap",39.99);
        inventory.addItem("Pen",9.99);
        cart.addItem("Dove Soap",2);
        //Update to quantiy zero should delete item from cart and return true
        assertTrue(cart.updateItemQuantity("Dove Soap",0));

        //Confirm if the item is deleted from cart
        assertFalse(cart.isItemPresent("Dove Soap"));

        cart.addItem("Pen",2);
        //delete from cart
        assertTrue(cart.deleteItem("Pen"));

        //Confirm deletion
        assertFalse(cart.isItemPresent("Pen"));
    }

    @Test
    public void invalidItemDelete(){
        ICart cart = new Cart();
        //trying tp delete item which is not in cart
        assertFalse(cart.deleteItem("Dove Soap"));
    }

    @Test
    public void getCartItemTest(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Dove Soap",39.99);
        inventory.addItem("Pen",9.99);

        cart.addItem("Dove Soap", 2);
        cart.addItem("Pen",3);

        //The unique Item list size should be 2
        assertEquals(2,cart.getCartItems().size());

        assertEquals("Dove Soap",cart.getCartItems().get(0));
        assertEquals("Pen",cart.getCartItems().get(1));


        cart.deleteItem("Dove Soap");
        //The unique Item list size should be 1
        assertEquals(1,cart.getCartItems().size());
        assertEquals("Pen",cart.getCartItems().get(0));

        cart.deleteItem("Pen");
        assertEquals(0,cart.getCartItems().size());


    }

    @Test
    public void itemQuantityTest(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        cart.addItem("Pen",3);

        assertEquals(3,cart.getItemQuantiy("Pen"));

        cart.deleteItem("Pen");

        assertEquals(0L,cart.getItemQuantiy("Pen"));
    }

    @Test
    public void cartEmptyTest(){
        ICart cart = new Cart();
        Inventory inventory = Inventory.getInstance();
        inventory.addItem("Pen",9.99);

        assertTrue(cart.isCartEmpty());
        cart.addItem("Pen",3);

        assertFalse(cart.isCartEmpty());

        cart.deleteItem("Pen");

        assertTrue(cart.isCartEmpty());
    }

    @Test
    public void getTaxRateTest(){
        ICart cart = new Cart();
        assertEquals(0.0,cart.getTaxRate());

        cart = new Cart(12.5);
        assertEquals(12.5,cart.getTaxRate());
    }
}
