package Odometria_com_Enconders;

import Motors.EdromMotor;
import java.math.*;
import robo.EV3;
import PID.PIDdigital;


public class EncoderAngle extends dxdy {
	private PIDdigital PID;
	private boolean FirstForward = false;

	public EncoderAngle(double kp, double ki, double kd) {
		super();
		this.PID = new PIDdigital(kp, ki, kd,EdromMotor.leftMotor.getMaxVelocity());
	}
	public void Forward(int vel) {
		// Metodo responsavel para andar para frente indeterminadamente
		// usando os dois enconders dos motores como refer�ncia.
		// Recomendacao: usar StopAngle para parar de andar
		if (this.FirstForward == false) {
      		           EdromMotor.stopUnReg();
      		           ajuste(0.0,0.0,0.0);
      			   FirstForward = true;
		}
		double Y = d("y") ;
		if (Y > 1) 
		Y = 1;
		else if (Y < -1)
		Y = -1;

		// APROXIMACAO PARA -Math.atan((dxdy.d("y")) / ErroY)
		this.PID.SetPoint((0.1985 * Y * Y * Y) - (0.9755 * Y));
		double U = this.PID.calculate(Alfa("rad"));
		
		EdromMotor.unRegForward();
		EdromMotor.setSpeedRight((int) (-U + vel));
		EdromMotor.setSpeedLeft((int) (U + vel));

	}
	
	public void StopAngle() {
		// Metodo responsavel por parar o ForwardAngle
		EdromMotor.stopUnReg();
		FirstForward = false;
		PID.resetPID();
	}
	 
	public void DForward(double metro, int vel){ 
              	 if(metro<0){
              	 metro = - metro;
            	 vel =  - Math.abs(vel);
              	 }else
            	   vel =  Math.abs(vel);
	
            	 while(EV3.step()!=-1 && Math.abs(d("x"))< metro )
            	 Forward(vel);
                     	StopAngle();
                     	ajuste(0.0,0.0,0.0);
	}



}
