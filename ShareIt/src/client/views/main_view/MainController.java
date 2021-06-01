package client.views.main_view;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * A class that manages an interface and handle interactions in Main view
 */
public class MainController
{
  @FXML
  private ViewHandler viewHandler;
  @FXML
  private VBox mainPane;

  /**
   * Init.
   *
   * @param viewHandler the view handler
   */
  public void init(ViewHandler viewHandler){
    this.viewHandler = viewHandler;
  }

  /**
   * Gets main pane.
   *
   * @return the main pane
   */
  public VBox getMainPane()
  {
    return mainPane;
  }
}
