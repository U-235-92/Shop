package com.shop.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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
	
	private JDesktopPane desktopPane;
	
	private JInternalFrame internalOrderListFrame;
	
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
			
			desktopPane = new JDesktopPane();
			desktopPane.setName("DesctopPane");
			
			loginPanel = LoginPanel.getLoginPanel();
			initLoginPanel(loginPanel);
			
			optionPanel = OptionPanel.getOptionPanel();
			initOptionPanel(optionPanel);
			
			desktopPane.add(optionPanel);
			
			layerPanel = new LayerPanel();
			layerPanel.add(loginPanel.getName(), loginPanel);
			layerPanel.add(desktopPane.getName(), desktopPane);
			
			shopFrame.add(layerPanel);
			
			shopFrame.pack();
			loginPanel.requestFocusInWindow();
			shopFrame.setVisible(true);
			shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		});
	}
	
	private void initOptionPanel(OptionPanel optionPanel) {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("����");
		JMenuItem menuItemCalc = new JMenuItem("�����������");
		JMenuItem menuItemExit = new JMenuItem("�����");
		JMenuItem menuItemLogout = new JMenuItem("������� ������������");
		
		menuFile.add(menuItemCalc);
		menuFile.add(menuItemLogout);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		
		JMenu menuView = new JMenu("���");
		
		JMenu menuStyle = new JMenu("����� ���������� ������");
		
		JMenuItem menuItemBold = new JMenuItem("������");
		JMenuItem menuItemItalic = new JMenuItem("������");
		
		JMenu menuFont = new JMenu("�����");
		
		JMenuItem menuArial = new JMenuItem("Arial");
		JMenuItem menuTimes = new JMenuItem("Times");
		
		JMenu menuInfo =  new JMenu("�������");
		JMenuItem menuItemAbout = new JMenuItem("� ���������");
		
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
		
		JTextField searchField = new JTextField(12);
		searchField.setToolTipText("������ ������ �� �������");
		
		//������ �������� ������ � �������
		JButton bSearchSetting = new JButton();
		bSearchSetting.setPreferredSize(new Dimension(19, 19));
		bSearchSetting.setToolTipText("��������� ������ ���������");
		//������ ������ ����������� ���������
		JButton bSearchPrev = new JButton();
		bSearchPrev.setPreferredSize(new Dimension(19, 19));
		bSearchPrev.setToolTipText("���������� ������");
		//������ ������ ���������� ���������
		JButton bSearchNext = new JButton();
		bSearchNext.setPreferredSize(new Dimension(19, 19));
		bSearchNext.setToolTipText("��������� ������");
		//������ ������� ���������� ���� ������
		JButton bClearSearchField = new JButton();
		bClearSearchField.setPreferredSize(new Dimension(19, 19));
		bClearSearchField.setToolTipText("�������� ������ ������");
		//������  ������ �������
		JButton bListOrder = new JButton();
		bListOrder.setPreferredSize(new Dimension(19, 19));
		bListOrder.setToolTipText("������ �������");
		bListOrder.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				initOrderList();
			}
		});
		//������ ������ ������
		JButton bOrder = new JButton();
		bOrder.setPreferredSize(new Dimension(19, 19));
		bOrder.setToolTipText("������� ����� �����");
		//������ ����� ������
		JButton bListSale = new JButton();
		bListSale.setPreferredSize(new Dimension(19, 19));
		bListSale.setToolTipText("������ ������");
		//������ ����� �������
		JButton bSale = new JButton();
		bSale.setPreferredSize(new Dimension(19, 19));
		bSale.setToolTipText("������� ����� �������");
		//������ ������ ������ ���������
		JButton bListPayCash = new JButton();
		bListPayCash.setPreferredSize(new Dimension(19, 19));
		bListPayCash.setToolTipText("������ ����� ���������");
		//������ ������ ������ ������
		JButton bListPayCard = new JButton();
		bListPayCard.setPreferredSize(new Dimension(19, 19));
		bListPayCard.setToolTipText("������ ����� ��������� ������");
		
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, searchField, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, searchField, 28, SpringLayout.WEST, optionPanel);
		//������� ������ �������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListOrder, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListOrder, 0, SpringLayout.WEST, searchField);
		//������ ������ ������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListSale, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListSale, 5, SpringLayout.EAST, bListOrder);
		//������ ������ ������ ���������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListPayCash, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListPayCash, 5, SpringLayout.EAST, bListSale);
		//������ ������ ������ ������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListPayCard, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListPayCard, 5, SpringLayout.EAST, bListPayCash);
		//������ �������� ������ � �������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bSearchSetting, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bSearchSetting, -24, SpringLayout.WEST, searchField);
		//������ ������� ���������� ���� ������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bClearSearchField, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bClearSearchField, 0, SpringLayout.EAST, searchField);
		//������ ������ ����������� ���������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bSearchPrev, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bSearchPrev, 2, SpringLayout.EAST, bClearSearchField);
		//������ ������ ���������� ���������
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bSearchNext, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bSearchNext, 2, SpringLayout.EAST, bSearchPrev);
		
		optionPanel.add(menuBar);
		optionPanel.add(searchField);
		optionPanel.add(bListOrder);
		optionPanel.add(bListSale);
		optionPanel.add(bListPayCard);
		optionPanel.add(bListPayCash);
		optionPanel.add(bSearchSetting);
		optionPanel.add(bClearSearchField);
		optionPanel.add(bSearchPrev);
		optionPanel.add(bSearchNext);
	}
	
	private void initOrderList() {
		
		if(internalOrderListFrame == null) {
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			internalOrderListFrame = new JInternalFrame("������ �������", true, true, false, true);
			internalOrderListFrame.setSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			internalOrderListFrame.setLocation(0, 100);
			internalOrderListFrame.setMaximumSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			
			
			desktopPane.add(internalOrderListFrame);
			
			internalOrderListFrame.setVisible(true);
			internalOrderListFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internalOrderListFrame.addInternalFrameListener(new InternalFrameAdapter() {
				
				public void internalFrameClosing(InternalFrameEvent e) {
					
					internalOrderListFrame.setVisible(false);
					internalOrderListFrame = null;
				}
			});
		}
	}
	
	private void initLoginPanel(LoginPanel loginPanel) {
		
		JButton loginButton = new JButton("����");
		
		JTextField loginField = new JTextField("�����", 15);
		loginField.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		JPasswordField passwordField = new JPasswordField("������", 15);
		passwordField.setEchoChar((char) 0);
		passwordField.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		EventQueue.invokeLater(() -> {
			
			loginField.addFocusListener(new FocusAdapter() {
			    
				@Override
			    public void focusGained(FocusEvent e) {
			    	
			    	if(loginField.getText().equals("") || loginField.getText().equals("�����")) {
			    		
			    		loginField.setText("");
			    	}
			    }
			    
			    @Override
			    public void focusLost(FocusEvent e) {
			    	
			    	if(loginField.getText().equals("")) {
			    		
			    		loginField.setText("�����");
			    	}
			    }
			});
			
			passwordField.addFocusListener(new FocusListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void focusLost(FocusEvent e) {
				
					if(passwordField.getText().equals("") || passwordField.getText().length() == 0) {
						
						passwordField.setEchoChar((char) 0);
						passwordField.setText("������");
					}
				}
				
				@SuppressWarnings("deprecation")
				@Override
				public void focusGained(FocusEvent e) {
					
					if(passwordField.getText().equals("") || passwordField.getText().equals("������")) {
						
						passwordField.setText("");
						passwordField.setEchoChar('*');
					}
				}
			});
			
			loginButton.addActionListener((ae) -> {
				
				layerPanel.getLayout().show(layerPanel, desktopPane.getName());
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
