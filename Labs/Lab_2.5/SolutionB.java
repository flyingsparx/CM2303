class LinkedListElement<T>{
    private LinkedListElement<T> next_element;
    private T data;

    public LinkedListElement(T data){
        this.data = data;
    }

    public T get_data(){
        return data;
    }

    public LinkedListElement<T> get_next(){
        return next_element;
    }

    public void set_next(LinkedListElement<T> next_element){
        this.next_element = next_element;
    }
}

class LinkedList<T>{
    private LinkedListElement<T> head;
    private int list_size;

    public LinkedList(){
        // The start of our list has null data.
        head = new LinkedListElement<T>(null);    
        list_size = 0;
    }

    public void add(T data){
        // Find the current end of the linked list
        LinkedListElement<T> current_list_end = head;
        while(current_list_end.get_next() != null){
            current_list_end = current_list_end.get_next();
        }

        /*
            The following code adds our new element to the end 
            of the list by adding it as the next element to the current
            list end.
        */

        // Create a new element
        LinkedListElement<T> new_element = new LinkedListElement<T>(data);
        // Set the next element of the end element to our new element
        current_list_end.set_next(new_element);
        // Increment the list length
        list_size++;
    }

    public void add(T data, int index){
        // Find the point in the list in which to enter a new element
        LinkedListElement<T> entry_point = head;
        for(int i = 0; i < index; i++){
            if(entry_point.get_next() == null){
                return;
            }
            entry_point = entry_point.get_next();
        }

        /*
            The following code inserts our new element between entry_point
            and the element PAST entry_point.
        */    

        // Create a new element
        LinkedListElement<T> new_element = new LinkedListElement<T>(data);
        // Set the next element of our new one to the one PAST the entry point
        new_element.set_next(entry_point.get_next());
        // Set the next element of the entry point to our new element
        entry_point.set_next(new_element);
        // Increment the list size
        list_size++;
    }

    public T get(int index){
        index = index + 1; // Because our first element (head) is null

        // Find the element at the requested index
        LinkedListElement<T> required_element = head;
        for(int i = 0; i < index; i++){
            if(required_element.get_next() == null){
                return null;
            } 
            required_element = required_element.get_next();
        } 
        
        // Return the data of this element
        T data = required_element.get_data();
        return data;
    }

    public void remove(int index){
        // We don't add one this time as we want to refer to the element BEFORE
        // the one we are deleting.
        
        // Find the element at the required index
        LinkedListElement<T> required_element = head;
        for(int i = 0; i < index; i++){
            if(required_element.get_next() == null){
                return;
            }
            required_element = required_element.get_next();
        }

        /*
            The following code removes the element after the required element
            by setting the next element of required_element to the element
            TWO elements down. Thus the element after required_element is deleted
            from the chain.
        */

        // Set the element's next element to the one two elements down
        required_element.set_next(required_element.get_next().get_next());
        // Decrement the list size
        list_size--;
    }

    public int size(){
        return list_size;
    }
}

public class SolutionB{
    private void print_list(LinkedList<Integer> list){
        for(int i = 0; i < list.size(); i++){
            int thing = list.get(i);
            System.out.println(thing);
        }
    }
    public SolutionB(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5, 2);
        list.remove(1);
        print_list(list);
    }
    public static void main(String[] args){
        new SolutionB();
    }
}
