package old.entities.carts;

import java.util.List;

/**
 * Interface specifying api for Cart object
 * It has Basic CRUD methods and methods related to Tax and Totals
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 09-11-2018
 */
public interface ICart {

    /**
     * This method is used to add a new Product to carts default quantity is one
     *
     * @param item String value, item from inventory
     * @return boolean Success status
     */
    boolean addItem(String item);

    /**
     * This method is used to add a new Product to carts with specific quantity
     *
     * @param item     String value, item from inventory
     * @param quantity non-negative, non-zero long values
     * @return boolean Success status
     */
    boolean addItem(String item, long quantity);

    /**
     * This method is used to update new quantity for already added item in the carts
     *
     * @param item     String value, item from inventory
     * @param quantity non-negative, non-zero long values
     * @return boolean Success status
     */
    boolean updateItemQuantity(String item, long quantity);

    /**
     * This method is used to delete an already added item in the carts
     *
     * @param item String value, item from inventory
     * @return boolean Success status
     */
    boolean deleteItem(String item);

    /**
     * This method is used to get a list of Unique Items in the carts
     *
     * @return List of Strings
     */
    List<String> getCartItems();

    /**
     * This method is used to get quantity of an already existing item in the carts
     *
     * @param item String value, item from inventory
     * @return long values, quantiy of item in carts
     * @throws NullPointerException
     */
    long getItemQuantiy(String item);

    /**
     * This method is used to check if carts is empty
     *
     * @return boolean
     */
    boolean isCartEmpty();

    /**
     * This method is get the rate of tax thats been specified
     *
     * @return double values, rate of tax
     */
    double getTaxRate();

    /**
     * This method is used to return total amount of tax for the content in carts
     *
     * @return double value, tax amount
     */
    double getTaxAmount();

    /**
     * This method is used to get the subtotal for the carts content, which excludes tax
     *
     * @return double value, subTotal amount
     */
    double getSubTotal();

    /**
     * This method is used to get total amount inclusive of tax for the carts content
     *
     * @return double value, total amount
     */
    double getTotalAmount();

    /**
     * This method is used to check if item is already present in the carts
     *
     * @param item String value, item to be searched
     * @return boolean
     */
    boolean isItemPresent(String item);
}
