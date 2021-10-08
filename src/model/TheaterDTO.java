package model;

public class TheaterDTO {
    private int id;
    private String theatername;
    private String theaterplace;
    private int theaternumber;
    
    
    public int getId() {
        return id;
    }
    public String getTheatername() {
        return theatername;
    }
    public String getTheaterplace() {
        return theaterplace;
    }
    public int getTheaternumber() {
        return theaternumber;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTheatername(String theatername) {
        this.theatername = theatername;
    }
    public void setTheaterplace(String theaterplace) {
        this.theaterplace = theaterplace;
    }
    public void setTheaternumber(int theaternumber) {
        this.theaternumber = theaternumber;
    }
    
    public boolean equals(Object o) {
        if(o instanceof TheaterDTO) {
            TheaterDTO m = (TheaterDTO)o;
            return id == m.id;
            
        }
        return false;
    }
    
    public TheaterDTO() {        
        id = 0;
        theatername = new String();
        theaterplace = new String();
        theaternumber = 0;
    }
    
    public TheaterDTO(TheaterDTO t) {
        id = t.id;
        theatername = new String(t.theatername);
        theaterplace = new String(t.theaterplace);
        theaternumber = t.theaternumber;

    }


}
