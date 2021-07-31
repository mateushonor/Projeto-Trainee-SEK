package MovColors;

import Definicores.InterSEK;
import Definicores.Colors;
import Motors.EdromMotor;
import PID.PIDdigital;
import Sensors.ColorSensor;

public class LineFollow {

    private static PIDdigital pidLine;
    private static ColorSensor RGBc = new ColorSensor("RGB_Center");

    private double u;
    private int sentido = 1,index;
    private double[] valor = {0.0, 0.0, 0.0};

    public LineFollow(double kp, double ki, double kd, double saturacao) {
        index = InterSEK.Index(Colors.Black,Colors.White);
        pidLine = new PIDdigital (kp, ki, kd,InterSEK.MediaR(Colors.Black,Colors.White), saturacao);
        }

    public void LineSentido(int tipo) {
        if (tipo == 1) {
            sentido = 1;
        } else if (tipo == 2) {
            sentido = -1;
        }
    }

    public void FollowLine(int pot) {
        valor = RGBc.getSensor();
        //System.out.println("valor "+valor[0]);
        u = pidLine.calculate(valor[index]);
        EdromMotor.setPowerRight((int) ((-u * sentido) + pot));
        EdromMotor.setPowerLeft((int) ((+u * sentido) + pot));
    }
}
