package ziper;
 import java.io.*;
import java.util.zip.*;
public class ZipEncoder{
	
	private String fileName,outputFilename;
	private long fileLen,outputFilelen;
	private String Summary;

		
	public ZipEncoder(){
		loadFile("","");
		}
	public ZipEncoder(String txt){
		loadFile(txt);
		}
	public ZipEncoder(String txt,String txt2){
		loadFile(txt,txt2);
		}
		
	public void loadFile(String txt){
		fileName = txt;
		outputFilename = txt + ".gz";
		Summary = "";
		}
	public void loadFile(String txt,String txt2){
		fileName = txt;
		outputFilename = txt2;
		Summary = "";
		}
		
	public boolean encodeFile() throws Exception{
		
		
		if(fileName.length() == 0) return false;
		try{
		FileInputStream in = new FileInputStream(fileName);
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outputFilename));
		fileLen = in.available();
		if(fileLen == 0 ) throw new Exception("Source File Empty!");
		Summary += "Original Size : " + fileLen + "\n";

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
		out.write(buf, 0, len);
		}
		in.close();
		out.finish();
		out.close();
		outputFilelen =  new File(outputFilename).length();
		float cratio = (float)(((outputFilelen)*100)/(float)fileLen);
		Summary += ("Compressed File Size : " + outputFilelen + "\n");
		Summary += ("Compression Ratio : " + cratio + "%" + "\n");

		}catch(Exception e){throw e; }
		return true;
		}
		
			
	public String getSummary(){
		return Summary;
		}

	}
    

