import processing.core.PApplet;


public class Muneca {
private int posXM, posYM;

PApplet app;
	
	public Muneca(PApplet app){
		posXM=0;
		posYM=0;
		this.app=app;
	}
	
	public void pintarMuneca(){
		app.fill(255,0,0);
		app.ellipse(posXM, posYM, 30, 30);
	}
	
//	public void moverMuneca(String instruccion){
//		switch (instruccion){
//		case "arriba": System.out.println("hola");
//			posYM -= 3;
//			break;
//			
//		case "abajo": posYM += 3;
//			break;
//			
//		case "derecha": posXM += 3;
//			break;
//			
//		case "izquierda": posXM -= 3;
//			break;
//	}
//	}
	
}
