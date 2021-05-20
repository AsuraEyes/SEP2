package client.viewmodel.view_rental;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewRentalViewModel
{
  private ShareItModel shareItModel;

  private StringProperty nameOfRental;
  private StringProperty descriptionOfRental;
  private StringProperty stateOfRental;
  private StringProperty priceOfRental;
  private StringProperty otherInformationOfRental;
  private StringProperty categoryOfRental;
  private StringProperty usernameOfRental;
  private StringProperty locationOfRental;
  private StringProperty ratingOfUserOfRental;
  private StringProperty imageIdMemberId;
  private ObjectProperty<Image> imageProperty;

  public ViewRentalViewModel(ShareItModel shareItModel){
    this.shareItModel = shareItModel;
    imageProperty = new SimpleObjectProperty<>();
    imageIdMemberId = new SimpleStringProperty();
    nameOfRental = new SimpleStringProperty();
    descriptionOfRental = new SimpleStringProperty();
    stateOfRental = new SimpleStringProperty();
    priceOfRental = new SimpleStringProperty();
    otherInformationOfRental = new SimpleStringProperty();
    categoryOfRental = new SimpleStringProperty();
    usernameOfRental = new SimpleStringProperty();
    locationOfRental = new SimpleStringProperty();
    ratingOfUserOfRental = new SimpleStringProperty();


    shareItModel.addListener("selectedRental",this::selectedRental);
  }

  private void selectedRental(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {

      if (evt.getNewValue() instanceof Rental)
      {
        Rental rental = (Rental) evt.getNewValue();
        nameOfRental.setValue(rental.getName());
        descriptionOfRental.setValue(rental.getDescription());
        stateOfRental.setValue(rental.getStateName());
        priceOfRental.setValue(String.valueOf(rental.getPrice()));
        imageProperty.setValue(new Image(rental.getPictureLink()));
        if(rental.getOtherInformation() !=null)
        {
          otherInformationOfRental.setValue(rental.getOtherInformation());
        }
        if(rental.getSelectedCategories() != null)
        {
          categoryOfRental.setValue(rental.getSelectedCategories().toString());
        }
        imageIdMemberId.setValue(String.valueOf(rental.getMemberId()));
        usernameOfRental.setValue(shareItModel.getMemberById(rental.getMemberId()).getUsername());
        locationOfRental.setValue(shareItModel.getMemberById(rental.getMemberId()).getAddressCity());
        ratingOfUserOfRental.setValue(String.valueOf(shareItModel.getMemberById(rental.getMemberId()).getAverageReview()));
      }
    });
  }

  public StringProperty nameOfRentalProperty()
  {
    return nameOfRental;
  }

  public StringProperty descriptionOfRentalProperty()
  {
    return descriptionOfRental;
  }

  public StringProperty stateOfRentalProperty()
  {
    return stateOfRental;
  }


  public StringProperty priceOfRentalProperty()
  {
    return priceOfRental;
  }

  public StringProperty otherInformationOfRentalProperty()
  {
    return otherInformationOfRental;
  }

  public StringProperty categoryOfRentalProperty()
  {
    return categoryOfRental;
  }

  public StringProperty usernameOfRentalProperty()
  {
    return usernameOfRental;
  }

  public StringProperty locationOfRentalProperty()
  {
    return locationOfRental;
  }

  public StringProperty ratingOfUserOfRentalProperty()
  {
    return ratingOfUserOfRental;
  }

  public ObjectProperty<Image> imagePropertyProperty()
  {
    return imageProperty;
  }

  public void getMemberById()
  {
    shareItModel.getMemberById(Integer.parseInt(imageIdMemberId.getValue()));
  }
  public StringProperty getImageIdMemberId(){
    return imageIdMemberId;
  }
}
