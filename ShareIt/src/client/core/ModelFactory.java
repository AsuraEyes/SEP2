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

public class ModelFactory {
  private ClientFactory clientFactory;
  private ShareItModel shareItModel;
  private RentalModel rentalModel;
  private MessageModel messageModel;
  private MemberModel memberModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public ShareItModel getShareItModel() throws IOException {
    if(shareItModel == null)
    {
      shareItModel = new ShareItModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }
public ShareItModel getRentalModel() throws IOException {
  if(shareItModel == null)
  {
    rentalModel = new RentalModelManager(clientFactory.getClient());
  }
  return shareItModel;
}

  public ShareItModel getMemberModel() throws IOException {
    if(shareItModel == null)
    {
      memberModel = new MemberModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }

  public ShareItModel getMessageModel() throws IOException {
    if(shareItModel == null)
    {
      messageModel = new MessageModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }
}
