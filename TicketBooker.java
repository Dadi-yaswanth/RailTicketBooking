import java.util.*;
public class TicketBooker {

    static int availableLowerBerths = 10;       //Can be modified these values.., then shloud modify List Positions too
    static int availableMiddleBerths =12;
    static int availableUpperBerths = 15;
    static int availableRacTickets = 0;
    static int availableWaitingList = 0;

    static Queue<Integer> waitingList = new LinkedList<>();                 //Queue for waitingList
    static Queue<Integer>racList = new LinkedList<>();                     //Queue for RacList
    static List<Integer>bookedTicketList = new ArrayList<>();               //List of booked Tickets


    static List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1)); //Here add the positions based on your available tickets //12,3,4,....
    static List<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1)); //Here add the positions based on your available tickets //12,3,4,....
    static List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1)); //Here add the positions based on your available tickets //12,3,4,....
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));  //Here add the positions based on your available tickets //12,3,4,....
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

    static HashMap<Integer,Passenger> passengers = new HashMap<>();      //HashMap of passengers



    public void bookTicket(Passenger pObj, int berthNum, String allottedBerth){
        //Assign allotted
        pObj.number = berthNum;
        pObj.allotted = allottedBerth;

        //Adding passenger to the HashMap
        passengers.put(pObj.passengerId,pObj);
        //Adding booked passenger in to list
        bookedTicketList.add(pObj.passengerId);
        System.out.println("....................Ticket Booked Successfully!");
    }


    public void addRac(Passenger pObj, int racInfo, String allottedRac){
        pObj.number = racInfo;
        pObj.allotted = allottedRac;

        //Adding passenger to the HashMap
        passengers.put(pObj.passengerId,pObj);
        //Adding passenger to the RacQueue
        racList.add(pObj.passengerId);

        availableRacTickets--;

        racPositions.remove(0);

        System.out.println("......................added to RAC Successfully");
    }

    public void addToWaitingList(Passenger pObj, int waitingListInfo, String allottedWl){
        pObj.number = waitingListInfo;
        pObj.allotted = allottedWl;

        passengers.put(pObj.passengerId, pObj);

        waitingList.add(pObj.passengerId);

        availableWaitingList--;

        waitingListPositions.remove(0);

        System.out.println("....................added to waiting list Successfully");
    }

    public void cancelTicket(int passengerId){
        Passenger pObj = passengers.get(passengerId);

        passengers.remove(passengerId);

        bookedTicketList.remove(passengerId);

        int positionBooked = pObj.number;

        System.out.println(".....................Cancelled Successfully!");

        //adding free position to the ticket list
        if(pObj.allotted.equals("L")){
            availableLowerBerths++;
            lowerBerthPositions.add(positionBooked);
        }if(pObj.allotted.equals("M")){
            availableMiddleBerths++;
            middleBerthPositions.add(positionBooked);
        }if(pObj.allotted.equals("U")){
            availableUpperBerths++;
            upperBerthPositions.add(positionBooked);
        }

        if(racList.size()>0){
            Passenger passengerFromRac = passengers.get(racList.poll());
            int positionRac = passengerFromRac.number;
            racPositions.add(positionRac);
            racList.remove(passengerFromRac.passengerId);
            availableRacTickets++;


            if(waitingList.size()>0){
                Passenger passengerFromWl = passengers.get(waitingList.poll());
                int positionWl = passengerFromWl.number;
                waitingListPositions.add(positionWl);
                waitingList.remove(passengerFromWl.passengerId);

                passengerFromWl.number = racPositions.get(0);
                passengerFromWl.allotted = "RAC";
                racList.add(passengerFromWl.passengerId);

                availableWaitingList++;
                availableRacTickets--;

            }

            Main.bookTicket(passengerFromRac);
        }
    }

    public void printAvailable(){
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Upper Berths" + availableUpperBerths);
        System.out.println("Available Middle Berths" + availableMiddleBerths);
        System.out.println("Available Rac :"+ availableRacTickets);
        System.out.println("Available waiting List: "+ availableWaitingList);
        System.out.println(".............................");
    }

    public void printPassenger(){
        if(passengers.size()==0){
            System.out.println("No Details of Passengers exist!");
            return;
        }

        for(Passenger pObj : passengers.values()){
            System.out.println("Passenger Id: " + pObj.passengerId);
            System.out.println("Name: " + pObj.name);
            System.out.println("Age: " + pObj.age);
            System.out.println("Status: "+pObj.number + pObj.allotted);
            System.out.println(".........................");
        }
    }
}
