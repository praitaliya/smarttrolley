package com.dao;

/**
 * class for various util checks
 * @author Prashant Italiya
 *
 */
public class Utils {
	public static int checkPageAccess(String role, String uid) { // check if user have access to request and page access
		int response = 0;
		if (!role.equalsIgnoreCase("admin")) {
			response = 0;
		} else {
			response = 1;
		}
		return response;
	}
}
