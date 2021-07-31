// File:          PIDdigital.java
// Date:14/02/21
// Description: clase de pid digital baseado em controle digital
// Author:Luiz da Silva Moura
// Modifications:
package PID;

import robo.EV3; 

public class PIDdigital {
	private double Kp, Ki, Kd, setPoint,saturation = Double.MAX_VALUE;
	private double alfa =0, beta = 0, gama = 0; 
	private double uk = 0, uAntes = 0;
	private double error = 0, errorAntes = 0 , error2Antes = 0, errorb;
	private double T = EV3.Period/1000.0;
	
	
	public PIDdigital (double kp, double ki, double kd,double sP, double sT) {
		setPoint = sP;
		saturation = sT;
		Kp = kp;
		Ki = ki;
		Kd = kd;
		Alfa_Beta_Gama();
		
		
	}
	
	
	public PIDdigital (double kp, double ki, double kd,double sT) {
		saturation = sT;
		Kp = kp;
		Ki = ki;
		Kd = kd;
		Alfa_Beta_Gama();
	}

	public PIDdigital (double kp, double ki, double kd) {
		setPoint = 0;
		Kp = kp;
		Ki = ki;
		Kd = kd;
		Alfa_Beta_Gama();
	
	}
	
	private void Alfa_Beta_Gama() {
		alfa = (Kp + (Kd / T) + (Ki * T / 2));
		beta = (-Kp + ((-2 * Kd) / T) + (Ki * T / 2));
		gama = (Kd / T);
	}
	
	
	public void setPID(double kp, double ki, double kd) {
		Kp = kp;
		Ki = ki;
		Kd = kd;
		Alfa_Beta_Gama();
	}
	
	public void SetPoint(double sP) {
		setPoint = sP;
	}
  	public void setParameters(double[] params) {
		Kp = params[0];
		Ki = params[1];
		Kd = params[2];
		Alfa_Beta_Gama();
	}

	public double[] getParameters () {
		double[] aux = {Kp, Ki, Kd};
		return aux;
	}
	
	public void setSaturation(double sT) {
		saturation = sT;
	}
	
	public void setT(double periodo){
	T = periodo;
	}
	public void resetPID() {
		error = 0;
		errorAntes = 0;
		error2Antes = 0;
		uAntes=0;
		uk = 0;
	}
 
	public double getERROR(){
	return  errorb;
	} 
 
	public double calculate(double in) {
                      	
			error = setPoint - in;
			errorb = error;

			
				uk = (uAntes + (alfa * error) + (beta * errorAntes) + (gama * error2Antes));
                                //System.out.println("uk: "+ uk +" alferrosr: "+ (alfa * error)+" betaerror: "+(beta * errorAntes));            
			  
			
			if (uk > Math.abs(saturation)) {
				uk = Math.abs(saturation);
				error = 0;
				errorAntes = 0;
			}else if (uk < -Math.abs(saturation)) {
				uk=-Math.abs(saturation);
				error = 0;
				errorAntes = 0;
			}

		uAntes = uk;
		error2Antes = errorAntes;
		errorAntes = error;
		return uk;
	}
}
