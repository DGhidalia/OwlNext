package internals;

public class RateData {

    private String Currency1;
    private String Currency2;
    private String Date;
    private Double Rate;


    public RateData(String currency1, String currency2, String date, Double rate) {
        Currency1 = currency1;
        Currency2 = currency2;
        Date = date;
        Rate = rate;
    }

    public String getCurrency1() {
        return Currency1;
    }

    public String getCurrency2() {
        return Currency2;
    }

    public String getDate() {
        return Date;
    }

    public Double getRate() {
        return Rate;
    }
}
