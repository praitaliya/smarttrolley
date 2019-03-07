package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Model.serverInfo;
import com.dao.TrolleyLogs;
import com.xml.EncryptUtils;
/** 
 * database connection class for hibernate and jdbc connection
 * 
 * @author Prashant Italiya
 *
 */
public class DBConnection {

	static SessionFactory sf = null;
	Session session = null;
	Connection conn = null;

	private static SessionFactory sessionFactory = openConnection(); // sessionfactory made static, so only 1 connection to be open during life cycle

	public static SessionFactory openConnection() { // hibernate session menthod
		try {
			sf = new Configuration().addAnnotatedClass(com.Model.itemModel.class)
					.addAnnotatedClass(com.Model.discountModel.class)
					.addAnnotatedClass(com.Model.itemDetailsModel.class)
					.addAnnotatedClass(com.Model.itemTaxPlanModel.class)
					.addAnnotatedClass(com.Model.itemCategoryModel.class).addAnnotatedClass(com.Model.RFIDModel.class)
					.addAnnotatedClass(com.Model.CustomerModel.class)
					.addAnnotatedClass(com.Model.transactionModel.class).addAnnotatedClass(com.Model.TrolleyModel.class)
					.addAnnotatedClass(com.Model.transactionMaster.class)
					.addAnnotatedClass(com.Model.billingModel.class).addAnnotatedClass(com.Model.CustomerDetails.class)
					.configure().buildSessionFactory();
			System.out.println("Session Factory Created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sf;
	}

	public Session getHiberConnection() { // hibernate get connection
		try {
			if (sessionFactory == null) {
				sessionFactory = openConnection();
			}
			this.session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.session;
	}

	public Connection getConnection(serverInfo sInfo) { // jdbc connection
		Connection conn = null;
		serverInfo data = sInfo; // variables of serverInfo model are encrypted using base64
		System.out.println("Conn String : " + data);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String connURL = "jdbc:mysql://" + EncryptUtils.base64decode(sInfo.getIp()) + ":" + sInfo.getPort() + "/"
						+ sInfo.getDatabase();
				conn = DriverManager.getConnection(connURL, EncryptUtils.base64decode(sInfo.getUname()),
						EncryptUtils.base64decode(sInfo.getPass()));
			} catch (Exception e) {
				TrolleyLogs.AppendToLog(e);
				e.printStackTrace();
			}
		return conn;
	}
}
