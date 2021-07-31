/* File:          Sensors.java
Date:07/03/21
Description: Classe para referenciar os sensores,
             usada principalmente para quando ler o dado do sensor e
             quando consumir os dados do sensor.
Author: Luiz da Silva Moura , Rafael lins e Iury  Resende
References:https://www.devmedia.com.br/enums-no-java/38764
Modifications:
*/
package Thread;
import Definicores.Colors;
import Definicores.DefineCor;

public enum Sensors {

  RGB_Left,
  RGB_Right,
  RGB_Center,
  ready;

  private double[] vdouble;
  private double doub;
  private boolean bol;
  private int inte;


  public Boolean GetBol() {
   synchronized (this){
      return bol;
    }
  }

  public void SetBol(Boolean value) {
     synchronized (this){
     bol = value;
    }
  }

  public double GetDouble() {
    synchronized (this){
      return doub;
    }
  }

  public void SetDouble(double value) {
     synchronized (this){
     doub = value;
    }
  }


   public double[] GetVdouble() {
     synchronized (this){
     return vdouble;
    }
  }

    public void SetVdouble(double[] value) {
     synchronized (this){
     vdouble = value;
    }
  }

  public Colors GetCor() {
    synchronized (this){
     return DefineCor.Cor(vdouble);
    }
  }

   public int GetInt() {
     synchronized (this){
     return inte;
    }
  }

    public void SetInt(int value) {
     synchronized (this){
     inte = value;
    }
  }
}
