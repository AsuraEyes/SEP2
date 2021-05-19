package client.views.view_rental;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;

public class ViewRentalController
{
  @FXML private TextField searchField;
  
  @FXML private Label nameOfRentalLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label stateLabel;
  @FXML private Label priceLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Label categoriesLabel;
  @FXML private ImageView ImageView;
  
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;

  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler){
    this.viewHandler = viewHandler;
  }

  public void searchButton(ActionEvent actionEvent)
  {
  }

  public void goBackToSearchResultsButton(ActionEvent actionEvent)
      throws IOException, SQLException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
  }

  public void seeMoreButton(ActionEvent actionEvent)
  {
  }
}
