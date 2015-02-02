import java.util.ArrayList;

public class Solution{

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
    }

    public Solution(){
        Tree t = new Tree();
        t.add_node(2, null);
        t.add_node(4, 2);
        t.add_node(3, 2);
        t.add_node(5, 2);
        t.add_node(1, 4);
        t.add_node(7, 5);
        t.display(2, 0);
    }
    public static void main(String[] args){
        new Solution();
    }
}
