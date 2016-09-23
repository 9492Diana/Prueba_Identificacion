import processing.core.PApplet;

//Diana Aristizábal-11205050
//Taller 2

public class Ejecutable extends PApplet{

	Logica app;
	
	public void setup(){
		size(400,700); //Tamaño de las pantallas
		app=new Logica(this);
		}
	
   public void draw(){
 	   background(0);
	   app.ejecutar();   
   }
   
   public void mousePressed(){
	   app.clickPantallas(mouseX, mouseY);
   }
	
}//End ejecutable
