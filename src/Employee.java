public class Employee extends Customer {
    private String jobTitle;

    public Employee(int id, String creationDate, String firstName, String lastName, int customerNumber, int password, String address, String jobTitle) {
        super(id, creationDate, firstName, lastName, customerNumber, password, address);
        this.jobTitle = jobTitle;
    }
    public Employee() {

        String jobTitle=null;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
