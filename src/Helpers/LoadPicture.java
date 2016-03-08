/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Helpers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import Components.*;
/**
 * Class which serves to load original image.
 */
public class LoadPicture {

	File file;
	JFileChooser fc;
	
	public LoadPicture(PicturePanel chooser) {
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new ExtensionFilter(".png", "PNG"));
		fc.addChoosableFileFilter(new ExtensionFilter(".jpg", "JPG"));
		openFileDialog(chooser);
	}
	
	private void openFileDialog(PicturePanel chooser) {
		int result = fc.showOpenDialog(chooser);
        if (result==JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            chooser.image = null;
			try {
				chooser.image = ImageIO.read(new File(file.getAbsolutePath()));	
				chooser.pictureLabel.setIcon(new ImageIcon(chooser.image));

			} catch (IOException e1) {
				e1.printStackTrace();
			}		
        }
	}
}
