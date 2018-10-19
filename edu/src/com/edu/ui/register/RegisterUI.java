package com.edu.ui.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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

import org.apache.commons.lang3.StringUtils;

import com.edu.domain.entity.Role;
import com.edu.domain.entity.User;
import com.edu.domain.response.StatusCode;
import com.edu.exception.BizException;
import com.edu.service.RoleService;
import com.edu.service.UserService;
import com.edu.service.impl.RoleServiceImpl;
import com.edu.service.impl.UserServiceImpl;
import com.edu.utils.MD5Utils;

public class RegisterUI {

	/** 窗体标题 */
	private String title = "教学管理系统";
	/** 窗体横坐标 */
	private Integer x;
	/** 窗体纵坐标 */
	private Integer y;
	/** 窗体宽度 */
	private Integer width;
	/** 窗体高度 */
	private Integer height;
	/** 窗体脚部的高度 */
	private Integer footerHeight;
	/** 北部的中间容器 */
	private JPanel north;
	/** 中部的中间容器 */
	private JPanel center;
	/** 南部的中间容器 */
	private JPanel south;
	/** 中部的北部中间容器 */
	private JPanel centerNorth;
	/** 中部的中部中间容器 */
	private JPanel centerCenter;

	private JPanel centerNorthNorth;
	private JPanel centerNorthCenter;
	private JPanel centerNorthSouth;

	/** 中部的北部的北部的西部中间容器 */
	private JPanel centerNorthNorthWest;
	/** 中部的北部的北部的中部中间容器 */
	private JPanel centerNorthNorthCenter;
	/** 中部的北部的中部的西部中间容器 */
	private JPanel centerNorthCenterWest;
	/** 中部的北部的中部的中部中间容器 */
	private JPanel centerNorthCenterCenter;
	/** 中部的北部的南部的西部中间容器 */
	private JPanel centerNorthSouthWest;
	/** 中部的北部的南部的中部中间容器 */
	private JPanel centerNorthSouthCenter;

	/** 欢迎文字 */
	private JLabel labelWelcome;
	/** 用户名标签 */
	private JLabel labelName;
	/** 用户密码标签，第一次输入 */
	private JLabel labelPasswordFirst;
	/** 用户密码标签，第二次输入 */
	private JLabel labelPasswordSeconds;
	/** 用户名文本框 */
	private JTextField textFieldName;
	/** 用户密码文本框，第一次输入 */
	private JPasswordField textFieldPasswordFirst;
	/** 用户密码文本框，第二次输入 */
	private JPasswordField textFieldPasswordSeconds;
	/** 下拉菜单 */
	private JComboBox<String> jComboBox;
	/** 注册按钮 */
	private JButton buttonRegister;
	/** 取消按钮 */
	private JButton buttonCancel;
	/** 注册界面窗体 */
	private JFrame registerFrame;
	/** 登录界面窗体 */
	private JFrame loginFrame;
	/**角色业务层实例*/
	private RoleService roleService = new RoleServiceImpl();
	/**用户业务层实例*/
	private UserService userService = new UserServiceImpl();
	public RegisterUI(JFrame loginFrame) {
		this.loginFrame = loginFrame;
		// 初始化对象
		init();
		// 设置组件大小
		setDefaultSize();
		// 设置默认布局
		setDefaultLayout();
		// 设置背景
		setBackGround();
		// 设置透明
		setDefaultOpaque();
		// 组件的添加操作
		add();
		// 设置窗体默认样式
		setDefault();
		// 添加监听事件
		addListener();
	}

	/**
	 * 初始化对象
	 */
	private void init() {
		registerFrame = new JFrame();
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		centerNorth = new JPanel();
		centerCenter = new JPanel();
		centerNorthNorth = new JPanel();
		centerNorthCenter = new JPanel();
		centerNorthSouth = new JPanel();
		centerNorthNorthWest = new JPanel();
		centerNorthNorthCenter = new JPanel();
		centerNorthCenterWest = new JPanel();
		centerNorthCenterCenter = new JPanel();
		centerNorthSouthWest = new JPanel();
		centerNorthSouthCenter = new JPanel();

		labelWelcome = new JLabel("欢迎注册");
		labelName = new JLabel("用户名：");
		labelPasswordFirst = new JLabel("请输入密码：");
		labelPasswordSeconds = new JLabel("请再次输入密码：");

		textFieldName = new JTextField(20);
		textFieldPasswordFirst = new JPasswordField(20);
		textFieldPasswordFirst.setEchoChar('*');
		textFieldPasswordSeconds = new JPasswordField(20);
		textFieldPasswordSeconds.setEchoChar('*');
		buttonRegister = new JButton("注册");
		buttonCancel = new JButton("取消");
		jComboBox = new JComboBox<String>();

	}

