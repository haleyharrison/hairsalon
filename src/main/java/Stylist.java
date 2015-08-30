import java.util.List;
import org.sql2o.*;

public class Stylist {
  private String name;
  private String hours;
  private String contact_info;
  private int rating;
  private int id;

  public String getName() {
    return name;
  }
  public String getHours() {
    return hours;
  }
  public String getContact_info() {
    return contact_info;
  }
  public int getRating() {
    return rating;
  }
  public int getId() {
    return id;
  }
  //constructor method, initializes an instance of Stylist
  public Stylist (String name, String hours, String contact_info, int rating, int cuisine_id) {
    this.name = name;
    this.hours = hours;
    this.contact_info = contact_info;
    this.rating = rating;
  }
  public static List<Stylist> all()  {
    String sql = "SELECT id, name, hours, contact_info, rating FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Sylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getHours().equals(newStylist.getHours()) &&
        this.getContact_info().equals(newStylist.getContact_info()) &&
        this.getRating() == newStylist.getRating() &&
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists ( name, hours, contact_info, rating) VALUES ( :name, :hours, :contact_info, :rating)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("hours", this.hours)
      .addParameter("contact_info", this.contact_info)
      .addParameter("rating", this.rating)
      .executeUpdate()
      .getKey();
    }
  }
  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
        return stylist;
    }
  }
  public static List<Stylist> allByClient(int client_id)  {
    String sql = "SELECT id, name, hours, contact_info, rating, client_id FROM stylists WHERE client_id = :client_id ";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("client_id", client_id)
        .executeAndFetch(Stylist.class);
    }
  }
  public void updateRating(int rating) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET rating = :rating WHERE id = :id";
      con.createQuery(sql)
        .addParameter("rating", rating)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateContact_info(String contact_info) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET contact_info = :contact_info WHERE id = :id";
      con.createQuery(sql)
        .addParameter("contact_info", contact_info)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void updateHours(String hours) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET hours = :hours WHERE id = :id";
      con.createQuery(sql)
        .addParameter("hours", hours)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM sylists WHERE id = :id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }
}
