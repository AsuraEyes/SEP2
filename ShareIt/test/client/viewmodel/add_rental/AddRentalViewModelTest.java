//package client.viewmodel.add_rental;
//
//import client.core.ClientFactory;
//import client.model.ShareItModel;
//import client.model.ShareItModelManager;
//import client.model.rental.RentalModel;
//import client.model.rental.RentalModelManager;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.image.Image;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import shared.transferobjects.Category;
//import shared.transferobjects.Member;
//import shared.transferobjects.State;
//
//import javax.swing.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class AddRentalViewModelTest
//{
//  private AddRentalViewModel vm;
//
//  @BeforeEach public void setup() throws IOException, SQLException
//  {
//    ClientFactory clientFactory = new ClientFactory();
//    RentalModel model = new RentalModelManager(clientFactory.getClient());
//    vm = new AddRentalViewModel(model);
//
//    JFrame frame = new JFrame("Java Swing And JavaFX");
//    JFXPanel jfxPanel = new JFXPanel();
//    frame.add(jfxPanel);
//  }
//
//  @Test public void addValidRental() throws IOException
//  {
//    StringProperty nameField = new SimpleStringProperty();
//    StringProperty descriptionField = new SimpleStringProperty();
//    StringProperty priceField = new SimpleStringProperty();
//    StringProperty otherInfoField = new SimpleStringProperty();
//    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
//
//
//    vm.getNameField().bind(nameField);
//    vm.getDescriptionField().bind(descriptionField);
//    vm.getPriceField().bind(priceField);
//    vm.getOtherInfoField().bind(otherInfoField);
//    vm.imagePropertyProperty().bind(imageProperty);
//
//    nameField.setValue("new drill");
//    descriptionField.setValue("description");
//    priceField.setValue("15");
//    otherInfoField.setValue("otherinfo");
//    imageProperty.setValue(new Image("file:image1.jpeg"));
//
//    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
//
//    String result = vm.onAddRentalButtonPressed("New", objects);
//
//
//    assertEquals("Adding successful", result);
//
//  }
//
//  @Test public void nameNotGiven() throws IOException
//  {
//    StringProperty nameField = new SimpleStringProperty();
//    StringProperty descriptionField = new SimpleStringProperty();
//    StringProperty priceField = new SimpleStringProperty();
//    StringProperty otherInfoField = new SimpleStringProperty();
//    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
//
//    JFrame frame = new JFrame("Java Swing And JavaFX");
//    JFXPanel jfxPanel = new JFXPanel();
//    frame.add(jfxPanel);
//
//    vm.getNameField().bind(nameField);
//    vm.getDescriptionField().bind(descriptionField);
//    vm.getPriceField().bind(priceField);
//    vm.getOtherInfoField().bind(otherInfoField);
//    vm.imagePropertyProperty().bind(imageProperty);
//
//    nameField.setValue("");
//    descriptionField.setValue("description");
//    priceField.setValue("15");
//    otherInfoField.setValue("otherinfo");
//    imageProperty.setValue(new Image("file:image1.jpeg"));
//
//    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
//
//    String result = vm.onAddRentalButtonPressed("New", objects);
//    assertEquals("Name cannot be empty", result);
//  }
//
//  @Test public void descriptionNotGiven() throws IOException
//  {
//    StringProperty nameField = new SimpleStringProperty();
//    StringProperty pictureLinkField = new SimpleStringProperty();
//    StringProperty descriptionField = new SimpleStringProperty();
//    StringProperty priceField = new SimpleStringProperty();
//    StringProperty otherInfoField = new SimpleStringProperty();
//    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
//
//    JFrame frame = new JFrame("Java Swing And JavaFX");
//    JFXPanel jfxPanel = new JFXPanel();
//    frame.add(jfxPanel);
//
//    vm.getNameField().bind(nameField);
//    vm.getDescriptionField().bind(descriptionField);
//    vm.getPriceField().bind(priceField);
//    vm.getOtherInfoField().bind(otherInfoField);
//    vm.imagePropertyProperty().bind(imageProperty);
//
//    nameField.setValue("ae86");
//    pictureLinkField.setValue("url");
//    descriptionField.setValue("");
//    priceField.setValue("15");
//    otherInfoField.setValue("otherinfo");
//    imageProperty.setValue(new Image("file:image1.jpeg"));
//
//
//    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
//    String result = vm.onAddRentalButtonPressed("New", objects);
//    assertEquals("Description cannot be empty", result);
//  }
//
//  @Test public void numberIsntNumber() throws IOException
//  {
//    StringProperty nameField = new SimpleStringProperty();
//    StringProperty pictureLinkField = new SimpleStringProperty();
//    StringProperty descriptionField = new SimpleStringProperty();
//    StringProperty priceField = new SimpleStringProperty();
//    StringProperty otherInfoField = new SimpleStringProperty();
//    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
//
//    JFrame frame = new JFrame("Java Swing And JavaFX");
//    JFXPanel jfxPanel = new JFXPanel();
//    frame.add(jfxPanel);
//
//    vm.getNameField().bind(nameField);
//    vm.getDescriptionField().bind(descriptionField);
//    vm.getPriceField().bind(priceField);
//    vm.getOtherInfoField().bind(otherInfoField);
//    vm.imagePropertyProperty().bind(imageProperty);
//
//    nameField.setValue("dupadupadupa");
//    pictureLinkField.setValue("url");
//    descriptionField.setValue("description");
//    priceField.setValue("XD");
//    otherInfoField.setValue("otherinfo");
//    imageProperty.setValue(new Image("file:/image1.jpeg"));
//
//
//    ObservableList<String> objects = FXCollections.observableArrayList("Tools","Kitchenware");
//
//    String result = vm.onAddRentalButtonPressed("New", objects);
//    assertEquals("Price is a not number", result);
//  }
//
//}
