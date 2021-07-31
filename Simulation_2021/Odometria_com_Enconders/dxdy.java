/* File:          dxdy.java
Date: 07/03/21
Description: Responsavel por calcular a quantidade que o 
             robo rotaciol e andou no eixo x e y estabelecendo um referencial.
References:
Author:Luiz da Silva Moura.
Modifications:
*/
package Odometria_com_Enconders;

import Motors.EdromMotor;
import java.math.*;

//Para usar esta classe deve atualizar razaoR_W, r e w 
public class dxdy{

          // R/W: razao responsavel pelo calculo do angulo de cada robo
	// R: raio da roda
	// w: distancia entre as duas rodas
	private double Rd = 0.022, Re = 0.022,w = 0.15;
	private double dTeta = 0,Alfa = 0;
	private double tetaAntesD = 0, tetaAntesE = 0, TachoCountRight, TachoCountLeft;
	private double x0 = 0, y0 = 0, alfa0 = 0,reset = 0;
	private boolean first = false;
          
	public dxdy(double RD, double RE,double W){
	this.Rd = RD;
	this.Re = RE;
	this.w = W;
	}  
	public dxdy(){
	}
	
	public double Alfa(String type){  
  	double a;
              TachoCountLeft = EdromMotor.TachoCountLeft();
              TachoCountRight = EdromMotor.TachoCountRight();
               
             a = (Re/w* TachoCountLeft) - (Rd/w * TachoCountRight) - alfa0;
            System.out.println("a: "+a+"alfa: "+alfa0+" x: "+x0+" TR: "+TachoCountRight);
             if(type=="rad")
              return a*Math.PI/180;
              return a;	
	}

	
	public double d(String eixo) {
		// calculo da distancia linear de que cada motor anda
		// x= distancia andada do carrinho no eixo x(multiplicada pelo coseno do agulo
		// y= distancia andada do carrinho no eixo y(multiplicada pelo seno do agulo
		
		if (first == true) {
			// dteta= teta1+teta2-tetaantes2-tetaantes2//variaçao do encoder
			Alfa = Alfa("rad") - reset;
			dTeta = (Rd * (TachoCountRight - tetaAntesD)) + (Re * (TachoCountLeft - tetaAntesE));
                                 
		} else {
			
			dTeta = 0;
			reset = Alfa("rad");
			first = true;
		}
		tetaAntesD = TachoCountRight;
		tetaAntesE = TachoCountLeft;
                      //x0 = cos(alfa) *(Dteta/2*pi/180) +xantes
		x0 = (Math.cos(Alfa) * dTeta * Math.PI / 360.0) + x0;
		y0 = (Math.sin(Alfa) * dTeta * Math.PI / 360.0) + y0;
		if (eixo == "x")
			return x0;
		if (eixo == "y")
			return y0;
        	throw new IllegalArgumentException("key can't be null");

		//return (-1);
	}

	public void ajuste(double x, double y, double alfa) {
		EdromMotor.stopUnReg();
		alfa0 = 0;
		alfa0 = (alfa*Math.PI/180) + Alfa("graus");
		x0 = x;// - d("x");
		y0 = y;// - d("y");
		first = false;
		d("x");

	}
	

}
