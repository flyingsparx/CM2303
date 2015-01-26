/*
 * This class contains (untested!) solution pointers up until question 10.
 * You will need to think about the best way to solve question 11. 
*/

public class Solution{

    static class MyHashEntry{
        private int key;
        private String value;
        
        public MyHashEntry(int key, String value){
            this.key = key;
            this.value = value;
        }
        public int get_key(){
            return key;
        }
        public String get_value(){
            return value;
        }
    }   

    static class MyHashTable{
        private MyHashEntry[] table;

        public MyHashTable(int size){
            table = new MyHashEntry[size];
        }
        private int hash(int key){
            return (key % table.length);
        }
        public void put(int key, String value){
            int index = hash(key);
            while (table[index] != null && table[index].get_key() != key){
                index++;
                if(index >= table.length){
                    index = 0;
                }
            }
            table[index] = new MyHashEntry(key, value);
        }
        public String get(int key){
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

    public static void main(String[] args){
        // Test code here
    }
}
