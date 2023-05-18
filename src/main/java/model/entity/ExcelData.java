package model.entity;

import javax.ws.rs.FormParam;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ExcelData {
	public ExcelData() {}

	private byte [] data;
	private String fileName;
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	@FormDataParam("filename")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	@FormParam("file")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
	}

}
