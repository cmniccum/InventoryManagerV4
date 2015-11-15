import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableData{
	
	//--------------------------------------------->>>>>    Connection to Database   <<<<<----------------------------------------------------------------------
	
	//Connect to the database
	ConnectSQL con = new ConnectSQL();
	Connection con1 = con.getMySqlConnection();
	
	JOptionPane frame = new JOptionPane();
	
	//Variables
	private long upc;
	private long getcode;			
	private int getsize;			
	private String getunit; 		
	private int getamount;			
	private int getstock;			
	private long obtaincode;			
	private int getcurrentstock;	
	private int getaddedstock;		
	private long upcsell;			
	private int sold;				
	private int cstock;				
	private String table_click;		
		
	private DefaultTableModel model =  new DefaultTableModel(getTableData(), getHeader());
	
	//Constructor
	public TableData()throws Exception{
	
		}
		
	//getHeader creates the header for the jTable
	public Vector<String> getHeader(){
		Vector<String> header = new Vector<String>();
		
		header.add("Product"); 
		header.add("BarCode");
		header.add("Size"); 
		header.add("Unit");
		header.add("Quantity");
		header.add("Current Stock"); 
		header.add("Total Sold"); 
			
		return header;	
		}
			
	//getTableData gets data from the server and passes it as a vector array
	public Vector<Vector<String>> getTableData() throws Exception{
			
		Vector<Vector<String>> vec = new Vector <Vector<String>>();
			
		PreparedStatement stmt1 = con1.prepareStatement
				("SELECT * FROM supplies ORDER BY ProductName");

		ResultSet rs1 = stmt1.executeQuery();
		
		for(int x = 1;rs1.next();x++){
			Vector<String> vec2 = new Vector<String>();

			for (int i = 1; i < 8; i++){
				vec2.add(rs1.getString(i)); 
			}
				
			vec.add(vec2);
			
		}
			
		return vec;
		
		}
		
	//*****************************************************
	//addData adds the user's input to the database
	//*****************************************************
	public void addData(String product, String barCode,
			String size, String combo, String amount){
				
		addRow : try {
				
			
			upc =Long.parseLong(barCode);
			
			if (barCode.length() !=12){
				JOptionPane.showMessageDialog(frame, "Barcode most be 12 digits.",
						"Invalid UPC",JOptionPane.ERROR_MESSAGE);
				System.out.println("FAILED - invalid barcode input");
				break addRow;
				
			}
			
			
			PreparedStatement stmt =  con1.prepareStatement ("INSERT INTO supplies(ProductName," +
					"ProductBarCode,ProductSize,ProductUnit," +
					"ProductQuantity,AmountOnHand,AmountSold)" +
					"VALUES(?,?,?,?,?,?,?);");
				 
			stmt.setString (1, product);
			stmt.setString (2, barCode);
			stmt.setString (3, size);
			stmt.setString (4, combo);
			stmt.setString (5, amount);
			stmt.setInt    (6, 0);				
			stmt.setInt    (7, 0);
					
			stmt.executeUpdate();
			
			PreparedStatement stmt1 = con1.prepareStatement("Set @row = 0;");
			stmt1.executeQuery();
			
			PreparedStatement stmt2 = con1.prepareStatement("SELECT Row FROM (SELECT @row := " +
					"@row + 1 AS Row, ProductBarCode AS ProductBarCode " + 
					"FROM supplies Order by ProductName) " + 
					"As indexing  WHERE ProductBarCode ='"+ upc +"'; ");
			ResultSet rs = stmt2.executeQuery();
					
			rs.next();
			int x = rs.getInt(1);
			x -= 1;
					 
			String[] insertrow = {product,barCode,size,combo,amount,"0","0"};
					
			model.insertRow(x, insertrow);
				
		} catch (Exception e) {
		
			e.printStackTrace();
			//Error message
			JOptionPane.showMessageDialog(frame, 
					"Complete form to add an entry.",
					"Error",JOptionPane.ERROR_MESSAGE);
					
		}
	}
	
	
	//*****************************************************
	//editData reads changes made by the user 
	//and alters the database
	//*****************************************************
	public void editData(String size, String measuredUnit2,
			String amount, String stock, int row){
		
		try{
			
			getcode = Long.parseLong((String) model.getValueAt(row, 1));
				
			getsize = Integer.parseInt(size);
			model.setValueAt(getsize, row, 2);
				
			getunit = null;
			if (measuredUnit2.equals("ounce")){
				getunit = "ounce";
				model.setValueAt(getunit, row, 3);
			}
			else if (measuredUnit2.equals("pound")){
				getunit = "pound";
				model.setValueAt(getunit, row, 3);
			}
			else if (measuredUnit2.equals("count")){
				getunit = "count";
				model.setValueAt(getunit, row, 3);
			}
				
			getamount = Integer.parseInt(amount);
			model.setValueAt(getamount, row, 4);
				
			getstock = Integer.parseInt(stock);
			model.setValueAt(getstock, row, 5);
					
				
		    PreparedStatement stmt = con1.prepareStatement("UPDATE supplies SET ProductSize='" +
					getsize + "', ProductUnit='" + getunit + "', ProductQuantity='" + 
					getamount + "', AmountOnHand='" + getstock + 
					"' WHERE ProductBarCode='" + getcode + "';");
		    stmt.executeUpdate();
		            
		            
		}catch(Exception e){
			e.printStackTrace();
			//Error message
			JOptionPane.showMessageDialog(frame,
					"Complete form to edit entry.",
					"Error",JOptionPane.ERROR_MESSAGE);
				}
			
	}
	
	
	//*****************************************************
	//addOrder adds to the current stock of a product 
	//from user input
	//*****************************************************
	public void addOrder(String barCode, String currentStock,
			String addedStock, int row){
		
		try {
				 
			obtaincode = Long.parseLong(barCode);
			getcurrentstock = Integer.parseInt(currentStock);
	        getaddedstock = Integer.parseInt(addedStock);
	            		
	        getcurrentstock += getaddedstock;
	        model.setValueAt(getcurrentstock, row, 5);
	            
	        PreparedStatement stmt = con1.prepareStatement("UPDATE supplies SET AmountOnHand='" + 
	    			getcurrentstock + "' WHERE ProductBarCode='" + obtaincode + "';");
	        stmt.executeUpdate(); 
	       
	        
	        
	    } catch (Exception e) {
			e.printStackTrace();
			
			
			
			//Error Message
			JOptionPane.showMessageDialog(frame,
					"An error as occured.",
					"Error",JOptionPane.ERROR_MESSAGE);
					
		}
		
	}
	
	
	//*****************************************************
	//sellStock removes from the current stock of a product
	//from user input
	//*****************************************************
	public void sellStock(String barCodeSell, String quantity){
		
		try {
				 
			upcsell = Long.parseLong(barCodeSell);
				 
			PreparedStatement stmt1 = con1.prepareStatement("Set @row = 0; ");
			stmt1.executeQuery();
					
			PreparedStatement stmt2 = con1.prepareStatement("SELECT Row FROM (SELECT @row := " +
					"@row + 1 AS Row, ProductBarCode AS ProductBarCode " + 
					"FROM supplies Order by ProductName) " + 
					"As indexing  WHERE ProductBarCode ='"+ upcsell +"'; ");
			ResultSet rs = stmt2.executeQuery();
					
			rs.next();
			int x = rs.getInt(1);
			x -= 1;
				 
			String mod1 = (String) model.getValueAt(x, 6);
			int sold = Integer.parseInt(mod1);
					
			int quant = Integer.parseInt(quantity);
					
			String mod2 = (String) model.getValueAt(x, 5);
			int cstock = Integer.parseInt(mod2);
					
			if ((cstock - quant) >= 0){
				cstock = cstock - quant;
				sold = sold + quant;
			}
					
		    PreparedStatement stmt = con1.prepareStatement("UPDATE supplies SET AmountOnHand='" + 
					cstock + "', AmountSold='" + sold + 
					"' WHERE ProductBarCode='" + upcsell + "';");
		    stmt.executeUpdate();
		            
		    model.setValueAt(cstock, x, 5);
			model.setValueAt(sold, x, 6);
				 
	    } catch (Exception e) {
	        e.printStackTrace();
	      	
		}
		
	}
	

	//*****************************************************
	//deleteRow removes a product row from the database
	//*****************************************************
	public void deleteRow (TableModel tbl, int row){
	
		try {
				
			table_click = (tbl.getValueAt(row, 1).toString());
				
			model.removeRow(row);
				
			PreparedStatement stmt2 = con1.prepareStatement("DELETE FROM supplies WHERE ProductBarCode='" + 
					table_click + "'");
			stmt2.executeUpdate();
				
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Please select a row.",
					"Error",JOptionPane.ERROR_MESSAGE);	
		}
	
	}
	
	
	//*****************************************************
	//clearTextFields clears the text fields used for input
	//*****************************************************
	public JTextField [] clearTextFields(JTextField product,
			JTextField barCode, JTextField size,
			JTextField measuredUnit2, JTextField amount,
			JTextField stock, JTextField currentStock,
			JTextField addedStock, JTextField quantity,
			JTextField barCodeSell){
		
		product.setText("");
		barCode.setText("");
		size.setText("");
		measuredUnit2.setText("");
		amount.setText("");
		stock.setText("");
		currentStock.setText("");
		addedStock.setText("");
		quantity.setText("");
		barCodeSell.setText("");
			
		JTextField [] textfield = {product,barCode, size, measuredUnit2, amount, stock, currentStock, addedStock, quantity, barCodeSell};
		
		return textfield;
		
	}
	
	
	//*****************************************************
	//loadTable reloads/updates the jTable
	//*****************************************************
	public JTextField [] loadTable(TableModel tbl, int row,
			JTextField product, JTextField barCode,
			JTextField size, JTextField measuredUnit2,
			JTextField amount, JTextField currentStock,
			JTextField stock) throws Exception{
		
		String table_click = (tbl.getValueAt(row, 1).toString());
				
		PreparedStatement stmt = con1.prepareStatement("Select * from supplies where ProductBarCode ='" +
				table_click + "' ");
				
		ResultSet rs = stmt.executeQuery();
				
		if(rs.next()){
			String add1 = rs.getString("ProductName");
			product.setText(add1);
			String add2 = rs.getString("ProductBarCode");
			barCode.setText(add2);
			String add3 = rs.getString("ProductSize");
			size.setText(add3);
			String add4 = rs.getString("ProductUnit");
			measuredUnit2.setText(add4);
			String add5 = rs.getString("ProductQuantity");
			amount.setText(add5);
			String add6 = rs.getString("AmountOnHand");
			currentStock.setText(add6);
			String add7 = rs.getString("AmountOnHand");
			stock.setText(add7);
		}
		
		JTextField [] entry = {product,barCode,size,measuredUnit2,amount,currentStock,stock};
		
		
		return entry;	
	}
	

	//*****************************************************
	//getTableModel returns the table model
	//*****************************************************
	public TableModel getTableModel(){
		return model;
	}
}
