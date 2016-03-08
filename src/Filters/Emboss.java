/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Filters;

import Components.PicturePanel;
import Helpers.ConvolutionFilter;
/**
 * Embossing image by setting convolution matrix, divisor(factor) and offset. Then convolution matrix is applied to each and every pixel. 
 */
public class Emboss {

	int[][] filter;
	int divisor, offset;
	    
	public Emboss(PicturePanel panel) {
		this.filter = new int[][] {
			{-1,-1,0},
			{-1, 0,1},
			{ 0, 1,1}
		};
	
		this.divisor = 1;
		this.offset = 128;
	
		new ConvolutionFilter(filter, divisor, offset, panel);
	}
	
	public void setDivisor(int value) {
		this.divisor = value;
	}
	
	public void setOffset(int value) {
		this.offset = value;
	}
}