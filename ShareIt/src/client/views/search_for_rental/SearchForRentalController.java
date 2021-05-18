package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class SearchForRentalController {
    private AnchorPane parent;
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
        rentalNameLabel.textProperty().bind(searchForRentalViewModel.getRentalNameLabel());
        locationLabel.textProperty().bind(searchForRentalViewModel.getLocationLabel());
        priceLabel.textProperty().bind(searchForRentalViewModel.getPriceLabel());
        otherInfoLabel.textProperty().bind(searchForRentalViewModel.getOtherInfoLabel());
    }

    public void searchButton(ActionEvent actionEvent) {

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
