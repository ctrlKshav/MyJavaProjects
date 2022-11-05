import java.util.Scanner;
public class TaxCalc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your income ");
        float   income=sc.nextFloat();
        float   tax=0;
        if(income<250000f){
            System.out.println("You have no tax to pay");

        }
        else if(income>250000f && income<500000f)
        {
            tax = tax + (income-250000)*0.05f;
        }
        else if(income>500000f && income<1000000f)
        {
            tax = tax + 250000f*0.05f + (income-500000f)*0.1f;
        }
        else if(income>1000000f)
        {
            tax = tax + 250000f*0.05f + 500000f*0.1f + (income-1000000f)*0.2f;
        }
        System.out.printf("Your income is %f and your tax payable is %f",income,tax);
    }
}
