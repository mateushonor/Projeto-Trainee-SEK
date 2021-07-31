/*
Modificado: 08/07/2021
Código de decisão feito no 
https://colab.research.google.com/drive/1HL4uBz_7KAs8CmBkPGzaTuF4ra5Iw0Fs?usp=sharing#scrollTo=6Yj8dr0KOLCR
com os dados coletados na arena contruida com as cores dadas pelo projeto no webots.
*/

package Definicores;

import Definicores.Colors;

public class DefineCor{

public static Colors Cor(double[] cor) {

  double R = cor[0];
  double G = cor[1];
  double B = cor[2];


   if(R <= 59.5) {
     if(B <= 90.0){
       if(G <= 34.0){
         return Colors.Black;
       }else{
         if(B <= 50.0 && R <= 33.0){
           return Colors.Green;
         }else{
           return Colors.Black;
         }
       }
     }else{
       return Colors.Blue;
     }
   }else{
     if(B <= 77.5){
       if(G <= 85.0){
         return Colors.Black;
       }else{
         return Colors.Yellow;
       }
     }else{
       if(G <= 164.5){
         if(G <= 105.0){
           return Colors.Black;
         }else{
           if(G <= 115.0){
             return Colors.Blue;
           }else{
            return Colors.White;
           }
         }
       }else{
         if(B <= 157.0){
           //if(B <= 135.0){
             return Colors.Yellow;
           //}else{
            //  return Colors.Green;
          // }
         }else{   
          return Colors.White;
            
         }
       }
     }
   }
   }
   }