package com.shop.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ShopFrame extends JFrame {

	private static final long serialVersionUID = 692569920916695983L;
	
	public ShopFrame() {
		
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width;
		int height = screenSize.height  - 30;
		
		setName(getClass().getSimpleName());
		setPreferredSize(new Dimension(width, height));
	}

}
