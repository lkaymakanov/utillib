package file.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class FileHandle {


	
	String filename = "";
	File file;
	FileInputStream fileInStream;
	FileOutputStream fileOutStream;
	FileReader  fileReader;
	FileWriter  fileWriter;
	BufferedReader bFileReader;
	BufferedWriter bFileWriter;
	PrintWriter pWriter ;
	
	//constructor
	public FileHandle(String filename){
	    	this.filename = filename;
	}
	
	
	
	
	//release resources
	public void release(){
			try{
				if(bFileReader!=null){ bFileReader.close(); return;}
				if(bFileWriter!=null) {bFileWriter.flush(); bFileWriter.close();return;}
				if(pWriter != null)  {pWriter.flush(); pWriter.close();return;}
				if(fileReader != null){ fileReader.close();return;}
				if(fileWriter != null){fileWriter.flush(); fileWriter.close();return;}
				if(fileInStream != null) {fileInStream.close();return;}
				if(fileOutStream != null){fileOutStream.flush(); fileOutStream.close();return;}
				
			}catch (Exception e) {
				// TODO: handle exception
				throw  new RuntimeException(e);
			}finally{
				fileReader = null;
				fileWriter = null;
				pWriter = null;
				fileInStream = null;
				fileOutStream = null;
				bFileReader = null;
				bFileWriter = null;
			}
	}

	

	public String getFilename() {
		return filename;
	}




	public FileInputStream getFileInStream() {
		release();
		try {
			fileInStream = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
		return fileInStream;
	}

	public FileOutputStream getFileOutStream(boolean append) {
		release();
		try {
			fileOutStream = new FileOutputStream(new File(filename), append);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
		return fileOutStream;
	}

	public FileReader getFileReader() {
		release();
		try {
			fileReader = new FileReader(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
		return fileReader;
	}

	public FileWriter getFileWriter() {
		release();
		try {
			fileWriter = new FileWriter(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException(e);

		}
		return fileWriter;
	}

	public BufferedReader getbFileReader() {
		release();
		bFileReader = new BufferedReader(getFileReader());
		return bFileReader;
	}

	public BufferedWriter getbFileWriter() {
		release();
		bFileWriter = new BufferedWriter(getFileWriter());
		return bFileWriter;
	}

	public PrintWriter getpWriter(boolean append) {
		release();
			pWriter = new PrintWriter(getFileOutStream(append));
		return pWriter;
	}
	
	
	public ObjectOutputStream getObjectOutStream(boolean b) {
		release();
		try {
			return new ObjectOutputStream(getFileOutStream(b));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException(e);
		}
	}
	

	
	public static void main(String [] arg){
		FileHandle h = new FileHandle("D:\\mytext.txt");
		h.getbFileReader();
		h.getFileInStream();
		
	}
	
}
