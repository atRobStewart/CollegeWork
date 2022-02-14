/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiclab5;

/**
 *
 * @author Rob
 */
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TruthTable tt = null;

        System.out.println("===== TruthTableGenerator =====");
        printHelp();

        while (true){
            String input = scanner.nextLine();

            switch (input){
                case "e":
                    System.exit(0);
                case "f":
                    if(tt != null) System.out.println(tt.getFormulas());
                    else System.out.println("No truthtable generated yet!");
                    break;
                case "h":
                    printHelp();
                    break;
                default:{
                    String[] vals = input.split(" ");
                    if(vals.length != 2) System.out.println("Invalid Input!");
                    else{
                        try{
                            int in = Integer.parseInt(vals[0]);
                            int out = Integer.parseInt(vals[1]);

                            if(in <= 0 || out <= 0)throw new Exception();

                            tt = new TruthTable(in, out);
                            System.out.println(tt);
                        }catch (Exception e){
                            System.out.println("Invalid Input!");
                        }
                    }
                }
            }
        }
    }

    private static void printHelp(){
        System.out.println(
                "Help:" +
                "\n[in] [out] -> new Truthtable with [in] Input variables and [out] Output variables!" +
                "\nf -> Print out DNF and KNF!" +
                "\nh -> Print out help!" +
                "\ne -> Exit program!");
    }

    private static class TruthTable{
        private boolean[][] inVals, outVals;

        /**
         * @param in  in variable count
         * @param out out variable count
         */
        public TruthTable(int in, int out){
            int powIn = (int)Math.pow(2, in);

            inVals = new boolean[in][powIn];
            outVals = new boolean[out][powIn];

            for(int i = 1; i <= in; i++){
                int range = (int) Math.pow(2, i);
                for(int j = 0; j < powIn; j++){
                    inVals[inVals.length-i][inVals[inVals.length-i].length-j-1] = (range / 2) > (j % range);
                }
            }

            for(int o = 1; o <= out; o++){
                for(int j = 0; j < powIn; j++){
                    outVals[outVals.length-o][j] = random.nextBoolean();
                }
            }
        }

        private String getFormulas(){

            System.out.println("DNF:");
            for(int o = 0; o < outVals.length; o++){
                System.out.println("o" + (o) + ":\t" + DNFof(o));
            }

            System.out.println("KNF:");
            for(int o = 0; o < outVals.length; o++){
                System.out.println("o" + (o) + ":\t" + KNFof(o));
            }

            return "";
        }

        private String DNFof(int o){
            String formula = "";
            for(int i = 0; i < outVals[0].length; i++){
                if(outVals[outVals.length-o-1][i]){
                    formula += " (";
                    for(int j = inVals.length-1; j >= 0; j--){
                        formula += " " + (inVals[inVals.length-j-1][i] ? "" : "¬") + "i" + j + " ∧";
                    }
                    formula = formula.substring(0, formula.length()-1);
                    formula += ") ∨";
                }
            }
            if(formula.length() > 0)formula = formula.substring(0, formula.length()-1);
            else formula = " false ";
            return formula;
        }

        private String KNFof(int o){
            String formula = "";
            for(int i = 0; i < outVals[0].length; i++){
                if(!outVals[outVals.length-o-1][i]){
                    formula += " (";
                    for(int j = inVals.length-1; j >= 0; j--){
                        formula += " " + (!inVals[inVals.length-j-1][i] ? "" : "¬") + "i" + j + " ∨";
                    }
                    formula = formula.substring(0, formula.length()-1);
                    formula += ") ∧";
                }
            }
            if(formula.length() > 0)formula = formula.substring(0, formula.length()-1);
            else formula = " false ";
            return formula;
        }

        public String toString(){
            String s = "\t|\t";
            for(int i = 0; i < inVals.length; i++){
                s += "i" + (inVals.length-i-1) + '\t';
            }
            s += "|\t";
            for(int o = 0; o < outVals.length; o++){
                s += "o" + (outVals.length-o-1) + '\t';
            }
            s += '\n';

            for(int y = 0; y < inVals[0].length; y++){
                s += y + "\t" + "|\t";
                for(int i = 0; i < inVals.length; i++)s += (inVals[i][y] ? "1" : "0") + "\t";
                s += "|" + "\t";
                for(int o = 0; o < outVals.length; o++)s += (outVals[o][y] ? "1" : "0") + "\t";
                s += "\n";
            }
            s = s.substring(0, s.length()-1);

            return s;
        }
    }
}