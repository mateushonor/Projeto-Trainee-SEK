package waitTime;


public class tictac2 {
	private double inicio = 0;
	private boolean seguranca = false;
	
	public void delay(double t){
	ligar("off");
	while(ligar("on")<t);
	
	}

	public double ligar(String acionar) {// quando acionar==1 fixa um tempo refencial
									// obs se ja colocou referencial nao sobrepoe (so coloca outro referencial
									// depois de tirar
									// quando aciianar==0 tira o refencial

		if (acionar == "on"&& seguranca==false) {
			inicio = System.currentTimeMillis();
			seguranca = true;
		}else if (acionar == "off") {
			seguranca = false;
			inicio = 0;
			return 0;
		}
		return ((System.currentTimeMillis() - inicio)/1000);
	}
	
	public static void main(String[] args) {
		tictac2 a = new tictac2();
		a.ligar("on");

		while (true) {
			//LCD.drawString("teste 1 do exempleo 2", 0, 0);
			if (a.ligar("on")<10) {
				//LCD.drawString(""+ a.ligar("on") ,0, 1);
			}else a.ligar("off");
		}
	}

}
