import java.math.BigDecimal;
import supportingFunctions.SecurityAlert;

public class Customer {
    public static void main(String[] args) throws SecurityAlert{
        
        BankInterface cust1 = new BankInterface(
            "123-456",
            "dinesh",
            12,
            "07-09-2002",
            new BigDecimal(500.0),
            "1234"
        );

        BankInterface cust2 = new BankInterface(
            "128-456",
            "kumar",
            13,
            "20-06-2001",
            new BigDecimal(1000.0),
            "2345"
        );

        cust1.userDetails();
        cust2.userDetails();
        /* 
            Functions which can bank-interface perform.
            * obj.showWallet()
            * obj.showMyMoney()
            * obj.putMoney(BigDecimal)
            * obj.loan()
            * obj.takeMoney(BigDecimal)
            * obj.contactUS(String)
            * obj.userDetails()
            * obj.debitCard()
            * obj.transactAmount(Bank, BigDecimal, String)
        */
    }
}
