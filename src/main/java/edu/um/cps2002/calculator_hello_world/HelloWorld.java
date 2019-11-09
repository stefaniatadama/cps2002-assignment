package edu.um.cps2002.calculator_hello_world;

public class HelloWorld {

    public String getMessage(){
        return "Hello, World!";
    }

    public String getMessage(String name){
        return "Hello, " + name + "!";
    }

    public String getMessage2(String name){
        if(name == null)
            return getMessage();
        else if (name.equals("William"))
            return getMessage("your majesty");
        else return getMessage(name);
    }

    public int fibonacci(int n){
        if(n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacci(n-1) + fibonacci(n-2);
    }

//    public static void main(String[] args) {
//        HelloWorld hello = new HelloWorld();
//        System.out.println(hello.getMessage());
//        System.out.println(hello.getMessage("Luke"));
//        System.out.println(hello.getMessage2("William"));
//    }


}
