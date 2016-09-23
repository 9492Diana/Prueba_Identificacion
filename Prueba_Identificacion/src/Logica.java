import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {
	
//Creo un objeto de comunicacion
 Comunicacion com;
 
 private int id; //para saber que pantalla es
 //private int pantallas;
 private int pantallaIzq;
 private int pantallaMedio;
 private int pantallaDer;

 
  private PApplet app;
 
 //IMAGENES
  //Home
 private PImage pantallaHomeDer;
 private PImage pantallaHomeMedio;
 private PImage pantallaHomeIzq;
 
 //Instrucciones
 private PImage instrucciones;

 //Juego
 private PImage pantallaIzquierda;
 private PImage pantallaJugador;
 private PImage pantallaDerecha;
 
 
 private Muneca doll;
 
 ArrayList<Aranas> aranitas;
 
 
 public Logica(PApplet app){
	 
	 //inicializo
	 com = new Comunicacion(5000, app);
	 
	 //pantallas=0;
	 pantallaIzq=0;
	 pantallaMedio=0;
	  pantallaDer=0;
	 
	 this.app = app;
	 
	 doll=new Muneca(app);
	 
	 aranitas= new ArrayList<Aranas>();
	 
	 
	 //IMAGENES
	 
	 //app.imageMode(app.CENTER);
	 
	 //Home
	 pantallaHomeDer = app.loadImage("data/pantallaPrincipalDerecha.png");
	 pantallaHomeMedio = app.loadImage("data/pantallaPrincipalMedio.png");
	 pantallaHomeIzq = app.loadImage("data/pantallaPrincipalIzquierda.png");
	 
	 //Instrucciones
	 instrucciones = app.loadImage("data/instrucciones.png");
	 
	 //Juego
	 pantallaIzquierda = app.loadImage("data/pantallaIzquierda.png");
	 pantallaJugador = app.loadImage("data/pantallaJugador.png");
	 pantallaDerecha = app.loadImage("data/pantallaDerecha.png");
	 	 
 }//END PUBLIC LOGICA
 
 public void ejecutar(){
	 id= com.getIdentificador();
	 System.out.println("en logica soy " + id);

	 
	 //Hago un switch para cada id (pantalla distinta) y para cada caso le hago otro switch
	 //donde cada caso es una interfaz distinta en la pantalla (home, instrcucciones y juego)
	 
	 switch (id){
	 //Pantalla izq
	 case 0:
		 switch(pantallaIzq){
		 case 0://home
			app.image(pantallaHomeIzq, 0, 0);
			 break;
			 
		 case 1://negra
			 
			 break;
			 
		 case 2://juego
			 app.image(pantallaIzquierda, 0, 0);
			 
			 //Arañas
			 //añado las arañas y le doy un tiempo entre cada una para que aparezcan
			    if(app.frameCount%120==0){
			 aranitas.add(new Aranas(app));
			 }
			 
			 //pinto las arañas
			 for (int i = 0 ; i < aranitas.size() ; i++) {
			      aranitas.get(i).pintarArana();//llamo al metodo pintar de las arañas noramles
			      aranitas.get(i).moverArana();//llamo al metodo mover de las arañas normales
			      
			      int posY= aranitas.get(i).getPosY();
			      
			      //este string me guarda la posicion en Y de las arañas para pasarla a la otra pantalla despues
			      String blah= "posY:" + Integer.toString(posY); 
			      
			      
			      int posX= aranitas.get(i).getPosX();
			      
			      //condicional par cuando la araña llegue al final de la pantalla envie la posY 
			      if(posX>=400){
			    	  com.enviameTuPos(blah);
			    	  
			    	  
			    System.out.println("llegue soy la peor araña buahaha" + " " + blah);
			      }
			    
			      
			    }//for
				 
			 break;
			 
		 }//end switch pantallas
		 
		 break;
		 
	//Pantalla centro
	 case 1:
		 switch(pantallaMedio){
		 case 0://home
			app.image(pantallaHomeMedio, 0,0);
			 break;
			 
		 case 1://instrucciones
			 app.image(instrucciones, 0, 0);
			 break;
			 
		 case 2://juego
			 app.image(pantallaJugador, 0, 0);
			 
			 if(app.frameCount%120==0){
				 aranitas.add(new Aranas(app));
			 }
				 
				 //pinto 
				 for (int i = 0 ; i < aranitas.size() ; i++) {
				      aranitas.get(i).pintarAranasMedio(com.getPosY());//llamo al metodo pintar de las arañas noramles
				      aranitas.get(i).moverAranaNueva();//llamo al metodo mover de las arañas normales
				      
				      /*int posY= aranitas.get(i).getPosY();
				      String blah= "posY:" + Integer.toString(posY); 
				      
				      
				      int posX= aranitas.get(i).getPosX();
				      
				      if(posX>=400){
				    	  com.enviameTuPos(blah);
				    	  
				    System.out.println("llegue soy la peor araña buahaha" + " " + blah);
				      }*/
				    }//for
				
			 break;
		 }//end switch pantallas
		 
		 break;
		 
	 //Pantalla der
	 case 2:
		 switch(pantallaDer){
		 case 0://home
			 app.image(pantallaHomeDer, 0,0);
			 break;
			 
		 case 1://negra
			 break;
			 
		 case 2://juego
			 app.image(pantallaDerecha, 0, 0);
			 
			 break;
		 }//end switch pantallas
		 
		 break;
	 }//end switch
	 
 }//end ejecutar
 
 //SOLO PARA PROBAR SI LAS IMAGENES ESTAN
// public void clickPantallas(int x, int y){
//	  if (app.mouseButton==app.LEFT) {
//	     pantallas++;
//	    }//IF
// }
 
 //Estem metodo es solo para probar sin el celular que las pantallas cambien (por controlar)
 public void clickPantallas(int x, int y){
	  if (app.mouseButton==app.LEFT) {
	     pantallaIzq++;
	     pantallaMedio++;
	     pantallaDer++;
	    }//IF
}
 
}//public class logica
