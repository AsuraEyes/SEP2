package client.viewmodel.seatch_for_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;

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

    public SearchForRentalViewModel(ShareItModel model)
    {
        this.model = model;
        searchField = new SimpleStringProperty();
        rentalNameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        priceLabel = new SimpleStringProperty();
        otherInfoLabel = new SimpleStringProperty();
    }

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
                       //if (imageView.getImage().getUrl().equals(getRentalsList().get(i).getPictureLink()))
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
    /*public Picture getPicture(Object object){
        if(object instanceof ImageView)
        {
            for (int i = 0; i < pictures.size(); i++)
            {
                if (((ImageView) object).getImage().getUrl().equals(pictures.get(i).getImage().getUrl()))
                {
                    System.out.println(pictures.get(i).toString());
                    return pictures.get(i);
                }
            }
        }
        else if(object instanceof LabeledText){
            for (int i = 0; i < pictures.size(); i++)
            {
                if(((LabeledText)object).getText().equals(pictures.get(i).toString())){
                    System.out.println(pictures.get(i));
                    return pictures.get(i);
                }
            }
        }
        return null;
    }*/
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
    public ArrayList<Rental> getRentalsList() throws RemoteException
    {
        return model.getRentalsList();
    }
}
