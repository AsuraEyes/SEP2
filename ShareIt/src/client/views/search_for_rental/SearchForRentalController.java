package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class SearchForRentalController {
    @FXML private AnchorPane parent;
    @FXML private TextField searchField;
    @FXML private Label rentalNameLabel;
    private Label locationLabel;
    private Label priceLabel;
    private Label otherInfoLabel;

    private SearchForRentalViewModel searchForRentalViewModel;
    private ViewHandler viewHandler;
    private Notifications notifications;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws
        SQLException, IOException
    {

       this.viewHandler = viewHandler;
        searchForRentalViewModel = viewModelFactory.getSearchForRentalViewModel();
        searchField.textProperty().bindBidirectional(searchForRentalViewModel.getSearchField());
       /* rentalNameLabel.textProperty().bind(searchForRentalViewModel.getRentalNameLabel());
        locationLabel.textProperty().bind(searchForRentalViewModel.getLocationLabel());
        priceLabel.textProperty().bind(searchForRentalViewModel.getPriceLabel());
        otherInfoLabel.textProperty().bind(searchForRentalViewModel.getOtherInfoLabel());*/
      notifications =  Notifications.create()
          .title("Error - invalid input!")
          .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
          .hideAfter(Duration.seconds(3));
    }


      public void searchButton(ActionEvent actionEvent) throws IOException
      {
        if(checkField(searchField)){
          String message = searchForRentalViewModel.onSearchButtonPressed();
          switch (message){
            case "Adding successful":
              break;
            default:
              notifications.owner(parent).text(message).showError();
          }
        }
      }


    public void filterLocationButton(ActionEvent actionEvent) {

    }

    public void filterCategoryButton(ActionEvent actionEvent) {

    }

    public void loadMoreRentals(ActionEvent actionEvent){

    }

    private boolean checkField(TextField nameOfField){
        if(nameOfField.textProperty().getValue() == null || nameOfField.textProperty().getValue().isBlank()){
            notifications.owner(parent).text(nameOfField.getPromptText()+" cannot be empty").showError();
            return false;
        }
        return true;
    }
}
