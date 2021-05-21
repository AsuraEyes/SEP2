package client.views.edit_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.edit_rental.EditRentalViewModel;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EditRentalController {
    @FXML private ImageView imageView;
    private Image picture;
    @FXML private CheckComboBox categoryBox;
    @FXML private TextField searchField;
    @FXML private AnchorPane parent;
    @FXML private ChoiceBox<String> stateBox;
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private TextField priceField;
    @FXML private TextArea otherInfoField;

    private String path;

    private ValidationSupport validationSupport;
    private EditRentalViewModel editRentalViewModel;
    private ViewHandler viewHandler;
    private Notifications notifications;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException {
        this.viewHandler = viewHandler;
        editRentalViewModel = viewModelFactory.getEditRentalViewModel();

        Bindings.bindBidirectional(this.imageView.imageProperty(), editRentalViewModel.imagePropertyProperty());
        Bindings.bindBidirectional(this.imageView.idProperty(), editRentalViewModel.getImageIdMemberId());

        nameField.textProperty().bindBidirectional(editRentalViewModel.getNameField());
        descriptionField.textProperty().bindBidirectional(editRentalViewModel.getDescriptionField());
        stateBox.setItems(editRentalViewModel.getStates());
        stateBox.getSelectionModel().selectFirst();
        priceField.textProperty().bindBidirectional(editRentalViewModel.getPriceField());
        otherInfoField.textProperty().bindBidirectional(editRentalViewModel.getOtherInfoField());
        categoryBox.getItems().addAll(editRentalViewModel.getCategories());

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

    public void editButton(ActionEvent actionEvent) throws IOException {
        boolean ok = true;
        if(checkField("Name", nameField) && checkField("Description",descriptionField) && checkField("Price", priceField) && checkPicture(imageView)){
            String message = editRentalViewModel.onEditRentalButtonPressed(stateBox.getValue(), categoryBox.getCheckModel().getCheckedItems(), path);

            switch (message){
                case "Edit successful":

                    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Rental successfully edited");
                    alert.initOwner(stage);
                    alert.getDialogPane().setContentText("Click ok to return rental.");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        viewHandler.setView(viewHandler.menu(), viewHandler.manageRentals());
                    }
                    break;
                default:
                    notifications.owner(parent).text(message).showError();
            }

        }
    }

    public void editPictureButton(ActionEvent actionEvent)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getPath();
            imageView.setFitHeight(220);
            imageView.setFitWidth(170);
            imageView.setPreserveRatio(false);
            System.out.println(path);
            imageView.setImage(new Image("file:///"+path));
            this.path = path;
        }
        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No DATA");
        }
    }

    public void onGoBack(ActionEvent actionEvent) {

    }

    private boolean checkField (String message, TextField nameOfField){
        if (nameOfField.textProperty().getValue() == null || nameOfField.textProperty().getValue().isBlank()) {
            notifications.owner(parent).text(message + " cannot be empty").showError();
            return false;
        }
        return true;
    }

    private boolean checkPicture(ImageView imageView){
        if(imageView.getImage() == null){
            notifications.owner(parent).text("Picture has to be added").showError();
            return false;
        }
        return true;
    }
}
