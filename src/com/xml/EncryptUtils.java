package com.xml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.dao.TrolleyLogs;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * methods for base64 encode and decode
 * @author Prashant Italiya
 *
 */
public class EncryptUtils {
	public static final String DEFAULT_ENCODING = "UTF-8";

	public static String base64encode(String text) {
		try {
			if (text.length() == 0) {
				return "aGVybmNoZ00=";
			} else {
				byte[] encodedBytes = Base64.getEncoder().encode(text.getBytes());
				return new String(encodedBytes);
			}
		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			return null;
		}
	}// base64encode

	public static String base64decode(String text) {
		try {
			if (text.equals("aGVybmNoZ00=")) {
				return "";
			} else {
				byte[] decodedBytes = Base64.getDecoder().decode(text.getBytes());
				return new String(decodedBytes);
			}

		} catch (Exception e) {
			TrolleyLogs.AppendToLog(e);
			return null;
		}
	}// base64decode
}// class