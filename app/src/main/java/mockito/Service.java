package mockito;

public class Service {

    private Database database;

    public Service(Database database) {
        this.database = database;
    }

    public boolean query() {
        return database.isAvailable();
    }

    public int queryId()  {
        return database.getUniqueId();
    }

    @Override
    public String toString() {
        return "Using DB with ID: " + String.valueOf(database.getUniqueId());
    }
}
