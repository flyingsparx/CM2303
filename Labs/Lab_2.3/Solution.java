import java.util.ArrayList;

public class Solution{

    class Stack extends ArrayList<Node>{
        public Node pop(){
            Node node = get(0);
            remove(0);
            return node;
        }
        public void push(Node node){
            add(0, node);
        }
        public Boolean is_empty(){
            return size() == 0;
        }
    }

    class Queue extends ArrayList<Node>{
        public Node dequeue(){
            Node node = get(0);
            remove(0);
            return node;
        }
        public void enqueue(Node node){
            add(node);
        }
        public Boolean is_empty(){
            return size() == 0;
        }
    }

    class MyHashEntry{
        private int key;
        private Node value;
        
        public MyHashEntry(int key, Node value){
            this.key = key;
            this.value = value;
        }
        public int get_key(){
            return key;
        }
        public Node get_value(){
            return value;
        }
    }   

    class MyHashTable{
        private MyHashEntry[] table;

        public MyHashTable(int size){
            table = new MyHashEntry[size];
        }
        private int hash(int key){
            return (key % table.length);
        }
        public void put(int key, Node value){
            int index = hash(key);
            while (table[index] != null && table[index].get_key() != key){
                index++;
                if(index >= table.length){
                    index = 0;
                }
            }
            table[index] = new MyHashEntry(key, value);
        }
        public Node get(int key){
            int index = hash(key);
            int first_index = index-1;
            while (table[index] != null && table[index].get_key() != key){                
                index++;
                if(index >= table.length){
                    index = 0;
                }   
                else if(index == first_index){
                    return null;
                }
            }
            if(table[index] == null){
                return null;
            }
            return table[index].get_value();
        }
    }

    class Node{
        private int id; 
        private ArrayList<Node> children;

        public Node(int id){
            this.id = id;
            children = new ArrayList<Node>();
        }
        public int get_id(){
            return id;
        }
        public Node[] get_children(){
            return children.toArray(new Node[children.size()]);
        }
        public void add_child(Node child){
            children.add(child);
        }
    }

    class Tree{
        private MyHashTable nodes;

        public Tree(){
            nodes = new MyHashTable(16);
        } 
        public void add_node(int id, Integer parent_id){
            Node node = new Node(id);
            nodes.put(id, node);
            if(parent_id != null){
                nodes.get(parent_id).add_child(node);
            }
        }
        public void display(int id, int depth) {
            Node[] children = nodes.get(id).get_children();
            System.out.println(new String(new char[depth]).replace("\0", "-") + nodes.get(id).get_id());
            depth++;
            for (Node child : children) {
                 this.display(child.get_id(), depth);
            }
        }

        public Node breadth_first_search(int start_id, Integer search_id){
            Queue queue = new Queue();
            queue.enqueue(nodes.get(start_id));
            System.out.println(""); // Print a new line
            while(!queue.is_empty()){
                Node node = queue.dequeue();
                System.out.print(node.get_id()+" ");
                if(search_id != null && node.get_id() == search_id){return node;}
                for(Node child : node.get_children()){queue.enqueue(child);}
            }  
            return null;
        }
        
        public Node depth_first_search(int start_id, Integer search_id){
            Stack stack = new Stack();
            stack.push(nodes.get(start_id));
            System.out.println(""); // print a new line
            while(!stack.is_empty()){
                Node node = stack.pop();
                System.out.print(node.get_id()+" ");
                if(search_id != null && node.get_id() == search_id){return node;}
                for(Node child : node.get_children()){stack.push(child);}
            }
            return null;
        }
    }

    public Solution(){
        Tree t = new Tree();
        t.add_node(1, null);
        t.add_node(2, 1);
        t.add_node(3, 1);
        t.add_node(4, 1);
        t.add_node(5, 2);
        t.add_node(6, 3);
        t.add_node(7, 3);
        t.add_node(8, 4);
        t.add_node(9, 4);
        t.add_node(10, 5);
        t.add_node(11, 5);
        t.add_node(12, 6);
        t.add_node(13, 11);

        Node search1 = t.breadth_first_search(1, null);
        Node search2 = t.depth_first_search(1, null);
    }
    public static void main(String[] args){
        new Solution();
    }
}
