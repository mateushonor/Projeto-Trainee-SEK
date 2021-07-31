package robo;
import com.cyberbotics.webots.controller.Robot;

  public class EV3 { 
  public static Robot robot = new Robot();
  public static int Period = 16;
  
  public static int step(){
  return robot.step(Period);
  }

}