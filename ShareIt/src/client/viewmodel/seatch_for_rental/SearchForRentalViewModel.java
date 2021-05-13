package client.viewmodel.seatch_for_rental;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchForRentalViewModel {
    private final StringProperty searchField;
    private final StringProperty rentalNameLabel;
    private final StringProperty locationLabel;
    private final StringProperty priceLabel;
    private final StringProperty otherInfoLabel;

    public SearchForRentalViewModel(){
        searchField = new SimpleStringProperty("Search");
        rentalNameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        priceLabel = new SimpleStringProperty();
        otherInfoLabel = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public StringProperty getRentalNameLabel(){
        return rentalNameLabel;
    }

    public StringProperty getLocationLabel(){
        return locationLabel;
    }

    public StringProperty getPriceLabel(){
        return priceLabel;
    }

    public StringProperty getOtherInfoLabel(){
        return otherInfoLabel;
    }
}
