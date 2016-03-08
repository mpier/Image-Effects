/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Components;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * PicturePanel class acts as a place to load and view wanted image.
 * PicturePanel class consists of stored BufferedImage, which is needed in further filtering.
 */
public class PicturePanel extends JPanel{
	
	
	public BufferedImage image;
	public JLabel pictureLabel;
	
	PicturePanel() {
		pictureLabel = new JLabel();
		image = null;
		
        add(pictureLabel);
	}
	
}
