package core.carts;

import entities.Product;

import java.util.List;

/**
 * Interface for Cart instance, provides basic APIs
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 13-09-2018
 */
public interface ICart {

    /**
     * This method is used to add product to carts
     *
     * @param product  product to be added in carts
     * @param quantity quantity of the product
     * @throws IllegalArgumentException
     */
    void addProduct(Product product, int quantity) throws IllegalArgumentException;

    /**
     * This method is check if cart is empty
     *
     * @return boolean
     */
    boolean isCartEmpty();

    /**
     * This method updated the product quantity to new quantity
     *
     * @param product     existing product, cannot be null
     * @param newQuantity quantity to overwritten to
     */
    void updateProductQuantity(Product product, int newQuantity) throws IllegalArgumentException;

    /**
     * This method is used to delete an existing product
     *
     * @param product cannot be null
     * @throws IllegalArgumentException
     */
    void deleteProduct(Product product) throws IllegalArgumentException;

    /**
     * This method is used to clear the cart
     */
    void clear();

    /**
     * This methods returns list of unique products in the cart
     *
     * @return list of products
     */
    List<Product> getAllProducts();

    /**
     * This methos returns the product quantity in the cart for a given product
     *
     * @param product key to searched in cart
     * @return int quantity
     */
    int getProductQuantity(Product product);

    /**
     * This method returns the total amount payable for the content of the cart
     *
     * @return double, round half-up to decimal places
     */
    double getTotalAmount();

    /**
     * This method returns total amount exclusive of tax
     *
     * @return
     */
    double getSubTotal();


}
