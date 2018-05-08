package LanguagePriniciples;


/*
* The heirarchy of the implementation is interface -> parent Class -> Sub class
* A parent class can't be casted to sub class
* A sub class can be casted to parent class
* A parent class instance can't access static or instance functions of the child but the reverse is true.
* A function with same name in the sub class overrides the parent class function. All the functions in the parent class are available for the subclass.
* A parent class instance can't be assigned to a subclass but the reverse is only true.
* The static methods of variables can be accessed by the class and its instances.
* The Constructor in subclass has to match the variables in super class.
* */
public class Implementation {
    public static void main(String[] args){
        Vehicle rapid = new Cars("Skoda");
        rapid.drive();

        Automobile octavia = new Cars("Octavia");
        octavia.isSafe();
        octavia.drive();
        octavia.getAutoName();



        Cars maruthi = new Cars("Swift");
        maruthi.getCarName();
        maruthi.getAutoName();
        maruthi.drive();
        maruthi.cardrive();

        Cars skoda_rapid = (Cars) new Automobile("Skoda Rapid",true);
        skoda_rapid.getCarName();
        skoda_rapid.getAutoName();

    }
}
