package refined.core.carts;

import refined.entities.Product;

import java.util.List;

/**
 * Interface for Cart instances
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
    void addItem(Product product, int quantity) throws IllegalArgumentException;

    /**
     * This methods returns list of unique products in the cart
     *
     * @return list of products
     */
    List<Product> getAllProducts();

    /**
     * This methos returns the product quantity in the cart for a given product
     *
     * @param product
     * @return
     */
    int getProductQuantity(Product product);

    double getTotalAmount();

    double getSubTotal();
}
