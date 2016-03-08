/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Components;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Filters.Blur;
import Filters.Brightness;
import Filters.Contrast;
import Filters.EdgeDetection;
import Filters.Emboss;
import Filters.Gamma;
import Filters.GaussianBlur;
import Filters.Inverse;
import Filters.Sharpen;
import Helpers.LoadPicture;
import Helpers.SavePicture;
/**
 * Menu class creates JMenuBar in main frame. 
 * Menu consists of 3 sub-menus with many items.
 */
public class Menu extends JMenuBar implements ActionListener{

	private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenu filtersMenu1;
    private JMenuItem inversionMenuItem;
    private JMenu brightnessMenu;
    private JMenuItem brightnessMenuItem;
    private JMenu contrastMenu;
    private JMenuItem contrastMenuItem;
    private JMenu gammaMenu;
    private JMenuItem gammaMenuItem;
    private JMenu filtersMenu2;
    private JMenuItem blurMenuItem;
    private JMenuItem gaussianBlurMenuItem;
    private JMenuItem edgeDetectionMenuItem;
    private JMenuItem embossMenuItem;
    private JMenuItem sharpenMenuItem;
    private JMenuItem customMenuItem;
    
    private JTextField brightnessValue;
    private JTextField contrastValue;
    private JTextField gammaValue;
    
