package application;

import Resources.RateResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RateApplication extends Application {

    public RateApplication(Context context){
        super(context);
    }

    @Override
    public Restlet createInboundRoot(){
        Router router = new Router(getContext());
        router.attach("/rate", RateResource.class);
        return router;
    }
}
