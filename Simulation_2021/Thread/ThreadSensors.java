/* File:          ThreadSensors.java
Date:07/03/21
Description: Classe que cria thread para capitação dos dados dos sensores
              como a comnunicação entre processos é de vital inportsancia a
              utilização da classe Data.java
Author: Luiz da Silva Moura , Rafael lins e Iury  Resende
References:http://www.dsc.ufcg.edu.br/~jacques/cursos/map/html/threads/sincronizacao.html
Modifications:
*/
package Thread;
  
import robo.EV3;    
import Definicores.DefineCor;
import Definicores.Colors;
import Sensors.*;
import Motors.*;
import Odometria_com_Enconders.*;

public final class ThreadSensors extends Thread {
  private static Thread T = new Thread(new read());

  /* SENSORES */
  private static ColorSensor RGBr = new ColorSensor("RGB_Right");
  private static ColorSensor RGBl = new ColorSensor("RGB_Left");
  private static ColorSensor RGBc = new ColorSensor("RGB_Center");

  public static void StartSensor() {
    EV3.step();
    T.start();
   // EV3.step();
    //
    while (EV3.step() != -1 && Sensors.ready.GetBol() == false);
    /* ESPERA PRODUZIR UMA VEZ PARA CONTINUAR (TER O Q CONSUMIR) */
  }


  private static class read implements Runnable {

    public void run() {


      Boolean first = false;
      while (true) {
        Sensors.RGB_Left.SetVdouble(RGBl.getSensor());
        //System.out.println("thread" + RGBl.getSensor()[0]);
        Sensors.RGB_Right.SetVdouble(RGBr.getSensor());
        Sensors.RGB_Center.SetVdouble(RGBc.getSensor());
        if (first == false) {
        Sensors.ready.SetBol(true);
          first = true;
        }

      }
    }

  }

  public static void test() {


    StartSensor();

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
         Rotation R = new Rotation(2.0,2.0,0.0);
        
        int i = 0;
        int j = 0;
    while (EV3.step() != -1) {
      rgbl = RGBl.GetVdouble();
      rgbr = RGBr.GetVdouble();
      rgbc = RGBc.GetVdouble();
      
     System.out.println("RGBlCor = "+ RGBl.GetCor());
      //System.out.println("R: "+rgbl[0]+" G: "+rgbl[1]+" B: "+rgbl[2]);
      System.out.println("RGBrCor = "+ RGBr.GetCor());
     //System.out.println("R: "+rgbr[0]+" G: "+rgbr[1]+" B: "+rgbr[2]);
   System.out.println("RGBcCor = "+ RGBc.GetCor());
    //System.out.println("R: "+rgbc[0]+" G: "+rgbc[1]+" B: "+rgbc[2]);
      
     if(RGBc.GetCor()==Colors.Black || RGBc.GetCor()==Colors.Yellow || RGBc.GetCor()==Colors.Green && RGBl.GetCor()==Colors.White){ 
      System.out.println("Andar para frente 1");
        EdromMotor.Straight(10); 
      } 
      else if(j==0 && RGBr.GetCor()==Colors.Yellow){
        System.out.println("Rodar");
         R.RotAngle(80);
        // EdromMotor.rotateRight(90);
         System.out.println("Test 1");
      /* while(RGBr.GetCor()==Colors.White || RGBl.GetCor()==Colors.Yellow){
         System.out.println("Andar para frente 2");
           EdromMotor.Straight(10);
           
         }*/
         j++;
         
        }    
        else if(j==1 && RGBr.GetCor()==Colors.Blue){
        System.out.println("Rodar");
         R.RotAngle(-80);
        // EdromMotor.rotateRight(90);
         System.out.println("Test 1");
       /* while(RGBr.GetCor()==Colors.White || RGBl.GetCor()==Colors.Yellow){
         System.out.println("Andar para frente 2");
           EdromMotor.Straight(10);
           
         }*/
         j++;
         
        }         
         else if(j==2 && RGBr.GetCor()==Colors.Green){
        System.out.println("Rodar");
         R.RotAngle(300);
        // EdromMotor.rotateRight(90);
         System.out.println("Test 1");
       /* while(RGBr.GetCor()==Colors.White || RGBl.GetCor()==Colors.Yellow){
         System.out.println("Andar para frente 2");
           EdromMotor.Straight(10);
           
         }*/
         j++;
         
        }  
       
      /*else{
       EdromMotor.stopUnReg();
       
        }*/
     
       System.out.println("----------------------"+i);
       //EV3.robot.step(1000);
       
    }
    
  }

    
  }


