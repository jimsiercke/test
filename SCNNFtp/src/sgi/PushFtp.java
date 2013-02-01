package sgi;

import java.io.File;
import java.io.IOException;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FileTransferClient;
import com.enterprisedt.net.ftp.WriteMode;

public class PushFtp {
	public static FileTransferClient ftp = new FileTransferClient();
	public static void pushFiles()  {
		try {
		    //Check local dir for files to push
			File dir = new File("/data/opt/wftp/data/Customers/loreal/carriers/SCNN/outbound");
			String directoryPath = "/data/opt/wftp/data/Customers/loreal/carriers/SCNN/outbound";
	    	int length = dir.list().length;
	    	File delete = null;
	    	if(length > 0) {
				String[] fileNames =  new String[length];
				fileNames = dir.list();
							    ftp.connect();
			    if(ftp.isConnected())   {
			    	BasicLogger.logger("Push Connection Made");
			    }else  {
			    	BasicLogger.logger("Push Connection Failed");
			    }
			    ftp.changeDirectory("/EDI");
			    for(int i=0;i<=length;i++) {
			    	delete = new File(directoryPath+"/"+fileNames[i].trim());
		    		ftp.uploadFile(directoryPath+"/"+fileNames[i].trim(), fileNames[i],WriteMode.OVERWRITE);
		    		if(ftp.exists(fileNames[i])) {
		    			//delete.delete();
		    		}	
			    }
			}		    
		    // Quit from the FTP server.
			BasicLogger.logger("Push FTP Disconnect");
			ftp.disconnect();
			BasicLogger.logger("PushFTP Disconnect Completed"); 
		    
		} catch (FTPException e) {
			SendEmail.sendEmail("Push FTPException:"+e.getMessage(),"SCNN FTP Process Failed");
			BasicLogger.logger(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			SendEmail.sendEmail("Push IOException:"+e.getMessage(),"SCNN FTP Process Failed");
			BasicLogger.logger(e.getMessage());
			e.printStackTrace();
		}finally {
				try {
					ftp.disconnect();
					BasicLogger.logger("PushFTP Disconnect Completed");
				} catch (FTPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	    
	}
}
