package security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.ejb.Stateless;

@Stateless
public class SecurityUtils {
	public String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageByte = md.digest(input.getBytes());
			BigInteger sigNum = new BigInteger(1, messageByte);
			String hashMd5 = sigNum.toString(16);
			while (hashMd5.length() < 32) {
				hashMd5 = "0" + hashMd5;
			}
			return hashMd5;

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
	}
	public static String createPasswordWithSecretKey() {
		String code="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int lengthOfCode=code.length();
		StringBuilder sbResult=new StringBuilder();
		SecureRandom secureRandom=new SecureRandom();
		for(int i=0;i<8;i++) {
			int random=secureRandom.nextInt(lengthOfCode);
			sbResult.append(code.charAt(random));
		}
		return sbResult.toString();
	}
	
}
