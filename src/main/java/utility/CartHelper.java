package utility;


import entities.Inventory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The purpose of this class is to help cart class by providing info and formatting support
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 09-11-2018
 */
public class CartHelper {

    /**
     * This method is used to check if an item is present in the inventory
     *
     * @param item String value key
     * @return boolean, found status
     */
    public static boolean isItemFromInventory(String item) {
        return Inventory.getInstance().isItemPresent(item);
    }

    /**
     * This method is used to get Items effective price considering the quantity
     *
     * @param item     String value key
     * @param quantity long value
     * @return double, total/effective price
     */
    public static double getEffectiveItemPrice(String item, long quantity) {
        return Inventory.getInstance().getUnitPriceForItem(item) * (double) quantity;
    }

    /**
     * This is method is used to format a double value by rounding half-up to two decimal values
     *
     * @param amount double, input amount
     * @return double, rounded up value
     */
    public static double formatAmountValue(double amount) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(amount));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }


}
