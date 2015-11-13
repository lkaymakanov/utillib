package base;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

import swing.utils.JAbstractColumn;
import swing.utils.JAbstractTable;


public class JQueryDataTable extends JAbstractTable{

	private static final long serialVersionUID = 5256474988218785197L;

	
	public JQueryDataTable(JPanel panel, JAbstractColumn[] cols, Dimension d) {
		super(panel, cols, d);
		// TODO Auto-generated constructor stub
	}

	public JQueryDataTable(JPanel panel, List<JAbstractColumn> col,  Dimension d){
		this(panel, col.toArray(new JAbstractColumn[0]), d);	
	}
	

	@Override
	public MyTableModel getTableModel() {
		// TODO Auto-generated method stub
		if(model == null) {
			model = new MyTableModel() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 6907000292662541470L;

				@Override
				public Object getValueAt(int row, int col) {
					// TODO Auto-generated method stub
					return ((Object [])model.getData()[row][0])[col];
				}

				@Override
				public void setValueAt(Object value, int row, int col) {
					// TODO Auto-generated method stub
					
				}

				@Override
				protected void printDebugData() {
					// TODO Auto-generated method stub
					
				}
			};
			
		}
		return model;
	}

}
