import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.jcraft.jsch.*;

public class Print {


	public void print(String username, String password, File document, JTextPane statusPane) {

		try {
			JSch jsch = new JSch();
			String filename = document.getName();
			Session session = jsch.getSession(username, "i08fs1.ira.uka.de", 22);
			session.setUserInfo(new MyUserInfo(password));
			Properties config = new Properties();
			config.setProperty("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			
			statusPane.setText("" + statusPane.getText() + "\nTransferring...");
			//System.out.println("Transferring...");
			ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			channelSftp.cd("/home/stud/" + username + "/");
			channelSftp.put(new FileInputStream(document), filename);
			channelSftp.disconnect();

			//System.out.println("Printing...");
			statusPane.setText("" + statusPane.getText() + "\nPrinting...");
			ChannelExec channelExecPrint = (ChannelExec) session.openChannel("exec");
			channelExecPrint.setCommand("lp -d pool-sw2 " + document.getName() + ";");
			// channelExecPrint.setCommand("rm " + document.getName() + ";");
			channelExecPrint.connect();
			channelExecPrint.disconnect();

			session.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class MyUserInfo implements UserInfo {

		private final String password;

		private MyUserInfo(String password) {
			this.password = password;
		}

		@Override
		public String getPassphrase() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		@Override
		public boolean promptPassphrase(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean promptPassword(String arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean promptYesNo(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void showMessage(String arg0) {
			// TODO Auto-generated method stub

		}

	}

}
