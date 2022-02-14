package logiclab5;
/**
 *
 * @author Rob
 */
public class LogicLab5 {

    public static void main(String[] args) {
        //  negate = !, A = &&, V = ||, implication = ?, equivlence = ?
    }
    
    
    /**
     * 
     * @param a
     * @return 
     */
    public static boolean Negation(boolean a){
        return !a;
    }
    
    public static boolean Conjunction(boolean a, boolean b){
        return (a && b); 
    }
    
    public static boolean Disjunction(boolean a, boolean b){
        return (a || b);
    }
    
    public static boolean Implication(boolean a, boolean b){
        return !(a == true && b == false);
    }
    
    public static boolean Equivalence(boolean a, boolean b){
        return (a == b);
    }
    
}
