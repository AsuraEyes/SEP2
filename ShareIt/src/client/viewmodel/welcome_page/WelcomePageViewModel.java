package client.viewmodel.welcome_page;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WelcomePageViewModel {
    private ShareItModel model;
    private final StringProperty searchField;

    public WelcomePageViewModel(ShareItModel model){
        searchField = new SimpleStringProperty();
        this.model = model;
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public String getUserType(){
        return model.checkUserType();
    }

    public void setSearchText(){
        model.getSearchText(searchField.getValue());
    }
}
