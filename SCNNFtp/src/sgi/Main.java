package sgi;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			
			
			PullFtp.getFiles();
			PushFtp.pushFiles();
		}catch(Exception e) {		
			e.printStackTrace();
		}
	}
}
