import application.RateApplication;
import backend.Backend;
import internals.Rate;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OwlNext {

    private static Backend backend;

    public OwlNext(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter first currency: ");
            String currency1 = sc.nextLine();
            System.out.println("Enter second currency: ");
            String currency2 = sc.nextLine();
            Rate test = new Rate(currency1, currency2, backend);
            test.start();
        }
    }

    /**
     * Enter both currencies in command line then you get the corresponding rate
     */
    public static void main (String[] args) throws Exception{
        // Create a component
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8124);

        // Create an application
        Application application = new RateApplication(context);

        // Add the backend into component's context
        backend = new Backend();
        context.getAttributes().put("backend", backend);
        component.getDefaultHost().attach(application);

        // Start the component
        component.start();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        //Stores each pair of currency every 10 minutes
        scheduler.scheduleAtFixedRate(new Rate("EUR", "CHF", backend),0 ,10, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new Rate("EUR", "GBP", backend),0 ,10, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new Rate("EUR", "USD", backend),0 ,10, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new Rate("USD", "CHF", backend),0 ,10, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new Rate("USD", "EUR", backend),0 ,10, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(new Rate("USD", "GBP", backend),0 ,10, TimeUnit.MINUTES);

        new OwlNext();
    }
}
