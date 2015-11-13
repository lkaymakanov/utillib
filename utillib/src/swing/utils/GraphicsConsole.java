package swing.utils;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GraphicsConsole extends JTextArea{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8250853830813411438L;
	private JPanel parent = null;  ///the parent component if any
	Color backColor = new  Color(0, 0, 0);
	Dimension d = new Dimension(600, 300);
	Color textColor = new  Color(0, 0, 0);
	
	//init console
	public GraphicsConsole(){
		this(null, new  Color(0, 0, 0), new  Color(0, 255, 0), new Dimension(600, 300));
	}
	
	//init console
	public GraphicsConsole(JPanel parent ){
		this(parent, new  Color(0, 0, 0), new  Color(0, 255, 0), new Dimension(600, 300));
	}
	
	//init from by parent panel
	public GraphicsConsole(JPanel com, Color backGround, Color textColor, Dimension d){
		parent = com;
		this.backColor = backGround;
		this.textColor = textColor;
		this.d = d;
		initConsole();
	}
	
	
	//init console
	private void initConsole(){
		if(parent != null) {
			
			setLineWrap(true);
			setVisible(true);
			
			setBackground(backColor);      //set back to black 
			setForeground(textColor);   //set text to green 
			
			JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(d);
			 
			parent.add(scrollPane, BorderLayout.CENTER);
		}
	
		append("This is the console output text...");
	}
	

	//write line to console
	public synchronized void writeln(String s){
		append(s + "\n");
	}
	
	
	//clear the console
	public synchronized void clear(){
		setText("");
	}
	
	
}