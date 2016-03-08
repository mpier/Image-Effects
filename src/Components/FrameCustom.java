/**
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Filters.Custom;
/**
 * FrameCustom class provides JFrame acting as "settings window", before applying CUSTOM filter
 */
public class FrameCustom extends JFrame implements ChangeListener, ActionListener{

	JPanel tablePanel, optionPanel;
	JButton button;
	PicturePanel picturePanel;
	
	SpinnerNumberModel spinnerWidthModel, spinnerHeightModel, divisorModel, offsetModel;
	JSpinner spinnerWidth, spinnerHeight, divisorSpinner, offsetSpinner;
	JLabel widthLabel, heightLabel, offsetLabel, divisorLabel;
	
	JTextField[][] buttonTable;
	int[][] filter;
	int divisor, offset;
	
	public FrameCustom(PicturePanel picturePanel){	
		super("Set custom filter");
		setLayout(new BorderLayout());
		
		this.picturePanel = picturePanel;
		
		optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(4,2));
		
		widthLabel = new JLabel("Width: ");
		optionPanel.add(widthLabel);
		
		spinnerWidthModel = new SpinnerNumberModel(1, 1, 9, 2);
		spinnerWidth = new JSpinner(spinnerWidthModel);
		spinnerWidth.addChangeListener(this);
		optionPanel.add(spinnerWidth);

		heightLabel = new JLabel("Height: ");
		optionPanel.add(heightLabel);
		
		spinnerHeightModel = new SpinnerNumberModel(1, 1, 9, 2);
		spinnerHeight = new JSpinner(spinnerHeightModel);
		spinnerHeight.addChangeListener(this);
		optionPanel.add(spinnerHeight);
		
		divisorLabel = new JLabel("Divisor: ");
		optionPanel.add(divisorLabel);
		
		divisorModel = new SpinnerNumberModel(1, -999, 999, 1);
		divisorSpinner = new JSpinner(divisorModel);
		optionPanel.add(divisorSpinner);
		
		offsetLabel = new JLabel("Offset: ");
		optionPanel.add(offsetLabel);
		
		offsetModel = new SpinnerNumberModel(0, -999, 999, 1);
		offsetSpinner = new JSpinner(offsetModel);
		optionPanel.add(offsetSpinner);
		
		add(optionPanel, BorderLayout.PAGE_START);
		tablePanel = new JPanel();
		addGrid(1, 1);
		
		add(tablePanel);
		button = new JButton("Filter");
		button.addActionListener(this);
		add(button, BorderLayout.PAGE_END);
		
		pack();
	    setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void addGrid(int width, int heigth) {
		buttonTable = new JTextField[width][heigth];
		filter = new int[width][heigth];
		
		for(int i=0; i<width; i++) {
			for(int j=0; j<heigth; j++) {
				JTextField cell = new JTextField(String.valueOf(1));
				cell.setHorizontalAlignment(SwingConstants.CENTER);
				buttonTable[i][j] = cell;
				
				tablePanel.add(cell);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		tablePanel.removeAll();
		tablePanel.setLayout(new GridLayout(spinnerWidthModel.getNumber().intValue(), spinnerHeightModel.getNumber().intValue()));
		addGrid(spinnerWidthModel.getNumber().intValue(), spinnerHeightModel.getNumber().intValue());

		add(tablePanel, BorderLayout.CENTER);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<filter.length; i++) {
			for(int j=0; j<filter[0].length; j++) {
				try {
					filter[i][j] = Integer.parseInt(buttonTable[i][j].getText());
				} catch(NumberFormatException ee) {
					JOptionPane.showMessageDialog(this, "Numerical entry expected. Please try again"); 
					return;
				}
			}
		}
		divisor = divisorModel.getNumber().intValue();
		offset = offsetModel.getNumber().intValue();
		
		dispose();
		new Custom(filter, divisor, offset, picturePanel);
	}
}
