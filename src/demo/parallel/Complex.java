package demo.parallel;
public class Complex {

    private double re;   // real part
    private double im;   // imaginary part

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }


    public Complex plus(Complex b) {
        re += b.re;
        im += b.im;
        return this;
    }

  
    public Complex times(Complex b) {
        double real = re * b.re - im * b.im;
        double imag = re * b.im + im * b.re;
        re = real;
        im = imag;
        return this;
    }

 
    public double lengthSQ() {
        return re * re + im * im;
    }

  
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

    public Complex timesCopy(Complex b) {
        return new Complex(re * b.re - im * b.im, re * b.im + im * b.re);
    }

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

 
    public double re() { return re; }
    public double im() { return im; }

    @Override
    public String toString() {
        return String.format("(%f%+fi)", re, im);
    }

  
}
