package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SearchForRentalController
{
  @FXML private CheckComboBox<String> categoryCheckComboBox;
  @FXML private ChoiceBox<String> locationBox;
  @FXML private FlowPane flowPane;
  @FXML private AnchorPane parent;
  @FXML private TextField searchField;
  //private ArrayList<ImageView> imageViewArrayList;

  private SearchForRentalViewModel searchForRentalViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    this.viewHandler = viewHandler;
    searchForRentalViewModel = viewModelFactory.getSearchForRentalViewModel();
    searchField.textProperty()
        .bindBidirectional(searchForRentalViewModel.getSearchField());
    locationBox.setItems(searchForRentalViewModel.getLocations());
    locationBox.getItems().add("");
    categoryCheckComboBox.getItems()
        .addAll(searchForRentalViewModel.getCategories());
    searchForRentalViewModel.setSearchField();
    System.out.println("Controller: " + searchField.textProperty().getValue());
    if(searchField.textProperty().getValue() != null)
    {
      displayRentals(searchForRentalViewModel.onSearchButtonPressed());
    }
    else
    {
      displayRentals(searchForRentalViewModel.getRentalsList());
    }
    }

    /*searchForRentalViewModel.loadObservableNodes();
    Bindings.bindContent(flowPane.getChildren(),
        searchForRentalViewModel.getNodeObservableList());*/

    //searchForRentalViewModel.setRentals(searchForRentalViewModel.onSearchButtonPressed());


     // List<Rental> rentals = searchForRentalViewModel.onSearchButtonPressed();
      //flowPane.getChildren().clear();

  /*public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, String search) throws
          IOException
  {
    this.viewHandler = viewHandler;
    searchForRentalViewModel = viewModelFactory.getSearchForRentalViewModel();
    displayRentals(searchForRentalViewModel.getRentalsList());
    searchField.textProperty().bindBidirectional(searchForRentalViewModel.getSearchField());
    locationBox.setItems(searchForRentalViewModel.getLocations());
    locationBox.getItems().add("");
    categoryCheckComboBox.getItems().addAll(searchForRentalViewModel.getCategories());
    searchField.textProperty().setValue(search);
    notifications =  Notifications.create()
            .title("Error - invalid input!")
            .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
            .hideAfter(Duration.seconds(3));
    searchButton(new ActionEvent());
  }

*/
  public void searchButton(ActionEvent actionEvent) throws IOException
  {
    List<Rental> rentals = searchForRentalViewModel.onSearchButtonPressed();
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  public void filterButton(ActionEvent actionEvent) throws IOException
  {

    List<Rental> rentals = searchForRentalViewModel
        .onFilterButtonPressed(locationBox.getValue(),
            categoryCheckComboBox.getCheckModel().getCheckedItems());
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  public void displayRentals(List<Rental> rentals) throws RemoteException
  {
      if (rentals != null && !rentals.isEmpty())
      {
        for (int i = 0; i < rentals.size(); i++)
        {
          Image image = new Image(rentals.get(i).getPictureLink());
          ImageView imageView = new ImageView();
          imageView.setImage(image);
          imageView.setFitWidth(275);
          imageView.setPreserveRatio(true);
          imageView.setSmooth(true);
          imageView.setCache(true);
          imageView.setId(String.valueOf(rentals.get(i).getId()));
          flowPane.getChildren().add(new StackPane(new InfoOverlay(imageView, rentals.get(i).toString())));
          System.out.println(rentals.get(i).getPictureLink());
          flowPane.getChildren().get(i)
              .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                try
                {
                  viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
                  searchForRentalViewModel.getRental(event.getSource());
                }
                catch (IOException e)
                {
                  e.printStackTrace();
                }
              });
        }
      }
  }
}