	/**
	 * 将组件添加到容器的操作
	 */
	private void add() {

		north.add(labelWelcome);

		centerNorthNorthWest.add(labelName);
		centerNorthNorthCenter.add(textFieldName);

		centerNorthCenterWest.add(labelPasswordFirst);
		centerNorthCenterCenter.add(textFieldPasswordFirst);

		centerNorthSouthWest.add(labelPasswordSeconds);
		centerNorthSouthCenter.add(textFieldPasswordSeconds);

		centerNorthNorth.add(centerNorthNorthWest, BorderLayout.WEST);
		centerNorthNorth.add(centerNorthNorthCenter, BorderLayout.CENTER);
		centerNorthCenter.add(centerNorthCenterWest, BorderLayout.WEST);
		centerNorthCenter.add(centerNorthCenterCenter, BorderLayout.CENTER);
		centerNorthSouth.add(centerNorthSouthWest, BorderLayout.WEST);
		centerNorthSouth.add(centerNorthSouthCenter, BorderLayout.CENTER);

		centerNorth.add(centerNorthNorth, BorderLayout.NORTH);
		centerNorth.add(centerNorthCenter, BorderLayout.CENTER);
		centerNorth.add(centerNorthSouth, BorderLayout.SOUTH);

		jComboBox.addItem("管理员");
		jComboBox.addItem("教师");
		jComboBox.addItem("学生");
		centerCenter.add(jComboBox);
		centerCenter.add(buttonRegister);
		centerCenter.add(buttonCancel);
		center.add(centerNorth, BorderLayout.NORTH);
		center.add(centerCenter, BorderLayout.CENTER);
		registerFrame.add(north, BorderLayout.NORTH);
		registerFrame.add(center, BorderLayout.CENTER);
		registerFrame.add(south, BorderLayout.SOUTH);
	}

