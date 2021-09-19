import java.util.*;
/**
 * Class to represent a polynomial, e.g. 3.5x^4 + 3x^2 - 4.
 * 
 * Polynomials can be added, subtracted, multiplied, and divided.
 * 
 * This class is a skeleton. You need to provide implementations
 * for the methods here defined. Feel free however, to add additional
 * methods as you see fit.
 *
 * @author Eric Wnorowski
 * @version 12.4.20
 */
public class Poly{

    // TODO your instance fields here
    List<Monomial> polynomial = new ArrayList<Monomial>();
    int exp;
    double coef;

    /**
     * Empty Constructor for Poly
     */
    public Poly(){

    }

    /**
     * Creates a new polynomial containing a single term with the coefficient
     * and exponent passed in as arguments. E.g. when called with coefficient
     * 3.5 and exponent 2, it should create a polynomial 3.5x^2.
     * 
     * You can create additional constructors if you'd like, but it's 
     * imperative that this one exists.
     * 
     * @param coef the single term's coefficient.
     * @param exp the single term's exponent.
     * @return the polynomial created.
     */
    public Poly(double coef, int exp){
        if (exp < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + exp);
        }
        Monomial mono = new Monomial(coef, exp);
        if(coef == 0)
            return;
        reduce();
        polynomial.add(mono);
    }

    /**
     * pre-compute the degree of the polynomial, in case of leading zero coefficients
     * (that is, the length of the array need not relate to the degree of the polynomial)
     */
    public void reduce() {
        for (int i=0; i< polynomial.size(); i++) {
            for(int j=0; j<polynomial.size(); j++){
                if(polynomial.get(i).exp == polynomial.get(j).exp && j != i){
                    polynomial.get(i).coef += polynomial.get(j).coef;
                    polynomial.remove(polynomial.get(j));
                }
            }
        }
    }

    /**
     * Method to remove coefficients of zero
     */
    public void removeZeroes(){
        List<Monomial> cpoly = new ArrayList<Monomial>();
        for (int i=0; i< polynomial.size(); i++) {
            Monomial m = new Monomial(0, 0);
            if(polynomial.get(i).coef != 0){
                m.coef = polynomial.get(i).coef;
                m.exp = polynomial.get(i).exp;
                cpoly.add(m);
            }
        }
        polynomial = cpoly;
    }

    /**
     * Adds the polynomial passed in as an argument, p, to the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial                                                              
     * with the result. I.e., it returns "this + p".
     * 
     * @param p the polynomial to add onto the polynomial on which the method is called on.
     * @return a polynomial representing the result of the addition.
     */
    public Poly add(Poly p){
        int polysize = this.polynomial.size();
        int ppolysize = p.polynomial.size();
        Poly rpoly = new Poly();
        for(int i=0; i<polysize; i++){
            Monomial m1 = this.polynomial.get(i);
            Monomial m2 = new Monomial(m1.coef, m1.exp);
            rpoly.polynomial.add(m2);
        }

        for(int i=0; i<ppolysize; i++){
            boolean match = false;
            Monomial m1 = p.polynomial.get(i);
            for(int j=0; j<rpoly.polynomial.size(); j++){
                Monomial m2 = rpoly.polynomial.get(j);
                if(m1.exp == m2.exp){
                    m2.coef += m1.coef;
                    match = true;
                    break;
                }
            }
            if(!match){
                rpoly.polynomial.add(m1);
            }
        }
        rpoly.removeZeroes();
        rpoly.reduce();
        return rpoly;
    }

    /**
     * Subtracts the 
     * polynomial passed in as an argument, p, from the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this - p".
     * 
     * @param p the polynomial to be subtracted from the polynomial on which the method is called on.
     * @return a polynomial representing the result of the subtraction.
     */
    public Poly subtract(Poly p){
        int polysize = this.polynomial.size();
        int ppolysize = p.polynomial.size();
        Poly rpoly = new Poly();
        for(int i=0; i<polysize; i++){
            Monomial m1 = this.polynomial.get(i);
            Monomial m2 = new Monomial(m1.coef, m1.exp);
            rpoly.polynomial.add(m2);
        }

        for(int i=0; i<ppolysize; i++){
            boolean match = false;
            Monomial m1 = p.polynomial.get(i);
            for(int j=0; j<rpoly.polynomial.size(); j++){
                Monomial m2 = rpoly.polynomial.get(j);
                if(m1.exp == m2.exp){
                    m2.coef = m2.coef - m1.coef;
                    match = true;
                    break;
                }
            }
            if(!match){
                Monomial m2 = new Monomial(m1.coef, m1.exp);
                m2.coef = m2.coef * -1;
                rpoly.polynomial.add(m2);
            }
        }
        rpoly.removeZeroes();
        rpoly.reduce();
        return rpoly;
    }

    /**
     * Multiplies the polynomial passed in as an argument, p, by the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this * p".
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the result of the multiplication.
     */
    public Poly multiply(Poly p){
        int polysize = this.polynomial.size();
        int ppolysize = p.polynomial.size();
        Poly rpoly = new Poly();
        Poly hpoly = new Poly();
        for(int i=0; i<polysize; i++){
            Monomial m1 = this.polynomial.get(i);
            Monomial m2 = new Monomial(m1.coef, m1.exp);
            rpoly.polynomial.add(m2);
        }

        for (int i=0; i<ppolysize; i++) {
            Monomial m1 = p.polynomial.get(i);
            for(int j=0; j<rpoly.polynomial.size(); j++){
                Monomial m2 = rpoly.polynomial.get(j);
                Monomial mtemp = new Monomial(m2.coef, m2.exp);
                mtemp.coef *= m1.coef;
                mtemp.exp += m1.exp;
                Poly helper = new Poly(mtemp.coef, mtemp.exp);
                hpoly = hpoly.add(helper);

            }
        }
        hpoly.removeZeroes();
        hpoly.reduce();
        return hpoly;
    }

    /**
     * Divides the polynomial on which the method is called on (the "this" polynomial), by
     * the polynomial passed in as an argument, p, and returns a new polynomial
     * with the resulting quotient. I.e., it returns "this / p".
     * 
     * The division should be performed according to the polynomial long division algorithm
     * ( https://en.wikipedia.org/wiki/Polynomial_long_division ).
     * 
     * Polynomial division may end with a non-zero remainder, which means the polynomials are
     * indivisible. In this case the method should return null. A division by zero should also
     * yield a null return value.
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the quotient of the division, or null if they're indivisible.
     */    
    public Poly divide(Poly p){
        List<Monomial> answer = new ArrayList<Monomial>();
        Poly polyAnswer = new Poly();
        this.removeZeroes();
        p.removeZeroes();
        this.reduce();
        p.reduce();

        if(this.polynomial.size() == 0){
            return polyAnswer;
        }
        if(p.polynomial.size() == 0){
            System.out.println("Division by zero please enter a valid value");
            return null;
        }

        Poly remainder = new Poly(this);  // helper method to copy elements represents what is left to divide at beginning is everything

        //extract highest degree monomial and divide by highest degree of divisor
        Collections.sort(p.polynomial, Collections.reverseOrder());
        Collections.sort(remainder.polynomial, Collections.reverseOrder());

        while(remainder.polynomial.size()>0){
            if(remainder.polynomial.get(0).exp < p.polynomial.get(0).exp){
                return null;
            }

            double coef1 = remainder.polynomial.get(0).coef / p.polynomial.get(0).coef;
            int exp1 = remainder.polynomial.get(0).exp - p.polynomial.get(0).exp;
            Poly t = new Poly(coef1, exp1); 

            polyAnswer = polyAnswer.add(t);

            remainder = remainder.subtract(t.multiply(p));

            remainder.removeZeroes();
            remainder.reduce();
            Collections.sort(remainder.polynomial, Collections.reverseOrder());
        }
        polyAnswer.reduce();
        polyAnswer.removeZeroes();
        return polyAnswer;
    }

    /**
     * Helper method that copies elements for divide method
     */
    public Poly(Poly p){
        for(int i=0; i<p.polynomial.size(); i++){
            Monomial a = new Monomial(p.polynomial.get(i).coef, p.polynomial.get(i).exp);
            this.polynomial.add(a);
        }
    }

    /**
     * Compares the polynomial on which the method is called (the "this" polynomial), 
     * to the object passed in as argument, o. o is to be considered equal to the "this"
     * polynomial if they both represent equivalent polynomials.
     * 
     * E.g., "3.0x^4 + 0.0x^2 + 5.0" and "3.0x^4 + 5.0" should be considered equivalent.
     * "3.0x^4 + 5.0" and "3.0x^4 + 3.0" should not.
     * 
     * @param o the object to be compared against the polynomial the method is called on.
     * @return true if o is a polynomial equivalent to the one the method was called on,
     * and false otherwise.
     */
    public boolean equals(Object o){

        if (o == this) return true;

        if (o == null) return false;

        Poly p = (Poly) o;
        p.reduce();
        this.reduce();
        p.removeZeroes();
        this.removeZeroes();
        // compare size tof array
        if(p.polynomial.size() != this.polynomial.size()) return false;

        // Check each element in the ArrayList
        // If any don't match, return false.
        for(int i=0; i<this.polynomial.size(); i++){
            Monomial m = this.polynomial.get(i);
            if(!(p.polynomial.contains(m)))
                return false;
        }

        return true;
    }

    /**
     * Returns a textual representation of the polynomial the method is called on.
     * The textual representation should be a sum of monomials, with each monomial 
     * being defined by a double coefficient, the letters "x^", and an integer exponent.
     * Exceptions to this rule: coefficients of 1.0 should be omitted, as should "^1",
     * and "x^0".
     * 
     * Terms should be listed in decreasing-exponent order. Terms with zero-coefficient
     * should be omitted. Each exponent can only appear once. 
     * 
     * Rules for separating terms, applicable to all terms other that the largest exponent one:
     *   - Terms with positive coefficients should be preceeded by " + ".
     *   - Terms with negative coefficients should be preceeded by " - ".
     * 
     * Rules for the highest exponent term (i.e., the first):
     *   - If the coefficient is negative it should be preceeded by "-". E.g. "-3.0x^5".
     *   - If the coefficient is positive it should not preceeded by anything. E.g. "3.0x^5".
     * 
     * The zero/empty polynomial should be represented as "0.0".
     * 
     * Examples of valid representations: 
     *   - "2.0x^2 + 3.0"
     *   - "3.5x + 3.0"
     *   - "4.0"
     *   - "-2.0x"
     *   - "4.0x - 3.0"
     *   - "0.0"
     *   
     * Examples of invalid representations: 
     *   - "+2.0x^2+3.0x^0"
     *   - "3.5x -3.0"
     *   - "- 4.0 + x"
     *   - "-4.0 + x^7"
     *   - ""
     * 
     * @return a textual representation of the polynomial the method was called on.
     */
    public String toString(){
        String s = "";
        this.reduce();
        this.removeZeroes();
        
        if(this.polynomial.size() == 0){
            s += "0.0";
        }

        Collections.sort(this.polynomial, Collections.reverseOrder());
        for (int i=0; i< this.polynomial.size(); i++) {
            Monomial m1 = this.polynomial.get(i);
            Monomial m2 = new Monomial(m1.coef, m1.exp);
            if(m2.equals(this.polynomial.get(0)))
                s += m2;
            else if(m2.coef >= 1)
                s += " + " + m2;
            else{
                m2.coef *= -1;
                s += " - " + m2;
            }
        }

        return s;
    }
}
