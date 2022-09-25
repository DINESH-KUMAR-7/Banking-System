package supportingFunctions;
import java.math.BigDecimal;
import java.util.*;


public class DebitCard {
    final Random rd = new Random();
    
    private BigDecimal balance;
    private String name;
    private int pwd;
    private String card = "";
    
    public DebitCard(String name,BigDecimal bt){
        this.name = name;
        card = generateCard();
        pwd = rd.nextInt(9999);
        balance = bt;
    }

    private String generateCard(){
        String t = "";
        for(int i=0;i<10;i++){
            int c = rd.nextInt(9);
            t += c;
            if((i+1)==3||(i+1)==6){t += " - ";}
        }
        return t;
    }

    public void showCard(){
        String template = "-------------------------\n  "+
        card+"\n  "+name+"   "+pwd+
        "\n  balance: "+balance+
        "\n-------------------------";
        System.out.println(template);
    }
}
