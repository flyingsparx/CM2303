/*
 * The MyString class performs the functions for most of the questions in 
 * Lab 1.6. Use the existing code to help you write the last few methods.
 */

class MyString{

    private char[] myString;

    public MyString(String s){
        myString = s.toCharArray();
    }
    
    public MyString(){
        myString = new char[0];
    }

    private String get_string(){
        return new String(myString);
    }

    public void print(){
        System.out.println(get_string());
    }

    public Boolean equals(String s){
        if(s.equals(get_string())){
            return true;
        }
        return false;
    }

    public Boolean equals(String s, Boolean ignore_case){
        if(ignore_case == false){
            return equals(s);
        }   
        if(s.equalsIgnoreCase(get_string())){
            return true;
        }
        return false;
    }

    public Boolean equals(MyString s){
        if(s.equals(get_string())){
            return true;
        }
        return false;
    }

    public int get_length(){
        return myString.length;
    }

    public int index_of(char c){
        for(int i = 0; i < myString.length; i++){
            if(c == myString[i]){
                return i;
            }
        }
        return -1;
    }

    public int index_of(String s){
        char[] s_array = s.toCharArray();
        for(int i = 0; i < myString.length-s_array.length; i++){
            Boolean match = false;
            for(int j = 0; j < s_array.length; j++){
                if(myString[i+j] == s_array[j]){
                    match = true;
                }
                else{match=false;}
            }
            if(match==true){return i;}
        }
        return -1;
    }

    public Boolean contains(String s){
        if(index_of(s) > -1){
            return true;
        }
        return false;
    }

    public char get_character(int i){
        return myString[i];
    }

    public void insert(int i, char c){
        char[] new_array = new char[myString.length+1];
        for(int j = 0; j < new_array.length; j++){
            if(j < i){
                new_array[j] = myString[j];
            }   
            if(j == i){
                new_array[j] = c;
            }
            if(j > i){
                new_array[j] = myString[j-1];
            }
        }
        myString = new_array;
    }

    public void delete(int i){
        char[] new_array = new char[myString.length-1];
        for(int j = 0; j < new_array.length; j++){
            if(j < i){
                new_array[j] = myString[j];
            }
            else{
                new_array[j] = myString[j+1];
            }
        }
        myString = new_array;
    }

    public void delete(char c){
        delete(index_of(c));
    }
}

public class Lab2{
    public static void main(String [] args){

        // Place test code here

    }
}