    Menu()  {
    	populateFileMenu();
	    add(fileMenu);
	    populateFiltersMenu1();
	    add(filtersMenu1);
	    populateFiltersMenu2();
	    add(filtersMenu2);
	    
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "load":
					new LoadPicture(Frame.panel);
					System.out.println("loaded");
					break;
				case "save":
					if(Frame.panel.image != null) {
						new SavePicture(Frame.panel);
						System.out.println("saved");
					} else {
						JOptionPane.showMessageDialog(this, "First load any image"); 
					}
					break;
				case "inversion":
					if(Frame.panel.image != null) {
						new Inverse(Frame.panel);
						System.out.println("inversion done");
						}
					break;
				case "brightness":
					if(Frame.panel.image != null) {
						try {
							new Brightness(Integer.parseInt(brightnessValue.getText()), Frame.panel);
							System.out.println("brightness done");
						} catch(NumberFormatException ee) {
							JOptionPane.showMessageDialog(this, "Numerical entry expected. Please try again"); 
							return;
						}
					}
					break;
				case "contrast":
					if(Frame.panel.image != null) {
						try {
							new Contrast(Double.parseDouble(contrastValue.getText()), Frame.panel);
							System.out.println("contrast done");
						} catch(NumberFormatException ee) {
							JOptionPane.showMessageDialog(this, "Numerical entry expected. Please try again"); 
							return;
						}
					}
					break;
				case "gamma":
					if(Frame.panel.image != null) {
						try {
							new Gamma(Double.parseDouble(gammaValue.getText()), Frame.panel);
							System.out.println("gamma done");
						} catch(NumberFormatException ee) {
							JOptionPane.showMessageDialog(this, "Numerical entry expected. Please try again"); 
							return;
						}
					}
					break;
				case "blur":
					if(Frame.panel.image != null) {
						new Blur(Frame.panel); // by 3x3 convolution matrix, each element filled with special matrix (see Blur class)
						System.out.println("blur done");
					}
					break;
				case "gaussianblur":
					if(Frame.panel.image != null) {
						new GaussianBlur(Frame.panel); // by 3x3 convolution matrix, each element filled with special matrix (see GaussianBlur class)
						System.out.println("gaussian blur done"); 
					}
					break;
				case "edgedetection":
					if(Frame.panel.image != null) {
						new EdgeDetection(Frame.panel); // by 3x3 convolution matrix, each element filled with special matrix (see EdgeDetection class)
						System.out.println("edge detection done"); 
					}
					break;
				case "emboss":
					if(Frame.panel.image != null) {
						new Emboss(Frame.panel); // by 3x3 convolution matrix, each element filled with special matrix (see Emboss class)
						System.out.println("emboss done"); 
					}
					break;
				case "sharpen":
					if(Frame.panel.image != null) {
						new Sharpen(Frame.panel); // by 3x3 convolution matrix, each element filled with special matrix (see Sharpen class)
						System.out.println("sharpen done"); 
					}
					break;
				case "custom":
					if(Frame.panel.image != null) {
						new FrameCustom(Frame.panel);
						System.out.println("custom done"); 
					}
					break;
			}
	}
	private void populateFileMenu() {
		fileMenu = new JMenu("File");
    	loadMenuItem = new JMenuItem("Load Picture");
    	loadMenuItem.setActionCommand("load");
    	loadMenuItem.addActionListener(this);
	    fileMenu.add(loadMenuItem);
	    
	    saveMenuItem = new JMenuItem("Save Picture");
	    saveMenuItem.setActionCommand("save");
	    saveMenuItem.addActionListener(this);
	    fileMenu.add(saveMenuItem);
	}
	
	private void populateFiltersMenu1() {
		filtersMenu1 = new JMenu("Simple");
	    inversionMenuItem = new JMenuItem("Inversion");
	    inversionMenuItem.setActionCommand("inversion");
	    inversionMenuItem.addActionListener(this);
	    filtersMenu1.add(inversionMenuItem);
	    
	    brightnessMenu = new JMenu("Brightness");
	    filtersMenu1.add(brightnessMenu);
	    
	    brightnessValue = new JTextField();
	    brightnessMenu.add(brightnessValue);
	    
	    brightnessMenuItem = new JMenuItem("Set brightness");
	    brightnessMenuItem.setActionCommand("brightness");
	    brightnessMenuItem.addActionListener(this);
	    brightnessMenu.add(brightnessMenuItem);
	    
	    contrastMenu = new JMenu("Contrast");
	    filtersMenu1.add(contrastMenu);
	    
	    contrastValue = new JTextField();
	    contrastMenu.add(contrastValue);
	    
	    contrastMenuItem = new JMenuItem("Set contrast");
	    contrastMenuItem.setActionCommand("contrast");
	    contrastMenuItem.addActionListener(this);
	    contrastMenu.add(contrastMenuItem);

	    gammaMenu = new JMenu("Gamma");
	    filtersMenu1.add(gammaMenu);

	    gammaValue = new JTextField();
	    gammaMenu.add(gammaValue);
	    
	    gammaMenuItem = new JMenuItem("Set gamma");
	    gammaMenuItem.setActionCommand("gamma");
	    gammaMenuItem.addActionListener(this);
	    gammaMenu.add(gammaMenuItem);
	}
	
	private void populateFiltersMenu2() {
		filtersMenu2 = new JMenu("Convolution");
		
	    blurMenuItem = new JMenuItem("Blur");
	    blurMenuItem.setActionCommand("blur");
	    blurMenuItem.addActionListener(this);
	    filtersMenu2.add(blurMenuItem);
	    
	    gaussianBlurMenuItem = new JMenuItem("Gaussian Blur");
	    gaussianBlurMenuItem.setActionCommand("gaussianblur");
	    gaussianBlurMenuItem.addActionListener(this);
	    filtersMenu2.add(gaussianBlurMenuItem);
	    
	    edgeDetectionMenuItem = new JMenuItem("Edge Detection");
	    edgeDetectionMenuItem.setActionCommand("edgedetection");
	    edgeDetectionMenuItem.addActionListener(this);
	    filtersMenu2.add(edgeDetectionMenuItem);
	    
	    embossMenuItem = new JMenuItem("Emboss");
	    embossMenuItem.setActionCommand("emboss");
	    embossMenuItem.addActionListener(this);
	    filtersMenu2.add(embossMenuItem);

	    sharpenMenuItem = new JMenuItem("Sharpen");
	    sharpenMenuItem.setActionCommand("sharpen");
	    sharpenMenuItem.addActionListener(this);
	    filtersMenu2.add(sharpenMenuItem);

		customMenuItem = new JMenuItem("CUSTOM FILTER");
		customMenuItem.setActionCommand("custom");
		customMenuItem.addActionListener(this);
		filtersMenu2.add(customMenuItem);
	}
}
