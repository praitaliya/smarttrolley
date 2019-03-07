package com.dao;

import org.apache.log4j.Logger;

/**
 * 
 * dirty implementation of log4j
 * @author Prashant Italiya
 *
 */

public class TrolleyLogs {

	private static final Logger logger = Logger.getLogger(TrolleyLogs.class);

	/*
	 * private static PrintStream m_streamLog = null; private static String
	 * strAppHostName = null; private static final String strTomcatWebappsFolder
	 * = "webapps"; private static final String strEmgrContext = "MSG-DB";
	 * private static String strLogsFolder = "logs";
	 */

	public static void AppendToLog(Throwable strLog) {
		System.out.println("Hello : " + strLog);
		logger.error(" **Error AppendToLog : ", strLog);
	}

	public static Logger getLogger() {
		return logger;
	}
}
