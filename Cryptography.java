package br.com.alelo.core.utils;

import java.util.Base64;

public class Cryptography {

	public static String decrypt(String encrypted) {
		return new String(Base64.getDecoder().decode(encrypted));
	}
	
}
