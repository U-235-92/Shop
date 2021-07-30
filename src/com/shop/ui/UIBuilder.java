package com.shop.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public final class UIBuilder {

	private static UIBuilder uiBuilder;
	
	private ShopFrame shopFrame;
	
	private LayerPanel layerPanel;
	private LoginPanel loginPanel;
	private OptionPanel optionPanel;
	private OrderPanel orderPanel;
	private OrderListPanel orderListPanel;
	private SalePanel salePanel;
	private SaleListPanel saleListPanel;
	private PayCardPanel payCardPanel;
	private PayCashPanel payCashPanel;
	
	private JButton loginBTN;
	
	private UIBuilder() {}
	
	public static UIBuilder getUIBuilder() {
		
		if(uiBuilder == null) {
			
			uiBuilder = new UIBuilder();
		}
		
		return uiBuilder;
	}
	
	public void build() {
		
		SwingUtilities.invokeLater(() -> {
			
			shopFrame = new ShopFrame();
			
			loginPanel = LoginPanel.getLoginPanel();
			fillLoginPanel(loginPanel);
			
			optionPanel = OptionPanel.getOptionPanel();
			fillOptionPanel(optionPanel);
			
			layerPanel = new LayerPanel();
			layerPanel.add(loginPanel.getName(), loginPanel);
			layerPanel.add(optionPanel.getName(), optionPanel);
			
			shopFrame.add(layerPanel);
			
			shopFrame.pack();
			loginPanel.requestFocusInWindow();
			shopFrame.setVisible(true);
			shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		});
	}
	
	private void fillOptionPanel(OptionPanel optionPanel) {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("Файл");
		JMenuItem menuItemCalc = new JMenuItem("Калькулятор");
		JMenuItem menuItemExit = new JMenuItem("Выход");
		JMenuItem menuItemLogout = new JMenuItem("Сменить пользователя");
		
		menuFile.add(menuItemCalc);
		menuFile.add(menuItemLogout);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		
		JMenu menuView = new JMenu("Вид");
		
		JMenu menuStyle = new JMenu("Стиль оформления текста");
		
		JMenuItem menuItemBold = new JMenuItem("Жирный");
		JMenuItem menuItemItalic = new JMenuItem("Курсив");
		
		JMenu menuFont = new JMenu("Шрифт");
		
		JMenuItem menuArial = new JMenuItem("Arial");
		JMenuItem menuTimes = new JMenuItem("Times");
		
		JMenu menuInfo =  new JMenu("Справка");
		JMenuItem menuItemAbout = new JMenuItem("О программе");
		
		ButtonGroup groupFont = new ButtonGroup();
		groupFont.add(menuArial);
		groupFont.add(menuTimes);
		
		ButtonGroup groupStyle = new ButtonGroup();
		groupStyle.add(menuItemBold);
		groupStyle.add(menuItemItalic);
		
		menuStyle.add(menuItemItalic);
		menuStyle.add(menuItemBold);
		menuStyle.addSeparator();
		menuStyle.add(menuFont);
		
		menuFont.add(menuArial);
		menuFont.add(menuTimes);
		
		menuView.add(menuStyle);
		
		menuInfo.add(menuItemAbout);
		
		menuBar.add(menuFile);
		menuBar.add(menuView);
		menuBar.add(menuInfo);
		
		JTextField searchField = new JTextField(10);
		searchField.setToolTipText("Строка поиска по столбцу");
		
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, searchField, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, searchField, 28, SpringLayout.WEST, optionPanel);
		
		optionPanel.add(menuBar);
		optionPanel.add(searchField);
	}
	
	private void fillLoginPanel(LoginPanel loginPanel) {
		
		JButton loginButton = new JButton("Вход");
		
		JTextField loginField = new JTextField("Логин", 15);
		loginField.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		JPasswordField passwordField = new JPasswordField("Пароль", 15);
		passwordField.setEchoChar((char) 0);
		passwordField.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		EventQueue.invokeLater(() -> {
			
			loginField.addFocusListener(new FocusAdapter() {
			    
				@Override
			    public void focusGained(FocusEvent e) {
			    	
			    	if(loginField.getText().equals("") || loginField.getText().equals("Логин")) {
			    		
			    		loginField.setText("");
			    	}
			    }
			    
			    @Override
			    public void focusLost(FocusEvent e) {
			    	
			    	if(loginField.getText().equals("")) {
			    		
			    		loginField.setText("Логин");
			    	}
			    }
			});
			
			passwordField.addFocusListener(new FocusListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void focusLost(FocusEvent e) {
				
					if(passwordField.getText().equals("") || passwordField.getText().length() == 0) {
						
						passwordField.setEchoChar((char) 0);
						passwordField.setText("Пароль");
					}
				}
				
				@SuppressWarnings("deprecation")
				@Override
				public void focusGained(FocusEvent e) {
					
					if(passwordField.getText().equals("") || passwordField.getText().equals("Пароль")) {
						
						passwordField.setText("");
						passwordField.setEchoChar('*');
					}
				}
			});
			
			loginButton.addActionListener((ae) -> {
				
				layerPanel.getLayout().show(layerPanel, optionPanel.getName());
			});
		});
		
		loginPanel.getLayout().putConstraint(SpringLayout.HORIZONTAL_CENTER, loginField, 0, SpringLayout.HORIZONTAL_CENTER, loginPanel);
		loginPanel.getLayout().putConstraint(SpringLayout.VERTICAL_CENTER, loginField, -(loginPanel.getHeight() / 4), SpringLayout.VERTICAL_CENTER, loginPanel);
		loginPanel.getLayout().putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 0, SpringLayout.HORIZONTAL_CENTER, loginPanel);
		loginPanel.getLayout().putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, -(loginPanel.getHeight() / 4) + 30, SpringLayout.VERTICAL_CENTER, loginPanel);
		loginPanel.getLayout().putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 0, SpringLayout.HORIZONTAL_CENTER, passwordField);
		loginPanel.getLayout().putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, -(loginPanel.getHeight() / 4) + 80, SpringLayout.VERTICAL_CENTER, loginPanel);
		
		loginPanel.add(Box.createVerticalStrut(loginPanel.getHeight() / 4));
		loginPanel.add(loginField);
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);
	}
}
