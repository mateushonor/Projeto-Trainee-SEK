package Executa;

import Thread.*;
import robo.EV3;
import Definicores.*;
import MovColors.*;
import Motors.*;
import Odometria_com_Enconders.*;


public class Executa {

  public static void executar() {
  //ThreadSensors.test();

  ThreadSensors.StartSensor();

  //LineFollow lp = new LineFollow(10.0,10.0,0.0,20.0);
  LineFollow lf = new LineFollow(1.75,0.01,0.0,60.0);
  
   

  /*Boas praticas parte 1
        Declaração de apelidos para dimnuir o script
    e melhorar o entendimento do leitor*/
        Sensors RGBl = Sensors.RGB_Left;
        Sensors RGBr = Sensors.RGB_Right;
        Sensors RGBc = Sensors.RGB_Center;
        /*Boas praticas parte 2
        Usar varuavis para receber o vetor de double
        para receber os dados dos RGBs*/
        double[] rgbl;  
        double[] rgbr;
        double[] rgbc;

        Rotation R = new Rotation(4.0,3.5,0.0);
        int i = 0;
        boolean flagYellow = true;
        boolean flagBlue = true;
        boolean flagGreen = true;
        boolean corrigeGreen = true;

      while (EV3.step() != -1) {
      rgbl = RGBl.GetVdouble();
      rgbr = RGBr.GetVdouble();
      rgbc = RGBc.GetVdouble();

    System.out.println("RGBlCor = "+ RGBl.GetCor());
      System.out.println("R: "+rgbl[0]+" G: "+rgbl[1]+" B: "+rgbl[2]);
      System.out.println("RGBrCor = "+ RGBr.GetCor());
     System.out.println("R: "+rgbr[0]+" G: "+rgbr[1]+" B: "+rgbr[2]);
   System.out.println("RGBcCor = "+ RGBc.GetCor());
    System.out.println("R: "+rgbc[0]+" G: "+rgbc[1]+" B: "+rgbc[2]);
     System.out.println("----------------------"+(i++));
     
     if(RGBc.GetCor()!=Colors.Yellow && RGBc.GetCor()!=Colors.Blue && RGBc.GetCor()!=Colors.Green){
      System.out.println("Andar para frente Preto");
      
       lf.FollowLine(40);
       
     }
     else if(RGBl.GetCor()==Colors.Yellow && RGBc.GetCor()==Colors.Yellow){
         System.out.println("Vai RODAR ");
        // EdromMotor.FowerdDist(-0.04, 10);
        if (flagYellow){
         R.RotAngle(80);
          flagYellow=false;
         }
           EV3.step();
          System.out.println("RODOU ");
         EdromMotor.FowerdDist(0.2, 10);
         
          //j=0;
         System.out.println("Rodar r Andar para frente ");
     }else if(RGBl.GetCor()==Colors.Blue && RGBc.GetCor()==Colors.Blue){
        if(flagBlue){
         R.RotAngle(-78);
         flagBlue=false;
        }
         EdromMotor.FowerdDist(0.2, 10);
        //j=0;
        
        
     }else if(RGBl.GetCor()==Colors.Green && RGBc.GetCor()==Colors.Green){
        if(flagGreen){
         R.RotAngle(300);
         flagGreen=false;
        } 
          EdromMotor.FowerdDist(0.2, 10);
         // j=0;
         
     }
    else if(RGBc.GetCor()==Colors.Yellow || RGBc.GetCor()==Colors.Blue || RGBc.GetCor()==Colors.Green){
          System.out.println("tCHURBANGOS");
            if(RGBc.GetCor()==Colors.Green && corrigeGreen){
              
             R.RotAngle(-1.0);
             EdromMotor.FowerdDist(0.015, 10);
             corrigeGreen=false;
             System.out.println("CORRIGE");
          }
           
          EdromMotor.stopUnReg();
          System.out.println("Para");
          EdromMotor.FowerdDist(0.015, 10);
          System.out.println("Andou");
    }
    
    }
     

}
 

}
