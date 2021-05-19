package client.viewmodel.add_rental;

import client.core.ClientFactory;
import client.model.ShareItModel;
import client.model.ShareItModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Category;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

public class AddRentalViewModelTest
{
  private AddRentalViewModel vm;

  @BeforeEach
  public void setup() throws IOException, SQLException
  {
    ClientFactory clientFactory = new ClientFactory();
    ShareItModel model = new ShareItModelManager(clientFactory.getClient());
    vm = new AddRentalViewModel(model);
  }
  @Test
  public void addvalidRental() throws IOException
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty pictureLinkField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();



    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);

    nameField.setValue("new drill");
    pictureLinkField.setValue("url");
    descriptionField.setValue("description");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");

    Member m = new Member(1);



    /*String result = vm.onAddRentalButtonPressed(m);
    assertEquals("Adding successful", result);*/
  }

}
