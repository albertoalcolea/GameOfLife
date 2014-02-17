package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;


public class Celula {

	private static int tamcel;
	
	// Atributos
	private boolean estado, nuevoEstado;
	
	private int pos_x;
	private int pos_y;
	
	private int     numVecinas;
	private Celula[] vecina = new Celula[8];

	
	// Constructores
	public Celula() {
		estado = false;  // celula muerta
        numVecinas = 0;
	}
	
	public Celula(int x, int y) {
		estado = false;  // celula muerta
        numVecinas = 0;
        pos_x = x;
        pos_y = y;
	}
	
	
	
	// Otros metodos
	public void draw(Graphics graphics) {	
		if (estado) {
			graphics.setColor(Color.DARK_GRAY);
		} else {
			graphics.setColor(Color.WHITE);
		}
		
		// corremos una (restar tam a pos) ya que los extremos no se dibujan
        // se almacenan para calcular el nuevo estado a conociendo los estados
		// de las celulas vecinas
		graphics.fillRect(pos_x-tamcel, pos_y-tamcel, tamcel-1, tamcel-1);  
	}

	
	public static void setTamCel(int tam) {
		tamcel = tam;
	}
	
	public static int getTamCel() {
		return tamcel;
	}
	
	
	public void setPosicion(int x, int y) {
		pos_x = x;
		pos_y = y;
	}
	
	public int getX() {
		return pos_x;
	}
	
	public int getY() {
		return pos_y;
	}
	
	
	public void nuevaVecina(Celula n) {
		if (numVecinas < 8) {
			vecina[numVecinas++] = n; 
		}
	}
	
	
	// Devuelve el estado que tendra la celula en la siguiente generacion
	public boolean comprobarEstado() {
        int numVivas = 0;  // numero de vecinas vivas
        
        nuevoEstado = estado;
        
        for (int i = 0; i < numVecinas; i++) {
        	if (vecina[i].estado) numVivas++;
        }
        
        if (estado) {
            if (numVivas < 2)  // muerte por soledad
            	nuevoEstado = false;
            if (numVivas > 3)  // muerte por superpoblacion
            	nuevoEstado = false;
        } else {
        	if (numVivas == 3) // nace nueva celula
        		nuevoEstado = true;
        }
        
        return nuevoEstado;
	}
	
	void actualizarEstado() {
		estado = nuevoEstado;
	}
	
	public void modificarEstado(boolean elEstado) {
		estado = elEstado;
	}
}
