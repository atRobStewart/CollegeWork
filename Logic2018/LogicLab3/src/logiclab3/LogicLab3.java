package logiclab3;
/**
 *
 * @author rob
 */
import java.util.Random;

public class LogicLab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random rng = new Random();
        
        double startPointX, startPointY, cornerX1, cornerY1, cornerX2, cornerY2, cornerX3, cornerY3;
        
        startPointX = 0;
        startPointY = 0;
        cornerX1 = -100;
        cornerY1 = -50;
        cornerX2 = 0;
        cornerY2 = 100;
        cornerX3 = 100;
        cornerY3 = -50;
                
        for (int i = 0; i < 1000; i++){
             int randomNumber = rng.nextInt(3) + 1;
             double vectorX;
             double vectorY;
             switch (randomNumber){
                 case 1:  
                        vectorX = cornerX1 - startPointX;
                        vectorY = cornerY1 - startPointY;
                        startPointX = startPointX + (vectorX / 2);
                        startPointY = startPointY + (vectorY / 2);
                 break;
                 
                 case 2:
                        vectorX = cornerX2 - startPointX;
                        vectorY = cornerY2 - startPointY;
                        startPointX = startPointX + (vectorX / 2);
                        startPointY = startPointY + (vectorY / 2);
                 break;
                 
                 case 3:
                        vectorX = cornerX3 - startPointX;
                        vectorY = cornerY3 - startPointY;
                        startPointX = startPointX + (vectorX / 2);
                        startPointY = startPointY + (vectorY / 2);
                 break;     
            }
        }    
    }  
}
