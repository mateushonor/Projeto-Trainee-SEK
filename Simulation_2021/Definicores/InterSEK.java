package Definicores;

import java.util.Arrays;

public class InterSEK {
 
  public static int Index(Colors corA, Colors corB){
  
      double max = 0;
      int index = 0;
      double diferenca;

      for (int i = 0; i < 3; i++){
            diferenca = Math.abs(corA.getValor()[i] - corB.getValor()[i]);
            if (diferenca > max) {
	     max = diferenca;
	     index = i;
	}
      }
       return index; 
  }
  
  public static double MediaR(Colors corA, Colors corB){

      int i = Index(corA,corB);
      double media = (corA.getValor()[i] + corB.getValor()[i])/2.0f;

      return media; 
  }
    
}
