package client.core;

import client.model.ShareItModel;
import client.model.ShareItModelManager;

import java.io.IOException;

public class ModelFactory
{
  private ClientFactory clientFactory;
  private ShareItModel shareItModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public ShareItModel getShareItModel() throws IOException
  {
    if(shareItModel == null)
    {
      shareItModel = new ShareItModelManager(clientFactory.getClient());
    }
    return shareItModel;
  }
}
