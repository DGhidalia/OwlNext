package Resources;

import backend.Backend;
import internals.RateData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class LastRateResource extends ServerResource {

    private Backend backend_;

    public LastRateResource(){
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getLastRate() throws JSONException{

        String currencyPair = (String) getRequest().getAttributes().get("currencyPair");

        RateData rateData = this.backend_.getDatabase().getLastRate(currencyPair);
        JSONObject jsonRate = new JSONObject();

        JSONObject current = new JSONObject();
        current.put("currency1", rateData.getCurrency1());
        current.put("currency2", rateData.getCurrency2());
        current.put("rate", rateData.getRate());
        current.put("date", rateData.getDate());

        return new JsonRepresentation(rateData);
    }
}
