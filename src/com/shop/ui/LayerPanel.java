package com.shop.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class LayerPanel extends JPanel {

	private static final long serialVersionUID = 8879501744163103241L;

	private CardLayout cardLayout;
	
	public LayerPanel() {
		
		super();
		
		cardLayout = new CardLayout();
		
		setLayout(cardLayout);
	}
	
	public CardLayout getLayout() {
		
		return cardLayout;
	}
}
