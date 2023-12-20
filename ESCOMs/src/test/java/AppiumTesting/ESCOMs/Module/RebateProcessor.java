package AppiumTesting.ESCOMs.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
public class RebateProcessor {
    
    private static double EC;
    private static double FC;

    public RebateProcessor(double EC, double FC) {
        this.EC = EC;
        this.FC = FC;
    }
    
    private static final Map<String, BiFunction<Double, String, Double>> rebateCalculations = new HashMap<>();

    static {
        rebateCalculations.put("YNN", (consumption, consumStatus) -> consumption * 0.2);
        rebateCalculations.put("NNY", (consumption, consumStatus) -> consumption * 0.02);
        rebateCalculations.put("LT-R", (consumption, consumStatus) -> {
            if("DL  : Door Lock".equals(consumStatus)) {
                return 0.0;
            }
            return consumption * 0.3; // Or any other calculation based on consumStatus
        });
        rebateCalculations.put("MSME",(consumption, consumStatus)-> consumption * 0.5);
        
        rebateCalculations.put("FL", (consumption, consumStatus) -> {
            double FLvalue = 0.0;
        	if (consumption <= 200) {
                return FLvalue = (EC + FC);
            } 
        	else if (200 <= consumption && consumption <= 280) {
                return FLvalue = (EC + FC) + ((consumption - 200 ) * 0.10 );
            }  
            else  if ( 280<=consumption && consumption <= 400 ) {
            	System.out.println("FLvalue 280-400 " + ((EC + FC) -((80 * 0.10) - ((consumption - 280 ) * 1.82 ) )));
               return FLvalue = ((EC + FC) )- (80 * 0.10)- (consumption - 280 ) *1.82 ;
            }
            else  if (consumption > 400) {
                return FLvalue =( (EC + FC)) - (80 * 0.10) - (120 * 1.82) - ((consumption - 400 )* 7.00 );
            }
           return FLvalue ;
        });
    }

    public static double calculateRebate(String rebateCode, double consumption, String ConsumStatus) {
        return rebateCalculations.getOrDefault(rebateCode, (c, s) -> 0.0).apply(consumption, ConsumStatus);
    }

    public static double calculateTotalRebates(List<String> rebateCodes, double consumption, String ConsumStatus) {
        double totalRebates = 0;

        for (String code : rebateCodes) {
            totalRebates += calculateRebate(code, consumption, ConsumStatus);
        }
        
        return totalRebates;
    }    
}
