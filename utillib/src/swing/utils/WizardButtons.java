package swing.utils;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class WizardButtons {

	//the three buttons of wizard connect to DB, back & next
	private JButton btnConnect = new JButton("Connect");
	private JButton btnBack = new JButton("<<Back");
	private JButton btnNext = new JButton("Next>>");
	
	public WizardButtons(){
		btnBack.setName("btnBack");
		btnConnect.setName("btnConnect");
		btnNext.setName("btnNext");
	}
	
	//register action listener  in constructor
	public WizardButtons(ActionListener listener){
		this();
		AddActionListenter(listener);
	}
	
	
	//register action listener for buttons
	public void AddActionListenter(ActionListener listener){
		
		if(listener == null) return;
		
		//register to buttons
		btnBack.addActionListener(listener);
		btnConnect.addActionListener(listener);
		btnNext.addActionListener(listener);
	}
	
	//enable disable buttons
	public void Enable(boolean b){
		btnConnect.setEnabled(b);
		btnBack.setEnabled(b);
		btnNext.setEnabled(b);
	}
	

	public JButton getBtnConnect() {
		return btnConnect;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnNext() {
		return btnNext;
	}
}
