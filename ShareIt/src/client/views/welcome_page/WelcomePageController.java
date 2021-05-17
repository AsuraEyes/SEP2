package client.views.welcome_page;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.welcome_page.WelcomePageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WelcomePageController {

    @FXML private TextField searchField;

    private WelcomePageViewModel welcomePageViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        welcomePageViewModel = viewModelFactory.getWelcomePageViewModel();
        //searchField.textProperty().bindBidirectional(welcomePageViewModel.getSearchField());
    }

    public void searchButton(ActionEvent actionEvent) {

    }
}
