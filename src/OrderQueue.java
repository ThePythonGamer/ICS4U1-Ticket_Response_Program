import java.util.ArrayList;

/**
 *
 * @version 1
 */
public class OrderQueue {
    private ArrayList<Customer> Queue = new ArrayList<>();

    /**
     * This add a Customer object to the queue
     * @param toAdd - Customer object to be added
     */
    public void enqueue(Customer toAdd) {
        Queue.add(toAdd);
    }

    /**This removes the first object in the queue and return the removed element
     *
     * @return the first Customer object in the queue, if there is no element to return, return null
     */
    public Customer dequeue() {
        try {
            return Queue.remove(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This return the size of the queue
     * @return the size of the queue
     */
    public int size() {
        return Queue.size();
    }

    /**Get the first element of the queue
     *
     * @return the first Customer object, if there is no element to return, return null
     */
    public Customer peek(){
        try {
            return Queue.get(0);
        } catch (Exception e){
            return null;
        }
    }
}
