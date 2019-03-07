package com.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.Model.serverInfo;
import com.dao.TrolleyLogs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.util.DBConnection;
/**
 * This class parse json file, which is used to store db connection details
 * @author Prashant Italiya
 *
 */
public class XMLParser {
	String serverName = null;
	String ip = null;
	int port = 0;
	String uname = null;
	String pass = null;
	String database = null;
	String type = null;
	String aculabIP = null;
	int status = 0;
	SAXBuilder saxBuilder = null;
	Element classElement = null;
	static String fileName = "E:\\Study\\UWin\\TrolleyWeb\\Smart-Trolley\\src\\com\\xml\\serverInfo.json";
	static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	static DBConnection db = new DBConnection();
	static Map<String, serverInfo> serverData = new TreeMap<String, serverInfo>();

	public Map<String, serverInfo> getServerInfo() {
		if (serverData == null || serverData.size() == 0) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				Type stringStringMap = new TypeToken<Map<String, serverInfo>>() {
				}.getType();
				serverData = gson.fromJson(br, stringStringMap);
				return serverData;
			} catch (Exception e) {
				TrolleyLogs.AppendToLog(e);
				e.printStackTrace();
			}
		}
		return serverData;
	}

	public ArrayList<String> getTextServerInfo(String type) { // convert serverInfo model to text string
		ArrayList<String> data = new ArrayList<String>();
		if (serverData == null || serverData.size() == 0) {
			serverData = getServerInfo();
		}
		for (Map.Entry<String, serverInfo> entry : serverData.entrySet()) {
			if (entry.getValue().getType().equalsIgnoreCase(type)) {
				data.add(EncryptUtils.base64encode(gson.toJson(entry.getValue())));
				// data.add(gson.toJson(entry.getValue()));
			}
		}
		return data;
	}

	public serverInfo getServerInfoByKey(String key) {
		serverInfo sInfo = new serverInfo();
		if (serverData == null || serverData.size() == 0) {
			serverData = getServerInfo();
		}
		sInfo = serverData.get(key);
		if (sInfo == null) {
			System.out.println("Unknown Key");
		}
		return sInfo;
	}

	public serverInfo convertString(String info) {
		serverInfo data = new serverInfo();
		if (info != null) {
			String serverInfo = EncryptUtils.base64decode(info);
			String server[] = serverInfo.split(" , ");
			data.setServerName(server[0]);
			data.setIp(server[1]);
			data.setPort(Integer.parseInt(server[2]));
			data.setUname(server[3]);
			data.setPass(server[4]);
			data.setDatabase(server[5]);
			data.setType(server[6]);
		} else {
			System.out.println("Something went wrong");
		}
		return data;
	}
}
