package entities;

/**
 * This is a pojo class for product instances with some mandatory fields and optional fields
 * This class also has public builder class in it
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 13-09-2018
 */
public class Product {

    //Mandatory fields
    private final String name;
    private final double unitPrice;

    /**
     * Constructor is made private to make sure that only inner class uses it
     */
    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.unitPrice = builder.unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * This is overridden method which generates hashCode using the name and unit price
     *
     * @return int, hashcode
     */
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + (int) (this.unitPrice * 100.00);
        return result;
    }

    /**
     * Method to compare an object with product object
     *
     * @param obj given object should be of type Product
     * @return boolean
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (!(obj instanceof Product))
            return false;
        else {
            Product product = (Product) obj;
            return (product.name.equals(this.name) && (product.unitPrice == this.unitPrice));
        }
    }

    /**
     * Inner Static Builder class for Product class
     *
     * @author Prashanth B S
     * @version 1.0
     * @since 13-09-2018
     */
    public static class ProductBuilder {
        //Mandatory fields
        private String name;
        private double unitPrice;

        /**
         * Constructor accepting mandatory fields of Product class
         */
        public ProductBuilder(String name, double unitPrice) {

            this.name = name;
            this.unitPrice = unitPrice;
        }

        /**
         * This method constructs the item instance with specified data
         *
         * @return Product instance
         * @throws IllegalArgumentException
         */
        public Product build() throws IllegalArgumentException {
            if (name == null) {
                throw new IllegalArgumentException("Product name cannot be null");
            } else if (unitPrice == 0.0 || unitPrice < 0.0) {
                throw new IllegalArgumentException("Invalid unit-price, unit-price should be greater than zero");
            }

            return new Product(this);
        }
    }
}
