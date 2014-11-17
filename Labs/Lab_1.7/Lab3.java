import java.util.*;

public class Lab3{
    
    public static int get_max(int[] a){
        int max = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] > max){max = a[i];} 
        }
        return max;
    }

    public static int[] get_random(int l, int m, int n){
        int[] a = new int[l];
        Random r = new Random();
        for(int i = 0; i < l; i++){
            a[i] = r.nextInt(n-m)+m;
        }
        return a;
    }

    public static int[] selection_sort (int[] a){
        for(int i = 0; i < a.length; i++){
            int smallest_index = i;
            for(int j = i; j < a.length; j++){
                if(a[j] < a[smallest_index]){
                    smallest_index = j;
                }    
            }
            int temp = a[i];
            a[i] = a[smallest_index];
            a[smallest_index] = temp;
        }   
        return a;        
    }

    public static void main(String [] args){
    
        // Place code to run program here
        
    }

}
