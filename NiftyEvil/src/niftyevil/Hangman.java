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
        f = new File("C:\\Users\\User\\Desktop\\hangman\\dictionary.txt");
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
}
