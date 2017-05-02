/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NiftyEvil;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class NiftyEvil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[]args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        int play = 1;
        do{
            System.out.print("Please input length of word : ");
            int numberOfWord = Integer.parseInt(input.nextLine());
            Hangman h = new Hangman(numberOfWord);
            if(!h.haveWords())continue;
            System.out.print("Please input number of guess : ");
            int guess = Integer.parseInt(input.nextLine());
            System.out.print("Do you want to see total remainning of words (1 = yes, 0 = no) : ");
            int showremain = Integer.parseInt(input.nextLine());
            for(int i = 1 ; i <= guess ; i++){
                System.out.print("Input the letter No" + (i+1) + " : ");
                String letter = input.nextLine();
                h.cluster(letter);
                h.updateNewGroup();
                h.printWordProgress();
                if(h.isCorrect())break;
                if(showremain == 1) h.showData();
            }
            if(h.isCorrect()){
                System.out.println("You are correct !!!");
            }
            else System.out.println("You are worng!!");
            System.out.println("Do you want to play again ? (1 = yes, 0 = no) : ");
            play = Integer.parseInt(input.nextLine());
            System.out.println("-----------------------------------------------------------------");
        }while(play == 1);
       
       
       /* Hangman n = new Hangman(4);
        n.cluster("e");
        n.showCluster();
        n.updateNewGroup();
        n.printWordProgress();*/
    }
    
}
