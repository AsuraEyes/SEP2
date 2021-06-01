package client.core;

import client.model.*;
import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;
//import client.model.ShareItModelManager;

import java.io.IOException;

/**
 * A class that creates a Model object without having to specify the exact class of the object.
 */
public class ModelFactory {
  private ClientFactory clientFactory;
  private ShareItModel shareItModel;
  private RentalModel rentalModel;
  private MessageModel messageModel;
  private MemberModel memberModel;

  /**
   * Instantiates a new Model factory.
   *
   * @param clientFactory
   */
  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  /**
   * Gets ShareItModel when it needs to be accessed.
   *
   * @return returns the ShareItModel
   * @throws IOException
   */
  public ShareItModel getShareItModel() throws IOException {
    if(shareItModel == null)
    {
      shareItModel = new ShareItModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }

  /**
   * Gets RentalModel when it needs to be accessed..
   *
   * @return returns the RentalModel
   * @throws IOException
   */
  public ShareItModel getRentalModel() throws IOException {
  if(shareItModel == null)
  {
    rentalModel = new RentalModelManager(clientFactory.getClient());
  }
  return shareItModel;
}

  /**
   * Gets MemberModel when it needs to be accessed.
   *
   * @return returns the MemberModel
   * @throws IOException
   */
  public ShareItModel getMemberModel() throws IOException {
    if(shareItModel == null)
    {
      memberModel = new MemberModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }

  /**
   * Gets MessageModel when it needs to be accessed..
   *
   * @return returns the MessageModel
   * @throws IOException
   */
  public ShareItModel getMessageModel() throws IOException {
    if(shareItModel == null)
    {
      messageModel = new MessageModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }
}
