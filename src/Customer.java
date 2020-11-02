/**
 *
 * @version 3
 */
public class Customer {
    private String email;
    private int standardTicket;
    private int VIPTicket;
    private String response;


    public Customer(String email, int standardTicket, int VIPTicket, String response) {
        this.email = email;
        this.standardTicket = standardTicket;
        this.VIPTicket = VIPTicket;
        this.response = response;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Customer with email " + email + " requested " +
                standardTicket + " standard ticket and " + VIPTicket + " VIP ticket";
    }
}
