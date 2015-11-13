package file.utils;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class FileSaver {
	
	protected String rootDirectory = "";
	private String rootDirhlp;
	private String FILE_EXT = ".txt";   //default is text
	private boolean bRelarivePath = true;
	String filePath = "";
	
	//set the root directory to write in and whether it's absolute path or relative to executable
	public FileSaver(String rootDirPath, boolean brelativePath, String ext){
		this(ext);
		rootDirectory = rootDirPath;
		rootDirhlp = rootDirectory;
		this.bRelarivePath = brelativePath;
	}
	
	//sets file extension
	public FileSaver(String ext){
		if(ext != null)
		FILE_EXT = ext;
		rootDirhlp = rootDirectory;
	}
	
   //create the directory for model
	protected void createDirInRootDir(String dirName) throws IOException{
		if(dirName == null || dirName.equals("")) return;
		
		//do some log here
		/*System.out.println("Entered in create Root Dir ....");
		System.out.println("Dir Name = " + dirName);*/
		
		int ind = dirName.indexOf("\\");
		if(ind == -1){  //path found 
			createSubDir(rootDirhlp, dirName);  //create last directory
			rootDirhlp += "\\" + dirName;      //extend path
			return;
		}		
		createSubDir(rootDirhlp, dirName.substring(0, ind));
		rootDirhlp += "\\" + dirName.substring(0, ind);     //extend path
		
		createDirInRootDir(dirName.substring(ind+1));       //call recursively
	}
	
	//create subDir in base path
	private void createSubDir(String basedir, String subdir) throws IOException{
		
		//do some log here
		/*System.out.println("Entered in createSubDir ....");
		System.out.println("basedir Name = " + basedir);
		System.out.println("subdir Name = " + subdir);
		*/
		File directory = new File (".");
		
		if(bRelarivePath == true){
			//relative path to executable folder
			filePath = directory.getCanonicalPath() + basedir + "\\" + subdir.toLowerCase();
		}else{
			 //absolute path
			filePath = basedir + "\\" + subdir.toLowerCase();
		}
		new File(filePath).mkdir();
	}
	

	
	//create the file to write in
	protected FileHandle createFileInDir(String filename, boolean append) throws IOException{
		
		if(filePath == null || filePath.equals(""))  filePath =new File (".").getCanonicalPath();
		//do some log here
		
		System.out.println("Entered in createFileInDir ....");
		System.out.println("filedirectory Name = " + filePath);
		System.out.println("filename Name = " + filename);

		
		FileHandle fh = new FileHandle(filePath +"\\" + filename + FILE_EXT);
		return fh;
	}
	
	
	
	public FileHandle getHandleToFile(String subdir, String filename, boolean append){
		try
		{
			rootDirhlp = rootDirectory;
			if(subdir != null) createDirInRootDir(subdir);
			
			//get FIlehandle
			return  createFileInDir(filename, append);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	
	//saves files 
	 /**Saves Object 'obTosave' to the file with file name 'filename' 
	 * located in the sub directory to  root directory specified 
	 * in FileSaver constructor. if append is true add content to file otherwise overwrite content.
	 */
	public void save(String subdir, String filename, Object obTosave, boolean append){
		try {
			rootDirhlp = rootDirectory;
			if(subdir != null) createDirInRootDir(subdir);
			
			if(obTosave == null) return ;
			//save to file
			FileHandle fh = createFileInDir(filename, append);
			
			if(obTosave instanceof String){  //save as string
				PrintWriter pr =  fh.getpWriter(true);
				pr.write((String)obTosave);
				pr.flush();
			}else{//save object to stream
				ObjectOutputStream wr =  fh.getObjectOutStream(true);
				wr.writeObject(obTosave);
				wr.flush();
			}
			
			fh.release();
				
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
