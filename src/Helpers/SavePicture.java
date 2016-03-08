/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Helpers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import Components.PicturePanel;
/**
 * Class which serves to save filtered image.
 */
public class SavePicture {

	File file;
	JFileChooser fc;
	
	public SavePicture(PicturePanel chooser) {
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new ExtensionFilter(".png", "PNG"));
		fc.setFileFilter(new ExtensionFilter(".jpg", "JPG"));
		fc.setAcceptAllFileFilterUsed(false);
		openFileDialog(chooser);
	}
	
	private void openFileDialog(PicturePanel chooser) {
		int result = fc.showSaveDialog(chooser);
        if (result==JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
			try {
				switch(fc.getFileFilter().getDescription()) {
					case "JPG":
						if(!file.getPath().endsWith(".jpg")) {
						    file = new File(file.getPath() + ".jpg");
						}
						ImageIO.write(chooser.image, "jpg", file);
						break;
					case "PNG":
						if(!file.getPath().endsWith(".png")) {
						    file = new File(file.getPath() + ".png");
						}
						ImageIO.write(chooser.image, "png", file);
						break;
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}		
        }
	}
}
