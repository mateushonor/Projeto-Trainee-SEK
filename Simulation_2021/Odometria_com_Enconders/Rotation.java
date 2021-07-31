package Odometria_com_Enconders;

import Motors.EdromMotor; 
import java.math.*;
import robo.EV3;
import PID.PIDdigital;

public class Rotation extends dxdy {
	private double V;
	private final double ErroMAX = 0.5;
	private PIDdigital PID;

	public Rotation(double kp, double ki, double kd) {
		super();
		PID = new PIDdigital(kp, ki, kd, EdromMotor.leftMotor.getMaxVelocity());
	}
 
	public void RotAngle(double AngGraus) {
		// Metodo Responsavel prara rotacionar X graus

		EdromMotor.stopUnReg();
		EdromMotor.resetTacho();
		ajuste(0, 0, 0);
		V = 0;
		PID.resetPID();
		PID.SetPoint(AngGraus);
		do {

			/* CONTROLADOR PROJETADO KP = 5 E KI = 0.02 */
			V = PID.calculate(Alfa("Graus"));

				EdromMotor.setSpeedLeft((int) (V));
				EdromMotor.setSpeedRight((int) (-V));

		} while ((EV3.step() != -1) && (Math.abs(Alfa("Graus") - AngGraus) > ErroMAX));
		EdromMotor.stopUnReg();
		//System.out.println("erro: " + (Math.abs(Alfa("Graus") - AngGraus)));

	}
	
	public void RotWLeft(double AngGraus) {
		// Metodo Responsavel prara rotacionar X graus

		EdromMotor.stopUnReg();
		EdromMotor.resetTacho();
		ajuste(0, 0, 0);
		V = 0;
		PID.resetPID();
		PID.SetPoint(AngGraus);
		do {

			/* CONTROLADOR PROJETADO KP = 5 E KI = 0.02 */
			V = PID.calculate(Alfa("Graus"));

			

				EdromMotor.setSpeedLeft((int) (V));
				EdromMotor.setSpeedRight(0);


		} while ((EV3.step() != -1) && (Math.abs(Alfa("Graus") - AngGraus) > ErroMAX));
		EdromMotor.stopUnReg();
		System.out.println("erro: " + (Math.abs(Alfa("Graus") - AngGraus)));

	}
	
	public void RotWRight(double AngGraus) {
		// Metodo Responsavel prara rotacionar X graus

		EdromMotor.stopUnReg();
		EdromMotor.resetTacho();
		ajuste(0, 0, 0);
		V = 0;
		PID.resetPID();
		PID.SetPoint(AngGraus);
		do {

			/* CONTROLADOR PROJETADO KP = 5 E KI = 0.02 */
			V = PID.calculate(Alfa("Graus"));

				EdromMotor.setSpeedRight((int) (V));
				EdromMotor.setSpeedLeft(0);

			

		} while ((EV3.step() != -1) && (Math.abs(Alfa("Graus") - AngGraus) > ErroMAX));
		EdromMotor.stopUnReg();
		System.out.println("erro: " + (Math.abs(Alfa("Graus") - AngGraus)));

	}

	/*public static void main(String[] args) {
		Rotation R = new Rotation(4.0, 4.0, 0.0);
		EV3.robot.step(1000);
		R.RotAngle(90, Wheels.Right);
		EV3.robot.step(1000);
		R.RotAngle(-90, Wheels.Left);
		EV3.robot.step(1000);
		R.RotAngle(180, Wheels.Duo);
	}*/

}
