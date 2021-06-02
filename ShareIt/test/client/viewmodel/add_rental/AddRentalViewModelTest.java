package client.viewmodel.add_rental;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRentalViewModelTest
{
  private AddRentalViewModel vm;
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;
  private ModelFactory modelFactory;

  @BeforeEach public void setup()
  {
    ClientFactory clientFactory = new ClientFactory();
    modelFactory = new ModelFactory(clientFactory);
    rentalModel = new RentalModelManager(clientFactory.getClient(), modelFactory);
    memberModel = new MemberModelManager(clientFactory.getClient(),
        modelFactory);
    messageModel = new MessageModelManager(clientFactory.getClient());
    vm = new AddRentalViewModel(rentalModel, memberModel);

    JFrame frame = new JFrame("Java Swing And JavaFX");
    JFXPanel jfxPanel = new JFXPanel();
    frame.add(jfxPanel);
  }

  @Test public void addValidRental() {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);
    vm.imagePropertyProperty().bind(imageProperty);

    nameField.setValue("new drillx");
    descriptionField.setValue("description");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");

    imageProperty.setValue(new Image("file:image1.jpeg"));

    ObservableList<String> objects = FXCollections
        .observableArrayList("Tools", "Kitchenware");

    String result = vm.onAddRentalButtonPressed("New", objects);

    assertEquals("Adding successful", result);

  }

  @Test public void nameNotGiven()
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    JFrame frame = new JFrame("Java Swing And JavaFX");
    JFXPanel jfxPanel = new JFXPanel();
    frame.add(jfxPanel);

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);
    vm.imagePropertyProperty().bind(imageProperty);

    nameField.setValue("");
    descriptionField.setValue("description");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");
    imageProperty.setValue(new Image("file:image1.jpeg"));

    ObservableList<String> objects = FXCollections
        .observableArrayList("Tools", "Kitchenware");

    String result = vm.onAddRentalButtonPressed("New", objects);
    assertEquals("Name cannot be empty", result);
  }

  @Test public void descriptionNotGiven()
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty pictureLinkField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    JFrame frame = new JFrame("Java Swing And JavaFX");
    JFXPanel jfxPanel = new JFXPanel();
    frame.add(jfxPanel);

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);
    vm.imagePropertyProperty().bind(imageProperty);

    nameField.setValue("ae86");
    pictureLinkField.setValue("url");
    descriptionField.setValue("");
    priceField.setValue("15");
    otherInfoField.setValue("otherinfo");
    imageProperty.setValue(new Image("file:image1.jpeg"));

    ObservableList<String> objects = FXCollections
        .observableArrayList("Tools", "Kitchenware");
    String result = vm.onAddRentalButtonPressed("New", objects);
    assertEquals("Description cannot be empty", result);
  }

  @Test public void numberIsntNumber()
  {
    StringProperty nameField = new SimpleStringProperty();
    StringProperty pictureLinkField = new SimpleStringProperty();
    StringProperty descriptionField = new SimpleStringProperty();
    StringProperty priceField = new SimpleStringProperty();
    StringProperty otherInfoField = new SimpleStringProperty();
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    JFrame frame = new JFrame("Java Swing And JavaFX");
    JFXPanel jfxPanel = new JFXPanel();
    frame.add(jfxPanel);

    vm.getNameField().bind(nameField);
    vm.getDescriptionField().bind(descriptionField);
    vm.getPriceField().bind(priceField);
    vm.getOtherInfoField().bind(otherInfoField);
    vm.imagePropertyProperty().bind(imageProperty);

    nameField.setValue("dupadupadupa");
    pictureLinkField.setValue("url");
    descriptionField.setValue("description");
    priceField.setValue("XD");
    otherInfoField.setValue("otherinfo");
    imageProperty.setValue(new Image("file:/image1.jpeg"));

    ObservableList<String> objects = FXCollections
        .observableArrayList("Tools", "Kitchenware");

    String result = vm.onAddRentalButtonPressed("New", objects);
    assertEquals("Price is a not number", result);
  }

}
