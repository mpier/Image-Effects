/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Filters;

import java.awt.image.BufferedImage;

import Components.PicturePanel;
import Helpers.BaseOfFilter;
/**
 * Changing brightness of an image by changing offset of each element of a pixel color (RGB).
 */
public class Brightness extends BaseOfFilter {
	
	int bLevel; 
	
	public Brightness(int brightnessLevel, PicturePanel panel) {
		super(panel);
		this.bLevel = brightnessLevel;
		pixelMatrix = changeBrightness(byteMatrix, panel.image);
		panel.image = reproduceImage(panel.image, pixelMatrix);
		updatePictureLabel();
		
	}
	
	private int[][] changeBrightness(byte[] byteMatrix, BufferedImage image) {

		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[width][height];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < byteMatrix.length; pixel += pixelLength) {
				int argb = 0;
	            argb += (((int) byteMatrix[pixel] & 0xff << 24)); // alpha
	            argb += (addConstantAndCheckPixelBounds((int) byteMatrix[pixel+1] & 0xff)); // blue
	            argb += ((addConstantAndCheckPixelBounds((int) byteMatrix[pixel+2] & 0xff)) << 8); // green
	            argb += ((addConstantAndCheckPixelBounds((int) byteMatrix[pixel+3] & 0xff)) << 16); // red
	            result[col][row] = argb;

	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < byteMatrix.length; pixel += pixelLength) {
				int argb = 0;
	            argb += -16777216; // 255 alpha
	            argb += (addConstantAndCheckPixelBounds((int) byteMatrix[pixel] )& 0xff); // blue
	            argb += ((addConstantAndCheckPixelBounds((int) byteMatrix[pixel+1] )& 0xff) << 8); // green
	            argb += ((addConstantAndCheckPixelBounds((int) byteMatrix[pixel+2] )& 0xff) << 16); // red
	            result[col][row] = argb;

	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
			}
		}
	   
        System.out.println("Brightness Correction DONE!");
	    return result;
		}
		
		private int addConstantAndCheckPixelBounds(int colorRepresentation){
			int temp = colorRepresentation + bLevel;

			if(temp>255)
            	return 255;
			else if(temp<0)
				return 0;
            else 
            	return temp;

		}
}
		