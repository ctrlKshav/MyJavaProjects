package MiniProjects;
import java.lang.reflect.Array;
import java.util.*;
class management {
    int number;
    ArrayList<String> library;
    management(){
        number=0;
        library=new ArrayList<>();
    }
    public void getLibraryDetails(){
        System.out.println("The number of books available are "+number);
        for(String ele:library){
            if(ele==null){
                continue;
            }
            System.out.println(ele);
        }
    }
    public void addBook(String name,String authorname){
        library.add(name);
//        System.out.println(name+" is added to the library");
        number=number +1;
    }
    public void issueBook(String bookname,String username,int date){
        for(String elem:library){
            if(bookname==elem){
                library.remove(bookname);
                System.out.println(bookname+" issued to "+username+" on "+date);
                number=number -1;
                return;
            }
        }
        System.out.println("Sorry this Book isn't available in our library");
    }
    public void returnBook(String bookname,String username){
        library.add(bookname);
        System.out.println(bookname+" returned to the library by "+username);
        number=number +1;
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        management lib=new management();
        lib.addBook("Ikigai","author1");
        lib.addBook("Sapiens","author2");
        lib.addBook("Homo Deus","author3");
        lib.addBook("Mint your Money","author4");
        lib.addBook("The Power of Subconscious Mind","author5");
        lib.addBook("Think and Grow Rich","author6");
        lib.getLibraryDetails();
        lib.issueBook("Homo Deus","Keshav Mittal",05112022);
        lib.getLibraryDetails();
        lib.returnBook("Homo Deus","Keshav Mittal");

    }
}
