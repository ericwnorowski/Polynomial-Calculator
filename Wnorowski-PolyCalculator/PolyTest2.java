import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
* A class with 5 additional unit tests for the Poly polynomial class.
* 
* We recommend you tackle them (i.e. try to make them pass) in the 
* order they appear in this file.
*
* @author  Eric Wnorowski
* @version 12.4.20
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PolyTest2{
    /**
     * Tests polynomial addition.
     * Assumes the following methods are correct: constructor, equals().
     */
    @Test
    public void test1Add(){
        // testing monomials with different exponents
        Poly p1 = new Poly(3, 1); // p1 = 3x
        Poly p2 = new Poly(3, 0); // p2 = 3
        Poly p3 = new Poly(3, 2); // p3 = 3x^2  
        Poly p4 = p1.add(p2); // p4 = 3x + 3
        Poly p5 = p3.add(p1); // p5 = 3x^2 + 3x
        Poly p6 = new Poly(3, 2); // p6 = 3x^2
        Poly p7 = p6.add(p1); // p7 = 3x^2 + 3x
        assertEquals("3x^2 + 3x = 3x^2 + 3x", p5, p7);
        
        Poly p8 = p2.add(p2);
        assertNotEquals("3x^2 + 3x + 3 != 3x^2 + 3x", p8, p5);
        
        Poly p9 = new Poly(0, 0);
        Poly p10 = new Poly(2, 2);
        Poly p11 = new Poly(-2, 0);
        Poly p12 = p10.add(p11);
        Poly p13 = p12.add(p9);
        assertEquals("2x^2 - 2 = 2x^2 - 2 + 0", p12, p13);
    }
    /**
     * Tests polynomial subtraction.
     * Assumes the following methods are correct: constructor, equals().
     */
    @Test
    public void test2Sub(){
        
        // testing monomials with different exponents
        Poly z1 = new Poly(3, 0); // z1 = 3
        Poly z2 = new Poly(3, 1); // z2 = 3x
        Poly z3 = new Poly(3, 2); // z3 = 3x^2
        Poly z4 = z2.subtract(z1); // z4 = 3x - 3
        Poly z5 = z3.subtract(z1); // z5 = 3x^2 - 3
        assertNotEquals("3x - 3 != 3x^2 - 3", z5, z4);
        
        Poly z6 = z5.add(z1); // z6 = 3x^2 - 3 + 3
        assertEquals("3x^2 - 3 + 3 = 3x^2", z6, z3);
        
        // testing polynomials
        Poly z7 = z4.subtract(z1); // z7 = 3x - 3 - 3
        Poly z8 = z5.subtract(z1); // z8 = 3x^2 - 3 - 3
        Poly z9 = z7.subtract(z2); // z9 = 3x - 6 - 3x
        Poly z10 = z8.subtract(z3); // z9 = 3x^2 - 6 - 3x^2
        assertEquals("3x - 6 - 3x = 3x^2 - 6 - 3x^2", z9, z10);
        
        Poly z11 = z7.add(z3); // z11 = 3x - 6 + 3x^2
        Poly z12 = z8.add(z2); // z8 = 3x^2 - 6 + 3x
        assertEquals("3x - 6 + 3x^2 = 3x^2 - 6 + 3x", z11, z12);
        
    }
    /**
     * Tests polynomial multiplication.
     * Assumes the following methods are correct: constructor, equals(), add(). 
     */
    @Test
    public void test3Mul() {

        // multiply multi-term poly by single term poly
        Poly p1 = new Poly(2,2); // p1 = 2x^2
        Poly p2 = new Poly(4,1); // p2 = 4x
        Poly p3 = p1.add(p2); // p3 = 2x^2 + 4x

        Poly p4 = new Poly(3,1); // p4 = 3x
        Poly p5 = p3.multiply(p4); // p5 = 6x^3 + 12x^2

        Poly p6 = new Poly(6,3); // p6 = 6x^3
        Poly p7 = new Poly(12,2); // p7 = 12x^2
        Poly res = p6.add(p7); // res = 6x^3 + 12x^2
        assertEquals("(x^2 + 5) * 2x != 2x^3 + 10x", res, p5); 

        Poly p8 = p1.add(p7);  // p8 = 2x^2 + 12x^2
        assertNotEquals("2x^2 + 12x^2 != 6x^3 + 12x^2", p8, p5); 
        
        
        // multiply multi-term poly by another multi-term poly
        Poly p9 = new Poly(12,0); // p9 = 12
        Poly p10 = p1.add(p2).add(p9); // p4 = 2x^2 + 4x + 12
        Poly p11 = new Poly(12,1); // p11 = 12x
        Poly p12 = p11.add(p9); // p6 = 12x + 12
        Poly p13 = new Poly(24,3); // p7 = 24x^3
        Poly p14 = new Poly(72,2); // p8 = 72x^2
        Poly p15 = new Poly(192,1); // p9 = 192x
        Poly p16 = new Poly(144,0); // p10 = 144
        Poly mult = p12.multiply(p10); // mult = (12x + 12)  * (2x^2 + 4x + 12)
        Poly res2 = p13.add(p14).add(p15).add(p16); // res = 24x^3 + 72x^2 + 192x + 144
        assertEquals("(x^2 + 5x + 3) * (7x + 3) != 7x^3 + 38x^2 + 36x + 9", res2, mult);
        
        // tests merging of different exponent terms
        Poly t1 = new Poly(2, 3); // t1 = 2x^3
        t1.add(new Poly(5,1)); // t1 = 2x^3 + 5x
        t1.add(new Poly(8,0)); // t1 = 2x^3 + 5x + 8
        Poly t2 = new Poly(4, 4); // t2 = 4x^4
        t2.add(new Poly(1, 3)); // t2 = 4x^4 + x^3
        t2.add(new Poly(5,2)); // t2 = 4x^4 + x^3 + 5x^2 
        t2.add(new Poly(2, 1)); // t2 = 4x^4 + x^3 + 5x^2 + 2x
        Poly t3 = t1.multiply(t2); // t3 = 8x^7 + 2x^6 + 30x^5 + 41x^4 + 33x^3 + 50x^2 + 16x
        
        // build result to test against
        Poly t4 = new Poly(8, 7); // t4 = 8x^7
        t4.add(new Poly(2, 6)); // t4 = 8x^7 + 2x^6
        t4.add(new Poly(30, 5)); // t4 = 8x^7 + 2x^6 + 30x^5
        t4.add(new Poly(41, 4)); // t4 = 8x^7 + 2x^6 + 30x^5 + 41x^4
        t4.add(new Poly(33, 3)); // t4 = 8x^7 + 2x^6 + 30x^5 + 41x^4 + 33x^3
        t4.add(new Poly(50, 2)); // t4 = 8x^7 + 2x^6 + 30x^5 + 41x^4 + 33x^3 + 50x^2
        t4.add(new Poly(16, 1)); // t4 = 8x^7 + 2x^6 + 30x^5 + 41x^4 + 33x^3 + 50x^2 + 16x
        
        assertEquals("(2x^3 + 5x + 8) * (4x^4 + x^3 + 5x^2 + 2x) = 8x^7 + 2x^6 + 30x^5 + 41x^4 + 33x^3 + 50x^2 + 16x", t3, t4);
        
    }
    /**
     * Tests polynomial division.
     * Assumes the following methods are correct: constructor, equals(), add(), multiply().
     */
    @Test
    public void test4Div() {
        // divide two-term poly by two-term poly
        Poly p1 = new Poly(2,2); // p1 = 2x^2
        Poly p2 = new Poly(4,1); // p2 = 4x
        Poly p3 = p1.add(p2); // p3 = 2x^2 + 4x

        Poly p4 = new Poly(2,1); // p4 = 2x
        Poly p5 = p3.divide(p4); // p5 = x + 2

        Poly p6 = new Poly(1,1); // p6 = x
        Poly p7 = new Poly(2, 0); // p7 = 2
        Poly res = p6.add(p7); // res = x + 2
        assertEquals("(2x^2 + 4x) / 2x = x + 2", res, p5); 

        Poly p8 = p3.divide(p5); // p8 = 2x
        res = new Poly(2, 1); // res = 2x
        assertEquals("(2x^2 + 4x) / (x + 2) = 2x", res, p8); 
        
        Poly p9 = p5.divide(p3); // null
        assertEquals("(x + 2) / (2x^2 + 4x)", null, p9);
        
        p1 = new Poly(1, 6);
        p2 = new Poly(2, 4);
        p3 = new Poly(6, 1);
        p4 = new Poly(9, 0);
        p5 = p1.add(p2).add(p3).subtract(p4); // x^6 + 2x^4 + 6x - 9
        
        p6 = new Poly(1, 3);
        p7 = new Poly(3, 0);
        p9 = p6.add(p7); // x^3 + 3
        
        Poly divRes = p5.divide(p9);
        
        //build result
        Poly bRes = new Poly(1, 3);
        bRes = bRes.add(new Poly(2, 1));
        bRes = bRes.subtract(new Poly(3, 0)); // x^3 + 2x - 3
        
        assertEquals("(x^6 + 2x^4 + 6x - 9) / (x^3 + 3) = x^3 + 2x - 3", divRes, bRes);
    }
    /**
     * A helper method to check wheter a polynomial's toString() returns a string
     * in the correct format.
     * 
     * @param polyStr the string that should be returned by polynomial toString().
     * @param p the polynomial to be tested.
     * @return an error message.
     */
    private void testToStringHelper(String polyStr, Poly p){
        String emsg = String.format("%s toString() incorrect", polyStr);
        assertEquals(emsg, polyStr, p.toString());
    }
    /**
     * Tests polynomial toString().
     * Assumes the following methods are correct: constructor, add(), subtract().
     */
    @Test
    public void test5ToString() {
    // printing zero polynomial with exponents
        Poly p1 = new Poly(0,4); // p1 = 0
        String s = "0.0";
        this.testToStringHelper(s, p1);

        // printing polynomial x
        p1 = new Poly(1,1); // p1 = x
        s = "x";
        this.testToStringHelper(s, p1);

        // printing polynomial -x
        p1 = new Poly(-1,1); // p1 = -x
        s = "-x";
        this.testToStringHelper(s, p1);

        // printing polynomials with different kinds of terms
        p1 = new Poly(-2.0, 4); // p1 = -2.0x^4
        s = "-2.0x^4";
        this.testToStringHelper(s, p1);

        p1 = p1.subtract(new Poly(3.4, 3)); // p1 = -2.0x^4 - 3.4x^3
        s += " - 3.4x^3";
        this.testToStringHelper(s, p1);

        // adding like-terms
        p1 = p1.add(new Poly(1.0, 4)); // p1 = -2.0x^4 - 3.4x^3 + x^4
        s = "-x^4 - 3.4x^3";
        this.testToStringHelper(s, p1);

        p1 = p1.add(new Poly(6.0, 3)); // p1 = -x^4 - 3.4x^3 + 6.4x^3
        s = "-x^4 + 2.6x^3";
        this.testToStringHelper(s, p1);

        //subtract to test order sorting
        p1 = p1.subtract(new Poly(2, 6)); // p1 = -2x^6 - x^4 + 3.0x^3
        s = "-2.0x^6 - x^4 + 2.6x^3";
        this.testToStringHelper(s, p1);

        // adding out of order to test sorting
        p1 = p1.add(new Poly(50, 40)); // p1 = 50.0x^40 - 2x^6 - x^4 + 3.0x^3
        s = "50.0x^40 - 2.0x^6 - x^4 + 2.6x^3"; 
        this.testToStringHelper(s, p1);

        // adding zero coef terms shouldn't affect printing
        p1 = p1.add(new Poly(0, 10)); // p1 = 50.0x^40 - 2x^6 - x^4 + 3.0x^3
        this.testToStringHelper(s, p1);

        // printing polynomial with zero exponent
        p1 = p1.add(new Poly(2.0, 0));
        s += " + 2.0";
        this.testToStringHelper(s, p1);
        
        // Polynomial with negative exponent
        // p1 = p1.add(new Poly(2.0, -5));
        // Java throws IllegalArgumentException
        
        // merging decimal terms to int
        p1 = p1.subtract(new Poly(1.6, 3)); // 2.2x^3
        s = "50.0x^40 - 2.0x^6 - x^4 + x^3 + 2.0";
        this.testToStringHelper(s, p1);
    }
}
