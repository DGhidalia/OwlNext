package database;

import internals.RateData;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Database of the application, wiped after each application closed
 */
public class InMemoryDatabase {

    private int rateCountEURCHF_;
    private int rateCountEURGBP_;
    private int rateCountEURUSD_;
    private int rateCountUSDCHF_;
    private int rateCountUSDEUR_;
    private int rateCountUSDGBP_;

    private Map<String, Map<Integer, RateData>> rate_;

    public InMemoryDatabase() {
        this.rateCountEURCHF_ =0;
        this.rateCountEURGBP_ =0;
        this.rateCountEURUSD_ =0;
        this.rateCountUSDCHF_ =0;
        this.rateCountUSDEUR_ =0;
        this.rateCountUSDGBP_ =0;
        this.rate_ = new HashMap<>();
        this.rate_.put("EURCHF", new HashMap<Integer, RateData>());
        this.rate_.put("EURGBP", new HashMap<Integer, RateData>());
        this.rate_.put("EURUSD", new HashMap<Integer, RateData>());
        this.rate_.put("USDCHF", new HashMap<Integer, RateData>());
        this.rate_.put("USDEUR", new HashMap<Integer, RateData>());
        this.rate_.put("USDGBP", new HashMap<Integer, RateData>());
    }

    public synchronized void addRate(String currency, RateData r){
        Class<?> c = this.getClass();
        try {
            Field f = c.getDeclaredField("rateCount" + currency + "_");
            int currentCount = (Integer) f.get(this);
            this.rate_.get(currency).put(currentCount, r);
            f.set(this, currentCount++);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public RateData getRate(String currency, int id){
        return this.rate_.get(currency).get(id);
    }

    public RateData getLastRate(String currency){
        Class<?> c = this.getClass();
        int lastCount = 0;
        try {
            Field f = c.getDeclaredField("rateCount" + currency + "_");
            lastCount = (Integer) f.get(this);
        } catch(Exception e){
            e.printStackTrace();
        }
        return this.getRate(currency, lastCount);
    }

    public Collection<RateData> getRates(String currency){
        return this.rate_.get(currency).values();
    }
}
