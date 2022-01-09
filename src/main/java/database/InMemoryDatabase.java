package database;

import internals.RateData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Database of the application, wiped after each application closed
 */
public class InMemoryDatabase {

    private Map<String, ArrayList<RateData>> rates_;

    public InMemoryDatabase() {
        this.rates_ = new HashMap<>();
        this.rates_.put("EURCHF", new ArrayList<RateData>());
        this.rates_.put("EURGBP", new ArrayList<RateData>());
        this.rates_.put("EURUSD", new ArrayList<RateData>());
        this.rates_.put("USDCHF", new ArrayList<RateData>());
        this.rates_.put("USDEUR", new ArrayList<RateData>());
        this.rates_.put("USDGBP", new ArrayList<RateData>());
    }

    public synchronized void addRate(String currency, RateData r){
        this.rates_.get(currency).add(r);
    }

    public RateData getRate(String currency, int id){
        return this.rates_.get(currency).get(id);
    }

    public RateData getLastRate(String currency){
        int size = this.rates_.get(currency).size();
        return this.rates_.get(currency).get(size-1);
    }

    public Collection<RateData> getRates(String currency){
        return this.rates_.get(currency);
    }
}
