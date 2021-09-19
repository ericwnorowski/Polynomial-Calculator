
import java.util.*;
/**
 * PolyCalc Class that holds main method that implements a text interface 
 * program should be executed when the program is run with java PolyCalc
 * @author Eric Wnorowski
 * @version 12.2.20
 */
public class PolyCalc
{
    /**
     * Main method: Prints a welcome message, asks the user for their first polynomial (must be in proper format),
     * asks the user for a valid operator, asks the user for their second valid polynomial,execute operation and 
     * display the result (if the operation is division and the two polynomials are not divisible, an error message 
     * should be printed)
     * 
     * user can enter quit at any time to exit the program
     *
     */
    public static void main(String[] args){
        startCalc();
        int counter = 1;
        while(true){   
            String s = "\n-----Calculation #" + counter +"------\n";
            System.out.print(s);
            Poly a = userPoly();
            if (a == null){
                System.out.println("User Quit");
                break;
            }
            String op = operator();
            if (op == null){
                System.out.println("User Quit");
                break;
            }
            Poly b = userPoly();
            if (b == null){
                System.out.println("User Quit");
                break;
            }

            Poly rpoly = new Poly();

            if(op.equals("+")){
                rpoly = a.add(b);
            }else if(op.equals("-")){
                rpoly = a.subtract(b);
            }else if(op.equals("*")){
                rpoly = a.multiply(b);
            }else{
                rpoly = a.divide(b);
            }
            String r = "\n" + a + " " + op + " " + b + " = " + rpoly;
            System.out.println(r);
            counter++;
        }
    }

    /**
     * startCalc method initializes the program, prints welcome message and gives an example
     * to the user on the proper polynomial input and operator symbols
     */
    public static void startCalc(){
        System.out.println(
            "/*** Welcome to Eric Wnorowski's polynomial calculator! ***/" + "\n" + 
            "Instructions:" + "\n" +
            "Polynomials are specified using space-seperated pairs of coefficient and exponent" + "\n" + 
            "  - Polynomials are specified using space-separated pairs of coefficient and exponent." + "\n" + 
            "   E.g., '2.5x^2 - 1' should be input as '2.5 2 -1 0'." + "\n" + "  - 'quit' can be used at any time to exit the program."); 

    }

    /**
     * UserPoly will be called in the main method to ask the user for either the first or second polynomial
     * Ensures that it is in proper format and will be executed 
     * 
     * @return Poly the polynomial the user inputs
     */
    public static Poly userPoly(){
        System.out.println("Polynomial: ");
        Poly p = new Poly();
        
        Scanner keybScanner = new Scanner(System.in); // scanner for System.in input
        String line = keybScanner.nextLine(); // gets a line of input, e.g., "2.3 4"
        String quitCheck = line.toLowerCase();
        if(quitCheck.equals("quit")){
            return null;
        }
        
       
        Scanner lineScanner = new Scanner(line); // scanner to scan inside line
        boolean error = false;
        double firstCoef = lineScanner.nextDouble();
        if(!(lineScanner.hasNext())){
            System.out.println("Invalid Polynomial");
            error = true;
            userPoly();
        }
        int firstExp = lineScanner.nextInt();

        Monomial m1 = new Monomial(firstCoef, firstExp);
        p.polynomial.add(m1);
        
        while(lineScanner.hasNext()){
        firstCoef = lineScanner.nextDouble();
        if(!(lineScanner.hasNext())){
            System.out.println("Invalid Polynomial");
            error = true;
            userPoly();
            break;
        }
        firstExp = lineScanner.nextInt();
        Monomial m2 = new Monomial(firstCoef, firstExp);
        
        p.polynomial.add(m2);
    }
        return p;
}

    /**
     * This helper method asks the user for a valid operator and once one is give it will
     * return that operator
     * @returns String that represents the operator
     */
    public static String operator(){
        System.out.println("Please enter a valid operator (+, -, *, /):");
        Scanner s = new Scanner(System.in);
        String op = s.nextLine();
        String quitCheck = op.toLowerCase();
        if(quitCheck.equals("quit")){
            return null;
        }

        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")){
            return op;
        }else{
            System.out.println("Invalid operator, try again!");
            return operator();
        }
    }
}
