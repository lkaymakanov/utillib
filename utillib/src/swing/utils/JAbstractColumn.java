package swing.utils;

import javax.swing.table.TableColumn;

public class  JAbstractColumn extends TableColumn{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 500609281038457076L;
	private String name  = "";
	int width = -1;
	private boolean editable = false;
	
	public JAbstractColumn(String name, int width, boolean editabble){
        setHeaderValue(name);
        if(width > 0)  setPreferredWidth(width);
        this.editable = editabble;
	}
	
	
	

	public boolean isEditable() {
		return editable;
	}




	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}
}
