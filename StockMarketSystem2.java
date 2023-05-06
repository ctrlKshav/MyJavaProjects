import java.util.*;

class Demat{
    //Many broking companies like Zerodha,Groww,AngelOne offer free Demat Accounts for Trading
    Stock AccHoldings[];
    String AccHolder;
    double AccBalance;
    int stockCount;

    Demat(String AccHolder){
        this.AccHolder=AccHolder;
        AccBalance=0;
        stockCount=0;
        AccHoldings=new Stock[20];
        System.out.println("Demat Account of "+AccHolder+" is created");
    }
    void depositMoney(double money){
        System.out.println(money+" INR deposited to your account");
        AccBalance+=money;
    }
    void withdrawMoney(double money){
        System.out.println(money+" INR withdrawn from your account");
        AccBalance-=money;
    }

    void viewPortfolio(){
        for(Stock sto:AccHoldings){
            if(sto==null)
                break;
            System.out.println(sto.stockName+" - "+sto.stockPrice);
        }
    }

    void buyStock(String userStock,int stockQnt){
        boolean stockAvailable=false;
        int stockCnt=0;
        for(int i=0;i<StockExchange.stockExchange.length;i++){//We iterate over the Stock Exchange's Array
            if(StockExchange.stockExchange[i]==null){
                System.out.println("Stock not available or not enough money");
                return;}
            if(StockExchange.stockExchange[i].stockName.equalsIgnoreCase(userStock)){//We check if any stock on the exchange matches the one with user's stock
                stockAvailable=true;
                stockCnt=i;
                break;
            }
        }
        if(AccBalance>StockExchange.stockExchange[stockCnt].stockPrice*stockQnt && stockAvailable) {//We check if we have enough balance in our account
            AccHoldings[stockCount] = StockExchange.stockExchange[stockCnt];
            stockCount++;
            AccBalance -= StockExchange.stockExchange[stockCnt].stockPrice*stockQnt;
            System.out.println(stockQnt+" "+userStock+" shares bought");
        }
    }
    void sellStock(String userstock){
        for (int i = 0; i < stockCount; i++) { // iterate over the AccHoldings array
            if (AccHoldings[i].stockName.equals(userstock)) { // check if the current stock is equal to the given stock
                AccBalance+=AccHoldings[i].stockPrice;
                System.out.println("Stock Sold");
                for (int j = i; j < stockCount - 1; j++) { // move all AccHoldings after the current index one position left
                    AccHoldings[j] = AccHoldings[j + 1];
                }
                AccHoldings[--stockCount] = null; // set the last element to null and decrement the size of the array
                break; // exit the method once the stock is removed
            }
            else{
                System.out.println("Stock Not available in your portfolio");
            }
        }
    }
}

class Stock{

    Scanner sc=new Scanner(System.in);
    String stockName,stockSector;
    double stockPrice,stockPERatio;
    long marketCap;

    Stock(){}
    public Stock(String stockName, String stockSector, double stockPrice, double stockPERatio, long marketCap) {
        this.stockName = stockName;
        this.stockSector = stockSector;
        this.stockPrice = stockPrice;
        this.stockPERatio = stockPERatio;
        this.marketCap = marketCap;
    }

    static void getStockDetails(Stock stoc){
        System.out.println("Name : "+stoc.stockName);
        System.out.println("Sector : "+stoc.stockSector);
        System.out.println("Price : "+stoc.stockPrice);
        System.out.println("PE Ratio : "+stoc.stockPERatio);
        System.out.println("Market Capitalisation : "+stoc.marketCap);
    }
}

