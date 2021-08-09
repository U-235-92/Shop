package com.shop.ui.swing.panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public final class LoginPanel extends JPanel {
	
	private static final long serialVersionUID = -4626371797063680657L;
	
	private static LoginPanel loginPanel;
	
	private SpringLayout layout;
	
	private LoginPanel() {
		
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width;
		int height = screenSize.height;
		
		layout = new SpringLayout();
		
		setName(getClass().getSimpleName());
		setLayout(layout);
		setSize(new Dimension(width, height));
	}
	
	public static LoginPanel getLoginPanel() {
		
		if(loginPanel == null) {
			
			loginPanel = new LoginPanel();
		}
		
		return loginPanel;
	}
	
	public SpringLayout getLayout() {
		
		return layout;
	}
}
