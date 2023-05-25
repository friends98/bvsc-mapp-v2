package model.entity;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ImageData {
	
	public ImageData() {}
	
	
	private byte [] data;
	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	@FormParam("image")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
	}
}