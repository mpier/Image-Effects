/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Filters;

import Components.PicturePanel;
import Helpers.*;
/**
 * Gaussian Blurring image by setting convolution matrix, divisor(factor) and offset. Then convolution matrix is applied to each and every pixel. 
 */
public class GaussianBlur {
	
	int[][] filter;
	int divisor, offset;
	    
	public GaussianBlur(PicturePanel panel) {
		this.filter = new int[][] {
			{0,1,0},
			{1,4,1},
			{0,1,0}
		};
	
		this.divisor = 9;
		this.offset = 0;
	
		new ConvolutionFilter(filter, divisor, offset, panel);
	}
	
	public void setDivisor(int value) {
		this.divisor = value;
	}
	
	public void setOffset(int value) {
		this.offset = value;
	}
}