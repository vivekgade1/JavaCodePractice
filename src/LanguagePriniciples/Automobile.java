package LanguagePriniciples;

public class Automobile implements Vehicle {
    private String name;
    private boolean safe;

    public Automobile(String name, Boolean safe){
        this.safe = safe;
        this.name = name;
    }

    public void isSafe(){
        System.out.println(this.safe);
    }
    public void drive(){
        System.out.println("This  is an Automobile Driving!!!" + this.name);
    }

    public static void getAutoName(){
        System.out.println("The Static function in Automobile ran !!");
    }
}
