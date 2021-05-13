package client.views.search_for_rental;

import javafx.scene.image.Image;
import org.controlsfx.control.GridView;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;

public class Controller
{
  private GridView<Image> list;
  private ArrayList<Image> images;
  private InfoOverlay infoOverlay;
  private PopOver popOver;

  public void init(){
    list = new GridView<>();
    images = new ArrayList<>();
    //list.
  }
}
