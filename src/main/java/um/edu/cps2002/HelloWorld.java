package um.edu.cps2002;

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

//    public static void main(String[] args) {
//        HelloWorld hello = new HelloWorld();
//        System.out.println(hello.getMessage());
//        System.out.println(hello.getMessage("Luke"));
//        System.out.println(hello.getMessage2("William"));
//    }


}
