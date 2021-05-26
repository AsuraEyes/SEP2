package client.core;

import client.model.*;
//import client.model.ShareItModelManager;

import java.io.IOException;

public class ModelFactory {
  private ClientFactory clientFactory;
  private ShareItModel shareItModel;

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
//public ShareItModel getRentalModel() throws IOException {
//  if(shareItModel == null)
//  {
//    shareItModel = new RentalModelManager(clientFactory.getClient());
//  }
//  return shareItModel;
//}
//
//  public ShareItModel getMemberModel() throws IOException {
//    if(shareItModel == null)
//    {
//      shareItModel = new MemberModelManager(clientFactory.getClient());
//    }
//    return shareItModel;
//  }
//
//  public ShareItModel getMessageModel() throws IOException {
//    if(shareItModel == null)
//    {
//      shareItModel = new MessageModelManager(clientFactory.getClient());
//    }
//    return shareItModel;
//  }
}
