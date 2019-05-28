/**
 * models a passenger's reservation ticket*/
public class Passenger {
    private final int price;
    private final String flight;
    private PassengerId id;
    private String name;
    public Passenger(final int price,
                     String flight, String name, PassengerId id) {
        this.price = price;
        this.flight = flight;
        this.name = name;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    public String getFlight() {
        return flight;
    }

    public PassengerId getPassengerId(){return id;}


}
