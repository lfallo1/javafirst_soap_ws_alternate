package com.javafirst.ws.services.fileUploadService;

import javax.activation.DataHandler;
import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

@WebService(targetNamespace = "http://customerorders.services.lance.com/", name = "FileService")
@HandlerChain(file="../interceptors/handlers.xml")
public interface FileService {

	void uploadFile(@WebParam(name="file") DataHandler attachInfo);
	
	 @XmlMimeType("application/octet-stream") DataHandler downloadFile();
	
}