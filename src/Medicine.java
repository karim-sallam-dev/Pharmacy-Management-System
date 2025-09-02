public class Medicine extends Employee {
    private String name;
    private double price;
    private int quantity;

    public Medicine(int id, String creationDate, String firstName, String lastName, int customerNumber, int password, String address, String jobTitle, String name, double price, int quantity) {
        super(id, creationDate, firstName, lastName, customerNumber, password, address, jobTitle);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Medicine() {
        this.name = null;
        this.price = 0.0;
        this.quantity = 0;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}