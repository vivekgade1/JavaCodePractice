package LanguagePriniciples;

public class Cars extends Automobile implements Vehicle{
    private String name;

    public Cars(String name) {
        super(name, true);
    }


    public Boolean cardrive(){
        System.out.println("The Car is Driving!!!");
        return true;
    }

    public static void getCarName(){
        System.out.println("The static function in Cars ran !!");
    }

}

