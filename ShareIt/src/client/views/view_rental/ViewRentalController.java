package client.views.view_rental;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.text.View;
import java.io.IOException;

public class ViewRentalController
{
  public TextField searchField;
  
  public Label nameOfRentalLabel;
  public Label descriptionLabel;
  public Label stateLabel;
  public Label priceLabel;
  public Label otherInformationLabel;
  public Label categoriesLabel;
  public ImageView ImageView;
  
  public Label usernameLabel;
  public Label locationLabel;
  public Label ratingLabel;

  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler){
    this.viewHandler = viewHandler;
  }

  public void searchButton(ActionEvent actionEvent)
  {
  }

  public void goBackToSearchResultsButton(ActionEvent actionEvent)
      throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
  }

  public void seeMoreButton(ActionEvent actionEvent)
  {
  }
}
