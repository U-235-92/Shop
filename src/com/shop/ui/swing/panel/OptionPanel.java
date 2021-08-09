package com.shop.ui.swing.panel;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class OptionPanel extends JPanel {
	
	private static final long serialVersionUID = 6179448184369963982L;
	
	private static OptionPanel optionPanel;
	
	private SpringLayout layout;
	
	private OptionPanel() {
		
		super();
		
		layout = new SpringLayout();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width;
		int height = screenSize.height / 7;
		
		setName(getClass().getSimpleName());
		setLayout(layout);
		setSize(new Dimension(width, height));
		
	}
	
	public static OptionPanel getOptionPanel() {
		
		if(optionPanel == null) {
			
			optionPanel = new OptionPanel();
		}
		
		return optionPanel;
	}
	
	public SpringLayout getLayout() {
		
		return layout;
	}
}
