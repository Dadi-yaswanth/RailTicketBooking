public class Passenger {
    static int id = 1;
    String name ;
    int age;
    String berthPref; //U, L, M
    int passengerId;
    String allotted; // U, L, M, RAC, WL
    int number; //seatNumber

    Passenger(String name, int age, String berthPref){
        this.name = name;
        this.age = age;
        this.berthPref = berthPref;
        passengerId = id++;
        allotted = "";
        number = -1; // This is BerthNumber if -1 means not given till now
    }

}
