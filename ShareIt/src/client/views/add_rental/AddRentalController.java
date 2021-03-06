package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in AddRental view
 */
public class AddRentalController {

  @FXML private ImageView pictureView;
  @FXML private CheckComboBox<String> categoryBox;
  @FXML private AnchorPane parent;
  @FXML private ChoiceBox<String> stateBox;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextArea otherInfoField;

  private AddRentalViewModel addRentalViewModel;
  private ViewHandler viewHandler;
  private Notifications notifications;

  /**
   * Init.
   * Instantiates the notification feature
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {

    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    this.viewHandler = viewHandler;
    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    nameField.textProperty()
        .bindBidirectional(addRentalViewModel.getNameField());
    descriptionField.textProperty()
        .bindBidirectional(addRentalViewModel.getDescriptionField());
    stateBox.setItems(addRentalViewModel.getStates());
    stateBox.getSelectionModel().selectFirst();
    priceField.textProperty()
        .bindBidirectional(addRentalViewModel.getPriceField());
    otherInfoField.textProperty()
        .bindBidirectional(addRentalViewModel.getOtherInfoField());
    categoryBox.getItems().addAll(addRentalViewModel.getCategories());
    Bindings.bindBidirectional(pictureView.imageProperty(),
        addRentalViewModel.imagePropertyProperty());

    notifications = Notifications.create().title("Error - invalid input!")
        .graphic(new Rectangle(300, 300, Color.RED))
        .hideAfter(Duration.seconds(3));
  }

  /**
   * If data is valid it uses a method from viewModel
   *
   */
  public void addRentalButton()
  {
    if (checkField("Name", nameField) && checkField("Description",
        descriptionField) && checkField("Price", priceField) && checkPicture(
        pictureView))
    {
      String message = addRentalViewModel
          .onAddRentalButtonPressed(stateBox.getValue(),
              categoryBox.getCheckModel().getCheckedItems());

      switch (message)
      {
        case "Adding successful":

          Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
          alert.setTitle("Confirmation");
          alert.setHeaderText("New rental successfully created");
          alert.initOwner(stage);
          alert.getDialogPane()
              .setContentText("Click ok to get to manage account page.");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK)
          {
            nameField.clear();
            descriptionField.clear();
            priceField.clear();
            otherInfoField.clear();
            addRentalViewModel.setAllMemberRentals();
            pictureView.setImage(null);
            viewHandler
                .setView(viewHandler.menu(), viewHandler.manageAccount());
          }
          break;
        default:
          notifications.owner(parent).text(message).showError();
      }

    }
  }

  /**
   * Handles adding a picture. It allows the member to browse available pictures
   * from their computer by using a GUI windows browser, implemented with the
   * help of JFileChooser.
   *
   */
  public void addPictureButton()
  {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select an image");
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All files", "*.*"),
            new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.gif", "*.png", "*.jpeg")
    );

    File file = fileChooser.showOpenDialog(viewHandler.getStage());

    if (file != null)
    {
      String path = file.getPath();
      pictureView.setFitHeight(220);
      pictureView.setFitWidth(170);
      pictureView.setPreserveRatio(false);
      pictureView.setImage(new Image("file:///" + path));
    }
  }

  public void onGoBack()
  {
    nameField.clear();
    descriptionField.clear();
    priceField.clear();
    otherInfoField.clear();
    pictureView.setImage(null);
    viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
  }

      /**
       * Validates data
       * @param message name of field in string in case of invalid data
       * @param nameOfField name of field
       * @return true if data is valid, false if not
       */
  private boolean checkField(String message, TextField nameOfField)
  {
    if (nameOfField.textProperty().getValue() == null || nameOfField
        .textProperty().getValue().isBlank())
    {
      notifications.owner(parent).text(message + " cannot be empty")
          .showError();
      return false;
    }
    return true;
  }

      /**
       * Checks if picture was added
       * @param imageView picture
       * @return true if picture was added, false if not
       */
  private boolean checkPicture(ImageView imageView)
  {
    if (imageView.getImage() == null)
    {
      notifications.owner(parent).text("Picture has to be added").showError();
      return false;
    }
    return true;
  }
}
