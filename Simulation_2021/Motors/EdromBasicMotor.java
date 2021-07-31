// File:          EdromBaicMotor.java
// Date:11/02/21
// Description:
// Author:Luiz da Silva Moura
// Modifications:
package Motors;

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.PositionSensor;
import java.math.*;
import robo.EV3;

public class EdromBasicMotor {
   private Motor motor;
   private PositionSensor Encoder;
   private int direction = 1;
   private double reset = 0;

   public EdromBasicMotor(String name){
   
     motor = EV3.robot.getMotor(name);
     motor.setPosition(Double.POSITIVE_INFINITY);
     motor.setVelocity(0.0);
     
     Encoder = motor.getPositionSensor();
     Encoder.enable(EV3.Period);
   }
  public void setPower(double power) {
  //recebe parametro de -100 a +100
    motor.setVelocity(direction*power/motor.getMaxVelocity());
  }
  public void stop(){
  motor.setVelocity(0.0);
  //colocar um brack
  }
  public double getTachoCount(){
  
  if(Double.isNaN(Encoder.getValue()))
  return 0.0;
  return ((Encoder.getValue()*180/Math.PI)-reset);
  }
  public double getTachoBruto(){
  if(Double.isNaN(Encoder.getValue()))
  return 0;
  return (Encoder.getValue()*180/Math.PI);
  }
  public void setSpeed(double speed) {
  //recebe parametro de -maxspeed a +maxspeed
  motor.setVelocity(speed*direction);
  }
  public double getSpeed(){
  return motor.getVelocity();
  }
  public void setAcceleration(double acceleration){
  motor.setAcceleration(acceleration);
  }
  public double getAcceleration(){
  return  motor.getAcceleration();
  }
  public void backward(){
  direction = -1;
  }
  public void forward(){
  direction = 1;
  }
  public void resetTachoCount() {
  reset = (Encoder.getValue()*180/Math.PI);
  }
  public void rotateTo(double grau){
  EV3.step();
  grau = grau + getTachoBruto();
  motor.setPosition(grau*Math.PI/180);
  motor.setVelocity(motor.getMaxVelocity()*0.8);
    while((Math.abs(getTachoBruto()-grau)>0.7)&&(EV3.step() != -1));
  motor.setVelocity(0.0);
  motor.setPosition(Double.POSITIVE_INFINITY);
  }
  public double getMaxVelocity(){
  return motor.getMaxVelocity();
  }
  
  //ORGANIZAR
}
