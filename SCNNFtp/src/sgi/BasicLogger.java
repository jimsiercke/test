package sgi;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class BasicLogger {
	public static  Logger logger;
	
	public static void logger(String message) {
		try {
			  Date date = new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy");
		      boolean append = true;
		      String fileName = "/data/opt/java/logs/"+sdf.format(date)+"SCNNFtp.log";
		      File file = new File(fileName);
		      
		      FileHandler fh = null;
		     if(file.exists())  {	
		   	  	fh = new FileHandler(fileName, append);
		     }else  {
		    	 file.createNewFile();
		    	 fh = new FileHandler(fileName, append);
		     }
		      //fh.setFormatter(new XMLFormatter());
		      fh.setFormatter(new SimpleFormatter());
		      logger = Logger.getLogger("BasicLogger");
		      logger.addHandler(fh);
		      logger.info(message);
		      fh.flush();
		      fh.close();
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
    }
	
}
