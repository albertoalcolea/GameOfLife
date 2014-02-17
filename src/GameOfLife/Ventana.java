package GameOfLife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Ventana implements ActionListener {
		
	// GUI
	private JFrame padre;
	
	private JPanel panel;
	private JButton btoIniciar;
	private JButton btoPausar;
	private JButton btoLimpiar;
	private JLabel lblGeneracion; 
	private JLabel lblAcercaDe;
	
	GameOfLife gol;
	
	// Menu
	private JMenu     mOpciones, 
                      mPatrones,
                      mZoom,
                      mAyuda;
    private JMenuItem miMasVelocidad,
                      miMenosVelocidad,
                      miGosperGliderGun,
                      miReptor,
                      miRectangle,
                      miAllBlack,
                      miRandom,
                      miAlejar,
                      miAcercar,
                      miTamDefecto,
                      miAyuda,
                      miAcercaDe;
	
    private boolean pausado;
    
    
	public Ventana(JFrame elPadre) { 
		super(); 
		padre = elPadre;
	}
	
	public Component createComponents() {
		
		panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
        	
		gol = new GameOfLife(this);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
        panel.add(gol, constraints);
		
        btoIniciar = new JButton("Iniciar");
        btoIniciar.setMnemonic(KeyEvent.VK_I);
        btoIniciar.addActionListener(this);
        constraints.gridx = 0;
    	constraints.gridy = 3;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	constraints.weightx = 0;
    	constraints.weighty = 0;
    	panel.add(btoIniciar, constraints);  	
    	
    	btoPausar = new JButton("Pausar");
    	btoPausar.setMnemonic(KeyEvent.VK_S);
    	btoPausar.addActionListener(this);
    	btoPausar.setEnabled(false);
    	pausado = false;
        constraints.gridx = 1;
    	constraints.gridy = 3;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	constraints.weightx = 0;
    	constraints.weighty = 0;
    	panel.add(btoPausar, constraints);  
    	
    	btoLimpiar = new JButton("Limpiar");
    	btoLimpiar.setMnemonic(KeyEvent.VK_L);
    	btoLimpiar.addActionListener(this);
        constraints.gridx = 2;
    	constraints.gridy = 3;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	constraints.weightx = 0;
    	constraints.weighty = 0;
    	panel.add(btoLimpiar, constraints);
    	
    	lblGeneracion = new JLabel("Generaci\u00F3n: 0");
    	constraints.gridx = 3;
    	constraints.gridy = 3;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill = GridBagConstraints.NONE;
    	constraints.anchor = GridBagConstraints.CENTER;
    	constraints.weightx = 1;
    	constraints.weighty = 0;
    	panel.add(lblGeneracion, constraints); 
    	
    	lblAcercaDe = new JLabel("Creado por Alberto Alcolea");
    	constraints.gridx = 4;
    	constraints.gridy = 3;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill = GridBagConstraints.NONE;
    	constraints.anchor = GridBagConstraints.EAST;
    	constraints.weightx = 0;
    	constraints.weighty = 0;
    	panel.add(lblAcercaDe, constraints); 
    	
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		return panel;		
	}
	
	public JMenuBar crearMenu() {
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
	    
	    miReptor = new JMenuItem("Reptor");
	    miReptor.getAccessibleContext().setAccessibleDescription(
	    		"Patron Reptor");
	    miReptor.addActionListener(this);
	    mPatrones.add(miReptor);
	    
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
	    
	    miRandom = new JMenuItem("Random");
	    miRandom.getAccessibleContext().setAccessibleDescription(
	    		"Patron Random");
	    miRandom.addActionListener(this);
	    mPatrones.add(miRandom);
		
	 // construccion del segundo menu
	    mZoom = new JMenu("Zoom");
	    mZoom.setMnemonic(KeyEvent.VK_Z);
	    mZoom.getAccessibleContext().setAccessibleDescription(
	            "Zoom del tablero");
	    m.add(mZoom);
	    
	    // JMenuItems
	    miAlejar = new JMenuItem("Alejar");
	    miAlejar.setAccelerator(KeyStroke.getKeyStroke(
	            KeyEvent.VK_M, ActionEvent.ALT_MASK));
	    miAlejar.getAccessibleContext().setAccessibleDescription(
	    		"Alejar tablero");
	    miAlejar.addActionListener(this);
	    mZoom.add(miAlejar);
	    
	    miAcercar = new JMenuItem("Acercar");
	    miAcercar.setAccelerator(KeyStroke.getKeyStroke(
	            KeyEvent.VK_N, ActionEvent.ALT_MASK));
	    miAcercar.getAccessibleContext().setAccessibleDescription(
               "Acercar tablero");
	    miAcercar.addActionListener(this);
	    mZoom.add(miAcercar);
	    
	    miTamDefecto = new JMenuItem("Por defecto");
	    miTamDefecto.getAccessibleContext().setAccessibleDescription(
	               "Ajustar zoom por defecto");
	    miTamDefecto.addActionListener(this);
		mZoom.add(miTamDefecto);

		// construccion del cuarto menu
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
	
	
	public void updateGeneracion() {
		lblGeneracion.setText("Generaci\u00F3n: " + gol.getGeneracion());	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		// captura eventos del menu
		actionMenu(e);
				
		// boton iniciar
		if (o == btoIniciar) {
			gol.requestFocusInWindow();
			gol.iniciarPartida();
			pausado = false;
			btoPausar.setText("Pausar");
			btoPausar.setEnabled(true);
			btoIniciar.setEnabled(false);
			return;
		}
				
		// boton pausar
		if (o == btoPausar) {
			gol.requestFocusInWindow();
			if (!pausado) {
				pausado = true;
				gol.pausarPartida();
				btoPausar.setText("Continuar");
				btoIniciar.setEnabled(true);
			} else {
				pausado = false;
				gol.continuarPartida();
				btoPausar.setText("Pausar");
				btoIniciar.setEnabled(false);
			}
			return;
		}
		
		// boton limpiar
		if (o == btoLimpiar) {
			gol.requestFocusInWindow();
			gol.limpiarTablero();
			pausado = false;
			btoPausar.setText("Pausar");
			btoPausar.setEnabled(false);
			btoIniciar.setEnabled(true);
            return;
		}	
	}
	
	
	public synchronized void actionMenu(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == miMasVelocidad) {
			if (gol.getVelocidad() <= GameOfLife.VEL_MAX) {
				gol.setVelocidad(GameOfLife.VEL_MAX);
			} else {
				gol.setVelocidad(gol.getVelocidad() - GameOfLife.VEL_DIFF);
			}
		}
		
		if (o == miMenosVelocidad) {
			if (gol.getVelocidad() >= GameOfLife.VEL_MIN) {
				gol.setVelocidad(GameOfLife.VEL_MIN);
			} else {
				gol.setVelocidad(gol.getVelocidad() + GameOfLife.VEL_DIFF);
			}
		}
		
		if (o == miGosperGliderGun) {
			Patrones.setMaxFil(gol.getFil()+2);
			Patrones.setMaxCol(gol.getCol()+2);
			gol.setPatron(Patrones.gosperGliderGun());
		}
		
		if (o == miReptor) {
			Patrones.setMaxFil(gol.getFil()+2);
			Patrones.setMaxCol(gol.getCol()+2);
			gol.setPatron(Patrones.reptor());
		}
		
		if (o == miRectangle) {
			Patrones.setMaxFil(gol.getFil()+2);
			Patrones.setMaxCol(gol.getCol()+2);
			gol.setPatron(Patrones.rectangle());
		}
		
		if (o == miAllBlack) {
			Patrones.setMaxFil(gol.getFil()+2);
			Patrones.setMaxCol(gol.getCol()+2);
			gol.setPatron(Patrones.allBlack());
		}
		
		if (o == miRandom) {
			Patrones.setMaxFil(gol.getFil()+2);
			Patrones.setMaxCol(gol.getCol()+2);
			gol.setPatron(Patrones.random());
		}
		
		if (o == miAlejar) {
			gol.alejar();
		}
		
		if (o == miAcercar) {
			gol.acercar();
		}
		
		if (o == miTamDefecto) {
			gol.pausarPartida();
			gol.zoomDefecto();
			gol.continuarPartida();
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
}