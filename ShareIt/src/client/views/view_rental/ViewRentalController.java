package client.views.view_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_rental.ViewRentalViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.MaskerPane;

import java.io.IOException;
import java.nio.file.Path;
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
  @FXML private ImageView imageView;
  private Image picture;
  
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;


  private ViewHandler viewHandler;
  private ViewRentalViewModel viewRentalViewModel;


  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    viewRentalViewModel = viewModelFactory.getViewRentalViewModel();
    this.viewHandler = viewHandler;



    Bindings.bindBidirectional(this.imageView.imageProperty(), viewRentalViewModel.imagePropertyProperty());
    imageView.setImage(picture);
    nameOfRentalLabel.textProperty().bind(viewRentalViewModel.nameOfRentalProperty());
    descriptionLabel.textProperty().bind(viewRentalViewModel.descriptionOfRentalProperty());
    stateLabel.textProperty().bind(viewRentalViewModel.stateOfRentalProperty());
    priceLabel.textProperty().bind(viewRentalViewModel.priceOfRentalProperty());
    otherInformationLabel.textProperty().bind(viewRentalViewModel.otherInformationOfRentalProperty());
    categoriesLabel.textProperty().bind(viewRentalViewModel.categoryOfRentalProperty());
    usernameLabel.textProperty().bind(viewRentalViewModel.usernameOfRentalProperty());
    locationLabel.textProperty().bind(viewRentalViewModel.locationOfRentalProperty());
    ratingLabel.textProperty().bind(viewRentalViewModel.ratingOfUserOfRentalProperty());


  }

  public void searchButton(ActionEvent actionEvent)
  {

  }

  public void goBackToSearchResultsButton(ActionEvent actionEvent)
      throws IOException, SQLException, InterruptedException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
  }

  public void seeMoreButton(ActionEvent actionEvent)
  {
  }
}
