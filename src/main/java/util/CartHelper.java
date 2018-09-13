package util;

import entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * This is a helper class for cart
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 13-09-2018
 */
public class CartHelper {

    /**
     * This is method is used to validate quantity
     *
     * @param quantity cannot to zero or negative
     * @return boolean
     */
    public static boolean isQuantityValid(int quantity) {
        return quantity != 0 && quantity >= 0;
    }

    /**
     * This method is used to get Items effective price considering the quantity
     *
     * @param product  billable product
     * @param quantity long value
     * @return double, total/effective price
     */
    public static double getEffectiveProductPrice(Product product, int quantity) {
        double effectivePrice;
        effectivePrice = product.getUnitPrice() * quantity;
        return effectivePrice;
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
