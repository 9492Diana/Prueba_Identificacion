import processing.core.PApplet;

//Estas seran los enemigos de la muñeca

public class Aranas {

	private int posX, posY;
	
	//esta es la variable para las nuevas arañas que apareceran en la pantalla del medio
	private int posXNuevo;
	
	PApplet app;
		
	public Aranas(PApplet app){
		this.app=app;
		
		//asigno posiciones random al comienzo
		posX=(int) app.random(0, 300);
		posY=(int) app.random(0, 700);
		
		//nueva pos para las arañas que llegan a la otra pantalla (medio)
		posXNuevo=0;
		
	}
	
	public void pintarArana(){
		//por ahora son elipses, despues se reemplaza con imagenes
		app.ellipse(posX, posY, 20, 20);
		app.fill(255);
	}
	
	public void pintarAranasMedio(int posYNuevo){
		app.ellipse(posXNuevo, posYNuevo, 20, 20);
		app.fill(255);
	}
	
	//solo necesito que se muevan en el eje horizontal
	public void moverArana(){
		posX++;
	}
	
	public void moverAranaNueva(){
		posXNuevo++;
	}
	

	//hago gets 
	public int getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}
	
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}
}
