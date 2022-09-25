import java.math.BigDecimal;
import java.util.Scanner;
import supportingFunctions.*;
import java.lang.Character;

public class BankInterface implements ContactDetails{
    private String accNo;
    private String name;
    private int id ;
    private String dob;
    private BigDecimal money;
    private BigDecimal loanAmount;
    private String pin;
    private DebitCard debCard; //Bank HAS-A debit Card
    private TranscationManager trans; //Bank HAS-A Transcation option
    private boolean isloaner = false;
    Scanner sc = new Scanner(System.in);

    public BankInterface(String acc,String n,int id,String dob,BigDecimal m,String pin){
        accNo = acc;
        name = n;
        this.id = id;
        this.dob = dob;
        money = m;
        this.pin = pin;
        loanAmount = new BigDecimal(0);
        trans = new TranscationManager();
    }
    
    /* 
     *     showWallet(): used to see the money int our account
     *    showMyMoney(): returns the amount in bigdecimal
     *       putMoney(): deposit amount
     *           loan(): provides loan to customer
     *      takeMoney(): withdraws amount
     *      contactUS(): used to conatact the bank via 3 ways
     *    userDetails(): provides necessary details about user
     *      debitCard(): generate a debit card template
     * transactAmount(): transaction between two objects
    */

    private String showWallet(){
        String t = "wallet:- "+ money;
        return t;
    }

    public BigDecimal showMyMoney(){
        return money;
    }

    public String putMoney(BigDecimal t){
        money = money.add(t);
        return showWallet()+" Money added.";
    }

    public int loan(){
        System.out.println("principle amount:- ");
        int p = sc.nextInt();
        System.out.println("Tenure of deposit:-");
        int t = sc.nextInt();
        int rateOfInterest = 6;

        // to find interest amount (Principle * rate_of_interest * tenture)/100
        BigDecimal topay = new BigDecimal(((p*t*rateOfInterest)/100)+p);
        loanAmount = loanAmount.add(topay);
        return p;
    }

    public String takeMoney(BigDecimal mt) throws SecurityAlert{                                
        /*   bigdecimal compareTo method
         *  0 -> equal
         *  1 -> value is greater than parameter
         * -1 -> value is less than parameter
        */
     
        switch(money.compareTo(mt)){
            case 1:
                money = money.subtract(mt);
                return showWallet()+", thank you for using our service.";
            case 0:
                System.out.println("your account will be dried out! (Y/n)");
                //asking user whether it is okay to dry is account.
                char c = sc.nextLine().charAt(0);
                if(c == 'y' || c== 'Y'){
                    money = money.subtract(mt);
                    return showWallet()+" Account Dried.";
                }
                else{
                    throw new SecurityAlert("ERR: Invlaid Input");
                }
            case -1:
                //not enough money suggesting user loan option
                System.out.print("Not enough money.\nNeed money, want any loan? (Y/n)"+" @6% interest");
                char ch = sc.nextLine().charAt(0);
                if(ch == 'y'|| ch=='Y'){
                    isloaner = true;
                    money = money.add(new BigDecimal(loan()));
                    money = money.subtract(mt);
                    return showWallet()+", thank you for using our service.";
                }
                else{return "deposit more.";}
        }
        throw new SecurityAlert("ERR: Operation failed!");
    }



    public String contactUS(String str) throws SecurityAlert{
        switch(str){
            case helpline:
                return "thanking for calling, How can we help you?";
            case address:
                return "welcome "+name+" ( Easter Egg )";
            case email:
                return "hi there, "+name+"\nreceived your problem we'll look into it.";
        }
        throw new SecurityAlert("Invalid contact detail.");
    }

    public void userDetails(){
        String t = Character.toUpperCase(name.charAt(0)) + name.substring(1) ;
        String template = t+"\n"+"Account-number: "+accNo+"\n"+"ID: "+id+"\n"+"D-O-B: "+dob+"\n"+"Balance: "+money+"\n"+"Pin code: "+pin;
        if(isloaner){template += "loan amount: "+loanAmount;}
        System.out.println(template);
    }

    public void debitCard(){
        debCard.showCard();
    }

    public String transactAmount(BankInterface receiver,BigDecimal amt,String p) throws SecurityAlert{
        if(!pin.equals(p)){return "pincode is wrong.";}
        if(trans.tranferMoney(money,amt)){
            takeMoney(amt);
            if(trans.recevieMoney(receiver.showMyMoney(),amt)){
                receiver.putMoney(amt);
                return "Successfully completed your transcation";
            }
        }
        else{
            System.out.println("low balance, need loan (Y/n).");
            char ch = sc.nextLine().charAt(0);
            if(ch == 'y'|| ch=='Y'){
                isloaner = true;
                money = money.add(new BigDecimal(loan()));
                return transactAmount(receiver, amt, p);
            }
            else{return "Failed due to insufficient amount.";}
        }
        throw new SecurityAlert("Transcation failed.");
    }
}
