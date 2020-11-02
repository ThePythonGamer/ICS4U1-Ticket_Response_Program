/** The class that creates the Customer Object that holds multiple attributes of customer information
 *
 * @author Hayden Rooney, Daniel Ly, Erin Zhang
 * @version 4
 */
public class Customer {
    private String email;
    private int standardTicket;
    private int VIPTicket;
    private String response;

    /**This is a Customer object that represent a customer with email and his/her request on standard ticket, vip ticket.
     * A response is included for emailing this customer later.
     *
     * @param email this represent the customer's email
     * @param standardTicket this represent the customer's request for standard ticket
     * @param VIPTicket this represent the customer's request for vip ticket
     * @param response this is the response to give for the customer's request.
     */
    public Customer(String email, int standardTicket, int VIPTicket, String response) {
        this.email = email;
        this.standardTicket = standardTicket;
        this.VIPTicket = VIPTicket;
        this.response = response;
    }

    /**This get email of the Customer object
     *
     * @return email of the customer
     */
    public String getEmail() {
        return email;
    }

    /**This returns the amount of standard ticket being requested by the customer
     *
     * @return amount of standard ticket being requested
     */
    public int getStandardTicket() {
        return standardTicket;
    }
    /**This returns the amount of VIP ticket being requested by the customer
     *
     * @return amount of VIP ticket being requested
     */
    public int getVIPTicket() {
        return VIPTicket;
    }

    /** This returns the response for the customer's request
     *
     * @return information for email response
     */
    public String getResponse() {
        return response;
    }

    /**This set the response for the customer's request
     *
     * @param response information for email response to be changed
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**Override toString
     *
     * @return Customer's information in one sentence
     */
    @Override
    public String toString() {
        return "Customer with email " + email + " requested " +
                standardTicket + " standard ticket and " + VIPTicket + " VIP ticket";
    }
}
