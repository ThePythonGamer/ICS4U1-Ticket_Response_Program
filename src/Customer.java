public class Customer {
    private String email;
    private int standardTicket;
    private int VIPTicket;

    public Customer(String email, int standardTicket, int VIPTicket) {
        this.email = email;
        this.standardTicket = standardTicket;
        this.VIPTicket = VIPTicket;
    }

    public String getEmail() {
        return email;
    }

    public int getStandardTicket() {
        return standardTicket;
    }

    public int getVIPTicket() {
        return VIPTicket;
    }
}
