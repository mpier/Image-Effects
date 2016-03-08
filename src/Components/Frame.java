/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Components;

import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * Frame class generates main application frame.
 * Frame consists of Picture Panel and Menu.
 */
public class Frame extends JFrame {			

	private Menu menuBar;
	static PicturePanel panel;
	
	public Frame(){	
		super("Image Effects");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		menuBar = new Menu();
		setJMenuBar(menuBar);
		
		panel = new PicturePanel();
		
		add(panel);
		setSize(new Dimension(500, 500));
		setVisible(true);
	}
	
}
