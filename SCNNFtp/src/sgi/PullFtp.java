package sgi;

import java.io.File;
import java.io.IOException;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FileTransferClient;

public class PullFtp {
	public static FileTransferClient ftp = new FileTransferClient();
	public static void getFiles()  {
		String email = "systems@storygroup.net";
		try {
		    
		    BasicLogger.logger("Setting Host");
		   		    ftp.connect();
		    File verified = null;
		    if(ftp.isConnected())   {
		    	BasicLogger.logger("Pull Connection Made");
		    }else  {
		    	BasicLogger.logger("Pull Connection Failed");
		    }
		    //Get files from dir specified in the params
		    String[] filesToPull = ftp.directoryNameList();
		    String localFileName = "/data/opt/wftp/data/Customers/loreal/carriers/SCNN/";
		    for(int i=0; i< filesToPull.length;i++) {
		    	localFileName = "/data/opt/wftp/data/Customers/loreal/carriers/SCNN/"+filesToPull[i];
		    	ftp.downloadFile(localFileName, filesToPull[i]);
		    	verified = new File(localFileName);
		    	if(verified.exists()) {
		    		BasicLogger.logger("Deleting Remote File:"+filesToPull[i]);
		    		ftp.deleteFile(filesToPull[i]);
		    	}
		    }
		    // Quit from the FTP server.
			BasicLogger.logger("Pull FTP Disconnect");
			ftp.disconnect();
			BasicLogger.logger("Pull FTP Disconnect Completed");
		   
		}catch (IOException e) {
			SendEmail.sendEmail("Pull IOException:"+e.getMessage(),"SCNN FTP Process Failed");
			BasicLogger.logger("IO ERROR:"+e.getMessage());
			BasicLogger.logger("email sent to:"+email);
			e.printStackTrace();
		} catch (FTPException e) {
			SendEmail.sendEmail("Pull FTPException:"+e.getMessage(),"SCNN FTP Process Failed");
			BasicLogger.logger("FTP ERROR:"+e.getMessage());
			BasicLogger.logger("email sent to:"+email);
			e.printStackTrace();
		}finally {
			try {
				ftp.disconnect();
				BasicLogger.logger("PullFTP Disconnect Completed");
			} catch (FTPException e) {	
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	}
}
