package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class AddRentalController
{

  @FXML ImageView pictureView;
  @FXML private CheckComboBox categoryComboBox;
  @FXML private ChoiceBox stateChoiceBox;
  @FXML private TextField searchField;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextArea otherInfoField;
  @FXML private AnchorPane parent;
  private Notifications notifications;

  private ValidationSupport validationSupport;
  private AddRentalViewModel addRentalViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {

    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    this.viewHandler = viewHandler;

    validationSupport = new ValidationSupport();
    validationSupport.registerValidator(nameField,
        Validator.createEmptyValidator("Text is required"));


    notifications = Notifications.create().title("ASDAASDASDQweqe")
        .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
        .hideAfter(Duration.seconds(1));


        /*addRentalViewModel = viewModelFactory.getAddRentalViewModel();
        searchField.textProperty().bindBidirectional(addRentalViewModel.getSearchField());
        nameField.textProperty().bindBidirectional(addRentalViewModel.getNameField());
        descriptionField.textProperty().bind(addRentalViewModel.getDescriptionField());
        stateField.textProperty().bind(addRentalViewModel.getStateField());
        priceField.textProperty().bindBidirectional(addRentalViewModel.getPriceField());
        otherInfoField.textProperty().bind(addRentalViewModel.getOtherInfoField());*/
  }

  public void searchButton(ActionEvent actionEvent)
  {
    notifications.owner(parent).text("Search field cannot be empty")
        .showError();
  }

  public void addButton(ActionEvent actionEvent)
  {

  }

  public void addRentalButton(ActionEvent actionEvent)
  {

  }

  public void addPictureButton(ActionEvent actionEvent)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
    fileChooser.addChoosableFileFilter(filter);
    int result = fileChooser.showSaveDialog(null);
    if(result == JFileChooser.APPROVE_OPTION){
      File selectedFile = fileChooser.getSelectedFile();
      String path = selectedFile.getPath();
      pictureView.setFitHeight(220);
      pictureView.setFitWidth(170);
      pictureView.setPreserveRatio(false);
      System.out.println(path);
      pictureView.setImage(new Image("file:///"+path));
    }
    else if(result == JFileChooser.CANCEL_OPTION){
      System.out.println("No DATA");
    }
  }

  public void onGoBack(ActionEvent actionEvent)
  {

  }
}
