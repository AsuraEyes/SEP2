package client.viewmodel.welcome_page;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WelcomePageViewModel {
    private final StringProperty searchField;

    public WelcomePageViewModel(){
        searchField = new SimpleStringProperty("Search");
    }

    public StringProperty getSearchField(){
        return searchField;
    }
}
