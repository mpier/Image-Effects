/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Helpers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;

import Components.PicturePanel;
/**
 * The real base of each of filter. Contains basic operations in order to filter image.
 */
public class BaseOfFilter {

	protected byte[] byteMatrix;
	protected int[][] pixelMatrix;
	PicturePanel panel;
	
	public BaseOfFilter(PicturePanel panel) {
		this.panel = panel; 
		pixelMatrix = new int[panel.image.getWidth()][panel.image.getHeight()];
		this.byteMatrix = convertToByteMatrix(panel.image);
	}
	
	public void updatePictureLabel() {
		panel.pictureLabel.setIcon(new ImageIcon(panel.image));
		System.out.println("picture output updated");
	}
	
	public BufferedImage reproduceImage(BufferedImage image, int[][] matrix) {
		image = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int i = 0; i < image.getWidth(); i++) {
	        for (int j = 0; j < image.getHeight(); j++) {
	            int pixel=matrix[i][j];
	            image.setRGB(i, j, pixel);
	        }
		}

	    return image;    
	}
	
	private byte[] convertToByteMatrix(BufferedImage image) {
		return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	}
	
	public int[][] makeIntRGBMatrix(byte[] matrix, BufferedImage image) {

	      final byte[] pixels = convertToByteMatrix(image);
	      final int width = image.getWidth();
	      final int height = image.getHeight();
	      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

	      int[][] result = new int[width][height];
	      if (hasAlphaChannel) {
	         final int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	            result[col][row] = argb;
	            
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      } else {
	         final int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += -16777216; // 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); // blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	            result[col][row] = argb;

	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      }

	    return result;
	   }
}
