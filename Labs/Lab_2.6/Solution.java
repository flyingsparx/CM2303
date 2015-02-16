import java.util.*;

class Stack<T> extends ArrayList<T>{
    public T pop(){
        T item = get(0);
        remove(0);
        return item;
    }
    public T peek(){
        return get(0);
    }
    public void push(T item){
        add(0, item);
    }
    public Boolean is_empty(){
        return size() == 0;
    }
}

class GreedyCoinsSolution{

    /*
     * Quick and dirty sorting (you could use another,
     * nicer algorithm!)
     */
    private int[] sort_descending(int[] array){
        Arrays.sort(array);
        int[] new_array = new int[array.length];
        for(int i = 0; i < array.length; i++){
            new_array[i] = array[array.length-1-i];
        }
        return new_array;
    }

    /*
     * Greedy coin algorithm.
     * Accepts an array of available coin values and a target to make with the coins.
     *
     * The method tries to find the largest coin that fits at each stage to
     * solve the problem with the fewest number of coins.
     *
     * Returns a list of coins used.
     */
    private ArrayList<Integer> greedy_coin_algorithm(int[] coins, int target){
        coins = sort_descending(coins);
        ArrayList<Integer> coins_used = new ArrayList<Integer>();
        
        while(target > 0){
            Boolean found = false;
            for(int i = 0; i < coins.length; i++){
                if(coins[i] <= target){ // If value of coin less than target
                    found = true;
                    target = target - coins[i]; // Reduce target by coin amount
                    coins_used.add(coins[i]); // Add coin to list
                    break;
                }
            }
            // If we didn't find a coin that fits, exit while loop.
            // Method is unsuccessful in this case.
            if(!found){break;}
        } 
        return coins_used;
    }

    /*
     * Test code in this constructor.
     */
    public GreedyCoinsSolution(){
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        ArrayList<Integer> coins_used = greedy_coin_algorithm(coins, 274);

        int[] coins2 = {100, 25, 10, 4};
        ArrayList<Integer> coins_used2 = greedy_coin_algorithm(coins2, 241);
    }
}

class TravellingSalesmanSolution{

    /*
     * Method to solve the travelling salesman problem using a greedy algorithm.
     * Accepts a matrix of distances between cities. In this example, cities are 
     * represented by their index position in the matrix.
     *
     * Start at one city, and travel to the next city with the smallest distance
     * until all cities have been visited. 
     *
     * Returns a stack of visited cities.
     */
    private Stack<Integer> travelling_salesman_algorithm(int[][] distances){
        // Create a stack of cities we've visited:
        Stack<Integer> visited_cities = new Stack<Integer>(); 
        // We're starting from city 0 (the first in the matrix):
        visited_cities.push(0);

        // Whilst there are still cities we haven't visited...
        while(visited_cities.size() < distances[0].length){
            // Initialise some helper variables:
            int smallest_distance = 1000000; // Init to vert large number so we can find the smallest distance
            int next_city = -1; 
            int current_city = visited_cities.peek(); // The current city is the one on top of the stack

            // Iterate through our set of cities and distances
            for(int i = 0; i < distances[current_city].length; i++){
                // Potential city and distance is the one we're looking at:
                int potential_city = i;
                int potential_distance = distances[current_city][potential_city];

                // If city has not been visited yet and if potential_distance smaller than the current smallest in this iteration... 
                if(potential_distance < smallest_distance && potential_distance > 0 && !visited_cities.contains(potential_city)){
                    smallest_distance = potential_distance;
                    next_city = potential_city;
                }
            }
            
            // next_city is now the city with the next smallest distance from current_city:
            visited_cities.push(next_city);
        }
        return visited_cities;
    }

    /*
     * Generate and print a distance matrix with given parameters.
     * This example has weightings based on distance, so matrix should be symmetrical.
     */
    private int[][] generate_distance_matrix(int num_cities, int min_distance, int max_distance){
        System.out.println("DISTANCE MATRIX:");
        int[][] distances = new int[num_cities][num_cities];
        Random rand = new Random();
        for(int i = 0; i < num_cities; i++){
            int current_city = i;
            for(int j = 0; j < num_cities; j++){
                int foreign_city = j;
                if(current_city != foreign_city && distances[current_city][foreign_city] == 0){  
                    int distance = rand.nextInt((max_distance-min_distance)+1)+min_distance;
                    distances[current_city][foreign_city] = distance;
                    distances[foreign_city][current_city] = distance;   
                }
                System.out.print(distances[current_city][foreign_city]+"\t");
            }
            System.out.println(); 
        }
        return distances;
    }

    /*
     * Test code in this constructor.
     */
    public TravellingSalesmanSolution(){
        int[][] distances = generate_distance_matrix(5, 1, 30);

        // Use greedy algorithm to get the city order (will be backwards as stack used): 
        Stack<Integer> visited_cities = travelling_salesman_algorithm(distances);
    }
}

public class Solution{
    public static void main(String[] args){
        new GreedyCoinsSolution();
        new TravellingSalesmanSolution();
    }
}
