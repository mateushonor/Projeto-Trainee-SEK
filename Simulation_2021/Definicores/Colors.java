package Definicores;

public enum Colors {

  Blue(11.0, 12.0, 122.0), 
  Green(20.0, 51.0, 25.0),
  Yellow(78.0, 89.0, 17.0), 
  White(78.0, 88.0, 121.0),
  Black(11.0, 12.0, 17.0);
 
  private final double[] vcolor = {0.0,0.0,0.0};
  
  Colors(double R, double G, double B){
    vcolor[0] = R;
    vcolor[1] = G;
    vcolor[2] = B;
  }
  
  public double[] getValor () {
    return vcolor;
  }
  

}