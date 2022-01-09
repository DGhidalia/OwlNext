package application;

import Resources.RateResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;


/**
 * Application that will route the request to the API to right class
 */
public class RateApplication extends Application {

    public RateApplication(Context context){
        super(context);
    }

    @Override
    public Restlet createInboundRoot(){
        Router router = new Router(getContext());
        router.attach("/rate/{currencyPair}", RateResource.class);
        return router;
    }
}
