import java.util.Date;

public class Rate {
    private String date;
    private Double rate;
    private String instrument;

    public Rate(String date, Double rate, String instrument){
        this.date = date;
        this.rate = rate;
        this.instrument = instrument;
    }
}
