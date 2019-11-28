package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUtil {

	public static String rename(String fileName) {
		String nameFile = "";
		if (!fileName.isEmpty()) {
			String[] arrImg = fileName.split("\\.");
			String duoiFileImg = arrImg[arrImg.length - 1];
			
			for (int i = 0; i < (arrImg.length - 1); i++) {
				if (i == 0) {
					nameFile = arrImg[i];
				} else {
					nameFile += "-" + arrImg[i];
				}
			}
			nameFile = nameFile + "-" + System.nanoTime() + "." + duoiFileImg;
		}
		return nameFile;
	}

	public static String getName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	public static String uploadFile(HttpServletRequest request,String inputFileName,String dirUpload) throws IOException, ServletException {
		final Part filePart = request.getPart(inputFileName);
		String fileName = filePart.getSubmittedFileName();
		if (!"".equals(fileName)) {
			// doi ten file
			fileName = FileUtil.rename(fileName);
			String dirPath = request.getServletContext().getRealPath("") + dirUpload;
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			String filePath = dirPath + File.separator + fileName;
			System.out.println(filePath);
			filePart.write(filePath);
		}
		return fileName;
	}
}
