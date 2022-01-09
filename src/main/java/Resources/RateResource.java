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

/**
 * Gives the representation of each currency pair's rate
 */
public class RateResource extends ServerResource {

    private Backend backend_;

    public RateResource(){
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getRates() throws JSONException{
        Collection<RateData> rateDatas = this.backend_.getDatabase().getRates();
        Collection<JSONObject> jsonRates = new ArrayList<>();

        for(RateData rd: rateDatas){
            JSONObject current = new JSONObject();
            current.put("currency1", rd.getCurrency1());
            current.put("currency2", rd.getCurrency2());
            current.put("rate", rd.getRate());
            current.put("date", rd.getDate());
            jsonRates.add(current);
        }

        JSONArray jsonArray = new JSONArray(jsonRates);
        return new JsonRepresentation(jsonArray);
    }
}
