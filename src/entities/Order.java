package entities;

public class Order extends Cart {

    private static final long serialVersionUID = 1L;

    int id;
    int userId;
    String date;
    boolean is_shipped;

    public boolean isIs_shipped() {
        return is_shipped;
    }

    public void setIs_shipped(boolean is_shipped) {
        this.is_shipped = is_shipped;
    }

    public Order() {
        super();
    }

    public Order(int userId, String date, boolean is_shipped) {
        this.userId = userId;
        this.date = date;
        this.is_shipped = is_shipped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
