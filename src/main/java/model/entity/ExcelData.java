package model.entity;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ExcelData {
	private InputStream ins;
	private String fileName;
	
	public ExcelData() {}

	/**
	 * @return the ins
	 */
	public InputStream getIns() {
		return ins;
	}

	/**
	 * @param ins the ins to set
	 */
	@FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)

	public void setIns(InputStream ins) {
		this.ins = ins;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	@FormParam("fileName")
    @PartType(MediaType.TEXT_PLAIN)
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
