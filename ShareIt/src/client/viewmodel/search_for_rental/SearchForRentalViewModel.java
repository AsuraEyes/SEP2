package client.viewmodel.search_for_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class SearchForRentalViewModel {
    private ShareItModel model;
    private final StringProperty searchField;
    private final StringProperty locationLabel;
    private ObservableList<String> locationsList;
    private ObservableList<String> categoriesList;

    public SearchForRentalViewModel(ShareItModel model)
    {
        this.model = model;
        searchField = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public StringProperty getLocationLabel(){
        return locationLabel;
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
                           model.sendSelectedRental(getRentalsList().get(i));
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
        return model.checkSearch(searchField.getValue());
    }

    public List<Rental> onFilterButtonPressed(String selectedCity,ObservableList<String> selectedCategory) throws IOException
    {
        ArrayList<String> selectedCategoriesList = new ArrayList<>(selectedCategory);
        return model.checkSearchWithFilter(searchField.getValue(),selectedCity, selectedCategoriesList);
    }
    public ArrayList<Rental> getRentalsList()
    {
        return model.getRentalsList();
    }

    public void setSearchField(){
        searchField.setValue(model.getSearchText());
    }

}
