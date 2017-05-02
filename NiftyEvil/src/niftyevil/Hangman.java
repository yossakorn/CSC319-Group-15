/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package niftyevil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 *
 * @author User
 */
public class Hangman {
    private TreeSet<String> words; 
    private TreeMap<String,TreeSet<String>> cluster; //group cluster
    private TreeSet<String> keys;//pattern ----
    private String myLetter; // letter that we input
    private File f; //file dic
    private int wordLength; //length of word
    private Scanner input;
    private String wordprogress;//----
    
    public Hangman(int wordLength) throws FileNotFoundException{
        this.wordLength = wordLength;//assign value
        keys = new TreeSet<String>(); //empty set
        words = new TreeSet<String>(); //
        cluster = new TreeMap<String,TreeSet<String>>();//cluster 
        f = new File("C:\\Users\\Window10\\Desktop\\NiftyEvil\\dictionary.txt");
        input = new Scanner(f);
        while(input.hasNext()){ //loop import value from dic to word
            String wordtemp = input.nextLine();
            if(wordtemp.length() != wordLength) continue;
            words.add(wordtemp);
        }
        wordprogress = ""; //created progress
        for(int i = 1 ; i <= wordLength ; i++){
            wordprogress += "-";
        }
    }
    
    public boolean haveWords(){ //check the word that have in dic file
        return words.size() != 0;
    } 
    
    public void showData(){ //show word 
        for(String word : words){
            System.out.print(word + " ");
        }
        System.out.println("");
    }
    
    public void cluster(String letter){
        myLetter = letter;
        generateKey();
        for(String key : keys){
            cluster.put(key, new TreeSet<String>());
        }
        for(String word : words){
            if(word.length() != wordLength) continue;
            for(String key : keys){
                if(compareWord(key,word)){
                    cluster.get(key).add(word);
                }
            }
        }
    }
    
    public void showCluster(){
        if(cluster == null) return;
        for(String key :keys){
            System.out.print(key + " : ");
            for(String word : cluster.get(key)){
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
    
    public void updateNewGroup(){
        int max = Integer.MIN_VALUE;
        String maxkey = ""; 
        for(String key : keys){
            int n = cluster.get(key).size();
            if(n > max){
                max = n;
                maxkey = key;
            }
        }
        updateWordProgess(maxkey);
        words = cluster.get(maxkey);
        myLetter = null;
        cluster.clear();
    }
    
    public void printWordProgress(){
        System.out.println(wordprogress);
    }
    
    public boolean isCorrect(){
        return checkWord();
    }
    
    private boolean checkWord(){
        boolean correct = true;
       for(int i = 0 ; i < wordLength ; i++){
           if(wordprogress.charAt(i) == '-'){
               correct = false;
               break;
           }
       }
       return correct;
    }
    
    private void updateWordProgess(String key){
        ArrayList<Integer> indexs = new ArrayList<Integer>();
        for(int i = 0 ; i < key.length() ; i++){
           if(key.charAt(i) == myLetter.charAt(0))indexs.add(i); 
        }
         for(int index : indexs){
               wordprogress = wordprogress.substring(0,index) + myLetter + wordprogress.substring(index+1);
         }
         
    }
    
    private boolean compareWord(String key, String word){
        boolean correct = true;
        ArrayList<Integer> indexs = new ArrayList<Integer>();
        ArrayList<Integer> wordIndexs = new ArrayList<Integer>();
        for(int i = 0 ; i < key.length() ; i++){
            if(key.charAt(i) == myLetter.charAt(0))indexs.add(i);
             if(word.charAt(i) == myLetter.charAt(0))indexs.add(i);    
        }
        if(indexs.size() == 0 && wordIndexs.size() > 0) correct = false;
        if(correct == true){
            for(int index : indexs){
                if(key.charAt(index) != word.charAt(index)){
                    correct = false;
                    break;
                }
            }
        }
        return correct;
    }
    
    private void generateKey(){
        String key = "";
        subset(0, wordLength, key);
    }
    
    private void subset(int position, int length,String mySubset){
        if(position == length){
            keys.add(mySubset);
            return;
        }
        
        mySubset = mySubset + "-";
        subset(position+1, length, mySubset);
        mySubset = mySubset.substring(0, mySubset.length()-1);
        mySubset = mySubset + myLetter;
        subset(position+1, length, mySubset);
        
    }

}

    

