package swing.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Utils {
     
     public static void showErrMsg(Component c, String msg){
    	 JOptionPane.showMessageDialog(c,
					"Problem  ...   \n " + msg,
					"Ooppps...",
					JOptionPane.ERROR_MESSAGE);
     }
     
     
     public static void showSuccessMsg(Component c, String msg){
    	 JOptionPane.showMessageDialog(c,
					"Well done  ...   \n " + msg,
					"Sucsess...",
					JOptionPane.INFORMATION_MESSAGE);
     }
}

