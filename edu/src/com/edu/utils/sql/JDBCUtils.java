package com.edu.utils.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��ʼ�����ݿ�Ĺ�����
 * ���Ը��������ļ���ָ����ip�ķ������ϴ������ݿ�
 * ���Ը���ָ����sql�ļ���ʼ�����ݿ��
 */
public class JDBCUtils {
	/**���ݿ�����*/
	private static String className ;
	/**���ݿ�url*/
	private static String url;
	/**���ݿ��¼��*/
	private static String user;
	/**���ݿ��¼����*/
	private static String password;
	/**���ݿ�sql�ļ�*/
	private static String sqlFileName;
	/**�Ƿ�ɾ��ԭ�������ݿ��*/
	private static String dropOldTable;
	private static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
	/**
	 * @param ��ȡ�����ļ�
	 * */
	static {
		Properties properties = new Properties();
		try {
			//properties.load(JDBCUtils.class.getResourceAsStream("conf\\jdbc.properties"));
			properties.load(new FileInputStream("conf"+File.separator+"jdbc.properties"));
			className = properties.getProperty("className");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			sqlFileName= properties.getProperty("sqlFileName");
			dropOldTable= properties.getProperty("dropOldTable");
			try {
				//��ʼ�����ݿ⣬���ݿ������������ļ�������
				initDatabase();
				//����sql�ļ�����ʼ����
				createTables(sqlFileName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return ����һ������
	 * @throws SQLException 
	 */
	private static Connection getConnection () throws SQLException{
		Connection connection = null;
		try {
			//��������
			Class.forName(className);
			//��ȡ����
			connection = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * ��ʼ�����ݿ�
	 * @throws SQLException
	 */
	private static void initDatabase() throws SQLException{
		logger.info("��ʼ�����ݿ�...");
		/**����һ�����Ӷ���*/
		Connection connection =null ;
		/**������sql���Ĵ���ʽ*/
		PreparedStatement preparedStatement = null;
		try {
			//��������
			Class.forName(className);
			//��ȡ���ӣ������ݿ����������ݿ��Ȼ���ڵ�һ�����ݿ⣬ͨ��������ݿ����Ӵ��������ڵ����ݿ⣩
			connection = DriverManager.getConnection(url.substring(0, url.lastIndexOf("/"))+"/mysql",user,password);
			//ƴ�Ӵ������ݿ��sql���
			String sql="create database if not exists "+url.substring(url.lastIndexOf("/")+1,url.indexOf("?"))+" character set = utf8mb4";
			//��ȡԤ����Ĵ������
			preparedStatement =connection.prepareStatement(sql);
			//ͨ��ָ����sql��䴦��ʽ���ж�sql���Ĵ���
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			//�ͷŶ����ݿ����Դ����
			close(connection,preparedStatement,null);
		}
	}
	/**
	 * �������ݿ��ķ���
	 * @param path ����һ���ļ�·��
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private static void createTables(String path) throws SQLException, IOException {
		logger.info("��ʼ�����ݿ��...");
		//����һ����ȡ�ļ���������
        if(path != null && !"".equals(path)){
        	BufferedReader reader = null;
        	//����һ�����ݿ�����
        	Connection connection = null;
        	PreparedStatement preparedStatement = null;
        	try {
        		connection = getConnection();
        		reader = new BufferedReader(new InputStreamReader(new FileInputStream("conf"+File.separator+path), "UTF-8"));
        		String line = "";
        		StringBuilder sb = new StringBuilder("");
        		while ((line = reader.readLine()) != null) {
        			//�����ǰ�д���drop���
        			if(line.toLowerCase().contains("drop")){
        				//�����false ��ʾ��ɾ���ɵı� ��ƴ��ɾ����sql
        				if("false".equals(dropOldTable)){
        					continue;
        				}
        			}
        			sb.append(line+"\n");
        		}
        		reader.close();
        		if(sb.length() > 0){
        			preparedStatement = connection.prepareStatement(sb.toString());
        			preparedStatement.executeUpdate();
        		}else {
        			throw new RuntimeException("���ݿ�sql�ļ��в���������");
        		}
        	} finally {
        		close(connection,preparedStatement,null);
        	}
        }else {
        	throw new RuntimeException("ȱ�ٳ�ʼ�����ݿ��sql�ļ�������");
        }
	}
	/**
	 * @param sql ����һ��sql���
	 * @return ���ز�ѯ���ı�����
	 * @throws SQLException 
	 */
	public static List<Map<String,Object>> doQuery(String sql) throws SQLException{
		logger.info(sql);
		//System.out.println(sql);
		/**��Ų�ѯ��������*/
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		/**���ݿ��ͷ����Ϊ��������������Ϊֵ�ŵ��ü�ֵ��ӳ����*/
		Map<String,Object> map = null;
		/**����һ�����Ӷ���*/
		Connection connection = null;
		/**��������sql���Ĵ���ʽ���÷�ʽΪԤ����Ĵ���ʽ���ڵ��øö���֮ǰ
		 * SQL ��䱻Ԥ���벢�洢�� PreparedStatement �����С�
		 * Ȼ�����ʹ�ô˶����θ�Ч��ִ�и���䣬������Ч�ķ�ֹsql��ע��*/
		PreparedStatement preparedStatement =null;
		/**����һ�����������*/
		ResultSet resultSet = null;
		/**����Ԫ���ݶ���������Ŵ����ݿ��л�ȡ����Ԫ���ݣ�
		 * �����ڻ�ȡ���� ResultSet �������е����ͺ�������Ϣ�Ķ���*/
		ResultSetMetaData resultSetMetaData = null;
		try {
			//��ȡ����
			connection = getConnection();
			//����sql���
			preparedStatement = connection.prepareStatement(sql);
			//����ȡ�������ݴ�ŵ����������resultSet��
			resultSet = preparedStatement.executeQuery();
			//��ȡ����Ϣ
			resultSetMetaData = resultSet.getMetaData();
			//ͨ������Ϣ��ȡ������
			int cols = resultSetMetaData.getColumnCount();
			//�ж��Ƿ�����һ������
			while(resultSet.next()){
				//����һ��HashMap��ֵ��ӳ��
				map = new HashMap<String, Object>();
				//��ȡÿһ�����ݽ����ŵ�map��
				for(int i = 1;i<=cols;i++){
					//����ͷ��Ϣ��Ϊ��������������Ϊֵ
					map.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
				}
				//��һ����������ӵ���ά��list������
				list.add(map);
			}
		} finally {
			//�ͷŶ����ݿ����Դ����
			close(connection,preparedStatement,resultSet);
		}
		return list;
	}
	/**
	 * @param sql ����һ��sql��䣬���ݸ�sql�����и������ݿ�
 	 * @return ����һ���������͵Ľ�������Ϊtrue����³ɹ����������ʧ��
	 * @throws SQLException 
	 */
	public static Integer doUpdate(String sql) throws SQLException{
		logger.info(sql);
		//System.out.println(sql);
		/**����һ�����Ӷ���*/
		Connection connection =null ;
		/**������sql���Ĵ���ʽ*/
		PreparedStatement preparedStatement = null;
		try {
			//��ȡ����
			connection = getConnection();
			//����sql���
			preparedStatement =connection.prepareStatement(sql);
			//ͨ��ָ����sql��䴦��ʽ���ж�sql���Ĵ���
			return preparedStatement.executeUpdate();
		} finally{
			//�ͷŶ����ݿ����Դ����
			close(connection,preparedStatement,null);
		}
	}
	/**
	 * @param connection ����һ����Ҫ���رյ�����
	 * @param statement ����һ����Ҫ���رյ�sql���ִ�ж���
	 * @param resultSet ����һ����Ҫ���رյĽ��������
	 * @throws SQLException 
	 */
	private static void close(Connection connection,Statement statement,ResultSet resultSet) throws SQLException{
		if(resultSet != null){
			resultSet.close();
		}
		if(statement != null){
			statement.close();
		}
		if(connection != null){
			connection.close();
		}
	}
}
