/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Helpers;

import Components.PicturePanel;
/**
 * ConvolutionFilter class stores necessary information to conduct convolution filtering.
 * Has also function applying filter to original image.
 */
public class ConvolutionFilter extends BaseOfFilter {

	int imageWidth, imageHeight, convWidth, convHeight, halfConvWidth, halfConvHeight;
	int[][] resultImage;
	int divisor = 0, offset = 0;
	int[][] filter;
	    
	public ConvolutionFilter(int[][] filter, int divisor, int offset, PicturePanel panel) {
		super(panel);
		this.imageWidth = panel.image.getWidth();
		this.imageHeight = panel.image.getHeight();

		this.filter = filter;
		
		this.convWidth = filter.length;
		this.convHeight = filter[0].length;
		this.halfConvWidth = convWidth/2;
		this.halfConvHeight = convHeight/2;
				
		this.divisor = divisor;
		this.offset = offset;
		
		resultImage = new int[imageWidth][imageHeight];
		pixelMatrix = makeIntRGBMatrix(byteMatrix, panel.image);
		panel.image = reproduceImage(panel.image, makeFiltering(pixelMatrix));
		updatePictureLabel();
	}
	
	private int[][] makeFiltering(int[][] pixelMatrix) {
		int[][] resultFilteringMatrix = new int[imageWidth][imageHeight];
		
		for(int i=0; i<imageWidth; i++) {
			for(int j=0; j<imageHeight; j++) {
				resultFilteringMatrix[i][j] = applyConvolutionMatrixFilter(pixelMatrix, filter, i, j);
			}
		}
		
		return resultFilteringMatrix;
	}
	
	private int applyConvolutionMatrixFilter(int[][] originalMatrix, int[][] convolutionMatrix, int x, int y) {
		int argb = 0, resultRed = 0, resultGreen = 0, resultBlue = 0;

		for(int i=-(halfConvWidth); i<=halfConvWidth; i++) {
			for(int j=-(halfConvHeight); j<=halfConvHeight; j++) {
				if(x+i < 0 || x+i >= originalMatrix.length || y+j < 0 || y+j >= originalMatrix[0].length) {
					resultRed += 0;
					resultGreen += 0;
					resultBlue += 0;
				} else {
					resultRed += getRed(originalMatrix[x+i][y+j])*convolutionMatrix[i+halfConvWidth][j+halfConvHeight]/divisor + offset;
					resultGreen += getGreen(originalMatrix[x+i][y+j])*convolutionMatrix[i+halfConvWidth][j+halfConvHeight]/divisor + offset;
					resultBlue += getBlue(originalMatrix[x+i][y+j])*convolutionMatrix[i+halfConvWidth][j+halfConvHeight]/divisor + offset;
				}
			}
		}
		
        argb += (((int) originalMatrix[x][y] & 0xff) << 24); // alpha
        argb += ((int) resultBlue & 0xff); // blue
        argb += (((int) resultGreen & 0xff) << 8); // green
        argb += (((int) resultRed & 0xff) << 16); // red
		
		return argb;
	}
	
	private int getRed(int value) {
		return (value >> 16) & 0xFF;
	}
	
	private int getGreen(int value) {
		return (value >> 8) & 0xFF;
	}
	
	private int getBlue(int value) {
		return value & 0xFF;
	}
}