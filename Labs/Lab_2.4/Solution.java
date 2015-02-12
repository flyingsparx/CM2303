import java.util.*;

class Item{
    private int weight, profit;
    public Item(int weight, int profit){
        this.weight = weight;
        this.profit = profit;
    }
    public int get_profit(){
        return profit;
    }
    public int get_weight(){
        return weight;
    }
    public Double get_profit_weight_ratio(){
        return (double)((double)profit/(double)weight);
    }
}

class KnapsackException extends Exception{
    public KnapsackException(String error){
        super(error);
    }
}

class Knapsack{
    private int capacity;
    private ArrayList<Item> items;
    public Knapsack(int capacity){
        this.capacity = capacity;
        items = new ArrayList<Item>();
    }
    public int get_weight(){
        int weight = 0;
        for(Item item : items){weight += item.get_weight();}
        return weight;
    }
    public int get_profit(){
        int profit = 0;
        for(Item item : items){profit += item.get_profit();}
        return profit;
    }
    public Item[] get_items(){
        return items.toArray(new Item[items.size()]);
    }
    private Boolean is_broken(){
        return get_weight() > capacity;
    }
    public void add_item(Item item){
        try{
            items.add(item);
            if(is_broken()){
                throw new KnapsackException("Knapsack is broken");
            }
        }
        catch(KnapsackException e){
            System.err.println(e);
        }
    }        
}

public class Solution{
    private Knapsack branch_and_bound_lower(int capacity, ArrayList<Item> items){
        // Sort items by descending profit/weight ratio:
        Collections.sort(items, new Comparator<Item>(){
            public int compare(Item current, Item next){
                return next.get_profit_weight_ratio().compareTo(current.get_profit_weight_ratio());
            }
        });        
        
        Knapsack k = new Knapsack(capacity);
        for(int i = 0; i < items.size(); i++){
            // If we're still under capacity...
            if(k.get_weight() + items.get(i).get_weight() <= capacity){                        
                k.add_item(items.get(i));
            }
            // Otherwise, we can't add anymore
            else{break;}
        }
        return k;
    }

    private Knapsack branch_and_bound_lower_improved(int capacity, ArrayList<Item> items){
        // Sort items by descending profit/weight ratio:
        Collections.sort(items, new Comparator<Item>(){
            public int compare(Item current, Item next){
                return next.get_profit_weight_ratio().compareTo(current.get_profit_weight_ratio());
            }
        });        

        // Keep adding items until break item reached
        Knapsack k = new Knapsack(capacity);
        for(int i = 0; i < items.size(); i++){
            // If we're still under capacity...
            if(k.get_weight() + items.get(i).get_weight() <= capacity){
                k.add_item(items.get(i));
            }
            // Otherwise, keep looking along list to find an item that will fit
            else{
                for(int j = i; j < items.size(); j++){
                    if(k.get_weight() + items.get(j).get_weight() <= capacity){
                        k.add_item(items.get(j));    
                    }
                    else{break;}
                break;
                }
            }
        } 
        return k;
    }

    private Knapsack branch_and_bound_upper(int capacity, ArrayList<Item> items){
        // Sort items by descending profit/weight ratio:
        Collections.sort(items, new Comparator<Item>(){
            public int compare(Item current, Item next){
                return next.get_profit_weight_ratio().compareTo(current.get_profit_weight_ratio());
            }
        });       

        Knapsack k =  new Knapsack(capacity);
        for(int i = 0; i < items.size(); i++){
            // If we're still under capacity...
            if(k.get_weight() + items.get(i).get_weight() <= capacity){
                k.add_item(items.get(i));
            }
            // Otherwise, just add a proportion of the next-best item
            else{
                // Calculate the propoortion of weight/profit we're allowed to add
                int remaining_allowed_weight = capacity - k.get_weight();
                double proportion = (double)remaining_allowed_weight/(double)items.get(i).get_weight();
                // Casting to int rounds the double down
                int new_item_weight = (int)(proportion*items.get(i).get_weight());
                int new_item_profit = (int)(proportion*items.get(i).get_profit());
                k.add_item(new Item(new_item_weight, new_item_profit));
                break;
            }
        }
        return k;
    }

    public Solution(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(42, 92));
        items.add(new Item(45, 57));
        items.add(new Item(29, 49));
        items.add(new Item(50, 50));
        items.add(new Item(53, 60));
        items.add(new Item(80, 40));
        items.add(new Item(38, 43));
        items.add(new Item(63, 67));
        items.add(new Item(85, 84));
        items.add(new Item(89, 87));
        items.add(new Item(82, 72));

        Knapsack k1 = branch_and_bound_lower(165, items);
        Knapsack k2 = branch_and_bound_lower_improved(165, items);
        Knapsack k3 = branch_and_bound_upper(165, items);

        System.out.println(k1.get_weight());
        System.out.println(k1.get_profit());
        System.out.println(k2.get_weight());
        System.out.println(k2.get_profit());
        System.out.println(k3.get_weight());
        System.out.println(k3.get_profit());

    }
    public static void main(String[] args){
        new Solution();
    }
}
