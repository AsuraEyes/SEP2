package client.viewmodel.add_rental;

import client.core.ClientFactory;
import client.model.ShareItModel;
import client.model.ShareItModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.transferobjects.Category;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRentalViewModelTest
{
  private AddRentalViewModel vm;

  @BeforeEach public void setup() throws IOException, SQLException
  {
    ClientFactory clientFactory = new ClientFactory();
    ShareItModel model = new ShareItModelManager(clientFactory.getClient());
    vm = new AddRentalViewModel(model);
  }

  @Test public void addValidRental() throws IOException
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);

    nameField.setValue("new drill");
    descriptionField.setValue("description");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");

    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
    String pictureLink = "qwe";

    String result = vm.onAddRentalButtonPressed("New", objects, pictureLink);

    assertEquals("Adding successful", result);

  }

  @Test public void nameNotGiven() throws IOException
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);

    nameField.setValue("");
    descriptionField.setValue("description");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");


    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");


    String pictureLink = "qwe";
    String result = vm.onAddRentalButtonPressed("New", objects, pictureLink);
    assertEquals("Name cannot be empty", objects);
  }

  @Test public void descriptionNotGiven() throws IOException
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

    nameField.setValue("ae86");
    pictureLinkField.setValue("url");
    descriptionField.setValue("");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");


    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
    String pictureLink = "qwe";
    String result = vm.onAddRentalButtonPressed("New", objects, pictureLink);
    assertEquals("Description cannot be empty", result);
  }

  @Test public void numberIsntNumber() throws IOException
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

    nameField.setValue("dupadupadupa");
    pictureLinkField.setValue("url");
    descriptionField.setValue("description");
    priceField.setValue("XD");
    otherInfoField.setValue("otherinfo");


    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");

    String pictureLink = "qwe";
    String result = vm.onAddRentalButtonPressed("New", objects, pictureLink);
    assertEquals("Price is a not number", result);
  }

}
