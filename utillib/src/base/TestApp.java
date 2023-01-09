package base;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import net.is_bg.ltf.db.common.DBConfig;
import net.is_bg.ltf.db.common.impl.DataSourceConnectionFactoryDrManager;
import net.is_bg.ltf.db.common.impl.logging.LogFactorySystemOut;
import net.is_bg.ltf.db.common.impl.timer.ElapsedTimer;
import net.is_bg.ltf.db.common.impl.visit.VisitEmpty;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactoryX;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimerFactory;
import net.is_bg.ltf.db.common.interfaces.visit.IVisit;
import net.is_bg.ltf.db.common.interfaces.visit.IVisitFactory;


public class TestApp extends JFrame{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	public static String getClipboard() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                return text;
            }
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	 
	
	public static void main(String [] ags){
		//DBConfig.initDBConfig(logFactory, visitFactory, cFactory, elapsedTimerFactory, transactionListener);
		
		System.out.println(getClipboard());
		
		//testApp();
	}
	
	
	private static void testApp() {
		DBConfig.initDBConfig(new LogFactorySystemOut(), new IVisitFactory() {
			@Override
			public IVisit getVist() {
				// TODO Auto-generated method stub
				return new VisitEmpty();
			}
		},
				new IConnectionFactoryX() {
					
					@Override
					public Connection getConnection() {
						// TODO Auto-generated method stub
						return getConnection(null);
					}
					
					@Override
					public Connection getConnection(String arg0) {
						// TODO Auto-generated method stub
						return new  DataSourceConnectionFactoryDrManager(Connections.dBases[11]).getConnection();
					}
				}, 
				new IElaplsedTimerFactory() {
			@Override
			public IElaplsedTimer getElapsedTimer() {
				// TODO Auto-generated method stub
				return new ElapsedTimer();
			}
		}, null);
		new TestApp();
	}
	
	private TestApp(){
		
		setLayout(new  GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		
		tabbedPane.addTab("SQL",  new QueryResultSetPanel());
		tabbedPane.addTab("SQL",  new QueryResultSetPanel());
		tabbedPane.addTab("SQL",  new QueryResultSetPanel());
		tabbedPane.addTab("SQL",  new QueryResultSetPanel());
		getContentPane().add(tabbedPane, c );
		
		setVisible(true);
		setSize(new Dimension(300, 200));
		setTitle("Sql Terminal...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
