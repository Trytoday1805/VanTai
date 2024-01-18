package filemanagement.filemanagement.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtil {
	private static String MAIN_DIRECTORY = "C:\\Files";
	public static Path uploadFile(MultipartFile fileToUpload, Long userId, String filename) throws IOException {
		Path uploadPath = Paths.get(MAIN_DIRECTORY).resolve(Long.toString(userId));
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = fileToUpload.getInputStream()){
			String extension = FilenameUtils.getExtension(fileToUpload.getOriginalFilename());
			Path filePath = uploadPath.resolve(filename + "."+ extension);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return filePath;
		} catch (IOException e) {
			throw new IOException("File not found!!!");
		}
		
	}

	public static void deleteFile(String path){
		File fx = new File(path);
		if(fx.isFile())
		{
//			System.out.println("Da xoa: "+ fx.getAbsolutePath());
			fx.delete();
		}
		else if (fx.isDirectory())
		{
			File [] mangcon= fx.listFiles();
			for(File f: mangcon)
			{
				// de quy xem no la tap tin hay la
				deleteFile(f.getPath());
			}
			// xóa đc mục cuối cùng
			fx.delete();
		}
	}
}
