import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ShareItApp extends Application
{
  @Override
  public void start(Stage stage) throws Exception {

    //ModelFactory mf = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory();
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    viewHandler.start();
   }
}
