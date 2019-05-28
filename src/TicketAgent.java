/**
 * Serves the passengers*/
import javax.swing.*;
public  class TicketAgent{
    private final int normal_price = 300;
    private final int price_by_window = 350;
    private final String flight = "101";
    private SeatArranger arranger;
    public int size_of_Airplane_row;
    public int size_of_Airplane_column;
    public TicketAgent() {
        size_of_Airplane_row = Integer.parseInt(JOptionPane.showInputDialog("Please type the " +
                "number of rows on the airplane"));
         size_of_Airplane_column = 6; // airplane most likely have 6 seats in a row
        arranger = new SeatArranger(size_of_Airplane_row, size_of_Airplane_column);}
    public void run(){
        boolean done = false;
        while (!done) {
            arranger.print();
            arranger.printOnConsole();
            int a = Integer.parseInt(JOptionPane.showInputDialog("What would you" +
                    " like to do \n Type 1 to reserve 1 seat \n Type 2 to reserve more seats" +
                    "\n Type 3 to delete any seat \n Type 4 to find an address \n Or type " +
                    "anything to terminate"));
            switch(a){
                case 1:{reserveOneSeat();break;}
                case 2:{reserveSeats();break;}
                case 3:{delete();break;}
                case 4:{find();break;}
                default:{done=true;}

            }

        }
        arranger.print();
        arranger.printOnConsole();
    }
    public void reserveOneSeat(){
        String name = JOptionPane.showInputDialog("Type your full name: ");
        PassengerId key = new PassengerId(Integer.parseInt(JOptionPane.showInputDialog(" Type your" +
                " id: ")));
        int a = Integer.parseInt(JOptionPane.showInputDialog("Type 1 for window seat" +
                " or type anything for a random one "));
        Passenger seats;
        if(a==1){
            seats = new Passenger(price_by_window,flight,name,key);
            arranger.reserveByWindow(seats);}
        else{
            seats = new Passenger(normal_price, flight, name, key);
            arranger.reserveByRandom(seats);}
        JOptionPane.showMessageDialog(null, " You have successfully" +
                " made a Reservation on the name " + name +
                " , and the Seat number  is " + arranger.getSeatNumber(seats));

    }
    public void reserveSeats(){
        int howMany = Integer.parseInt(JOptionPane.showInputDialog("How many seats do " +
                "you want to reserve"));
        int seat = arranger.reserveClose(howMany);
        for(int i =0;i<howMany;i++){
            if (seat==-1){JOptionPane.showMessageDialog(null,"We" +
                    " apologize but there is no place that suits your choices");
                int a = JOptionPane.showConfirmDialog(null,"Do you want to reserve" +
                        " them seperately ");
                if(a==0){for(int j =0;j!=howMany;j++){
                    reserve();}
                    } break;
            }
            else{
                String name = JOptionPane.showInputDialog("Type your full name: ");
                PassengerId key = new PassengerId(Integer.parseInt(JOptionPane.showInputDialog(" Type your" +
                        " id: ")));
                Passenger passenger = new Passenger (normal_price, flight, name, key);
                arranger.reserve(passenger,seat);
                JOptionPane.showMessageDialog(null, " You have successfully" +
                        " made a Reservation on the name " + name +
                        "  , and the Seat number  is " + arranger.getSeatNumber(passenger));


            }
        }
    }
        public void reserve (){
                 String name =JOptionPane.showInputDialog("Type your full name: ");
                 PassengerId key = new PassengerId(Integer.parseInt(JOptionPane.showInputDialog(" Type your" +
                            " id: ")));
                    Passenger passenger = new Passenger (normal_price, flight, name, key);
                    arranger.reserveLast(passenger);
                }

    public void delete(){
        PassengerId key = new PassengerId(Integer.parseInt(JOptionPane.showInputDialog(" Type the" +
                " key of the reservation you want to delete: ")));
        arranger.delete(key);
    }
    public void find(){
        PassengerId key = new PassengerId(Integer.parseInt(JOptionPane.showInputDialog(" Type the" +
                " key of the reservation you want to find: ")));
        System.out.print(arranger.find(key));
    }
    public void print(){
        arranger.print();
    }
}

