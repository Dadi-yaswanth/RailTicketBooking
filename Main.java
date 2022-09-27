import java.util.*;
public class Main {

    public static void bookTicket(Passenger pObj){
        TicketBooker booker = new TicketBooker();

        if(TicketBooker.availableWaitingList==0){
            System.out.println("No Tickets Available");
            return;
        }

        if((pObj.berthPref.equals("L") && TicketBooker.availableLowerBerths>0) ||
                                 (pObj.berthPref.equals("M")&& TicketBooker.availableMiddleBerths>0) ||
                (pObj.berthPref.equals("U") && TicketBooker.availableUpperBerths>0) )
        {

            System.out.println("Preferred berth is available.,");

            if(pObj.berthPref.equals("L")){
                System.out.println("Lower Berth Given");

                booker.bookTicket(pObj, TicketBooker.lowerBerthPositions.get(0), "L");

                TicketBooker.lowerBerthPositions.remove(0);
                TicketBooker.availableLowerBerths--;
            }
            else if(pObj.berthPref.equals("M")){
                System.out.println("Middle Berth Given..,");

                booker.bookTicket(pObj, TicketBooker.middleBerthPositions.get(0),"M");

                TicketBooker.middleBerthPositions.remove(0);
                TicketBooker.availableMiddleBerths--;

            }
            else if(pObj.berthPref.equals("U")){
                System.out.println("Upper Berth Given..,");

                booker.bookTicket(pObj, TicketBooker.upperBerthPositions.get(0),"U");

                TicketBooker.upperBerthPositions.remove(0);
                TicketBooker.availableUpperBerths--;

            }
        }
        else if(TicketBooker.availableLowerBerths>0){
            System.out.println("Lower Berth Given");

            booker.bookTicket(pObj, TicketBooker.lowerBerthPositions.get(0), "L");

            TicketBooker.lowerBerthPositions.remove(0);
            TicketBooker.availableLowerBerths--;

        }
        else if(TicketBooker.availableMiddleBerths>0){
            System.out.println("Middle berth Given ");

            booker.bookTicket(pObj, TicketBooker.middleBerthPositions.get(0),"M");

            TicketBooker.middleBerthPositions.remove(0);
            TicketBooker.availableMiddleBerths--;

        }
        else if(TicketBooker.availableUpperBerths>0){
            System.out.println("Upper berth Given");

            booker.bookTicket(pObj, TicketBooker.upperBerthPositions.get(0),"U");

            TicketBooker.upperBerthPositions.remove(0);
            TicketBooker.availableUpperBerths--;
        }
        else if(TicketBooker.availableRacTickets>0){
            System.out.println("RAC available");
            booker.addRac(pObj,TicketBooker.racPositions.get(0),"RAC");
        }
        else if(TicketBooker.availableWaitingList>0){
            System.out.println("Added to waitingList");
            booker.addToWaitingList(pObj, TicketBooker.waitingListPositions.get(0),"WL");
        }
    }

    public static void cancelTicket(int id){

        TicketBooker booker = new TicketBooker();

        if(!TicketBooker.passengers.containsKey(id)){
            System.out.println("Passenger detail Unknown");
        }else{
            booker.cancelTicket(id);
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop) {System.out.println("""
                1. Book Ticket\s
                2. Cancel Ticket\s
                3. Available Tickets\s
                4. Booked Tickets\s
                5. Exit""".indent(1));

            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter Passenger Details: ");

                    System.out.println("Name:");
                    String name = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Age:");
                    int age = sc.nextInt();

                    System.out.println("Enter the Berth Preference as(L, M, U)");
                    String berthPref = String.valueOf(sc.next().charAt(0));

                    Passenger pObj = new Passenger(name,age,berthPref);
                    bookTicket(pObj);
                }
                case 2 -> {
                    System.out.println("Enter the passenger id: to Cancel: ");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }
                case 3 -> {
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailable();
                }
                case 4 -> {
                    TicketBooker booker = new TicketBooker();
                    booker.printPassenger();
                }
                case 5 -> {
                    loop = false;
                }
                default -> {
                }
            }
        }

    }
}
