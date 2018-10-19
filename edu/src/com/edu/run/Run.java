package com.edu.run;

import com.edu.ui.login.LoginUI;
import com.edu.utils.sql.JDBCUtils;

public class Run {

	public static void main(String[] args) {
		try {
			new JDBCUtils();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LoginUI();
	}
}
