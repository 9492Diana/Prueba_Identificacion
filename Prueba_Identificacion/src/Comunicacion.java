//Comunicacion Doll


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;



import java.net.SocketException;
import java.net.SocketTimeoutException;




//Importar processing
import processing.core.PApplet;

public class Comunicacion extends Thread{

	//creo el objeto
	private PApplet app;
	
//necesito crear:
	//multicast: crea comunicacion
	private MulticastSocket ms;
	
	//puerto: al que me conecto
	private int puerto;
	
	//ip
	private InetAddress ip;
	
	//id: ident pantallas
	private int id;
	
	//para que el empiece a identificar cuando se le da play a la app (por controlar)
	private boolean identificando;
	
	private int posX, posY;
	
	public Comunicacion(int puerto, PApplet app){
		
		this.app= app;
		
		this.puerto=puerto;
		
		id= -1;
		
		identificando = false; 
		
		
		try {
			ms=new MulticastSocket(puerto);
			ip= InetAddress.getByName("228.5.10.18"); //este es el ip local de mi pc
			
			ms.joinGroup(ip);
			ms.setSoTimeout(500);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enviarSaludo(); // ver que estaba funcionando la comunicacion
		
		while (!identificando) {
			recibirIds();
		}
		
		start(); // mi hilo empieza
		
	}//end public comunicacion
	
	//METODOS:
	//ENVIAR ID
	private void enviarId(){
			
	byte[] buf = ("soy:" + id).getBytes();
	
	DatagramPacket paquete = new DatagramPacket(buf, buf.length, ip, puerto);
	
	try {
		ms.send(paquete);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}//end public void enviarId
	
	//___________________________________________________________________________________
	//RECIBIR ID: les da el nombre a las pantallas
	private void recibirIds() {
		byte[] buf = new byte[50];
		DatagramPacket paquete = new DatagramPacket(buf, buf.length);
		try {
			ms.receive(paquete);
			String recibido = new String(paquete.getData(), 0,
					paquete.getLength());
			if (recibido.startsWith("soy")) {
				
				int idExt = Integer.parseInt(recibido.split(":")[1]);
				if (idExt >= id) {
					id = idExt + 1;
				}
	
			}
			
			//excepcion especial para que el -1 se vuelva 0
		} catch (SocketTimeoutException ste) {
			if (id == -1) {
				id = 0;
			}
			try {
				ms.setSoTimeout(0);
			} catch (SocketException e) {
				e.printStackTrace();
	
	}
			identificando = true;
	System.out.println("Me identifique como: " + id);

} catch (IOException e) {
	e.printStackTrace();
}
		}//end public void recibir id
	
	//METODO PARA SABER SI NECESITA UN NOMBRE
	private void recibirAvisos() {
		byte[] buf = new byte[100];
		DatagramPacket paquete = new DatagramPacket(buf, buf.length);
		try {
			ms.receive(paquete);
			String mensaje = new String(paquete.getData(), 0,
					paquete.getLength());
			if (mensaje.startsWith("hola")) {
				enviarId();
			} else if (mensaje.startsWith("soy")) {
				// nada
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//END private void recibir avisos
	//______________________________________________________________________
	//METODO PARA SALUDAR Y SABER SI FUNCIONA LA CONEXION
	private void enviarSaludo() { // funciona la conexion
		byte[] buf = "hola funciona la conexion".getBytes();
		DatagramPacket paquete = new DatagramPacket(buf, buf.length, ip,
				puerto);
		try {
			ms.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end enviar saludo
	
	//_______________________________________________________
	public void run() {
		while (identificando) {
			recibirAvisos();
			
			//metodo
			recibirPos();
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}//end void run
		
	//____________________________________________
	
	public void enviameTuPos(String posY) { 
		byte[] buf = posY.getBytes();
		DatagramPacket paquete = new DatagramPacket(buf, buf.length, ip,
				puerto);
		try {
			ms.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end enviameTuPos
	
	//_______________________________________________________
	
	public void recibirPos() {
		byte[] buf = new byte[50];
		DatagramPacket paquete = new DatagramPacket(buf, buf.length);
	
		try {
			ms.receive(paquete);
			String recibido = new String(paquete.getData(), 0,
					paquete.getLength());
			if (recibido.startsWith("posY")) {
				 posY = Integer.parseInt(recibido.split(":")[1]);
				 
				 System.out.println("toma mi posY en la otra pantalla" + posY);
					
			}
			
			//excepcion especial para que el -1 se vuelva 0
		} catch (SocketTimeoutException ste) {
	System.out.println("Posicion recibida: " + posY);
	
} catch (IOException e) {
	e.printStackTrace();
}
		}//end public void recibirPos
	//_________________________________________________________
	
	//GETS
	public int getIdentificador() {
		return id;
	}
	
	public int getPosY() {
		return posY;
	}
	
}// end Class
