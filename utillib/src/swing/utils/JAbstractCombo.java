package swing.utils;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public abstract class JAbstractCombo extends JComboBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628440556939630517L;
	protected List<Object> objects = new ArrayList<Object>();
	ComboBoxRenderer render = new ComboBoxRenderer();
	
	int selindex = -1;
	
	
	public JAbstractCombo() {
		// TODO Auto-generated constructor stubn
		addItem(-1);
		setRenderer(render);
	}
	
	//init combo from connections
	public void initCombo(List<?> pr){
		removeAllItems();
		objects  = new ArrayList<Object>();
		if(pr!=null && pr.size() > 0){
	        for (int i = 0; i < pr.size(); i++) {
	            addItem(i);
	            objects.add(pr.get(i));
	        }
		}
		else{
			addItem(-1);
		}
	}
	
	public List<?>  getObjects(){
		return objects;
	}
	
	//value to show in combo box
	public abstract String getValToShow(int index);
	
	
	//index of selected object in combo
	public int getSelindex() {
		return selindex;
	}


	
	//return reference to the selected object from combo
    public Object getSelObject() {
    	if(selindex == -1 || objects == null || selindex >= objects.size()) return null;
		return objects.get(selindex);
	}


    


	//the renderer to show connections in combo box
	class  ComboBoxRenderer extends JLabel implements ListCellRenderer {
		
		private static final long serialVersionUID = 2897297403981058928L;
		
		public ComboBoxRenderer() {
			// TODO Auto-generated constructor stub
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			//return this;
			// TODO Auto-generated method stub
			int selectedIndex = ((Integer)value).intValue();
			selindex = selectedIndex;
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
 
            if(selectedIndex == -1) { setText("No items ..."); return this;}
            String pet = getValToShow(selindex);// prop.get(selectedIndex).getName_to_display();
           
            setText(pet);
            setFont(list.getFont());
         
            return this;
		}
	
	}


	
}
