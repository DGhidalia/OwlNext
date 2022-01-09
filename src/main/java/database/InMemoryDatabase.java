package database;

import internals.RateData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Database of the application, wiped after each application closed
 */
public class InMemoryDatabase {

    private int rateCount_;

    private Map<Integer, RateData> rate_;

    public InMemoryDatabase() {
        this.rateCount_ =0;
        this.rate_ = new HashMap<>();
    }

    public synchronized void addRate(RateData r){
        this.rate_.put(rateCount_, r);
        this.rateCount_++;
    }

    public RateData getRate(int id){
        return this.rate_.get(id);
    }

    public Collection<RateData> getRates(){
        return this.rate_.values();
    }
}
