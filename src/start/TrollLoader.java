package start;

import components.EasyImage;
import entities.Troll;

import java.util.ArrayList;
import java.util.Timer;

public class TrollLoader implements Runnable {

    public ArrayList<EasyImage> images = new ArrayList<EasyImage>();
    public ArrayList<EasyImage> zoomImages = new ArrayList<EasyImage>();
    public Troll troll;
    public EasyImage originalImage;
    public EasyImage originalZoomImage;

    public static final int NORMAL = 3;
    public static final int ZOOM = 4;
    public int type;



    public TrollLoader(Troll troll, int type){
        this.type = type;
        switch (type){
            case NORMAL:
                originalImage = troll.image;
                break;

            case ZOOM:
                originalZoomImage = troll.zoomImage;
                break;

            default:
                System.err.println("Type of trollLoader was not correctly specified.");
        }
        //originalImage = troll.image;
        //originalZoomImage = troll.zoomImage;
    }


    public void run(){

        switch (type){
            case NORMAL:
                EasyImage im = originalImage;

                for(int i = 0; i < 40; i++){
                    im = im.getScaledInstance(1.05);
                    images.add(im);
                }

                break;

            case ZOOM:
                EasyImage iZm = originalZoomImage;

                for(int i = 0; i < 40; i++){
                    iZm = iZm.getScaledInstance(1.08);
                    zoomImages.add(iZm);
                }

                break;

        }





    }


}
