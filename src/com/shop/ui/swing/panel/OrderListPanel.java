package com.shop.ui.swing.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class OrderListPanel extends JPanel {

	private static final long serialVersionUID = 847415272100590774L;

	private BorderLayout layout;
	
	public OrderListPanel() {
		
		super();
		
		layout = new BorderLayout();
		
		setLayout(layout);
	}
	
	public BorderLayout getLayout() {
		
		return layout;
	}
}
