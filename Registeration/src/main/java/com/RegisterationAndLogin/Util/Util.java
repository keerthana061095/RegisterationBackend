package com.RegisterationAndLogin.Util;

import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Util {

	public static String md5(String password) {
		try {
			String md5Data = decode(password);
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5Data.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	public static String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}


	public static boolean checkValidMail(String emailId) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (emailId == null)
			return false;
		return pat.matcher(emailId).matches();
	}


	public static boolean isValidMobileNumber(String phoneNumber) {
		Pattern p = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
		return p.matcher(phoneNumber).matches();
	}

}
