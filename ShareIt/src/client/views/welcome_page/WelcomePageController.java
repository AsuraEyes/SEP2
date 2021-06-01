package client.views.welcome_page;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.welcome_page.WelcomePageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * A class that manages an interface and handle interactions in WelcomePage view
 */
public class WelcomePageController {

    @FXML private TextField searchField;

    private WelcomePageViewModel welcomePageViewModel;
    private ViewHandler viewHandler;

    /**
     * Init.
     *
     * @param viewHandler      the view handler
     * @param viewModelFactory the view model factory
     * @throws IOException
     */
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
        this.viewHandler = viewHandler;
        welcomePageViewModel = viewModelFactory.getWelcomePageViewModel();
        searchField.textProperty().bindBidirectional(welcomePageViewModel.getSearchField());
        searchField.textProperty().setValue(null);

        if(welcomePageViewModel.getUserType().equals("Administrator")){
            searchField.setPromptText("username");
        }
    }

    /**
     * Runs method from viewModel after button was pressed and type of user was checked.
     *
     * @throws IOException
     */
    public void searchButton() throws IOException {
        welcomePageViewModel.setSearchText();
        if(welcomePageViewModel.getUserType().equals("Administrator")){
            viewHandler.setView(viewHandler.menu(), viewHandler.searchForMember());
        }
        else{
            viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
        }
    }
}
