package base;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import net.is_bg.ltf.db.common.DBConfig;
import net.is_bg.ltf.db.common.DBExecutor;
import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.paging.SelectPagingSqlStatement;
import swing.utils.JAbstractColumn;


public class QueryResultSetPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea sqlText = new JTextArea();
	private JPanel resultSetTablePanel  = new JPanel();
	private JQueryDataTable table;
	private List<JAbstractColumn> columns = new ArrayList<JAbstractColumn>();
	private List<JAbstractColumn> cols =new ArrayList<JAbstractColumn>();
	private List<Object[]> result = new ArrayList<Object[]>();
	private JButton btn = new  JButton(">>");
	private JLabel queryCount =  new JLabel("");
	private String markedText;
	
	{
		cols.add(new JAbstractColumn("Query Data...", 10, false));
		
	}
	
	public QueryResultSetPanel(){
		
		 //add btn click action listener
		 btn.addActionListener(new BtRun(this));
		 
		 sqlText.addCaretListener(new  CaretListener() {
			@Override
			public void caretUpdate(CaretEvent ce) {
				// TODO Auto-generated method stub
				int dot=ce.getDot();
		        int mark=ce.getMark();
                if(dot!=mark) markedText = sqlText.getSelectedText();
			}
		 });
		 
		 //add buttons & table to Jpanel
		 addComponentsToPanel();
		
	}
	
	
	private void addComponentsToPanel(){
		setLayout(new  GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel p  = new JPanel();
		p.setLayout(new  GridBagLayout());
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints exeBtnConstrains = new GridBagConstraints();
		exeBtnConstrains.anchor = GridBagConstraints.LINE_START;
		buttonPanel.add(btn, exeBtnConstrains);
		exeBtnConstrains.anchor = GridBagConstraints.LAST_LINE_END;
		buttonPanel.add(queryCount, exeBtnConstrains);
		exeBtnConstrains.anchor = GridBagConstraints.LINE_START;
		p.add(buttonPanel, exeBtnConstrains);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		p.add(sqlText, c);
		sqlText.setBackground(new Color(0x909090));
		sqlText.setForeground(new Color(0x00F000));
		sqlText.setFont(new Font("Arial", Font.BOLD, 20));
		
		resultSetTablePanel.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.BOTH;
		cc.gridx = 0;
		cc.gridy = 0;
		cc.weightx = 1;
		cc.weighty = 1;
		
	    table = new JQueryDataTable(null, cols, null);
	    resultSetTablePanel.add(table.getScrollPane(), cc);
	    
	    c.gridx = 0;
		c.gridy = 2;
	    p.add(resultSetTablePanel, c);
		add(p, c);
	}
	
	
	private void recreateTable(List<Object[]> result){
		resultSetTablePanel.remove(table.getScrollPane());
		table = new JQueryDataTable(null, columns, null);
		table.setTableDataFromListObjects(result);
		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.BOTH;
		cc.gridx = 0;
		cc.gridy = 0;
		cc.weightx = 1;
		cc.weighty = 1;
		resultSetTablePanel.add(table.getScrollPane(), cc);
	}
	
	
	private class BtRun implements ActionListener{
		
		QueryResultSetPanel p;
		BtRun(QueryResultSetPanel p){
			this.p = p;
		}
		
		private void performUpdate(String sql){
			final String sqll = sql;
			UpdateSqlStatement  update = new  UpdateSqlStatement() {
				@Override
				protected String getSqlString() {
					// TODO Auto-generated method stub
					return sqll;
				}
			};
			
			DBExecutor executor = new  DBExecutor(DBConfig.getConnectionFactory());
			executor.execute(update);
		}
		
		private void performSelect(String sql){
			//create select statement 
			final String sqll = sql;
			SelectPagingSqlStatement<IAbstractModel>  select = new  SelectPagingSqlStatement<IAbstractModel>(){
				
				@Override
				protected String rtnSqlString(String sql) {
					// TODO Auto-generated method stub
					return super.rtnSqlString(getSqlString());
				}
				
				@Override
				protected String getSqlString() {
					// TODO Auto-generated method stub
					return sqll;// markedText== null ? sqlText.getText() : markedText;
				}
				
				@Override
				protected void retrieveResult(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					p.result.clear();
					int i =0;
					while (rs.next()) {
						Object [] row = new Object[columns.size()];
						for(; i < columns.size(); i++){
							Object o = rs.getObject(i+1);
							row[i] = (o ==null ? null: o.toString());
						}
						p.result.add(row);
						i = 0;
					}
				}
				
			};
			
			/*select.setResultSetMetaDataListener(new IResultSetMetaDataListener() {
				@Override
				public void processMetaData(ResultSetMetaData arg0) {
					// TODO Auto-generated method stub
					try {
						int colcount  = arg0.getColumnCount();
						for(int i =1 ; i <=colcount; i++){
							JAbstractColumn c =new JAbstractColumn(arg0.getColumnName(i), 10, false);
							p.columns.add(c);
						}
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});*/
			
			DBExecutor executor = new  DBExecutor(DBConfig.getConnectionFactory());
			executor.execute(select);
			queryCount.setText("Query Count:" + p.result.size());
			recreateTable(p.result);
		}
		
		private boolean isSelect(String sql){
			if(sql == null) return true;
			if(sql.trim().toLowerCase().startsWith("select")) return true;
			return false;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			p.columns = new ArrayList<JAbstractColumn>();
			try{
				String sql = ( markedText== null) ? sqlText.getText() : markedText;
				if(isSelect(sql)) performSelect( sql);
				else performUpdate(sql);
			}catch(Exception ex){
				p.columns.clear();
				p.columns.add(new JAbstractColumn("Exception occurred...", 10, false));
				List<Object[]> l = new ArrayList<Object[]>();
				Object [] o = new  Object[1]; o[0] = ex.getMessage();
				l.add(o);
				recreateTable(l);
			}
			
			SwingUtilities.updateComponentTreeUI(p);
		}
		
	}
	
}
