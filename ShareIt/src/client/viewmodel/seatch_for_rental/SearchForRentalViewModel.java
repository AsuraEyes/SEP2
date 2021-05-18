package client.viewmodel.seatch_for_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class SearchForRentalViewModel {
    private ShareItModel model;
    private final StringProperty searchField;
    private final StringProperty rentalNameLabel;
    private final StringProperty locationLabel;
    private final StringProperty priceLabel;
    private final StringProperty otherInfoLabel;

    public SearchForRentalViewModel(ShareItModel model) throws SQLException
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

    public void fireProperty(){

    }

    public void fireProperty(MouseEvent event)
    {
        //client.fireProperty
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

    public String onSearchButtonPressed() throws IOException
    {
        return model.checkSearch(searchField.getValue());
    }
}
