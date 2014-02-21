/*
 * $HeadURL$
 * $Revision$
 * $Date$
 * $Author$
 * 
 * Application: spring-async
 * Contractor: ARHS
 */
package be.test;

public class Result {
    private int a;
    private int b;

    public Result(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getSum() {
        return this.a + this.b;
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }
}
