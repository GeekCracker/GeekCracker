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
 * 初始化数据库的工具类
 * 可以根据配置文件在指定的ip的服务器上创建数据库
 * 可以根据指定的sql文件初始化数据库表
 */
public class JDBCUtils {
	/**数据库驱动*/
	private static String className ;
	/**数据库url*/
	private static String url;
	/**数据库登录名*/
	private static String user;
	/**数据库登录密码*/
	private static String password;
	/**数据库sql文件*/
	private static String sqlFileName;
	/**是否删除原来的数据库表*/
	private static String dropOldTable;
	private static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
	/**
	 * @param 读取属性文件
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
				//初始化数据库，数据库名称在配置文件中配置
				initDatabase();
				//运行sql文件，初始化表
				createTables(sqlFileName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return 返回一个连接
	 * @throws SQLException 
	 */
	private static Connection getConnection () throws SQLException{
		Connection connection = null;
		try {
			//加载驱动
			Class.forName(className);
			//获取连接
			connection = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 初始化数据库
	 * @throws SQLException
	 */
	private static void initDatabase() throws SQLException{
		logger.info("初始化数据库...");
		/**声明一个连接对象*/
		Connection connection =null ;
		/**声明对sql语句的处理方式*/
		PreparedStatement preparedStatement = null;
		try {
			//加载驱动
			Class.forName(className);
			//获取连接（该数据库是所有数据库必然存在的一个数据库，通过这个数据库连接创建不存在的数据库）
			connection = DriverManager.getConnection(url.substring(0, url.lastIndexOf("/"))+"/mysql",user,password);
			//拼接创建数据库的sql语句
			String sql="create database if not exists "+url.substring(url.lastIndexOf("/")+1,url.indexOf("?"))+" character set = utf8mb4";
			//获取预处理的处理对象
			preparedStatement =connection.prepareStatement(sql);
			//通过指定的sql语句处理方式进行对sql语句的处理
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			//释放对数据库的资源访问
			close(connection,preparedStatement,null);
		}
	}
	/**
	 * 创建数据库表的方法
	 * @param path 传入一个文件路径
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private static void createTables(String path) throws SQLException, IOException {
		logger.info("初始化数据库表...");
		//声明一个读取文件的输入流
        if(path != null && !"".equals(path)){
        	BufferedReader reader = null;
        	//声明一个数据库连接
        	Connection connection = null;
        	PreparedStatement preparedStatement = null;
        	try {
        		connection = getConnection();
        		reader = new BufferedReader(new InputStreamReader(new FileInputStream("conf"+File.separator+path), "UTF-8"));
        		String line = "";
        		StringBuilder sb = new StringBuilder("");
        		while ((line = reader.readLine()) != null) {
        			//如果当前行存在drop语句
        			if(line.toLowerCase().contains("drop")){
        				//如果是false 表示不删除旧的表 则不拼接删除的sql
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
        			throw new RuntimeException("数据库sql文件中不包含内容");
        		}
        	} finally {
        		close(connection,preparedStatement,null);
        	}
        }else {
        	throw new RuntimeException("缺少初始化数据库的sql文件名配置");
        }
	}
	/**
	 * @param sql 传入一个sql语句
	 * @return 返回查询到的表数据
	 * @throws SQLException 
	 */
	public static List<Map<String,Object>> doQuery(String sql) throws SQLException{
		logger.info(sql);
		//System.out.println(sql);
		/**存放查询到的数据*/
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		/**数据库表头名作为键，将表数据作为值放到该键值对映射中*/
		Map<String,Object> map = null;
		/**创建一个连接对象*/
		Connection connection = null;
		/**声明处理sql语句的处理方式，该方式为预编译的处理方式，在调用该对象之前
		 * SQL 语句被预编译并存储在 PreparedStatement 对象中。
		 * 然后可以使用此对象多次高效地执行该语句，可以有效的防止sql的注入*/
		PreparedStatement preparedStatement =null;
		/**创建一个结果集对象*/
		ResultSet resultSet = null;
		/**创建元数据对象，用来存放从数据库中获取到的元数据，
		 * 可用于获取关于 ResultSet 对象中列的类型和属性信息的对象。*/
		ResultSetMetaData resultSetMetaData = null;
		try {
			//获取连接
			connection = getConnection();
			//处理sql语句
			preparedStatement = connection.prepareStatement(sql);
			//将获取到的数据存放到结果集对象resultSet中
			resultSet = preparedStatement.executeQuery();
			//获取列信息
			resultSetMetaData = resultSet.getMetaData();
			//通过列信息获取到列数
			int cols = resultSetMetaData.getColumnCount();
			//判断是否有下一行数据
			while(resultSet.next()){
				//定义一个HashMap键值对映射
				map = new HashMap<String, Object>();
				//获取每一列数据将其存放到map中
				for(int i = 1;i<=cols;i++){
					//将表头信息作为键，将表数据作为值
					map.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
				}
				//将一个行数据添加到二维的list集合中
				list.add(map);
			}
		} finally {
			//释放对数据库的资源访问
			close(connection,preparedStatement,resultSet);
		}
		return list;
	}
	/**
	 * @param sql 传入一个sql语句，根据该sql语句进行更新数据库
 	 * @return 返回一个布尔类型的结果，如果为true则更新成功，否则更新失败
	 * @throws SQLException 
	 */
	public static Integer doUpdate(String sql) throws SQLException{
		logger.info(sql);
		//System.out.println(sql);
		/**声明一个连接对象*/
		Connection connection =null ;
		/**声明对sql语句的处理方式*/
		PreparedStatement preparedStatement = null;
		try {
			//获取连接
			connection = getConnection();
			//处理sql语句
			preparedStatement =connection.prepareStatement(sql);
			//通过指定的sql语句处理方式进行对sql语句的处理
			return preparedStatement.executeUpdate();
		} finally{
			//释放对数据库的资源访问
			close(connection,preparedStatement,null);
		}
	}
	/**
	 * @param connection 传入一个需要被关闭的连接
	 * @param statement 传入一个需要被关闭的sql语句执行对象
	 * @param resultSet 传入一个需要被关闭的结果集对象
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
