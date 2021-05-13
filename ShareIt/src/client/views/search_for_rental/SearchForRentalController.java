package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchForRentalController {
    public TextField searchField;
    private Label rentalNameLabel;
    private Label locationLabel;
    private Label priceLabel;
    private Label otherInfoLabel;

    private SearchForRentalViewModel searchForRentalViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
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
}
