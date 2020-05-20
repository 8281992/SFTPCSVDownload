package downloadCSV;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
@EnableScheduling
public class downloadService {

	@AutoWired
	public SFTPConfigs sftpConnect;
	 @Scheduled(fixedDelay = 3600000)
	    public void fetchCSVs() {
		 String localPath = "C:/temp/";
		 String remotePath = "/export/csv/";
		 download(remotePath+"Perf", localPath);
		 
	 }
	
	 public void download(String fileName, String localDir) {

			byte[] buffer = new byte[1024];
			BufferedInputStream bis;
			sftpConnect.connect();
			try {
				// Change to output directory
				String cdDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
				sftpConnect.sftpChannel.cd(cdDir);

				File file = new File(fileName);
				bis = new BufferedInputStream(	sftpConnect.sftpChannel.get(file.getName()));

				File newFile = new File(localDir + "/" + file.getName());
				
				// Download file
				OutputStream os = new FileOutputStream(newFile);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				int readCount;
				while ((readCount = bis.read(buffer)) > 0) {
					bos.write(buffer, 0, readCount);
				}
				bis.close();
				bos.close();
				System.out.println("File downloaded successfully - "+ file.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
