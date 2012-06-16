import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*****************************************************************************
 * @name Juego de la vida
 * @author Alberto Alcolea
 * @date 8-10-2011
 *****************************************************************************/

public class GameOfLife extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	// GUI Celulas
	static final Color[] color = {Color.WHITE, Color.DARK_GRAY};
	// tamaño de la celula en pixeles
	static final int tam = 15;
	static final Dimension dim = new Dimension(tam, tam);
	
	
    // matriz de celulas
	private Celula[][] celula;
	
	// contador de generaciones o turnos
	private int generacion = 0;
	
	// control del juego
	private Timer timer;
	private int velocidad;
	private boolean juegoEnMarcha = false;
	private boolean mouseDown = false;
	
	// GUI
	private JFrame padre;
	private JPanel panel;
	private JButton btoIniciar   = new JButton("Iniciar"),
	                btoPausar    = new JButton("Pausar"),
	                btoLimpiar   = new JButton("Limpiar");
	private JLabel lblGeneracion = new JLabel("Generaci\u00F3n: 0");
	private JLabel lblAcercaDe   = new JLabel("Creado por Alberto Alcolea");
	
	// Menu
	private JMenu     mOpciones, 
                      mPatrones,
                      mAyuda;
    private JMenuItem miMasVelocidad,
                      miMenosVelocidad,
                      miGosperGliderGun,
                      miRectangle,
                      miAllBlack,
                      miAyuda,
                      miAcercaDe;
	
	
	// Constructor 
	GameOfLife(int filas, int columnas) {
		super("Juego de la vida");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		// crea la matriz de celulas
		// crea dos mas que no seran mostradas pero se utilizaran para calcular
		// las celulas vivas alrededor suya
		celula = new Celula[filas+2][columnas+2];
		for (int f = 0; f < filas+2; f++) {
			for (int c = 0; c < columnas+2; c++) {
				celula[f][c] = new Celula();
			}
		}
		
		// panel central con las celulas
		panel = new JPanel(new GridLayout(filas, columnas, 1, 1));
		panel.setBackground(Color.BLACK);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// agrega las celulas al panel y sus vecinas a cada celula
		for (int f = 1; f <= filas; f++) {
			for (int c = 1; c <= columnas; c++) {
				panel.add(celula[f][c]);
				celula[f][c].nuevaVecina(celula[f-1][c]);    // Norte
				celula[f][c].nuevaVecina(celula[f+1][c]); 	 // Sur
				celula[f][c].nuevaVecina(celula[f][c+1]); 	 // Este
				celula[f][c].nuevaVecina(celula[f][c-1]);    // Oeste
				celula[f][c].nuevaVecina(celula[f-1][c+1]);  // Noreste
				celula[f][c].nuevaVecina(celula[f-1][c-1]);  // Noroeste
				celula[f][c].nuevaVecina(celula[f+1][c+1]);  // Sureste
				celula[f][c].nuevaVecina(celula[f+1][c-1]);  // Suroeste
			}
		}
		
		// agregar el JPanel al JFrame
		add(panel, BorderLayout.CENTER);
		
		// panel inferior con los botones y demas elementos
		panel = new JPanel(new GridLayout(1,3));
		
		// panel con los botones
		JPanel panelBotones = new JPanel(new GridLayout(1,3));
		btoIniciar.addActionListener(this);
		panelBotones.add(btoIniciar);
		btoPausar.addActionListener(this);
		btoPausar.setEnabled(false);
		panelBotones.add(btoPausar);
		btoLimpiar.addActionListener(this);
		panelBotones.add(btoLimpiar);
		
		// agrega el panel de botones al panel inferior
		panel.add(panelBotones);
		
		// la etiqueta con el numero de generacion
		lblGeneracion.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblGeneracion);
		
		// la etiqueta acerca de
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAcercaDe);
		
		// agregar el JPanel al JFrame
		add(panel, BorderLayout.SOUTH);
				
		// agrega el menu al JFrame
		padre = this;
		setJMenuBar(crearMenu());
				
		
		// hace visible el JFrame
		setLocation(20, 20);
		pack();
		setVisible(true);
		
		velocidad = 50;
		timer = new Timer(velocidad, this);
	}
		
	public synchronized void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		// captura eventos del menu
		actionMenu(e);
		
		// boton iniciar
		if (o == btoIniciar) {
			iniciar();
			return;
		}
				
		// boton pausar
		if (o == btoPausar) {
            pausar();
            return;
		}
		
		// boton limpiar
		if (o == btoLimpiar) {
			limpiar();
            return;
		}
		
		// si el juego no esta en marcha esperamos a que comience antes de
		// avanzar a la siguiente generacion
		if (!juegoEnMarcha) return;
		
		// recoge el estado en la siguietne generacion de todas las celulas
		for (int f = 0; f < celula.length; f++) {
			for (int c = 0; c < celula[f].length; c++) {
				celula[f][c].comprobarEstado();
			}
		}
		
		generacion++;
		lblGeneracion.setText("Generaci\u00F3n: " + generacion);
		
		// actualiza el estado (pasa a siguiente generacion)
		for (int f = 0; f < celula.length; f++) {
			for (int c = 0; c < celula[f].length; c++) {
				celula[f][c].actualizarEstado();
			}
		}
	}
	
	public void iniciar() {
		btoPausar.setEnabled(true);
		btoIniciar.setEnabled(false);
		juegoEnMarcha = true;
		timer.setDelay(velocidad);
		timer.start();
	}
	
	public void pausar() {
		timer.stop();
		juegoEnMarcha = false;
		btoPausar.setEnabled(false);
		btoIniciar.setEnabled(true);
	}
	
	public void limpiar() {
		timer.stop();
		juegoEnMarcha = false;
		btoPausar.setEnabled(false);
		btoIniciar.setEnabled(true);
		// reinicia todas las celulas
		for (int f = 1; f < celula.length -1; f++) {
			for (int c = 1; c < celula[f].length -1; c++) {
				celula[f][c].limpiar();
			}
		}
		// reinicia la etiqueta con el numero de generacion
		generacion = 0;
		lblGeneracion.setText("Generaci\u00F3n: 0");
	}
	
	public void setPatron(boolean patron[][]) {
		limpiar();
		for (int f = 1; f < celula.length -1; f++) {
			for (int c = 1; c < celula[f].length -1; c++) {
				celula[f][c].modificarEstado(patron[f][c]);
			}
		}
	}

	
	private JMenuBar crearMenu() {
		JMenuBar m = new JMenuBar();
		
 		// construccion del primer menu
		mOpciones = new JMenu("Opciones");
    	mOpciones.setMnemonic(KeyEvent.VK_O);
    	mOpciones.getAccessibleContext().setAccessibleDescription(
	            "Menu con las opciones del juego");
	    m.add(mOpciones);

	    // JMenuItems
	    miMasVelocidad = new JMenuItem("Aumentar velocidad");
	    miMasVelocidad.setAccelerator(KeyStroke.getKeyStroke(
	            KeyEvent.VK_PLUS, ActionEvent.ALT_MASK));
	    miMasVelocidad.getAccessibleContext().setAccessibleDescription(
                "Aumentar velocidad de juego");
	    miMasVelocidad.addActionListener(this);
	    mOpciones.add(miMasVelocidad);

	    miMenosVelocidad = new JMenuItem("Disminuir velocidad");
	    miMenosVelocidad.setAccelerator(KeyStroke.getKeyStroke(
	            KeyEvent.VK_MINUS, ActionEvent.ALT_MASK));
	    miMenosVelocidad.getAccessibleContext().setAccessibleDescription(
                "Disminuir velocidad de juego");
	    miMenosVelocidad.addActionListener(this);
	    mOpciones.add(miMenosVelocidad);
	
       
		// construccion del segundo menu
	    mPatrones = new JMenu("Patrones");
	    mPatrones.setMnemonic(KeyEvent.VK_P);
	    mPatrones.getAccessibleContext().setAccessibleDescription(
	            "Patrones de celulas");
	    m.add(mPatrones);
	    
	    // JMenuItems
	    miGosperGliderGun = new JMenuItem("Gosper Glider Gun");
	    miGosperGliderGun.getAccessibleContext().setAccessibleDescription(
               "Patron Gosper Glider Gun");
	    miGosperGliderGun.addActionListener(this);
	    mPatrones.add(miGosperGliderGun);
	    
	    miRectangle = new JMenuItem("Rectangle");
	    miRectangle.getAccessibleContext().setAccessibleDescription(
	    		"Patron Rectangle");
	    miRectangle.addActionListener(this);
	    mPatrones.add(miRectangle);
	    
	    miAllBlack = new JMenuItem("All Black");
	    miAllBlack.getAccessibleContext().setAccessibleDescription(
	    		"Patron All Black");
	    miAllBlack.addActionListener(this);
	    mPatrones.add(miAllBlack);
			
		// construccion del tercer menu
	    mAyuda = new JMenu("Ayuda");
    	mAyuda.setMnemonic(KeyEvent.VK_A);
		mAyuda.getAccessibleContext().setAccessibleDescription(
	            "Menu de ayuda");
	    m.add(mAyuda);
	
        // JMenuItems
	    miAyuda = new JMenuItem("Ayuda del juego");
        miAyuda.getAccessibleContext().setAccessibleDescription(
	           "Ayuda del juego");
	    miAyuda.addActionListener(this);
	    mAyuda.add(miAyuda);

	    miAcercaDe = new JMenuItem("Acerca de");
	    miAcercaDe.getAccessibleContext().setAccessibleDescription(
                "Acerca de");
	    miAcercaDe.addActionListener(this);
	    mAyuda.add(miAcercaDe);
	    
	    return m;
	}
		
	public synchronized void actionMenu(ActionEvent e) {
		Object o = e.getSource();
		
		final int nFil = 30;
		final int nCol = 52;
		
		if (o == miMasVelocidad) {
			pausar();
			velocidad = velocidad - 50;
			if (velocidad < 0) velocidad = 0;
			iniciar();
		}
		
		if (o == miMenosVelocidad) {
			pausar();
			velocidad = velocidad + 50;
			if (velocidad > 5000) velocidad = 5000;
			iniciar();
		}
		
		if (o == miGosperGliderGun) {
			boolean patron[][] = new boolean[nFil+2][nCol+2];
			for (int f=0; f<nFil+2; f++) {
				for (int c=0; c<nCol+2; c++) {
					patron[f][c] = false;
				}
			}
			patron[13][9]  = true;
			patron[14][9]  = true;
			patron[13][10] = true;
			patron[14][10] = true;
			patron[13][19] = true;
			patron[14][19] = true;
			patron[15][19] = true;
			patron[12][20] = true;
			patron[16][20] = true;
			patron[11][21] = true;
			patron[11][22] = true;
			patron[17][21] = true;
			patron[17][22] = true;
			patron[14][23] = true;
			patron[12][24] = true;
			patron[16][24] = true;
			patron[13][25] = true;
			patron[14][25] = true;
			patron[15][25] = true;
			patron[14][26] = true;
			patron[13][29] = true;
			patron[12][29] = true;
			patron[11][29] = true;
			patron[13][30] = true;
			patron[12][30] = true;
			patron[11][30] = true;
			patron[10][31] = true;
			patron[14][31] = true;
			patron[10][33] = true;
			patron[9][33]  = true;
			patron[14][33] = true;
			patron[15][33] = true;
			patron[11][43] = true;
			patron[11][44] = true;
			patron[12][43] = true;
			patron[12][44] = true;
			setPatron(patron);
		}
		
		if (o == miRectangle) {
			boolean patron[][] = new boolean[nFil+2][nCol+2];
			for (int f=0; f<nFil+2; f++) {
				for (int c=0; c<nCol+2; c++) {
					if (f >= (nFil+2)/4 && f <= 3*(nFil+2)/4 &&
					    c >= (nCol+2)/4 && c <= 3*(nCol+2)/4) {
						patron[f][c] = true;
					} else {
						patron[f][c] = false;
					}
				}
			}
			setPatron(patron);
		}
		
		if (o == miAllBlack) {
			boolean patron[][] = new boolean[nFil+2][nCol+2];
			for (int f=0; f<nFil+2; f++) {
				for (int c=0; c<nCol+2; c++) {
					patron[f][c] = true;
				}
			}
			setPatron(patron);
		}
		
		if (o == miAyuda) {
			JOptionPane.showMessageDialog(padre, 
					"El tablero del juego se divide en c\u00E9lulas que " +
					"pueden estar vivas o muertas.\n\n" +
					"Cada c\u00E9lula tiene 8 vecinas que la rodean.\n\n" +
					"Durante cada turno o generaci\u00F3n las c\u00E9lulas van " +
					"evolucionando de la siguiente manera:\n\n" +
					"    - Una c\u00E9lula muerta con exactamente 3 c\u00E9lulas " +
					"vecinas vivas nace en la siguiente\n" +
					"      generaci\u00F3n.\n\n" +
					"    - Una c\u00E9lula viva con menos de 2 vecinas vivas " +
					"muere en la siguiente generaci\u00F3n\n" +
					"      por soledad.\n\n" +
					"    - Una c\u00E9lula viva con mas de 3 vecinas vivas " +
					"muere en la siguiente generaci\u00F3n\n" +
					"      por superpoblaci\u00F3n.\n\n" +
					"    - Una c\u00E9lula viva con exactamente 2 o 3 vecinas " +
					"vivas sigue viva en la siguiente\n" +
					"      generaci\u00F3n.\n\n", 
					"Ayuda",
			        JOptionPane.DEFAULT_OPTION);
		}
		
		if (o == miAcercaDe) {
			JOptionPane.showMessageDialog(padre, 
					"Juego de la vida de John Horton Conway\n\n" +
					"Implementado por Alberto Alcolea - 2011", 
					"Acerca de",
			        JOptionPane.DEFAULT_OPTION);
		}
	}
	
	public static void main(String[] arg) {
		new GameOfLife(30, 52);
	}
	
	
	private class Celula extends JLabel implements MouseListener {
		private static final long serialVersionUID = 1L;
				
		private boolean estado, nuevoEstado;
		private int     numVecinas;
		private Celula[] vecina = new Celula[8];
		
		
		Celula() {
			estado = false;  // celula muerta
            numVecinas = 0;
            
            // GUI
			setOpaque(true);
			setBackground(color[0]);
			addMouseListener(this);
			this.setPreferredSize(dim);
		}
		
		void nuevaVecina(Celula n) {
			if (numVecinas < 8) {
				vecina[numVecinas++] = n; 
			}
		}
		
		// Devuelve el estado que tendra la celula en la siguiente generacion
		boolean comprobarEstado() {
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
			if (estado != nuevoEstado) {	
				estado = nuevoEstado;
				if (estado) {
				    setBackground(color[1]);
				} else {
					setBackground(color[0]);
				}
			}
		}

		void modificarEstado(boolean nEstado) {
			if (estado != nEstado) {	
				estado = nEstado;
				if (estado) {
				    setBackground(color[1]);
				} else {
					setBackground(color[0]);
				}
			}
		}

		void limpiar() {
			if (estado || nuevoEstado) {
				estado = nuevoEstado = false;
				setBackground(color[0]);
			}
		}
		

		public void mouseClicked(MouseEvent arg0) {
		}
		

		public void mouseEntered(MouseEvent arg0) {
			if (mouseDown) {
				if (estado) {
					estado = nuevoEstado = false;
				    setBackground(color[0]);
				} else {
				    estado = nuevoEstado = true;
				    setBackground(color[1]);
				}
			}
		}
		

		public void mouseExited(MouseEvent arg0) {
		}
		

		public void mousePressed(MouseEvent arg0) {
			mouseDown = true;
			if (estado) {
				estado = nuevoEstado = false;
			    setBackground(color[0]);
			} else {
			    estado = nuevoEstado = true;
			    setBackground(color[1]);
			}
		}
		

		public void mouseReleased(MouseEvent arg0) {
			mouseDown = false;
		}		
	}
}
