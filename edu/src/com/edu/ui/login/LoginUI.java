package com.edu.ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.edu.domain.entity.User;
import com.edu.exception.BizException;
import com.edu.service.UserService;
import com.edu.service.impl.UserServiceImpl;
import com.edu.ui.register.RegisterUI;
import com.edu.utils.MD5Utils;

public class LoginUI {

	/** 窗口标题 */
	private String title = "教学管理系统";
	private String welcome = "欢迎登陆教学管理系统";
	/** 窗口横坐标 */
	private Integer x;
	/** 窗口纵坐标 */
	private Integer y;
	/** 窗口宽度 */
	private Integer width;
	/** 窗口高度 */
	private Integer height;
	/** 窗口脚部的高度 */
	private Integer footerHeight;
	/** 北部的中间容器 */
	private JPanel north;
	/** 南部的中间容器 */
	private JPanel south;
	/** 中部的中间容器 */
	private JPanel center;
	/** 中部的北部中间容器 */
	private JPanel centerNorth;
	/** 中部的中部中间容器 */
	private JPanel centerCenter;
	/** 中部的北部的北部中间容器 */
	private JPanel centerNorthNorth;
	/** 中部的北部的中部中间容器 */
	private JPanel centerNorthCenter;
	/** 中部的北部的北部的西部中间容器 */
	private JPanel centerNorthNorthWest;
	/** 中部的北部的北部的中部中间容器 */
	private JPanel centerNorthNorthCenter;
	/** 中部的北部的中部的西部中间容器 */
	private JPanel centerNorthCenterWest;
	/** 中部的北部的中部的中部中间容器 */
	private JPanel centerNorthCenterCenter;
	/** 欢迎文字 */
	private JLabel labelWelcome;
	/** 用户名标签 */
	private JLabel labelName;
	/** 用户密码标签 */
	private JLabel labelPassword;
	/** 用户名文本框 */
	private JTextField textFieldName;
	/** 用户密码文本框 */
	private JPasswordField textFieldPassword;
	/** 登录按钮 */
	private JButton buttonLogin;
	/** 注册按钮 */
	private JButton buttonRegister;
	/** 下拉菜单 */
	private JComboBox<String> jComboBox;
	/** 登录窗体 */
	private JFrame loginFrame;
	/**用户业务层实例*/
	private UserService userService = new UserServiceImpl();
	public LoginUI() {
		// 初始化对象
		init();
		// 设置容器大小
		setDefaultSize();
		// 设置布局
		setDefaultLayout();
		// 设置背景色
		setBackGround();
		// 各种容器的添加操作
		add();
		// 设置透明
		setDefaultOpaque();
		// 设置默认的窗口样式
		setDefault();
		// 添加事件
		addListener();
	}

	/**
	 * 初始化组件与容器
	 */
	private void init() {
		loginFrame = new JFrame();
		north = new JPanel();
		south = new JPanel();
		center = new JPanel();
		centerNorthNorthWest = new JPanel();
		centerNorthNorthCenter = new JPanel();
		centerNorthCenterWest = new JPanel();
		centerNorthCenterCenter = new JPanel();
		centerNorthNorth = new JPanel();
		centerNorthCenter = new JPanel();
		centerNorth = new JPanel();
		centerCenter = new JPanel();
		labelWelcome = new JLabel(welcome);
		labelName = new JLabel("用户名：");
		labelPassword = new JLabel("密码：");
		textFieldName = new JTextField(20);
		textFieldPassword = new JPasswordField(20);
		textFieldPassword.setEchoChar('*');
		buttonLogin = new JButton("登录");
		buttonRegister = new JButton("注册");
		jComboBox = new JComboBox<String>();
	}

	/**
	 * 组件与容器的添加操作
	 */
	public void add() {
		// 将中间容器放到顶级容器上
		centerNorthNorthWest.add(labelName);
		centerNorthNorthCenter.add(textFieldName);
		centerNorthCenterWest.add(labelPassword);
		centerNorthCenterCenter.add(textFieldPassword);

		jComboBox.addItem("管理员");
		jComboBox.addItem("教师");
		jComboBox.addItem("学生");
		centerCenter.add(jComboBox);
		centerCenter.add(buttonLogin);
		centerCenter.add(buttonRegister);

		centerNorthNorth.add(centerNorthNorthWest, BorderLayout.WEST);
		centerNorthNorth.add(centerNorthNorthCenter, BorderLayout.CENTER);
		centerNorthCenter.add(centerNorthCenterWest, BorderLayout.WEST);
		centerNorthCenter.add(centerNorthCenterCenter, BorderLayout.CENTER);

		centerNorth.add(centerNorthNorth, BorderLayout.NORTH);
		centerNorth.add(centerNorthCenter, BorderLayout.CENTER);
		north.add(labelWelcome);
		center.add(centerNorth, BorderLayout.NORTH);
		center.add(centerCenter, BorderLayout.CENTER);
		loginFrame.add(north, BorderLayout.NORTH);
		loginFrame.add(south, BorderLayout.SOUTH);
		loginFrame.add(center, BorderLayout.CENTER);
	}

