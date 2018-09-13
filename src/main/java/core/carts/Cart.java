package core.carts;

import entities.Product;
import util.CartHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.CartHelper.*;


/**
 * This class encapsulates billable items with quantity and binds them with billing operations
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 13-09-2018
 */
public class Cart implements ICart {

    private final HashMap<Product, Integer> cartProductMap;
    private final double taxRate;

    /**
     * Constructor for carts with out tax
     */
    public Cart() {
        cartProductMap = new HashMap<>();
        taxRate = 0;
    }

    /**
     * Parameterised constructor for specifying the tax rate
     *
     * @param taxRate double, rate of tax
     */
    public Cart(double taxRate) {
        cartProductMap = new HashMap<>();
        this.taxRate = taxRate;
    }

    @Override
    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        try {
            validateCartEntry(product, quantity);

            if (cartProductMap.containsKey(product))
                incrementProductQuantiy(product, quantity);
            else
                cartProductMap.put(product, quantity);
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * This is private method used to increment the product quantity
     *
     * @param product             product to be added in carts
     * @param incrementalQuantity number of new extra unit
     */
    private void incrementProductQuantiy(Product product, int incrementalQuantity) {
        int initQuantity;
        initQuantity = cartProductMap.get(product);
        cartProductMap.replace(product, initQuantity, initQuantity + incrementalQuantity);
    }

    private void validateCartEntry(Product product, int quantity) {
        if (product == null)
            throw new IllegalArgumentException("Product to be added is null");
        else if (!isQuantityValid(quantity))
            throw new IllegalArgumentException("Invalid quantity value, quantity cannot be zero or negative");
    }

    @Override
    public void updateProductQuantity(Product product, int newQuantity) throws IllegalArgumentException {
        try {
            validateCartEntry(product, newQuantity);
            if (cartProductMap.containsKey(product))
                cartProductMap.replace(product, newQuantity);
            else
                throw new IllegalArgumentException("Product doesn't exist in cart, update failed");
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    @Override
    public void deleteProduct(Product product) throws IllegalArgumentException {
        if (product == null || !cartProductMap.containsKey(product)) {
            throw new IllegalArgumentException("Input Product is null or doesn't exist in cart");
        } else {
            cartProductMap.remove(product);
        }
    }

    @Override
    public void clear() {
        cartProductMap.clear();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(cartProductMap.keySet());
    }

    @Override
    public int getProductQuantity(Product product) throws NullPointerException {
        if ((product == null) || (!cartProductMap.containsKey(product)))
            throw new IllegalArgumentException("Input Product is null or doesn't exist in cart");
        return cartProductMap.get(product);
    }

    @Override
    public double getTotalAmount() {
        double total;
        total = getSubTotal();
        total = CartHelper.formatAmountValue(total);
        return total;
    }

    @Override
    public double getSubTotal() {
        double subTotal;
        subTotal = 0;

        for (Map.Entry<Product, Integer> entry : cartProductMap.entrySet())
            subTotal += getEffectiveProductPrice(entry.getKey(), entry.getValue());

        subTotal = formatAmountValue(subTotal);
        return subTotal;
    }

    @Override
    public boolean isCartEmpty() {
        return cartProductMap.size() == 0;
    }


}
