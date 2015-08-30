import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

  public class StylistTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
    }

    @Test
    public void equals_returnTrueIfStylistsAreTheSame() {
//      Stylist firstStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
//      Stylist secondStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
      assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesIntoDatabase_true() {
//      Stylist myStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
    }

    @Test
    public void find_findStylistInDatabase_true() {
  //    Stylist myStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
      myStylist.save();
      Stylist savedStylist = Stylist.find(myStylist.getId());
      assertTrue(myStylist.equals(savedStylist));
    }

    @Test
    public void update_updatesInformationForAnObject() {
    //  Stylist newStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
      newStylist.save();
      newStylist.updateRating(1);
      Stylist savedStylist = Stylist.find(newStylist.getId());
      assertEquals(1, savedStylist.getRating());
    }

    @Test
    public void delete_checkThatDeletesFromDatabase_false() {
  //    Stylist newStylist = new Stylist("mi mero mole", "9:00-15-00", "23424231", 5, 1);
      newStylist.save();
      newStylistdelete();
      Stylist newStylist = Stylist.find(newStylist.getId());
      assertEquals(false, newStylist.equals(newStylist));
    }
  }
