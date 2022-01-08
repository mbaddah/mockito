package mockito;

public class Database {

    private int id = 42;
    public boolean isAvailable() {
        return false;
    }

    public int getUniqueId() {
        return id;
    }

    public void setUniqueId(int x) {
        id = x;

    }
}
