package MiniProjects;
import java.util.Random;
import java.util.Scanner;
class guessbro {
    private int randno;
    private int userno;
    int guessno = 1;
     guessbro() {
        Random randn = new Random();
        randno = randn.nextInt(1, 101);
    }

    public void takeUserInput() {
        System.out.println("Enter your Guess between 1 to 100");
        Scanner sc = new Scanner(System.in);
        userno = sc.nextInt();
    }

    public boolean Game() {
            if (userno == randno) {
                return true;
            } else if (userno > randno) {
                System.out.println("Too High!Please enter a lower number");
            } else if (userno < randno) {
                System.out.println("Too Low!Please enter a higher number");
            }
            guessno += 1;
        return false;
    }
}
public class GuessTheNo_2 {
    public static void main(String[] args) {
        guessbro gu=new guessbro();
        boolean b=false;
        while (!b) {
            gu.takeUserInput();
            b=gu.Game();
            if (b) {
                System.out.println("Congrats you guessed it right");
                System.out.println("You guessed it in "+gu.guessno);
                break;
            }
        }
    }
}
