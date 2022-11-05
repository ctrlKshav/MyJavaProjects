package MiniProjects;
import java.util.Scanner;
import java.util.Random;
public class RockPaperSci {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random rnum=new Random();
        int score=0;
        for(int i=0;i<5;i++) {
            int comp= rnum.nextInt(1,4);
            System.out.println("Enter 1 for Choosing Rock\nEnter 2 for Choosing Paper\nEnter 3 for Choosing Scissors");
            System.out.println("Press 0 to exit");
            int user=sc.nextInt();
            System.out.printf("Computer Chose %d\n", comp);
            if(user==0){
                break;
            }
            if (user == 1 && comp == 2) {
                System.out.println("You Chose Rock and Computer Chose Paper , you lost");
                score-=1;
            } else if (user == 1 && comp == 3) {
                System.out.println("You chose Rock and computer chose scissors, you won!");
                score+=1;
            } else if (user == 2 && comp == 3) {
                System.out.println("You Chose Paper and Computer Chose Scissors , you lost");
                score-=1;
            } else if (user == 2 && comp == 1) {
                System.out.println("You chose Paper and computer chose Rock, you won");
                score+=1;
            } else if (user == 3 && comp == 2) {
                System.out.println("You Chose Scissors and Computer Chose Paper , you won");
                score+=1;
            } else if (user == 3 && comp == 1) {
                System.out.println("You chose Scissors and computer chose Rock, you lost!");
                score-=1;
            } else {
                System.out.println("Match Tied, play again");
            }
        }
        System.out.println("Your score is "+score);
    }
}
