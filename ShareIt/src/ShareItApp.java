import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.database.member.MemberDAOImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class ShareItApp extends Application
{
  @Override
  public void start(Stage stage) throws Exception {

    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    String timothyPassword = "CoDex21";
    String maggiePassword = "SQLdatabaze";
    MemberDAOImpl.getInstance().setPassword(maggiePassword);
    viewHandler.start();
   }
}
