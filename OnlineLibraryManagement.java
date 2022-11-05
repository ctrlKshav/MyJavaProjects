package MiniProjects;
class library{
    String [] books;
    int noofbooks;
    library(){
        this.books=new String[10];
        this.noofbooks=0;
    }
    public void addBook(String book){
        this.books[noofbooks]=book;
        System.out.println(book+" has been added");
        noofbooks++;
    }
    public void showAvailableBooks(){
        for(String ele:books){
            if(ele==null){
                continue;
            }
            System.out.println(ele);
        }
    }
    public void issueBook(String wantbook){
        for(int i=0;i<this.books.length;i++){
            if(wantbook==books[i]){
                System.out.println("The book has been issued");
                String issuedBook=books[i];
                books[i]=null;
                return;
            }
        }
        System.out.println("Sorry!This Book isn't available");
    }
    public void returnBook(String returnbook){
        addBook(returnbook);
    }

}

public class OnlineLibraryManagement {
    public static void main(String[] args) {
        library central=new library();
        central.addBook("Sapiens");
        central.addBook("Homo Deus");
        central.addBook("The Power of Subconscious Mind");
        central.addBook("The Richest Person of Babylon");
        central.addBook("Ikigai");
        central.addBook("Think and Grow rich");
        System.out.println(central.noofbooks);
//        central.showAvailableBooks();
        central.issueBook("Ikigai");
        System.out.println(central.noofbooks);
        central.showAvailableBooks();

    }
}
