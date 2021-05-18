package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class SearchForRentalController {
   @FXML private CheckComboBox categoryCheckComboBox;
   @FXML private ChoiceBox<String> locationBox;
  @FXML private FlowPane flowPane;
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

      for (int i = 0; i < searchForRentalViewModel.getRentalsList().size(); i++)
      {
        Image image = new Image(searchForRentalViewModel.getRentalsList().get(i).getPictureLink(),220,220,false,false);
        flowPane.getChildren().add(new StackPane(new InfoOverlay(new ImageView(image),searchForRentalViewModel.getRentalsList().get(i).toString())));
        System.out.println(searchForRentalViewModel.getRentalsList().get(i).getPictureLink());
        //flowPane.getChildren().get(i).setOnMouseClicked(searchForRentalViewModel::fireProperty);
        flowPane.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
          try
          {
            //getPicture(event.getTarget());
            viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        });
      }
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


    public void filterButton(ActionEvent actionEvent) throws IOException {
      if(checkField(searchField))
      {
        String message = searchForRentalViewModel.onFilterButtonPressed(locationBox.getValue(), categoryCheckComboBox.getCheckModel().getCheckedItems());
        switch (message){
          case "Adding successfull":
            break;
          default:
            notifications.owner(parent).text(message).showError();
        }
      }
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
