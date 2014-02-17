package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GameOfLife extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final int TAMCEL_DEFAULT = 10;
	
	public static final int TAMCELMIN = 2;
	public static final int TAMCELMAX = 20;
	
	// Filas y columnas por tamaño de celda: 2..20
	public static final int[] LFIL = {300, 200, 150, 120, 100, 86, 75, 67, 60, 55, 50, 46, 43, 40, 38, 35, 33, 32, 30};
	public static final int[] LCOL = {500, 333, 250, 200, 167, 143, 125, 111, 100, 91, 83, 77, 71, 67, 63, 59, 56, 53, 50};
	
	
	public static final int VEL_DEFAULT = 0;
	public static final int VEL_DIFF = 50;
	public static final int VEL_MIN = 1000;
	public static final int VEL_MAX = 0;
	
	
	// Ventana en la que esta GameOfLife
	private Ventana ventana;
	
	
	// Dimensiones
	private int fil;
	private int col;
	private int tamcel;
	
	
	// Celulas
	private Vector<Vector<Celula>> celulas;
	
	
	// Generacion actual del juego
	private int generacion;
	
	// Otros atributos
	private boolean juegoEnMarcha;
	
	private Timer timer;
	private int velocidad;

	
	
	public GameOfLife(Ventana laVentana) {
		ventana = laVentana;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		velocidad = VEL_DEFAULT;
		timer = new Timer(velocidad, this);
		
		fil = LFIL[TAMCEL_DEFAULT - TAMCELMIN];
		col = LCOL[TAMCEL_DEFAULT - TAMCELMIN];
		tamcel = TAMCEL_DEFAULT;
		Celula.setTamCel(TAMCEL_DEFAULT);
		
		celulas = new Vector<Vector<Celula>>();
		
		juegoEnMarcha = false;
				
		generarTablero();
		
		repaint();
	}
	
	
	private void generarTablero() {
		// genera los vectores de celulas
		for (int f=0; f<LFIL[0]+2; f++) {
			celulas.add(new Vector<Celula>());
			for (int c=0; c<LCOL[0]+2; c++) {
				celulas.get(f).add(new Celula(c*Celula.getTamCel(), f*Celula.getTamCel()));
			}
		}
		
		// agrega sus vecinas a cada celula
		for (int f=1; f<LFIL[0]+1; f++) {
			for (int c=1; c<LCOL[0]+1; c++) {
				celulas.get(f).get(c).nuevaVecina(celulas.get(f-1).get(c));    // Norte
				celulas.get(f).get(c).nuevaVecina(celulas.get(f+1).get(c));    // Sur
				celulas.get(f).get(c).nuevaVecina(celulas.get(f).get(c+1));    // Este
				celulas.get(f).get(c).nuevaVecina(celulas.get(f).get(c-1));    // Oeste
				celulas.get(f).get(c).nuevaVecina(celulas.get(f-1).get(c+1));  // Noreste
				celulas.get(f).get(c).nuevaVecina(celulas.get(f-1).get(c-1));  // Noroeste
				celulas.get(f).get(c).nuevaVecina(celulas.get(f+1).get(c+1));  // Sureste
				celulas.get(f).get(c).nuevaVecina(celulas.get(f+1).get(c-1));  // Suroeste
			}
		}
	}
	
	public void iniciarPartida() {
		// Inicia una nueva partida de GameOfLife
		generacion = 0;
		ventana.updateGeneracion();
		juegoEnMarcha = true;
		timer.setDelay(velocidad);
		timer.start();
	}
	
	public void pausarPartida() {
		juegoEnMarcha = false;
		timer.stop();
	}
	
	public void continuarPartida() {
		juegoEnMarcha = true;
		timer.setDelay(velocidad);
		timer.start();
	}
	
	
	public int getGeneracion() {
		return generacion;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int vel) {
		velocidad = vel;
		timer.setDelay(velocidad);
	}
	
	public void setPatron(boolean patron[][]) {
		limpiarEstados();
		for (int f=0; f<fil+2; f++) {
			for (int c=0; c<col+2; c++) {
				celulas.get(f).get(c).modificarEstado(patron[f][c]);
			}
		}
		repaint();
	}
	
	public void limpiarEstados() {
		for (int i=0; i<LFIL[0]+2; i++) {
			for (int j=0; j<LCOL[0]+2; j++) {
				celulas.get(i).get(j).modificarEstado(false);
			}
		}
	}
	
	public void limpiarTablero() {
		generacion = 0;
		ventana.updateGeneracion();
		juegoEnMarcha = false;
		timer.stop();
		limpiarEstados();
		repaint();
	}
	
	public void alejar() {
		if ( tamcel-1 >= TAMCELMIN ) {
			setDim(tamcel - 1);
		}
	}
	
	public void acercar() {
		if ( tamcel+1 <= TAMCELMAX ) {
			setDim(tamcel + 1);
		}
	}
	
	public void zoomDefecto() {
		setDim(TAMCEL_DEFAULT);
	}
	
	private void setDim(int tamc) {
		fil = LFIL[tamc - TAMCELMIN];
		col = LCOL[tamc - TAMCELMIN];
		tamcel = tamc;
		Celula.setTamCel(tamc);
		
		// Reposiciona las celulas
		for (int f=0; f<LFIL[0]+2; f++) {
			for (int c=0; c<LCOL[0]+2; c++) {
				celulas.get(f).get(c).setPosicion(c*tamcel, f*tamcel);
			}
		}
		
		repaint();
	}
	
	public int getFil() {
		return fil;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getTamCel() {
		return tamcel;
	}
	
	
	@Override
	public void paint(Graphics graphics) {		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		// Celulas Filas		
		for (int f=1; f<fil+1; f++) {
			for (int c=1; c<col+1; c++) {
				celulas.get(f).get(c).draw(graphics);
			}
		}
	}
	
	
	
	
	/********************************************/
	/** Métodos de MouseListener               **/
	/********************************************/
	
	@Override
	public void mousePressed(MouseEvent event) {}
    	
    @Override
	public void mouseReleased(MouseEvent event) {}
    
    @Override
	public void mouseEntered(MouseEvent event) {}
    
    @Override
	public void mouseExited(MouseEvent event) {}
    
    @Override
	public void mouseClicked(MouseEvent event) {}
    	
    
    
	/********************************************/
	/** Métodos de MouseMotionListener         **/
	/********************************************/

    @Override
	public void mouseMoved(MouseEvent event) {}
    
    // OJO!! Permitir elminar
    /* Combinación de teclas eliminar método keypressed */
   	@Override
	public void mouseDragged(MouseEvent event) {
   		if (event.getX() >= 0 && event.getY() >= 0 && 
   				event.getX() <= col*tamcel && event.getY() <= fil*tamcel) {
   			celulas.get( (event.getY() / tamcel) +1).get( (event.getX() / tamcel) +1).modificarEstado(true);
   			repaint();
   		}
   	}


   	
   	/********************************************/
	/** Métodos de ActionListener              **/
	/********************************************/
   	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// si el juego no esta en marcha esperamos a que comience antes de
		// avanzar a la siguiente generacion
		if (!juegoEnMarcha) return;
		
		// recoge el estado en la siguietne generacion de todas las celulas
		for (int f=0; f<fil+2; f++) {
			for (int c=0; c<col+2; c++) {
				celulas.get(f).get(c).comprobarEstado();
			}
		}
		
		generacion++;
		ventana.updateGeneracion();
		
		// actualiza el estado (pasa a siguiente generacion)
		for (int f=0; f<fil+2; f++) {
			for (int c=0; c<col+2; c++) {
				celulas.get(f).get(c).actualizarEstado();
			}
		}
		
		repaint();
	}
   
}