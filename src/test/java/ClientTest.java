import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

  public class CuisineTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
    }

    @Test
    public void equals_returnTrueIfClientsAreTheSame() {
      Client firstClient = new Client("leslie knope");
      Client secondClient = new Client("ann perkins");
      assertTrue(firstClient.equals(secondClient));
    }
    @Test
    public void save_savesIntoDatabase_true() {
      Client myClient = new Client("april ludgate");
      myClient.save();
      assertTrue(Client.all().get(0).equals(myClient));
    }
    @Test
    public void find_findClientInDatabase_true() {
      Client myClient = new Client("donna meagle");
      myClient.save();
      Client savedClient = Client.find(myClient.getId());
      assertTrue(myClient.equals(savedClient));
    }
    @Test
    public void update_updatesInformationForAnObject() {
      Client newClient = new Client("ben wyatt");
      newClient.save();
      newClient.update("ron swanson");
      Client savedClient = Client.find(newClient.getId());
      assertEquals("tom haverford", savedClient.getName());
    }

    @Test
    public void delete_checkThatDeletesFromDatabase_false() {
      Client newClient = new Client("andy dwyer");
      newClient.save();
      newClient.delete();
      Client otherClient = Client.find(newClient.getId());
      assertEquals(false, newClient.equals(otherClient));
    }
}
