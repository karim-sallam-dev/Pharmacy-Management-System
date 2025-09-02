public class Customer {
    private int id;
    private String creationDate;
    private String firstName;
    private String lastName;
    private int customerNumber;
    private int password;
    private String address;


    public Customer() {
        this.id =0;
        this.creationDate = null;
        this.firstName = "";
        this.lastName = "";
        this.customerNumber = 0;
        this.password = 0;
        this.address = "";
    }


    public Customer(int id, String creationDate, String firstName, String lastName, int customerNumber, int password, String address) {
        this.id = id;
        this.creationDate = creationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
        this.password = password;
        this.address = address;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
