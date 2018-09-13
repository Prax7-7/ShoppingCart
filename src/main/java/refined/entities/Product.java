package refined.entities;

/**
 * This is a immutable pojo class for product instances with some mandatory fields and optional fields
 * This class also has public builder class in it
 *
 * @author Prashanth B S
 * @version 1.0
 * @since 13-09-2018
 */
public final class Product {

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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + (int) this.unitPrice;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (!(obj instanceof Product))
            return false;
        else {
            Product product = (Product) obj;
            boolean isEqual;
            isEqual = product.name.equals(this.name) && (product.unitPrice == this.unitPrice);
            return isEqual;
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
         */
        public Product build() {
            return new Product(this);
        }
    }
}
