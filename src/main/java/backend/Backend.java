package backend;

import database.InMemoryDatabase;

/**
 * Backend containig the database
 */
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
