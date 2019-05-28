import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class SeatArranger {
    private int row;
    private int column;
    private int NOT_FOUND = -1;
    public MyWriter writer;
    private Passenger[][] passengers;

    public SeatArranger(int row, int column) {
        this.row = row;
        this.column = column;
        passengers = new Passenger[row][column];
        writer = new MyWriter(600, 600, row);

    }

    /**
     * findLocationOfRow Locates the row of the reserved seat
     */

    private int findLocationOfRow(PassengerId k) {
        boolean found = false;
        int index = NOT_FOUND;
        for (int i = 0; i < row && !found; i++) {
            for (int j = 0; j < column && !found; j++) {
                if (passengers[i][j] != null) {
                    if (passengers[i][j].getPassengerId().equals(k)) {
                        index = i;
                        found = true;
                    }
                }
            }
        }
        return index;
    }

    /**
     * findLocationOfColumn locates the column of the reserved seat
     */
    public int findLocationOfColumn(PassengerId k) {
        boolean found = false;
        int index = NOT_FOUND;
        for (int i = 0; i < row && !found; i++) {
            for (int j = 0; j < column && !found; j++) {
                if (passengers[i][j] != null) {
                    if (passengers[i][j].getPassengerId().equals(k)) {
                        index = j;
                        found = true;
                    }
                }
            }
        }
        return index;
    }

    /**
     * find returns the location of an item based on it's key by using the two upper helper methods
     */
    public Passenger find(PassengerId k) {
        Passenger answer = null;
        int index1 = findLocationOfRow(k);
        int index2 = findLocationOfColumn(k);
        if (index1 != NOT_FOUND && index2 != NOT_FOUND) {
            answer = passengers[index1][index2];
        }
        else{JOptionPane.showMessageDialog(null,"No valid key found");}
        return answer;
    }

    /**
     * delete erases an items based on it's key
     */
    public boolean delete(PassengerId k) {
        boolean result = false;
        int index1 = findLocationOfRow(k);
        int index2 = findLocationOfColumn(k);
        if (index1 != NOT_FOUND && index2 != NOT_FOUND) {
            passengers[index1][index2] = null;
            result = true;
        }
        else{JOptionPane.showMessageDialog(null,"No valid key found");}
        return result;
    }

    /**
     * method arranges seats by order
     */

    public Passenger reserve(Passenger passenger,int row) {
        boolean succeeded = false;
        if (findLocationOfColumn(passenger.getPassengerId()) == NOT_FOUND && findLocationOfColumn(
                passenger.getPassengerId()) == NOT_FOUND)  // r  not already in  base?
        {
            boolean found_empty_place = false;
            for(int j = 0; j != column && !found_empty_place; j++) {
                if (passengers[row][j] == null) {
                    passengers[row][j] = passenger;
                    found_empty_place = true;
                }

            }
            if (!found_empty_place) {
                JOptionPane.showMessageDialog(null, "There are no more seats on" +
                        " the plane ");
            }
        }
        return null;
    }

    /**
     * as the user wishes to seat by window this method allows that by searching
     * for free seats only at the first column and the last one
     */
    public boolean reserveByWindow(Passenger passenger) {
        boolean result = false;
        boolean result2 = false;
        if (findLocationOfColumn(passenger.getPassengerId()) == NOT_FOUND &&
                findLocationOfColumn(passenger.getPassengerId()) == NOT_FOUND)  // make sure not in base
        {
            int j = 0;
            for (int i = 0; i < row && !result; i++) {
                if (passengers[i][j] == null) {
                    passengers[i][j] = passenger;
                    result = true;
                }
            }
            if (!result) {
                result = false;
                j = column - 1;
                for (int i = 0; i < row && !result2; i++) {
                    if (passengers[i][j] == null) {
                        passengers[i][j] = passenger;
                        result2 = true;
                    }
                }
            }
        }
        return result;
    }
    public int freeSeatsOnRow(int row){
        int k = 0;
        for(int i =0;i!=column;i++){
            if(passengers[row][i]==null){
                k=k+1;
            }else{break;}
        }
        return k;
    }

    public int reserveClose(int num) {
        boolean found = false;
        int l=0;
        for(int i = l;i!=row&&!found;i++){
            if (freeSeatsOnRow(i)>=num){
                l=i; found=true;
            }
            if(!found){ l = -1;}
        }
        return l;
    }
    public boolean reserveByRandom(Passenger passenger) {
        boolean result = false;
        if (findLocationOfColumn(passenger.getPassengerId()) == NOT_FOUND) {
            while (!result) {
                int index_1 = ThreadLocalRandom.current().nextInt(1, row);
                int index_2 = ThreadLocalRandom.current().nextInt(1, column);
                if (passengers[index_1][index_2] == null) {
                    passengers[index_1][index_2] = passenger;
                    result = true;
                }
            }

        }
        return result;
    }
    public void reserveLast(Passenger passenger) {
        if (findLocationOfColumn(passenger.getPassengerId()) == NOT_FOUND && findLocationOfColumn(
                passenger.getPassengerId()) == NOT_FOUND)  // r  not already in  base?
        { boolean found_empty_place = false;
            for(int i = 0;i!=row && !found_empty_place;i++){
                for(int j = 0; j != column && !found_empty_place; j++) {
                if (passengers[i][j] == null) {
                    passengers[i][j] = passenger;
                    found_empty_place = true;
                                             }

                }
             }
            if (!found_empty_place) {
                JOptionPane.showMessageDialog(null, "There are no more seats on" +
                        " the plane ");
            }
        }
    }

    /**
     * getSeatNumber returns the number where the passenger will seat
     */
    public int getSeatNumber(Passenger passenger) {
        int index_1 = findLocationOfRow(passenger.getPassengerId());
        int index_2 = findLocationOfColumn(passenger.getPassengerId());
        int seatNumber = 0;
        if (index_1 == 0) {
            seatNumber = index_1 + index_2 + 1;
        } else {
            seatNumber = column * index_1 + index_2 + 1;
        }return seatNumber;
    }
    /**
     * print displays an matrix with rows and columns where each element of the matrix
     * represents a seat if the seat is marked with X it is taken if it is marked with 0 it is free
     */
    public void print() {
        String[] Sentence = new String[row];
        String temporary = "";

        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                if (passengers[i][j] == null) {
                    temporary = temporary + "0 ";
                } else {
                    temporary = temporary + "X ";
                }
            }
            Sentence[i] = temporary;
            writer.writeSentence(Sentence, 10, 20);
            temporary = "";
        }
    }
    public void printOnConsole(){
        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                if (passengers[i][j] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print("X "); }
            }System.out.println();
        }System.out.println("------------");
    }
}
