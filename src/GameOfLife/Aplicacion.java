package GameOfLife;

import javax.swing.*;
import java.awt.*;

public class Aplicacion {
	
	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("Game Of Life");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventana ventana = new Ventana(frame);
		Component contents = ventana.createComponents();
		frame.setJMenuBar(ventana.crearMenu());
		frame.getContentPane().add(contents, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(1012, 690);
		frame.setVisible(true);		
	}
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
					public void run() { createAndShowGUI(); }
				}
		);
		
	}
	
}