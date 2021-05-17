import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.database.member.MemberDAOImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class ShareItApp extends Application
{
  @Override
  public void start(Stage stage) throws Exception {
    //ModelFactory mf = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory();
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    MemberDAOImpl.getInstance().setPassword("SQLdatabaze");
    viewHandler.start();
   }
}
