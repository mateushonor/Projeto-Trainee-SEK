// File:          EdromMotor.java
// Date:14/02/21
// Description: Clase estatica do motores que o robo utiliza, 
              //estatica para nao precissar criar os motores para cada clase  
// Author:Luiz da Silva Moura
// Modifications:
package Motors;
import robo.EV3;
import Odometria_com_Enconders.*;

public final class EdromMotor {

	// Motores principais
	public static EdromBasicMotor leftMotor = new EdromBasicMotor("MotorLeft"); // MOTOR PARA MOVIMENTO
	public static EdromBasicMotor rightMotor = new EdromBasicMotor("MotorRight"); // MOTOR PARA MOVIMENTO
       private static Rotation R = new Rotation(4.0, 4.0, 0.0);
       private static EncoderAngle EA = new EncoderAngle(5.0,1.0,0.0);
	// Motores auxiliares
	//public static EdromBasicMotor extraMotor = new EdromBasicMotor("Motor_Center"); // MOTOR PARA TORUQE EXTRA
         
           public static void resetTacho() {
		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();
	}

	public static void stopUnReg() {
		leftMotor.stop();
		rightMotor.stop();
		EV3.step();
	}

	public static void setSpeed(int vel) {
		leftMotor.setSpeed(vel);
		rightMotor.setSpeed(vel);
	}
	public static void setSpeedLeft(int pow) {
		leftMotor.setSpeed(pow);
	}

	public static void setSpeedRight(int pow) {
		rightMotor.setSpeed(pow);
	}

	public static void setPower(int pow) {
		leftMotor.setPower(pow);
		rightMotor.setPower(pow);
	}
	public static void setPowerLeft(int pow) {
		leftMotor.setPower(pow);
	}

	public static void setPowerRight(int pow) {
		rightMotor.setPower(pow);
	}

	public static void unRegForward() {
		leftMotor.forward();
		rightMotor.forward();
	}

	public static void unRegBackward() {
		leftMotor.backward();
		rightMotor.backward();
	}
	//
	public static void rotateTo(int limitAngle) {
		leftMotor.rotateTo(limitAngle);
		rightMotor.rotateTo(limitAngle);
	}
	
	public static void rotateLeft(int limitAngle) {
		leftMotor.rotateTo(limitAngle);
	}
	
	public static void rotateRight(int limitAngle) {
		rightMotor.rotateTo(limitAngle);
	}

	public static double TachoCountLeft() {
		return leftMotor.getTachoCount();
	}

	public static double TachoCountRight() {
		return rightMotor.getTachoCount();
	}

	// Metodo de motores auxiliares
	/*
	public static void stopExtra() {
		extraMotor.stop();
	}
	
	public static double TachoCountExtra() {
		return extraMotor.getTachoCount();
	}
	
	public static void rotateToExtra(int limitAngle) {
		extraMotor.rotateTo(limitAngle);

	}
	
	 public static void resetTachoExtra() {
		extraMotor.resetTachoCount();
	}

	public static void extraForward() {
		extraMotor.forward();
	}
	
	public static void extraBackward() {
		extraMotor.backward();
	}
		
	public static void setSpeedExtra(int s) {
		extraMotor.setSpeed(s);

	}

	public static void setPowerExtra(int p) {
		extraMotor.setPower(p);

	}
	*/
	
	//odometria
	public static void TurnLeft(){
	R.RotAngle(-90); 
	}
	
	public static void TurnRight(){
	R.RotAngle(90); 
	}
	
	public static void TurnAng(double graus){
	R.RotAngle(graus); 
	}
	
	public static void FowerdDist(double metros, int vel){
	EA.DForward(metros,vel);
	}
	
	public static void Straight(int vel){
	EA.Forward(vel);
	}
	public static void StopBould(){
	EA.StopAngle();
	}
	
	

	
	
	
}