package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.sql.SQLException;

public class AddRentalController
{
  @FXML private ChoiceBox categoryBox;
  @FXML private ChoiceBox stateBox;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextArea otherInfoField;
  @FXML private AnchorPane parent;

  private AddRentalViewModel addRentalViewModel;
  private ViewHandler viewHandler;
  private ValidationSupport validationSupport;
  private Notifications notifications;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException
  {
    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    this.viewHandler = viewHandler;
    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    nameField.textProperty().bindBidirectional(addRentalViewModel.getNameField());
    descriptionField.textProperty().bind(addRentalViewModel.getDescriptionField());
    stateBox.setItems(addRentalViewModel.getStates());
    stateBox.getSelectionModel().selectFirst();
    priceField.textProperty().bindBidirectional(addRentalViewModel.getPriceField());
    otherInfoField.textProperty().bind(addRentalViewModel.getOtherInfoField());
    categoryBox.setItems(addRentalViewModel.getCategories());
    categoryBox.getSelectionModel().selectFirst();

    notifications =  Notifications.create()
            .title("Error - invalid input!")
            .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
            .hideAfter(Duration.seconds(3));
  }

  public void searchButton(ActionEvent actionEvent)
  {
    notifications.owner(parent).text("Search field cannot be empty")
        .showError();
  }

  public void addButton(ActionEvent actionEvent)
  {

  }
}