class StockExchange{
    Scanner sc=new Scanner(System.in);
    public static Stock []stockExchange=new Stock[50];
    void listAllStocks(){
        for(Stock sto:stockExchange){
            if(sto==null)
                break;
            System.out.println(sto.stockName+" - "+sto.stockPrice);
        }
    }
    void listStockDetails(String userStockName){
        for(Stock sto:stockExchange){
            if(sto==null)
                break;
            if(sto.stockName.equalsIgnoreCase(userStockName))
                Stock.getStockDetails(sto);

        }
    }
    void IPONewStock(){
        for(int i=0;i<stockExchange.length;i++){
            if(stockExchange[i]==null) {
                System.out.println("\nWelcome to Your Stock's Initial Public Offering");
                System.out.println("Enter the stock Name");
                String sn=sc.nextLine();
                System.out.println("Enter the stock Sector");
                String ss=sc.nextLine();
                System.out.println("Enter the stock Price");
                double sp=sc.nextDouble();
                System.out.println("Enter the stock PE Ratio");
                double spe=sc.nextDouble();
                System.out.println("Enter the stock's Market Capitalisation");
                long mc=sc.nextLong();
                stockExchange[i]=new Stock(sn,ss,sp,spe,mc);
                System.out.println("Your Stock is now listed on the exchange");
                break;
            }
        }
    }
}

public class StockMarketSystem2 {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        Demat dmt=new Demat("Keshav");//Created a Demat Account where we will store our portfolio
        StockExchange BSE=new StockExchange();//Created a Stock Exchange Object BSE which stands for Bombay Stock Exchange where all the shares are listed

        //Below I have hard coded some stocks which already exist in the stock exchange
        BSE.stockExchange[0]=new Stock("HDFC","Banking",1600,17,891377);
        BSE.stockExchange[1]=new Stock("Kotak Mahindra","Banking",1800,11,347012);
        BSE.stockExchange[2]=new Stock("Asian Paints","Paint",2841,9,272546);
        BSE.stockExchange[3]=new Stock("Berger Paints","Paint",580,7,21977);
        BSE.stockExchange[4]=new Stock("TCS","IT Services",3385,12,1238899);
        BSE.stockExchange[5]=new Stock("Infosys","IT Services",1496,10,626510);
        BSE.stockExchange[6]=new Stock("Bharti Airtel","Telecommunication",750,14,425246);
        BSE.stockExchange[7]=new Stock("Sun Pharma"," Pharmaceuticals ",1000,15,230168);
        BSE.stockExchange[8]=new Stock("Maruti Suzuki","Automobile",8764,16,312000);
        BSE.stockExchange[9]=new Stock("UltraTech Cement","Cement",7500,114,190000);
        BSE.stockExchange[10]=new Stock("Adani Enterprises","Trading",1600,7,178330);
        BSE.listAllStocks();

        /*Now We will IPO some new stocks on the exchange
        For Reference
        Nestle-FMCG-378-75-182489
        IRCTC-Travel-600-52-48540
        */

        //BSE.IPONewStock();
        BSE.listStockDetails("Kotak Mahindra");

        //We can now Safely Trade shares from our demat account
        //Happy Investing
        boolean loop=true;
        while(loop) {
            System.out.println("Hello "+dmt.AccHolder+"\nWelcome to Your Demat Account");
            System.out.println("What's on your mind!");
            System.out.println("1. Check your Account balance");
            System.out.println("2. Deposit money to your account");
            System.out.println("3. Withdraw money from your account");
            System.out.println("4. View Your Portfolio");
            System.out.println("5. Buy some Shares");
            System.out.println("6. Sell some Shares");
            System.out.println("7. Exit the Program");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Your Account balance is "+dmt.AccBalance);
                    break;
                case 2:
                    System.out.println("Enter the Amount you want to deposit");
                    double amt=sc.nextDouble();
                    dmt.depositMoney(amt);
                    break;
                case 3:
                    System.out.println("Enter the Amount you want to Withdraw");
                    amt = sc.nextDouble();
                    dmt.withdrawMoney(amt);
                    break;
                case 4:
                    dmt.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Enter the Name of the stock you want to buy");
                    sc.nextLine();
                    String stock=sc.nextLine();
                    System.out.println("Enter the Quantity of the stock");
                    int qnt=sc.nextInt();
                    dmt.buyStock(stock,qnt);
                    break;
                case 6:
                    System.out.println("Enter the Name of the stock you want to sell");
                    sc.nextLine();
                    stock=sc.nextLine();
                    dmt.sellStock(stock);
                    break;
                case 7:
                    loop=false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
