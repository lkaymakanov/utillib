package helpfun;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class  HelpFunctions {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd_mm_yyyy");
	private static String emptySymbol = " ";

	public static Date parseDBDate(String dateString) {
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatDBDate(Date date) {
		if (date == null)
			return null;
		return sdf.format(date);
	}

	public static String formatDBDate(Date date, SimpleDateFormat sdf) {
		if (date == null)
			return null;
		return sdf.format(date);
	}
	
	/**
	 * this is used to calculate how long something takes to  be executed... 
	 * @param time
	 * @return
	 */
	public static void getStartTimeMillis(/*out param */ElapsedTimeAttributes attrib){
		attrib.startTime = System.currentTimeMillis();
	}
	
	public static void getEndTTimeMillis(/*out param */ElapsedTimeAttributes attrib){
		long duration = System.currentTimeMillis() - attrib.startTime;
		long endTime = duration + attrib.startTime;
		
		//fill attributes
		if(attrib != null){
			attrib.duration = duration;
			attrib.endTime = endTime;
		}
	}
	
	
	
	
	
	
	/**Traverse directory trees
	 * 
	 * @param node - the current file node
	 * @param trcallback  - what is to be done forward & backward
	 */
	public static void traverseDirs(File node, TraverseDirsCallBack trcallback){
		
		if(trcallback != null)trcallback.OnForward(node);
		//System.out.println(node.getAbsoluteFile());
		
		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				traverseDirs(new File(node, filename), trcallback);
				if(trcallback != null) trcallback.OnReturnFromRecursion(node);//act on the directory
			}
		}
		if(trcallback != null) trcallback.OnReturnFromRecursion(node);  //act on the file
	}
		
	/**
	 * Set parameter, replace the first occurrence of '?' in String 'where'  with the   String 'what'  & returns the new String
	 * @param where
	 * @param what
	 * @return the String with the parameter set
	 */
	public static String  setParam(String where,  String what){
		if(where == null) where = emptySymbol;
		if(what == null) what = emptySymbol;
		return where.replaceFirst("\\?", what);
	}
	
	
}
