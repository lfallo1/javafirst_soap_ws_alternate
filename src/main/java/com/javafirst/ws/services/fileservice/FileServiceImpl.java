package com.javafirst.ws.services.fileservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class FileServiceImpl implements FileService {

	public void uploadFile(DataHandler attachinfo) {

		try (InputStream input = attachinfo.getInputStream();
				OutputStream output = new FileOutputStream(new File(
						"C:\\Users\\lfallon\\Desktop\\Test.jpg"));) {

			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = input.read(b)) != -1) {
				output.write(b, 0, bytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataHandler downloadFile() {
		DataSource source = new FileDataSource(new File("C:\\Users\\lfallon\\Desktop\\Test.jpg"));
		return new DataHandler(source);
	}
}
