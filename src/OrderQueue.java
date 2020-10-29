import java.util.ArrayList;

/**
 *
 * @version 1
 */
public class OrderQueue {
    private ArrayList<Customer> Queue = new ArrayList<>();

    public void enqueue(Customer toAdd) {
        Queue.add(toAdd);
    }

    public Customer dequeue() {
        try {
            return Queue.remove(0);
        } catch (Exception e) {
            return null;
        }
    }

    public int size() {
        return Queue.size();
    }

    public Customer peek(){
        try {
            return Queue.get(0);
        } catch (Exception e){
            return null;
        }
    }
}
