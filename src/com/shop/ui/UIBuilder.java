package com.shop.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import com.shop.ui.swing.frame.ShopFrame;
import com.shop.ui.swing.model.DataBaseTableModel;
import com.shop.ui.swing.panel.LayerPanel;
import com.shop.ui.swing.panel.LoginPanel;
import com.shop.ui.swing.panel.OptionPanel;
import com.shop.ui.swing.panel.OrderListPanel;
import com.shop.ui.swing.panel.OrderPanel;
import com.shop.ui.swing.panel.PayCardPanel;
import com.shop.ui.swing.panel.PayCashPanel;
import com.shop.ui.swing.panel.SaleListPanel;
import com.shop.ui.swing.panel.SalePanel;

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
	private JInternalFrame internalNewOrderFrame;
	
	private JButton loginBTN;
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
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
		
		JTextField searchField = new JTextField(12);
		searchField.setToolTipText("Строка поиска по столбцу");
		
		//кнопка настроек поиска в столбце
		JButton bSearchSetting = new JButton();
		bSearchSetting.setPreferredSize(new Dimension(19, 19));
		bSearchSetting.setToolTipText("Настройки отбора постолбцу");
		//кнопка поиска предыдущего вхождения
		JButton bSearchPrev = new JButton();
		bSearchPrev.setPreferredSize(new Dimension(19, 19));
		bSearchPrev.setToolTipText("Предыдущая запись");
		//кнопка поиска следующего вхождения
		JButton bSearchNext = new JButton();
		bSearchNext.setPreferredSize(new Dimension(19, 19));
		bSearchNext.setToolTipText("Следующая запись");
		//кнопка очистки текстового поля поиска
		JButton bClearSearchField = new JButton();
		bClearSearchField.setPreferredSize(new Dimension(19, 19));
		bClearSearchField.setToolTipText("Очистить строку поиска");
		//кнопка  списка заказов
		JButton bListOrder = new JButton();
		bListOrder.setPreferredSize(new Dimension(19, 19));
		bListOrder.setToolTipText("Список заказов");
		bListOrder.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				initOrderList();
			}
		});
		//кнопка нового заказа
		JButton bOrder = new JButton();
		bOrder.setPreferredSize(new Dimension(19, 19));
		bOrder.setToolTipText("Создать новый заказ");
		//кнопка спска продаж
		JButton bListSale = new JButton();
		bListSale.setPreferredSize(new Dimension(19, 19));
		bListSale.setToolTipText("Список продаж");
		//кнопка новой продажи
		JButton bSale = new JButton();
		bSale.setPreferredSize(new Dimension(19, 19));
		bSale.setToolTipText("Создать новую продажу");
		//кнопка списка оплаты наличными
		JButton bListPayCash = new JButton();
		bListPayCash.setPreferredSize(new Dimension(19, 19));
		bListPayCash.setToolTipText("Список оплат наличными");
		//кнопка списка оплаты картой
		JButton bListPayCard = new JButton();
		bListPayCard.setPreferredSize(new Dimension(19, 19));
		bListPayCard.setToolTipText("Список оплат платежной картой");
		
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, searchField, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, searchField, 28, SpringLayout.WEST, optionPanel);
		//кнопака списка заказов
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListOrder, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListOrder, 0, SpringLayout.WEST, searchField);
		//кнопка списка продаж
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListSale, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListSale, 5, SpringLayout.EAST, bListOrder);
		//кнопка списка оплаты наличными
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListPayCash, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListPayCash, 5, SpringLayout.EAST, bListSale);
		//кнопка списка оплаты картой
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bListPayCard, 8, SpringLayout.SOUTH, searchField);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bListPayCard, 5, SpringLayout.EAST, bListPayCash);
		//кнопка настроек поиска в столбце
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bSearchSetting, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bSearchSetting, -24, SpringLayout.WEST, searchField);
		//кнопка очистки текстового поля поиска
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bClearSearchField, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bClearSearchField, 0, SpringLayout.EAST, searchField);
		//кнопка поиска предыдущего вхождения
		optionPanel.getLayout().putConstraint(SpringLayout.NORTH, bSearchPrev, 10, SpringLayout.SOUTH, menuBar);
		optionPanel.getLayout().putConstraint(SpringLayout.WEST, bSearchPrev, 2, SpringLayout.EAST, bClearSearchField);
		//кнопка поиска следующего вхождения
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
			
			internalOrderListFrame = new JInternalFrame("Список заказов", true, true, false, true);
			internalOrderListFrame.setSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			internalOrderListFrame.setLocation(0, 100);
			internalOrderListFrame.setMaximumSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			
			orderListPanel = new OrderListPanel();
			initOrderListPanel(orderListPanel);
			
			internalOrderListFrame.add(orderListPanel);
			
			desktopPane.add(internalOrderListFrame);
			
			internalOrderListFrame.setVisible(true);
			internalOrderListFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internalOrderListFrame.addInternalFrameListener(new InternalFrameAdapter() {
				
				public void internalFrameClosing(InternalFrameEvent e) {
					
					internalOrderListFrame.setVisible(false);
//					internalOrderListFrame = null;
				}
			});
		} else {
			
			internalOrderListFrame.setVisible(true);
		}
	}
	
	private void initOrderListPanel(OrderListPanel orderPanel) {
		
		SpringLayout springLayout = new SpringLayout();
		BorderLayout borderLayout = new BorderLayout();
		
		JPanel buttonPanel = new JPanel(springLayout);
		buttonPanel.setPreferredSize(new Dimension(orderPanel.getWidth(), 40));
		
		JPanel tablePanel = new JPanel(borderLayout);
		
		JButton buttonOrderCreate = new JButton();
		buttonOrderCreate.setPreferredSize(new Dimension(19, 19));
		buttonOrderCreate.setToolTipText("Создать новый заказ");
		buttonOrderCreate.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				initNewOrder();
			}
		});
		
		JButton buttonOrderEdit = new JButton();
		buttonOrderEdit.setPreferredSize(new Dimension(19, 19));
		buttonOrderEdit.setToolTipText("Изменить текущий выбранный заказ");
		
		JButton buttonOrderDelete = new JButton();
		buttonOrderDelete.setPreferredSize(new Dimension(19, 19));
		buttonOrderDelete.setToolTipText("Пометить выбранный заказ на удаление");
		
		JButton buttonOrderState = new JButton();
		buttonOrderState.setPreferredSize(new Dimension(19, 19));
		buttonOrderState.setToolTipText("Узнать структуру заказа");
		
		JButton buttonSortByColumn = new JButton();
		buttonSortByColumn.setPreferredSize(new Dimension(19, 19));
		buttonSortByColumn.setToolTipText("Отбор по значению в выбранном столбце");
		
		JButton buttonSortByDate = new JButton();
		buttonSortByDate.setPreferredSize(new Dimension(19, 19));
		buttonSortByDate.setToolTipText("Установить отбор в интервале дат");
		
		DataBaseTableModel tableModel = new DataBaseTableModel(false);
		
		/////////
		JTable table = new JTable(new String[][] {{"Статус", "Дата", "Сумма", "Валюта", "Контрагент", "Ответственный", "Склад"}}, new String[] {"Статус", "Дата", "Сумма", "Валюта", "Контрагент", "Ответственный", "Склад"});
		table.setSize(new Dimension((int)screenSize.getWidth(), table.getRowHeight() * table.getRowCount()));
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);
		/////////
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonOrderCreate, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonOrderCreate, 20, SpringLayout.WEST, buttonPanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonOrderEdit, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonOrderEdit, 5, SpringLayout.EAST, buttonOrderCreate);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonOrderDelete, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonOrderDelete, 5, SpringLayout.EAST, buttonOrderEdit);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonOrderState, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonOrderState, 5, SpringLayout.EAST, buttonOrderDelete);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonSortByColumn, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonSortByColumn, 5, SpringLayout.EAST, buttonOrderState);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonSortByDate, 10, SpringLayout.NORTH, buttonPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonSortByDate, 5, SpringLayout.EAST, buttonSortByColumn);
		
		buttonPanel.add(buttonOrderCreate);
		buttonPanel.add(buttonOrderEdit);
		buttonPanel.add(buttonOrderDelete);
		buttonPanel.add(buttonOrderState);
		buttonPanel.add(buttonSortByColumn);
		buttonPanel.add(buttonSortByDate);
		buttonPanel.add(tablePanel);
		
		orderPanel.add(buttonPanel, BorderLayout.NORTH);
		orderPanel.add(tablePanel, BorderLayout.CENTER);
	}
	
	private void initNewOrder() {
		
		if(internalNewOrderFrame == null) {
			
			internalNewOrderFrame = new JInternalFrame("Новый заказ", true,  true, false, true);
			internalNewOrderFrame.setSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			internalNewOrderFrame.setLocation(0, 100);
			internalNewOrderFrame.setMaximumSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight() -  160));
			
			JPanel borderPanel = new JPanel();
			
			JPanel optionPanel = new JPanel();
			optionPanel.setPreferredSize(new Dimension((int)(screenSize.getWidth()), 70));
			
			JPanel gridPanel = new JPanel();
			JPanel flowPanel = new JPanel();
			flowPanel.setPreferredSize(new Dimension((int)(screenSize.getWidth()), 40));
			flowPanel.setBorder(new LineBorder(Color.BLUE));
			
			JPanel tablePanel = new JPanel();
			tablePanel.setPreferredSize(new Dimension((int) (screenSize.getWidth()), internalNewOrderFrame.getHeight() - optionPanel.getHeight() - flowPanel.getHeight()));
			tablePanel.setBorder(new LineBorder(Color.RED));
			
			SpringLayout springLayout = new SpringLayout();
			BorderLayout borderLayout = new BorderLayout();
			GridLayout gridLayout = new GridLayout(1, 2, 10, 0);
			FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
			
			JTextField customerTextField = new JTextField(12);
			
			JLabel totalPriceLabel = new JLabel("Всего: ");
			
			JButton choiseCustomerBtn = new JButton();
			choiseCustomerBtn.setPreferredSize(new Dimension(19, 19));
			choiseCustomerBtn.setToolTipText("Выбрать покупателя");

			JButton editElementBtn = new JButton();
			editElementBtn.setPreferredSize(new Dimension(19, 19));
			editElementBtn.setToolTipText("Изменить выбранный элемент");
			
			JButton removeRowBtn = new JButton();
			removeRowBtn.setPreferredSize(new Dimension(19, 19));
			removeRowBtn.setToolTipText("Удалить выбраную строку");
			
			JButton searchProductBtn = new JButton();
			searchProductBtn.setPreferredSize(new Dimension(19, 19));
			searchProductBtn.setToolTipText("Поиск товара из списка");
			
			JButton okBtn = new JButton("OK");
			JButton closeBtn = new JButton("Закрыть");
			
			DefaultTableModel tableModel = new DefaultTableModel(new String[][] {{"№", "Наименование", "Характеристика", "Количество", "Цена", "Скидка %", "Сумма", "Всего"}}, new String[] {"№", "Наименование", "Характеристика", "Количество", "Цена", "Скидка %", "Сумма", "Всего"});
			JTable table = new JTable(tableModel);
			table.setSize(new Dimension((int)screenSize.getWidth(), table.getRowHeight() * table.getRowCount()));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.add(table);
			
			borderPanel.setLayout(borderLayout);
			optionPanel.setLayout(springLayout);
			tablePanel.setLayout(new BorderLayout());
			gridPanel.setLayout(gridLayout);
			flowPanel.setLayout(flowLayout);
			
			springLayout.putConstraint(SpringLayout.NORTH, customerTextField, 10, SpringLayout.NORTH, optionPanel);
			springLayout.putConstraint(SpringLayout.WEST, customerTextField, 10, SpringLayout.WEST, optionPanel);
			
			springLayout.putConstraint(SpringLayout.NORTH, choiseCustomerBtn, 10, SpringLayout.NORTH, optionPanel);
			springLayout.putConstraint(SpringLayout.WEST, choiseCustomerBtn, 0, SpringLayout.EAST, customerTextField);
			
			springLayout.putConstraint(SpringLayout.NORTH, editElementBtn, 30, SpringLayout.NORTH, customerTextField);
			springLayout.putConstraint(SpringLayout.WEST, editElementBtn, 10, SpringLayout.WEST, optionPanel);
			
			springLayout.putConstraint(SpringLayout.NORTH, removeRowBtn, 30, SpringLayout.NORTH, customerTextField);
			springLayout.putConstraint(SpringLayout.WEST, removeRowBtn, 4, SpringLayout.EAST, editElementBtn);
			
			springLayout.putConstraint(SpringLayout.NORTH, searchProductBtn, 30, SpringLayout.NORTH, customerTextField);
			springLayout.putConstraint(SpringLayout.WEST, searchProductBtn, 4, SpringLayout.EAST, removeRowBtn);
			
			gridPanel.add(okBtn);
			gridPanel.add(closeBtn);
			
			flowPanel.add(gridPanel);
			
			tablePanel.add(scrollPane, BorderLayout.NORTH);
			tablePanel.add(totalPriceLabel, BorderLayout.CENTER);
			
			optionPanel.add(customerTextField);
			optionPanel.add(choiseCustomerBtn);
			optionPanel.add(editElementBtn);
			optionPanel.add(removeRowBtn);
			optionPanel.add(searchProductBtn);
			
			borderPanel.add(optionPanel, BorderLayout.NORTH);
			borderPanel.add(tablePanel, BorderLayout.CENTER);
			borderPanel.add(flowPanel, BorderLayout.SOUTH);
			
			internalNewOrderFrame.add(borderPanel);
			
			desktopPane.add(internalNewOrderFrame);
			
			internalNewOrderFrame.setVisible(true);
			
			internalNewOrderFrame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internalNewOrderFrame.addInternalFrameListener(new InternalFrameAdapter() {
				
				public void internalFrameClosing(InternalFrameEvent e) {
					
					internalNewOrderFrame.setVisible(false);
					internalNewOrderFrame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
					internalNewOrderFrame = null;
				}
			});
		} else {
			
//			internalNewOrderFrame.requestFocus();
		}
	}
	
	private void initLoginPanel(LoginPanel loginPanel) {
		
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
