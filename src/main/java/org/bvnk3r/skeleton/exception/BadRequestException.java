/**
 * @author bunk3r
 */

package org.bvnk3r.skeleton.exception;

public class BadRequestException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String data;

	public BadRequestException(String data) {
		this.data = data;
	}
	
	@Override
	public String getMessage() {
		return "BadRequestException: Missing request data (data is "+ data +")";
	}

}
