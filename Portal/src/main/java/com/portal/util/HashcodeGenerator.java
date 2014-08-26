package com.portal.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashcodeGenerator {
	
	public static String getMD5(String inputString) throws NoSuchAlgorithmException {
		
		MessageDigest messageDigest;
	    String hashcode = "";
		
		messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(inputString.getBytes(), 0, inputString.length());  
		hashcode = new BigInteger(1, messageDigest.digest()).toString(16);  
	        
	    if (hashcode.length() < 32) {
	        hashcode = "0" + hashcode; 
	    }

		return hashcode;
	}

	
}
