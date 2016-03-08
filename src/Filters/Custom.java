/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Filters;

import Components.PicturePanel;
import Helpers.ConvolutionFilter;
/**
 * Custom filter by setting BY USER convolution matrix, divisor(factor) and offset. Then convolution matrix is applied to each and every pixel. 
 * Values typed by user comes from FrameCustom class.
 */
public class Custom {

	int[][] filter;
	int divisor, offset;
	    
	public Custom(int[][]filter, int divisor, int offset, PicturePanel panel) {
		this.filter = filter;
	
		this.divisor = divisor;
		this.offset = offset;
	
		new ConvolutionFilter(filter, divisor, offset, panel);
	}
	
	public void setFilter(int[][] filter) {
		this.filter = filter;
	}
	
	public void setDivisor(int value) {
		this.divisor = value;
	}
	
	public void setOffset(int value) {
		this.offset = value;
	}
}