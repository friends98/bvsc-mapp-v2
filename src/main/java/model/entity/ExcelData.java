package model.entity;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ExcelData {
	private InputStream ins;
	private Integer idMeeting;
	
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
	public Integer getIdMeeting() {
		return idMeeting;
	}

	/**
	 * @param fileName the fileName to set
	 */
	@FormParam("idMeeting")
    @PartType(MediaType.APPLICATION_JSON)
	public void setFileName(Integer idMeeting) {
		this.idMeeting = idMeeting;
	}

}