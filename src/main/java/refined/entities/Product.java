package refined.entities;

/**
 * This is a pojo class for Items instances with some mandatory fields and optional fields
 * This class also has public builder class in it
 */
public class Product {
    //Mandatory fields
    private final String name;
    private final double unitPrice;

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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

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


        /*
        this is where the optional fields are set
        */
//        public ProductBuilder withManufacturer(){
//            return this;
//        }

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
