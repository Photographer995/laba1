package demo.parallel;

/**
 * Complex number class used in Mandelbrot demo.
 * Expanded with additional non-mutating operations for safe usage
 * in new fractal formulas and unit testing.
 *
 * Note: original methods plus(...) and times(...) mutate 'this' (kept for
 * backward compatibility). New methods (minus, scale, conjugate, divide,
 * timesCopy, pow, abs, lengthSQ) return new Complex instances and do not
 * change the current object.
 */
public class Complex {

    private double re;   // real part
    private double im;   // imaginary part

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    /* ------------------------------
       Original (mutating) API kept
       ------------------------------ */

    /**
     * Add operation (mutates this).
     * @param b summand
     * @return this (modified)
     */
    public Complex plus(Complex b) {
        re += b.re;
        im += b.im;
        return this;
    }

    /**
     * Multiply operation (mutates this).
     * @param b multiplier
     * @return this (modified)
     */
    public Complex times(Complex b) {
        double real = re * b.re - im * b.im;
        double imag = re * b.im + im * b.re;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Square of length (non-mutating).
     * @return re*re + im*im
     */
    public double lengthSQ() {
        return re * re + im * im;
    }

    /* ------------------------------
       New non-mutating API
       ------------------------------ */

    /** Return new Complex = this - b */
    public Complex minus(Complex b) {
        return new Complex(re - b.re, im - b.im);
    }

    /** Return new Complex = this * scalar */
    public Complex scale(double k) {
        return new Complex(re * k, im * k);
    }

    /** Return new Complex = conjugate(this) */
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    /** Return modulus (abs) */
    public double abs() {
        return Math.hypot(re, im);
    }

    /** Return new Complex = this / b */
    public Complex divide(Complex b) {
        double denom = b.re * b.re + b.im * b.im;
        if (denom == 0) {
            throw new ArithmeticException("Division by zero in complex divide");
        }
        double real = (re * b.re + im * b.im) / denom;
        double imag = (im * b.re - re * b.im) / denom;
        return new Complex(real, imag);
    }

    /** Return a copy of this complex */
    public Complex copy() {
        return new Complex(re, im);
    }

    /**
     * Non-mutating multiply (returns new Complex = this * b)
     * Useful to avoid side-effects when computing powers.
     */
    public Complex timesCopy(Complex b) {
        return new Complex(re * b.re - im * b.im, re * b.im + im * b.re);
    }

    /**
     * Fast integer power: returns new Complex = this^n (n >= 0).
     * Implemented via binary exponentiation and uses timesCopy (non-mutating).
     */
    public Complex pow(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative exponent not supported");
        }
        if (n == 0) return new Complex(1.0, 0.0);
        Complex base = this.copy();
        Complex result = new Complex(1.0, 0.0);
        int e = n;
        while (e > 0) {
            if ((e & 1) == 1) result = result.timesCopy(base);
            base = base.timesCopy(base);
            e >>= 1;
        }
        return result;
    }

    /* ------------------------------
       Accessors (helpful for tests / usage)
       ------------------------------ */

    public double re() { return re; }
    public double im() { return im; }

    @Override
    public String toString() {
        return String.format("(%f%+fi)", re, im);
    }

    /* equals/hashCode optional - not strictly required for tests,
       but can be added if needed. */
}
