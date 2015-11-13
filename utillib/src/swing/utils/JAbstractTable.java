package swing.utils;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;



public abstract class JAbstractTable  extends JTable {

	private static final long serialVersionUID = -3553407817360022481L;
	protected static boolean DEBUG = false;
	JScrollPane scrollPane;
	List<JAbstractColumn> colums;
	
	
	//the model containing data to be fed to data table
	protected MyTableModel model;// = new MyTableModel();
	private Dimension dimensions =null; //new Dimension(360, 350);
	

	//constructor
	public JAbstractTable (JPanel panel, List<JAbstractColumn> cols, Dimension d){
		colums = cols;
		if(d != null ) dimensions = d;
		initTable(panel);
	}
	
	
	
	
	public JAbstractTable (JPanel panel, JAbstractColumn[] cols, Dimension d){
		if(cols != null){
			if(colums == null) colums = new ArrayList<JAbstractColumn>();
			for(int i=0; i < cols.length ; i++)
			colums.add(cols[i]);
		}
		if(d != null ) dimensions = d;
		initTable(panel);
	}
	
	
	public void recreateTable(List<JAbstractColumn> cols, List<IAbstractModel> data){
		TableColumnModel tc = getColumnModel();
		int cnt =  tc.getColumnCount();
		
		//remove all the columns
		while(cnt > 0){
			TableColumn c = tc.getColumn(0);
			tc.removeColumn(c);
			cnt = tc.getColumnCount();
		}
		for(JAbstractColumn c : cols) {
			tc.addColumn(c);
		}
		
		setTableDataFromObjecs(data);
		//SwingUtilities.updateComponentTreeUI(scrollPane);
		repaint();
	}
	
	
	
	//init table rows, cols
	public void initTable(JPanel panel /*, ListSelectionListener list*/){
		
		//table columns are not equal sized
		//setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
	
		//create table columns
		if(colums != null)
		for (int i = 0; i < colums.size(); i++) {
            addColumn(colums.get(i));
        }
		
		
		//set the data table size
		//setPreferredScrollableViewportSize(dimensions);
		
		
		//add table to scroll panel
		scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setFillsViewportHeight(true);
		
		//set the data
		setModel(getTableModel());
		
		//add to parent panel
		if(panel != null)	panel.add(scrollPane);
	
	}
	
	
	public void setTableDataFromObjecs(List<IAbstractModel> obj){
		if(model == null) return;
		model.setTableDataFromObjecs(obj);
	}
	
	public void setTableDataFromListObjects(List<Object []> obj){
		if(model == null) return;
		model.setTableDataFromLObjecs(obj);
	}
	
	public abstract  MyTableModel getTableModel();
	
	//each column in data member is actually a reference to UserObject
	//get UserObject attributes to show in JTable
	 public  abstract  class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = -8135211110136095955L;
			private Object[][] data  = null;
	 
	 
	        public int getColumnCount() {
	        	if(colums == null) return 0;
	        	return colums.size();
	        }
	 
	        public int getRowCount() {
	        	if(data == null)  return 0;
	            return data.length;
	        }
	 
	        public String getColumnName(int col) {
	            return colums.get(col).getHeaderValue().toString();
	        }
	 
	        public  abstract Object getValueAt(int row, int col);
	        
	        
	        //fill data from object list
	        private void setTableDataFromObjecs(List<IAbstractModel> obj){
	        	if(obj == null || obj.size() == 0){ data = null; return;}
	        	
	        	//allocate array for data
	        	data = new Object[obj.size()][1];
	        	
	        	for(int i = 0 ; i < obj.size(); i++){
	        		data[i][0] = obj.get(i);
	        	}
	        	
	        	//update scroll pane
	        	SwingUtilities.updateComponentTreeUI(scrollPane);
	        }
	        
	      //fill data from object list
	        private void setTableDataFromLObjecs(List<Object[]> obj){
	        	if(obj == null || obj.size() == 0){ data = null; return;}
	        	
	        	//allocate array for data
	        	data = new Object[obj.size()][1];
	        	
	        	for(int i = 0 ; i < obj.size(); i++){
	        		data[i][0] = obj.get(i);
	        	}
	        	
	        	//update scroll pane
	        	SwingUtilities.updateComponentTreeUI(scrollPane);
	        }
	 
	        /*
	         * JTable uses this method to determine the default renderer/
	         * editor for each cell.  If we didn't implement this method,
	         * then the last column would contain text ("true"/"false"),
	         * rather than a check box.
	         */
	        @SuppressWarnings("unchecked")
			public  Class getColumnClass(int c){
	           // return getValueAt(0, c).getClass();
	        	return super.getColumnClass(c);
	        }
	 
	        /*
	         * Don't need to implement this method unless your table's
	         * editable.
	         */
	        public  boolean isCellEditable(int row, int col){
	        	if (colums != null && colums.get(col).isEditable() == false) 
	                return false;
	            return true;
	        }
	 
	        /*
	         * Don't need to implement this method unless your table's
	         * data can change.
	         */
	        public abstract void setValueAt(Object value, int row, int col) ;
	 
	        protected abstract void printDebugData();
	        
	        
	        //get data
	        public Object [][] getData(){
	        	return data;
	        }
	    }
	 
	 
		
		public MyTableModel getModel() {
			return model;
		}



		public JScrollPane getScrollPane() {
			return scrollPane;
		}



		public List<JAbstractColumn> getColums() {
			return colums;
		}




}
