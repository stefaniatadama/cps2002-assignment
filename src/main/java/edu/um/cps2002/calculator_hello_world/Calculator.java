package edu.um.cps2002.calculator_hello_world;
import java.lang.Math;

public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multiply(int a, int b){
        return a*b;
    }

    public int divide(int a, int b){
        if(b == 0)
            return -999;
        else return a/b;
    }

    public int exponentiate(int a, int b){
        if(a == 0 && b == 0)
            return -999;
        else return (int)Math.pow(a,b);
    }

    public int choose(int n, int k){
        if(k == 0 || k == n){
            return 1;
        }
        else return (choose(n-1, k-1) + choose(n-1, k));
    }
}
