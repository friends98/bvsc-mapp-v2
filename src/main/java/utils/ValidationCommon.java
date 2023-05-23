package utils;

import java.util.regex.Pattern;

public class ValidationCommon {
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	private static final String PHONE_REGEX = "^\\d{10}$";
	
	private boolean checkEmail(String email) {
		Pattern pat = Pattern.compile(EMAIL_REGEX);
		return pat.matcher(email).matches();
	}
	
	private boolean checkPhoneNumber(String phoneNumber) {
		Pattern pat = Pattern.compile(PHONE_REGEX);
		return pat.matcher(phoneNumber).matches();
	}
}
