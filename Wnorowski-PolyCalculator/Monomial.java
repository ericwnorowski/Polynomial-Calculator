/**
 * This class represents the monomials that are ArrayList of Polynomials
 *
 * @author Eric Wnorowski
 * @version 11.29.20
 */
public class Monomial implements Comparable
{
    // instance variables
    double coef;
    int exp;

    /**
     * Constructor for objects of class Monomial
     */
    public Monomial(double coef, int exp){
        this.coef = coef;
        this.exp = exp;
    }

    /**
     * Compares two Monomials in order to differentiate order
     * 
     * @param Object o - generic object
     * @returns boolean - true if Monomials are equal, false otherwise
     */
    public boolean equals(Object o){
        if(!(o instanceof Monomial))
            return false;

        Monomial m = (Monomial) o;

        if(m.coef == this.coef && m.exp == this.exp)
            return true;
        else
            return false;
    }

    /**
     * toString for the monomial class, helps to format each monomial properly
     * 
     * @returns String - Properly formatted string representation of monomial
     */
    public String toString(){
        String s = "";

        
        if(this.exp<0)
            return "Exponent Error: must be positive integer";
        else if(this.coef < 0 && this.exp == 0)
            s += this.coef;
        else if(this.coef < -1 && this.exp == 1)
            s += this.coef + "x";
        else if(this.coef < -1 && this.exp > 1)
            s += this.coef + "x^" + this.exp;
        else if(this.coef == -1 && this.exp == 1)
            s += "-x";
        else if(this.coef < 0 && this.exp > 0)
            s += "-" + "x^" + this.exp;
        else if(this.coef == 1.0 && this.exp == 0)
            s += "1.0";
        else if(this.coef == 1.0 && this.exp == 1)
            s += "x";
        else if(this.coef == 1.0 && this.exp>1)
            s += "x^" + this.exp;
        else if(this.coef > 1.0 && this.exp == 0)
            s += this.coef;
        else if(this.coef > 1.0 && this.exp == 1)
            s += this.coef + "x";
        else if(this.coef > 1.0 && this.exp > 1)
            s += this.coef + "x^" + this.exp;

        return s;
    }

    /**
     * compareTo for the monomial class, helps to implement Comparable
     * Neccesary for sorting in Poly class
     * 
     * @returns int - 1 if object (monomial) passed through is of lower exponent and 
     * -1 if it is of higher exponent than the monomial being compared to.
     */
    public int compareTo(Object o){
        Monomial m = (Monomial) o;
        int result = 0;
        if(this.exp > m.exp)
            result = 1;
        else if(this.exp < m.exp)
            result = -1;

        return result;
    }
}
