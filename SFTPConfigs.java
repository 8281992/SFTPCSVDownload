package downloadCSV;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPConfigs {
	private String host;
	private Integer port;
	private String user;
	private String password;
	
	private JSch jsch;
	private Session session;
	private Channel channel;
	private ChannelSftp sftpChannel;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JSch getJsch() {
		return jsch;
	}
	public void setJsch(JSch jsch) {
		this.jsch = jsch;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public ChannelSftp getSftpChannel() {
		return sftpChannel;
	}
	public void setSftpChannel(ChannelSftp sftpChannel) {
		this.sftpChannel = sftpChannel;
	}
	
	
	 public void connect() {
			
			System.out.println("connecting..."+host);
			try {
				jsch = new JSch();
				session = jsch.getSession(user, host,port);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(password);
				session.connect();

				channel = session.openChannel("sftp");
				channel.connect();
				sftpChannel = (ChannelSftp) channel;

			} catch (JSchException e) {
				e.printStackTrace();
			}

		}
	
}
