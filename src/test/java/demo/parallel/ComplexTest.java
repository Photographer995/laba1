package test.java.demo.parallel;

import static org.junit.jupiter.api.Assertions.*;

import demo.parallel.Complex;
import org.junit.jupiter.api.Test;

class ComplexTest {

    private static final double EPS = 1e-9;

    @Test
    void pow3_ofOnePlusI_shouldBe_minus2_plus2i() {
        Complex z = new Complex(1.0, 1.0);
        Complex z3 = z.pow(3); // (1+i)^3 = -2 + 2i
        assertEquals(-2.0, z3.re(), EPS, "Re should be -2");
        assertEquals( 2.0, z3.im(), EPS, "Im should be  2");
    }

    @Test
    void pow0_shouldBe_one() {
        Complex z = new Complex(0.7, -0.3);
        Complex z0 = z.pow(0); // any^0 == 1
        assertEquals(1.0, z0.re(), EPS);
        assertEquals(0.0, z0.im(), EPS);
    }

    @Test
    void firstIteration_withZeroStart_equals_comp() {
        Complex comp = new Complex(0.3, -0.5);
        Complex z = new Complex(0.0, 0.0);
        //z1 = z0^3 + comp = 0 + comp = comp
        z = z.pow(3).plus(comp);
        assertEquals(comp.re(), z.re(), EPS);
        assertEquals(comp.im(), z.im(), EPS);
    }
}