	/**
	 * 设置默认大小
	 */
	private void setDefaultSize() {
		// 获取屏幕的大小
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = 500;
		height = 520;
		x = ((int) screensize.getWidth() - width) / 2;
		y = 300;
		footerHeight = 50;
		labelWelcome.setFont(new Font("楷体", Font.PLAIN, 50));
		labelName.setFont(new Font("楷体", Font.PLAIN, 25));
		labelPasswordFirst.setFont(new Font("楷体", Font.PLAIN, 25));
		labelPasswordSeconds.setFont(new Font("楷体", Font.PLAIN, 25));
		textFieldName.setPreferredSize(new Dimension(180, 50));
		textFieldPasswordFirst.setPreferredSize(new Dimension(180, 50));
		textFieldPasswordSeconds.setPreferredSize(new Dimension(180, 50));
		textFieldName.setFont(new Font("楷体", Font.PLAIN, 30));
		textFieldPasswordFirst.setFont(new Font("楷体", Font.PLAIN, 30));
		textFieldPasswordSeconds.setFont(new Font("楷体", Font.PLAIN, 30));

		jComboBox.setFont(new Font("楷体", Font.BOLD, 30));
		jComboBox.setPreferredSize(new Dimension(160, 60));
		buttonRegister.setPreferredSize(new Dimension(100, 60));
		buttonCancel.setPreferredSize(new Dimension(100, 60));
		buttonRegister.setFont(new Font("楷体", Font.BOLD, 30));
		buttonCancel.setFont(new Font("楷体", Font.BOLD, 30));

		centerNorthNorthWest
				.setPreferredSize(new Dimension(width / 3 + 20, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthCenterWest
				.setPreferredSize(new Dimension(width / 3 + 20, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthSouthWest
				.setPreferredSize(new Dimension(width / 3 + 20, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthNorthCenter.setPreferredSize(
				new Dimension(width - width / 3, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthCenterCenter.setPreferredSize(
				new Dimension(width - width / 3, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthSouthCenter.setPreferredSize(
				new Dimension(width - width / 3, (int) Math.floor((height - footerHeight) * 0.7) / 6));

		centerNorthNorth.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthCenter.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 6));
		centerNorthSouth.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 6));

		centerNorth.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 2));
		centerCenter.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7) / 2));

		north.setPreferredSize(new Dimension(width, (int) Math.ceil((height - footerHeight) * 0.3)));
		center.setPreferredSize(new Dimension(width, (int) Math.floor((height - footerHeight) * 0.7)));
		south.setPreferredSize(new Dimension(width, footerHeight));
	}

	/**
	 * 设置默认的布局格式
	 */
	private void setDefaultLayout() {
		registerFrame.setLayout(new BorderLayout());
		north.setLayout(new FlowLayout());
		center.setLayout(new BorderLayout());
		centerNorth.setLayout(new BorderLayout());
		centerNorthNorth.setLayout(new BorderLayout());
		centerNorthCenter.setLayout(new BorderLayout());
		centerNorthSouth.setLayout(new BorderLayout());
		centerNorthNorthWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorthCenterWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorthSouthWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
	}

	/**
	 * 设置默认的背景
	 */
	private void setBackGround() {
		ImageIcon image = new ImageIcon("images\\background_login.jpg");// 这是背景图片
		JLabel imgLabel = new JLabel();// 将背景图放在标签里。
		imgLabel.setBounds(0, 0, width, height);
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		imgLabel.setIcon(image);
		registerFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * 设置组件透明
	 */
	private void setDefaultOpaque() {

		textFieldName.setOpaque(false);
		textFieldPasswordFirst.setOpaque(false);
		textFieldPasswordSeconds.setOpaque(false);
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
		buttonRegister.setContentAreaFilled(false);
		buttonCancel.setContentAreaFilled(false);
		centerNorthNorthWest.setOpaque(false);
		centerNorthNorthCenter.setOpaque(false);
		centerNorthCenterWest.setOpaque(false);
		centerNorthCenterCenter.setOpaque(false);
		centerNorthSouthWest.setOpaque(false);
		centerNorthSouthCenter.setOpaque(false);
		centerNorthNorth.setOpaque(false);
		centerNorthCenter.setOpaque(false);
		centerNorthSouth.setOpaque(false);
		centerNorth.setOpaque(false);
		centerCenter.setOpaque(false);
		north.setOpaque(false);
		center.setOpaque(false);
		south.setOpaque(false);
		((JPanel) registerFrame.getContentPane()).setOpaque(false);
	}

	/**
	 * 设置窗体默认样式
	 */
	private void setDefault() {
		// 设置窗体标题
		registerFrame.setTitle(title);
		// 设置窗体位置及大小
		registerFrame.setBounds(x, y, width, height);
		// 设置不改变窗体大小
		registerFrame.setResizable(false);
		// 设置窗体为显示状态
		registerFrame.setVisible(true);
		// 设置窗体默认关闭方式
		registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void addListener() {
		buttonRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = textFieldName.getText();
				char[] chPwdFirst = textFieldPasswordFirst.getPassword();
				char[] chPwdSeconds = textFieldPasswordSeconds.getPassword();
				
				String userPasswordFirst = new String(chPwdFirst);
				String userPasswordSeconds = new String(chPwdSeconds);
				
				StringBuilder sb = new StringBuilder("");
				if(sb.length()==0 && StringUtils.isBlank(userName)){
					sb.append("请输入用户名！！！");
				}
				if(sb.length()==0 && StringUtils.isBlank(userPasswordFirst)){
					sb.append("请输入用户密码！！！");
				}
				if(sb.length()==0 && StringUtils.isBlank(userPasswordSeconds)){
					sb.append("请再次输入密码！！！");
				}
				if(sb.length()==0 && !userPasswordFirst.equals(userPasswordSeconds)){
					sb.append("两次密码输入不一致！！！");
				}
				if(sb.length() > 0){
					JOptionPane.showMessageDialog(registerFrame, sb.toString());
				}else {
					try {
						userService.queryByName(userName);
						JOptionPane.showMessageDialog(registerFrame,"用户名已存在！！！");
					} catch (BizException e2) {
						String roleName = jComboBox.getSelectedItem().toString();
						User user = new User();
						Role role = null;
						try {
							role = roleService.queryByName(roleName);
							user.setRoleId(role.getId());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						user.setUserName(userName);
						user.setUserPassword(MD5Utils.encode(userPasswordFirst));
						Date date = new Date();
						user.setCreatedTime(date);
						user.setUpdatedTime(date);
						user.setDeleted(true);
						userService.save(user);
					} catch (Exception e2){
						throw new BizException(StatusCode.UNKNOWN);
					}
				}
			}
		});
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		});
		registerFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				registerFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		});
	}
}
