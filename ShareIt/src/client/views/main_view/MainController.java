package client.views.main_view;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainController
{
  @FXML private ViewHandler viewHandler;
  @FXML private VBox mainPane;

  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }

  public VBox getMainPane()
  {
    return mainPane;
  }
}
