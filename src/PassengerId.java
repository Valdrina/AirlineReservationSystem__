/**
 * models the Key*/
public class PassengerId {
    private int id;
    public PassengerId(int id){
        this.id=id;

    }
    public boolean equals(PassengerId c){
        boolean p = false;
        int a = c.getId();
        if(a==id) p = true;
        return p;
    }

    public int getId() {
        return id;
    }


}
