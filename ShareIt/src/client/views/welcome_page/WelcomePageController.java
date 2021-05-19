package client.views.welcome_page;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.welcome_page.WelcomePageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WelcomePageController {

    @FXML private TextField searchField;

    private WelcomePageViewModel welcomePageViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
        this.viewHandler = viewHandler;
        welcomePageViewModel = viewModelFactory.getWelcomePageViewModel();
        //searchField.textProperty().bindBidirectional(welcomePageViewModel.getSearchField());

        if(welcomePageViewModel.getUserType().equals("Administrator")){
            searchField.setPromptText("username");
        }
    }

    public void searchButton(ActionEvent actionEvent) throws IOException {
        viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental(searchField.textProperty().getValue()));
    }
}
