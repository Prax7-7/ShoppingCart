package old.entities.carts;


import old.entities.utility.CartHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements ICart
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 09-11-2018
 */
public class Cart implements ICart {
    private final double taxRate;
    private HashMap<String, Long> cartItemsMap;


    /**
     * Constructor for carts with out tax
     */
    public Cart() {
        cartItemsMap = new HashMap<>();
        taxRate = 0;
    }

    /**
     * Parameterised constructor for specifying the tax rate
     *
     * @param taxRate double, rate of tax
     */
    public Cart(double taxRate) {
        cartItemsMap = new HashMap<>();
        this.taxRate = taxRate;
    }

    @Override
    public boolean addItem(String item) {
        if (CartHelper.isItemFromInventory(item)) {
            cartItemsMap.put(item, 1L);
            return true;
        } else {
            System.out.println(String.format("Product %s is not from inventory, could not add to carts", item));
            return false;
        }
    }

    @Override
    public boolean addItem(String item, long quantity) {

        if (!CartHelper.isItemFromInventory(item)) {
            System.out.println(String.format("Could not add item to carts, as item %s is not from inventory,", item));
            return false;
        } else if (!CartHelper.isQuantityValid(quantity)) {
            System.out.println("Invalid quantity , value is either 0 or negative");
            return false;
        } else if (cartItemsMap.containsKey(item)) {
            long initQuantity, updatedQuantity;
            initQuantity = cartItemsMap.get(item);
            updatedQuantity = initQuantity + quantity;

            System.out.println(String.format("Product %s is already present in the carts, hence updated the quantity from %s to" +
                    " %s", item, initQuantity, updatedQuantity));
            cartItemsMap.put(item, updatedQuantity);
            return true;
        } else {
            System.out.println(String.format("Successfully added item %s to the Cart", item));
            cartItemsMap.put(item, quantity);
            return true;
        }
    }

    @Override
    public boolean updateItemQuantity(String item, long newQuantity) {
        if (newQuantity == 0L) {
            System.out.println(String.format("Quantity is zero, hence item %s is removed from carts", item));
            cartItemsMap.remove(item);
            return true;
        } else if (newQuantity < 0L) {
            System.out.println(String.format("New Quantity cannot be negative, hence item %s cannot be updated", item));
            return false;
        } else if (cartItemsMap.containsKey(item)) {
            System.out.println(String.format("Product quantity was updated successfully to %s", newQuantity));
            cartItemsMap.put(item, newQuantity);
            return true;
        } else {
            System.out.println(String.format("Product is not present in the carts, hence item %s cannot be updated", item));
            return false;
        }
    }

    @Override
    public boolean deleteItem(String item) {
        if (cartItemsMap.containsKey(item)) {
            cartItemsMap.remove(item);
            System.out.println(String.format("Successfully deleted Product %s from the carts", item));
            return true;
        } else {
            System.out.println(String.format("Product %s is not found in the carts", item));
            return false;
        }

    }

    @Override
    public List<String> getCartItems() {
        return new ArrayList<String>(this.cartItemsMap.keySet());
    }

    @Override
    public long getItemQuantiy(String item) {
        return cartItemsMap.getOrDefault(item, 0L);
    }

    @Override
    public boolean isCartEmpty() {
        return cartItemsMap.size() == 0;
    }

    @Override
    public double getTaxRate() {
        return taxRate;
    }

    @Override
    public double getTaxAmount() {
        double taxAmount;
        taxAmount = getSubTotal() * (this.taxRate / 100);
        taxAmount = CartHelper.formatAmountValue(taxAmount);
        return taxAmount;
    }

    @Override
    public double getTotalAmount() {
        double total;
        total = getSubTotal() + getTaxAmount();
        total = CartHelper.formatAmountValue(total);
        return total;
    }

    @Override
    public boolean isItemPresent(String item) {
        return cartItemsMap.containsKey(item);
    }

    @Override
    public double getSubTotal() {
        double subTotal;
        subTotal = 0;

        for (Map.Entry<String, Long> entry : cartItemsMap.entrySet())
            subTotal += CartHelper.getEffectiveItemPrice(entry.getKey(), entry.getValue());

        subTotal = CartHelper.formatAmountValue(subTotal);
        return subTotal;
    }

}
