import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainManager {
	//*****************************************************
	//Variable/Object Declarations
	//*****************************************************
	private JFrame frame;
	public JTextField product;
	public JTextField barCode;
	public JTextField unit;
	public JTextField size;
	public JTextField amount;
	public JTextField currentStock;
	public JTextField addedStock;
	public JTextField quantity;
	public JTextField barCodeSell;
	public JTable table;
	public JTextField stock;
	public JTextField measuredUnit2;
	public DefaultTableModel model;
	public int combo;
	TableData data = new TableData();
	
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	//*****************************************************
	//Main Method
	//*****************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainManager window = new MainManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainManager() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		//*************************************************
		//JFrame
		//*************************************************
		frame = new JFrame();
		frame.setBounds(100, 100, 1088, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//*************************************************
		// Labels
		//*************************************************
		
		JLabel lblProduct = new JLabel("Product :");
		lblProduct.setBounds(23, 95, 80, 21);
		frame.getContentPane().add(lblProduct);
		
		JLabel lblBarCode = new JLabel("Bar Code :");
		lblBarCode.setBounds(23, 127, 80, 21);
		frame.getContentPane().add(lblBarCode);
		
		JLabel lblNewLabel = new JLabel("Size :");
		lblNewLabel.setBounds(23, 159, 80, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUnit = new JLabel("Measured Unit :");
		lblUnit.setBounds(23, 192, 93, 21);
		frame.getContentPane().add(lblUnit);
		
		JLabel lblNewLabel_1 = new JLabel("Amount :");
		lblNewLabel_1.setBounds(23, 224, 80, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblInventoryManager = new JLabel("Inventory Manager");
		lblInventoryManager.setFont(new Font("Arial", Font.PLAIN, 36));
		lblInventoryManager.setBounds(373, 11, 316, 43);
		frame.getContentPane().add(lblInventoryManager);
		
		JLabel lblNewLabel_2 = new JLabel(" Add or Edit an Entry");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setBounds(21, 40, 174, 44);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" Add an " + 
				"Order to Current Inventory");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(23, 339, 204, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Current Stock:");
		lblNewLabel_4.setBounds(23, 383, 89, 21);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("per case");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setBounds(196, 231, 58, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblUnits = new JLabel("units");
		lblUnits.setForeground(Color.GRAY);
		lblUnits.setBounds(196, 390, 46, 14);
		frame.getContentPane().add(lblUnits);
		
		JLabel lblNewLabel_6 = new JLabel("Added Stock:");
		lblNewLabel_6.setBounds(23, 415, 80, 21);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblCase = new JLabel("cases");
		lblCase.setForeground(Color.GRAY);
		lblCase.setBounds(196, 422, 46, 14);
		frame.getContentPane().add(lblCase);
		
		JLabel lblNewLabel_7 = new JLabel("Sell Inventory");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(33, 447, 158, 33);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(23, 491, 80, 21);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblBarCode_1 = new JLabel("Bar Code :");
		lblBarCode_1.setBounds(23, 523, 80, 21);
		frame.getContentPane().add(lblBarCode_1);
		
		JLabel lblNewLabel_8 = new JLabel("Stock :");
		lblNewLabel_8.setBounds(23, 313, 80, 21);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(" Edit Inventory");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(23, 269, 93, 33);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel label = new JLabel("units");
		label.setForeground(Color.GRAY);
		label.setBounds(196, 320, 46, 14);
		frame.getContentPane().add(label);
		
		//*************************************************
		//TextFields 
		//*************************************************
		
		product = new JTextField();
		product.setBounds(102, 95, 93, 21);
		frame.getContentPane().add(product);
		product.setColumns(10);
		
		barCode = new JTextField();
		barCode.setBounds(102, 127, 93, 21);
		frame.getContentPane().add(barCode);
		barCode.setColumns(10);
		
		size = new JTextField();
		size.setBounds(102, 159, 93, 21);
		frame.getContentPane().add(size);
		size.setColumns(10);
		
		amount = new JTextField();
		amount.setBounds(102, 224, 93, 21);
		frame.getContentPane().add(amount);
		amount.setColumns(10);
		
		unit = new JTextField();
		unit.setBounds(119, 192, 76, 21);
		frame.getContentPane().add(unit);
		unit.setColumns(10);
	
		currentStock = new JTextField();
		currentStock.setBounds(115, 383, 80, 21);
		frame.getContentPane().add(currentStock);
		currentStock.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(102, 491, 93, 21);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);
		
		barCodeSell = new JTextField();
		barCodeSell.setBounds(102, 523, 93, 21);
		frame.getContentPane().add(barCodeSell);
		barCodeSell.setColumns(10);
		
		stock = new JTextField();
		stock.setBounds(102, 313, 93, 21);
		frame.getContentPane().add(stock);
		stock.setColumns(10);
		
		addedStock = new JTextField();
		addedStock.setBounds(102, 415, 93, 21);
		frame.getContentPane().add(addedStock);
		addedStock.setColumns(10);
	
		//*************************************************
		//JTable Info
		//*************************************************
		
		//Table specifications and into to populate
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 85, 752, 449);
		frame.getContentPane().add(scrollPane);
		table = new JTable(data.getTableModel());
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				try {
					data.loadTable(table.getModel(),
							table.getSelectedRow(),product, barCode,
							size, unit, amount, currentStock, stock);
					model = (DefaultTableModel) data.getTableModel();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
		});
		
		//*************************************************
		//Clears the JTextFields
		//*************************************************
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				data.clearTextFields(product, barCode,
						size, unit, amount, stock,
						currentStock, addedStock, quantity,
						barCodeSell);
				model = (DefaultTableModel) data.getTableModel();
			}
		});
		clear.setBounds(102, 578, 89, 23);
		frame.getContentPane().add(clear);
		
		
		//*************************************************
		//Button used to add a row of Data
		//*************************************************
		JButton addEntry = new JButton("ADD ENTRY");
		addEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.addData(product.getText(),barCode.getText(),size.getText(),unit.getText(),amount.getText());
				model = (DefaultTableModel) data.getTableModel();
			}
		});
		
		addEntry.setBounds(313, 558, 116, 43);
		frame.getContentPane().add(addEntry);
		//*************************************************
		//Button used to Edit Product Errors
		//*************************************************
		JButton edit = new JButton("EDIT ");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.editData(size.getText(),
						unit.getText(),amount.getText(),
						stock.getText(), table.getSelectedRow());	
				model = (DefaultTableModel) data.getTableModel();
			}
		});
		edit.setBounds(466, 558, 116, 43);
		frame.getContentPane().add(edit);
		//*************************************************
		//Button used to Add Stock
		//*************************************************
		JButton addStock = new JButton("ADD ORDER");
		addStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				data.addOrder(barCode.getText(), currentStock.getText(), addedStock.getText(), table.getSelectedRow());
				model = (DefaultTableModel) data.getTableModel();
				
			}
		});
		addStock.setBounds(612, 558, 116, 43);
		frame.getContentPane().add(addStock);
		//***********************************************
		// Button used to Sell Stock
		//*************************************************
		JButton sell = new JButton("SELL STOCK");
		sell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 
				
				data.sellStock(barCodeSell.getText(), quantity.getText());
				model = (DefaultTableModel) data.getTableModel();
				
			}
		});
		sell.setBounds(764, 558, 116, 43);
		frame.getContentPane().add(sell);
		//*************************************************
		//Button Used to Delete a Selected Row
		//*************************************************
		JButton delete = new JButton("DELETE");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				
				
				data.deleteRow(table.getModel(),table.getSelectedRow());
				model = (DefaultTableModel) data.getTableModel();
						
		}});
		delete.setBounds(918, 558, 116, 43);
		frame.getContentPane().add(delete);
		
		
		
	}
}