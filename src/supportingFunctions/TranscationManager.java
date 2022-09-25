package supportingFunctions;
import java.math.BigDecimal;

public class TranscationManager {
    public boolean tranferMoney(BigDecimal total, BigDecimal amt){
        if(total.compareTo(amt)==1 || total.compareTo(amt)==0){
            total = total.subtract(amt);
            return true;
        }
        return false;
    }
    public boolean recevieMoney(BigDecimal total, BigDecimal amt){
        total = total.add(amt);
        return true;
    }
}
