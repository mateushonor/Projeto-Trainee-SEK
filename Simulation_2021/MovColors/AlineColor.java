package AlineColors;
import robo.EV3;
import PID.PIDdigital;
import Thread.*;
import Motors.EdromMotor;

public final class AlineColor {
    
    // criar um setter na classe PIDdigital
    private static PIDdigital leftPID = new PIDdigital(0.2, 0.0, 0.0, EdromMotor.leftMotor.getMaxVelocity());
    private static PIDdigital rightPID = new PIDdigital(0.2, 0.0, 0.0, EdromMotor.leftMotor.getMaxVelocity());
    private static double potMinULeft = 2.0;
    private static double potMinURight = 2.0;
    private static double uLeft, uRight;
    private static double erro, erroMin = 1.0;
    private static int dir = -1;

    // criar um setter na classe PIDdigital (boa pratica/padrao)
    public static void setLeftPID (double[] data) {
        leftPID.setParameters(data);
    }

    // criar um getter na classe PIDdigital (boa pratica/padrao)
    public static double[] getLeftPID () {
        return leftPID.getParameters();
    }

    public static void setRightPID (double[] data) {
        rightPID.setParameters(data);
    }

    public static double[] getRightPID () {
        return rightPID.getParameters();
    }
    
    public static void setDir(int val) {
        if (dir != val) dir = val;
        System.out.println((dir == 1) ? "Frente" : "Para tras");
    }

    // verificar se de fato deve ser passado um valor int para EdromMotor.setSpeedLeft, ja que leftPID.calculate retorna um double
    // verificar se e viavel atribuir a uma variaval auxiliar o valor de ThreadSensors.RGB(Sensors.RGB_Left)[index]
    public static double alignLeft (double[] mediaRepresentativa) {
        double media = mediaRepresentativa[0];
        int index = (int) mediaRepresentativa[1];
        // System.out.println("Media Esquerda:" + media);
        leftPID.SetPoint(media);
        System.out.println("Input Esquerda:" + ThreadSensors.RGB(Sensors.RGB_Left)[index]);
        uLeft = leftPID.calculate(ThreadSensors.RGB(Sensors.RGB_Left)[index]);
        EdromMotor.setSpeedLeft((int) (uLeft * dir));
        // return Math.abs(leftPID.getERROR());
        return leftPID.getERROR();
    }

    public static double alignRight (double[] mediaRepresentativa) {
        double media = mediaRepresentativa[0];
        int index = (int) mediaRepresentativa[1];
        // System.out.println("Media Direita:" + media);
        rightPID.SetPoint(media);
        System.out.println("Input Direita:" + ThreadSensors.RGB(Sensors.RGB_Right)[index]);
        uRight = rightPID.calculate(ThreadSensors.RGB(Sensors.RGB_Right)[index]);
        EdromMotor.setSpeedRight((int) (uRight * dir));
        // return Math.abs(rightPID.getERROR());
        return rightPID.getERROR();
    }

    // verificar/testar valores viaveis para erroMin, potMinU, potMinV
    public static void align (double[] mediaRepresentativaLeft, double[] mediaRepresentativaRight) {
        do {
            EV3.step();
            erro = alignRight(mediaRepresentativaRight) - alignLeft(mediaRepresentativaLeft);
            System.out.println("Potencias: " + uLeft + "  ---  " + uRight);
            System.out.println("Erros: " + leftPID.getERROR() + " --- " + rightPID.getERROR() + " --- " + erro);
            System.out.println();
        } while ((Math.abs(erro) > erroMin) || (Math.abs(uLeft) > potMinULeft) || (Math.abs(uRight) > potMinURight));
        EdromMotor.stopUnReg();
        leftPID.resetPID();
        rightPID.resetPID();

    }
    
    public static void main(String[] args) {
  
      LineParameters.fulfillColorsArrays();
      LineParameters.fulfillColorsIntersections();
      
      ThreadSensors.StartSensor();

      //System.out.println(ThreadSensors.RGB(Sensors.RGB_Right));
      System.out.println("esperou tempo");
      AlineColor.setDir(1);
      AlineColor.align(LineParameters.whiteAndBlack, LineParameters.whiteAndBlack);
  
  }

}
