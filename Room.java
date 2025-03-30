public class Room {
    private String number;
    private int seatcap;
    public Room(String number, int seatcap)
    {
        this.number=number;
        this.seatcap=seatcap;
    }
    public String getNumber(){
        return number;
    }
    public int getSeatCap(){return seatcap;}
}
