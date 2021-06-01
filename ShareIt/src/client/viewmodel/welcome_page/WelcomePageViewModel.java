package client.viewmodel.welcome_page;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the WelcomePage view.
 */
public class WelcomePageViewModel {
    private ShareItModel model;
    private final StringProperty searchField;

    /**
     * Instantiates a new WelcomePageViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public WelcomePageViewModel(ShareItModel model){
        searchField = new SimpleStringProperty();
        this.model = model;
    }

    /**
     * Gets searchField.
     *
     * @return returns searchField input
     */
    public StringProperty getSearchField(){
        return searchField;
    }

    /**
     * Checks user type.
     *
     * @return returns which type of account is viewing profile
     */
    public String getUserType(){
        return model.checkUserType();
    }

    /**
     * Sets search text.
     */
    public void setSearchText(){
        model.setSearchText(searchField.getValue());
    }
}
