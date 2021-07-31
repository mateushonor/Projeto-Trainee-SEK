package Sensors;
import com.cyberbotics.webots.controller.Camera;
import robo.EV3;

public class ColorSensor {

private Camera rgb; 


  public ColorSensor(String name){
    rgb = EV3.robot.getCamera(name);
    rgb.enable(EV3.Period);
  }

  public double[] getSensor(){
    int[] image = rgb.getImage();
    int MediaR = 0;
    int MediaG = 0;
    int MediaB = 0;
      for (int i=0; i < image.length; i++) {
      int pixel = image[i]; 
      MediaR = Camera.pixelGetRed(pixel) + MediaR;
      MediaG = Camera.pixelGetGreen(pixel) + MediaG;
      MediaB = Camera.pixelGetBlue(pixel) + MediaB;
}
    double[] return1 = {MediaR/image.length, MediaG/image.length, MediaB/image.length};
    //System.out.println("MediaR=" + return1[0] + " MediaG=" + return1[1] + " MediaB=" + return1[2]);
      return return1;

   }
   
}
