package backend;

import database.InMemoryDatabase;

public class Backend {

    private InMemoryDatabase database_;

    public Backend()
    {
        database_ = new InMemoryDatabase();
    }

    public InMemoryDatabase getDatabase()
    {
        return database_;
    }
}