	/**
	 * 设置窗体大小
	 */
	private void setDefaultSize() {
		// 获取屏幕的大小
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = 600;
		height = 600;
		x = ((int) screensize.getWidth() - width) / 2;
		y = 300;
		footerHeight = 50;
		labelWelcome.setFont(new Font("楷体", Font.PLAIN, 50));
		labelName.setFont(new Font("楷体", Font.PLAIN, 30));
		labelPassword.setFont(new Font("楷体", Font.PLAIN, 30));
		textFieldName.setPreferredSize(new Dimension(200, 50));
		textFieldPassword.setPreferredSize(new Dimension(200, 50));
		textFieldName.setFont(new Font("楷体", Font.PLAIN, 30));
		textFieldPassword.setFont(new Font("楷体", Font.PLAIN, 30));
		buttonLogin.setPreferredSize(new Dimension(100, 60));
		buttonRegister.setPreferredSize(new Dimension(100, 60));
		jComboBox.setPreferredSize(new Dimension(160, 60));
		buttonLogin.setFont(new Font("楷体", Font.BOLD, 30));
		buttonRegister.setFont(new Font("楷体", Font.BOLD, 30));
		buttonLogin.setBorder(BorderFactory.createRaisedBevelBorder());
		buttonRegister.setBorder(BorderFactory.createRaisedBevelBorder());
		jComboBox.setFont(new Font("楷体", Font.BOLD, 30));
		centerNorthNorthWest
				.setPreferredSize(new Dimension(width / 4, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorthCenterWest
				.setPreferredSize(new Dimension(width / 4, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorthNorthCenter.setPreferredSize(
				new Dimension(width - width / 4 - 70, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorthCenterCenter.setPreferredSize(
				new Dimension(width - width / 4 - 70, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorthNorth.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorthCenter.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 4));
		centerNorth.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 2));
		centerCenter.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 2));
		north.setPreferredSize(new Dimension(width, (int) Math.ceil((height - footerHeight) * 0.3)));
		center.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7)));
		south.setPreferredSize(new Dimension(width, footerHeight));
	}

	/**
	 * 设置布局
	 */
	private void setDefaultLayout() {
		// 设置布局为边界布局
		centerNorthNorthWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorthCenterWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorth.setLayout(new BorderLayout());
		// centerCenter.setLayout(new BorderLayout());
		north.setLayout(new FlowLayout());
		center.setLayout(new BorderLayout());
		loginFrame.setLayout(new BorderLayout());
	}

	/**
	 * 设置背景
	 */
	private void setBackGround() {
		/*
		 * north.setBackground(Color.red); south.setBackground(Color.blue);
		 * centerNorth.setBackground(Color.GREEN);
		 * centerCenter.setBackground(Color.red);
		 * centerNorthNorthCenter.setBackground(Color.black);
		 * centerNorthCenterCenter.setBackground(Color.blue);
		 */
		ImageIcon image = new ImageIcon("images\\background_login.jpg");// 这是背景图片
		JLabel imgLabel = new JLabel();// 将背景图放在标签里。
		imgLabel.setBounds(0, 0, width, height);
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		imgLabel.setIcon(image);
		loginFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 设置透明
	 */
	private void setDefaultOpaque() {
		textFieldName.setOpaque(false);
		textFieldPassword.setOpaque(false);
		jComboBox.setOpaque(false);
		jComboBox.setUI(new BasicComboBoxUI() {
			public void installUI(JComponent comboBox) {
				super.installUI(comboBox);
				listBox.setForeground(Color.WHITE);
				listBox.setSelectionForeground(Color.BLACK);
				listBox.setFont(new Font("楷体", Font.PLAIN, 30));
			}

			/**
			 * 该方法返回右边的按钮
			 */
			protected JButton createArrowButton() {
				JButton jbutton = new JButton("选择");
				jbutton.setBackground(Color.WHITE);
				jbutton.setFont(new Font("楷体", Font.BOLD, 12));
				return jbutton;
			}
		});
		buttonLogin.setContentAreaFilled(false);
		buttonRegister.setContentAreaFilled(false);
		centerNorthNorthWest.setOpaque(false);
		centerNorthNorthCenter.setOpaque(false);
		centerNorthCenterWest.setOpaque(false);
		centerNorthCenterCenter.setOpaque(false);
		centerNorthNorth.setOpaque(false);
		centerNorthCenter.setOpaque(false);
		centerNorth.setOpaque(false);
		centerCenter.setOpaque(false);
		north.setOpaque(false);
		center.setOpaque(false);
		south.setOpaque(false);
		((JPanel) loginFrame.getContentPane()).setOpaque(false);
	}

	/**
	 * 设置窗体默认样式
	 */
	private void setDefault() {
		// 设置标题
		loginFrame.setTitle(title);
		// 设置坐标与窗口大小
		loginFrame.setBounds(x, y, width, height);
		// 设置窗体不能改变大小
		loginFrame.setResizable(false);
		// 设置窗口显示
		loginFrame.setVisible(true);
		// 设置点击x后的关闭方式为退出程序
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 添加事件
	 */
	private void addListener() {
		// 给登录按钮添加事件
		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = textFieldName.getText();
				char[] ch = textFieldPassword.getPassword();
				String password = new String(ch);
				try {
					User user = userService.queryByName(userName);
					if(user.getUserPassword().equals(MD5Utils.encode(password))){
						JOptionPane.showMessageDialog(loginFrame, "登录成功！！");
					}else {
						JOptionPane.showMessageDialog(loginFrame, "密码错误！！");
					}
				} catch (BizException e1) {
					JOptionPane.showMessageDialog(loginFrame, "用户不存在，请注册！！");
				} catch (Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		// 给注册按钮添加事件
		buttonRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(false);
				new RegisterUI(loginFrame);
			}
		});

	}
}
