package client.viewmodel.seatch_for_rental;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchForRentalViewModel {
    private ShareItModel model;
    private final StringProperty searchField;
    private final StringProperty rentalNameLabel;
    private final StringProperty locationLabel;
    private final StringProperty priceLabel;
    private final StringProperty otherInfoLabel;
    private ObservableList<String> locationsList;
    private ObservableList<String> categoriesList;
    private ObservableList<Rental> rentalsList;
    private ObservableList<Node> nodeObservableList;
    private ArrayList<Node> nodes = new ArrayList<>();

    public SearchForRentalViewModel(ShareItModel model)
    {
        this.model = model;
        searchField = new SimpleStringProperty();
        rentalNameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        priceLabel = new SimpleStringProperty();
        otherInfoLabel = new SimpleStringProperty();
       // model.addListener("searchText", this::searchText);
    }

    /*private void searchText(PropertyChangeEvent evt)
    {
        Platform.runLater(()-> {
            if(evt.getNewValue() == null){
                searchField.setValue("");
                try
                {
                    onSearchButtonPressed();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else{
                searchField.setValue(evt.getNewValue().toString());
                try
                {
                    onSearchButtonPressed();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }*/

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


    public void getRental(Object object) throws RemoteException
    {
       if(object instanceof StackPane){
           StackPane stackPane = (StackPane) object;
           if(stackPane.getChildren().get(0) instanceof InfoOverlay)
           {
               InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
               if(infoOverlay.getContent() instanceof ImageView)
               {
                   ImageView imageView = (ImageView) infoOverlay.getContent();
                   for (int i = 0; i < getRentalsList().size(); i++)
                   {
                       if(imageView.getId().equals(String.valueOf(getRentalsList().get(i).getId())))
                       {
                           model.getSelectedRental(getRentalsList().get(i));
                           break;
                       }
                   }
               }
           }
       }
    }
    public ObservableList<String> getLocations(){
        ArrayList<City> cityList = model.getCityList();
        ArrayList<String> cityListString = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            cityListString.add(cityList.get(i).toString());
        }
        locationsList = FXCollections.observableArrayList(cityListString);
        return locationsList;
    }

    public ObservableList<String> getCategories(){
        ArrayList<Category> categoryList = model.getCategoryList();
        ArrayList<String> categoryListString = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryListString.add(categoryList.get(i).toString());
        }
        categoriesList = FXCollections.observableArrayList(categoryListString);
        return categoriesList;
    }

    public List<Rental> onSearchButtonPressed() throws IOException
    {
        //setRentals(model.checkSearch(searchField.getValue()));
        return model.checkSearch(searchField.getValue());
    }

    public List<Rental> onFilterButtonPressed(String selectedCity,ObservableList<String> selectedCategory) throws IOException
    {
        ArrayList<String> selectedCategoriesList = new ArrayList<>(selectedCategory);
        return model.checkSearchWithFilter(searchField.getValue(),selectedCity, selectedCategoriesList);
    }
    public ArrayList<Rental> getRentalsList() throws RemoteException
    {
        return model.getRentalsList();
    }

    public void setSearchField(){

        searchField.setValue(model.getSearchText());
        System.out.println("View model: " + searchField.getValue());
    }
    /*public void setRentals(List<Rental> rentals)
    {
        this.rentalsList = FXCollections.observableArrayList(rentals);
    }
    public ObservableList<Rental> getRentals(){
        return rentalsList;
    }
    public ObservableList<Node> getNodeObservableList(){
        return nodeObservableList;
    }
    public void loadNodes() throws RemoteException
    {
       // if (rentalsList != null && !rentalsList.isEmpty())
      //  {
            for (int i = 0; i < getRentalsList().size(); i++)
            {
                Image image = new Image(getRentalsList().get(i).getPictureLink());
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(275);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setId(String.valueOf(getRentalsList().get(i).getId()));
                nodes.add(new StackPane(new InfoOverlay(imageView, getRentalsList().get(i).toString())));
            }
   // }

}
    public void loadObservableNodes() throws RemoteException
    {
        loadNodes();
        nodeObservableList = FXCollections.<Node>observableArrayList(nodes);
 }*/
}
