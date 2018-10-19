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

	/** ������� */
	private String title = "��ѧ����ϵͳ";
	/** ��������� */
	private Integer x;
	/** ���������� */
	private Integer y;
	/** ������ */
	private Integer width;
	/** ����߶� */
	private Integer height;
	/** ����Ų��ĸ߶� */
	private Integer footerHeight;
	/** �������м����� */
	private JPanel north;
	/** �в����м����� */
	private JPanel center;
	/** �ϲ����м����� */
	private JPanel south;
	/** �в��ı����м����� */
	private JPanel centerNorth;
	/** �в����в��м����� */
	private JPanel centerCenter;

	private JPanel centerNorthNorth;
	private JPanel centerNorthCenter;
	private JPanel centerNorthSouth;

	/** �в��ı����ı����������м����� */
	private JPanel centerNorthNorthWest;
	/** �в��ı����ı������в��м����� */
	private JPanel centerNorthNorthCenter;
	/** �в��ı������в��������м����� */
	private JPanel centerNorthCenterWest;
	/** �в��ı������в����в��м����� */
	private JPanel centerNorthCenterCenter;
	/** �в��ı������ϲ��������м����� */
	private JPanel centerNorthSouthWest;
	/** �в��ı������ϲ����в��м����� */
	private JPanel centerNorthSouthCenter;

	/** ��ӭ���� */
	private JLabel labelWelcome;
	/** �û�����ǩ */
	private JLabel labelName;
	/** �û������ǩ����һ������ */
	private JLabel labelPasswordFirst;
	/** �û������ǩ���ڶ������� */
	private JLabel labelPasswordSeconds;
	/** �û����ı��� */
	private JTextField textFieldName;
	/** �û������ı��򣬵�һ������ */
	private JPasswordField textFieldPasswordFirst;
	/** �û������ı��򣬵ڶ������� */
	private JPasswordField textFieldPasswordSeconds;
	/** �����˵� */
	private JComboBox<String> jComboBox;
	/** ע�ᰴť */
	private JButton buttonRegister;
	/** ȡ����ť */
	private JButton buttonCancel;
	/** ע����洰�� */
	private JFrame registerFrame;
	/** ��¼���洰�� */
	private JFrame loginFrame;
	/**��ɫҵ���ʵ��*/
	private RoleService roleService = new RoleServiceImpl();
	/**�û�ҵ���ʵ��*/
	private UserService userService = new UserServiceImpl();
	public RegisterUI(JFrame loginFrame) {
		this.loginFrame = loginFrame;
		// ��ʼ������
		init();
		// ���������С
		setDefaultSize();
		// ����Ĭ�ϲ���
		setDefaultLayout();
		// ���ñ���
		setBackGround();
		// ����͸��
		setDefaultOpaque();
		// �������Ӳ���
		add();
		// ���ô���Ĭ����ʽ
		setDefault();
		// ��Ӽ����¼�
		addListener();
	}

	/**
	 * ��ʼ������
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

		labelWelcome = new JLabel("��ӭע��");
		labelName = new JLabel("�û�����");
		labelPasswordFirst = new JLabel("���������룺");
		labelPasswordSeconds = new JLabel("���ٴ��������룺");

		textFieldName = new JTextField(20);
		textFieldPasswordFirst = new JPasswordField(20);
		textFieldPasswordFirst.setEchoChar('*');
		textFieldPasswordSeconds = new JPasswordField(20);
		textFieldPasswordSeconds.setEchoChar('*');
		buttonRegister = new JButton("ע��");
		buttonCancel = new JButton("ȡ��");
		jComboBox = new JComboBox<String>();

	}

	/**
	 * �������ӵ������Ĳ���
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

		jComboBox.addItem("����Ա");
		jComboBox.addItem("��ʦ");
		jComboBox.addItem("ѧ��");
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
	 * ����Ĭ�ϴ�С
	 */
	private void setDefaultSize() {
		// ��ȡ��Ļ�Ĵ�С
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = 500;
		height = 520;
		x = ((int) screensize.getWidth() - width) / 2;
		y = 300;
		footerHeight = 50;
		labelWelcome.setFont(new Font("����", Font.PLAIN, 50));
		labelName.setFont(new Font("����", Font.PLAIN, 25));
		labelPasswordFirst.setFont(new Font("����", Font.PLAIN, 25));
		labelPasswordSeconds.setFont(new Font("����", Font.PLAIN, 25));
		textFieldName.setPreferredSize(new Dimension(180, 50));
		textFieldPasswordFirst.setPreferredSize(new Dimension(180, 50));
		textFieldPasswordSeconds.setPreferredSize(new Dimension(180, 50));
		textFieldName.setFont(new Font("����", Font.PLAIN, 30));
		textFieldPasswordFirst.setFont(new Font("����", Font.PLAIN, 30));
		textFieldPasswordSeconds.setFont(new Font("����", Font.PLAIN, 30));

		jComboBox.setFont(new Font("����", Font.BOLD, 30));
		jComboBox.setPreferredSize(new Dimension(160, 60));
		buttonRegister.setPreferredSize(new Dimension(100, 60));
		buttonCancel.setPreferredSize(new Dimension(100, 60));
		buttonRegister.setFont(new Font("����", Font.BOLD, 30));
		buttonCancel.setFont(new Font("����", Font.BOLD, 30));

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
	 * ����Ĭ�ϵĲ��ָ�ʽ
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
	 * ����Ĭ�ϵı���
	 */
	private void setBackGround() {
		ImageIcon image = new ImageIcon("images\\background_login.jpg");// ���Ǳ���ͼƬ
		JLabel imgLabel = new JLabel();// ������ͼ���ڱ�ǩ�
		imgLabel.setBounds(0, 0, width, height);
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		imgLabel.setIcon(image);
		registerFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * �������͸��
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
				listBox.setFont(new Font("����", Font.PLAIN, 30));
			}
			/**
			 * �÷��������ұߵİ�ť
			 */
			protected JButton createArrowButton() {
				JButton jbutton = new JButton("ѡ��");
				jbutton.setBackground(Color.WHITE);
				jbutton.setFont(new Font("����", Font.BOLD, 12));
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
	 * ���ô���Ĭ����ʽ
	 */
	private void setDefault() {
		// ���ô������
		registerFrame.setTitle(title);
		// ���ô���λ�ü���С
		registerFrame.setBounds(x, y, width, height);
		// ���ò��ı䴰���С
		registerFrame.setResizable(false);
		// ���ô���Ϊ��ʾ״̬
		registerFrame.setVisible(true);
		// ���ô���Ĭ�Ϲرշ�ʽ
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
					sb.append("�������û���������");
				}
				if(sb.length()==0 && StringUtils.isBlank(userPasswordFirst)){
					sb.append("�������û����룡����");
				}
				if(sb.length()==0 && StringUtils.isBlank(userPasswordSeconds)){
					sb.append("���ٴ��������룡����");
				}
				if(sb.length()==0 && !userPasswordFirst.equals(userPasswordSeconds)){
					sb.append("�����������벻һ�£�����");
				}
				if(sb.length() > 0){
					JOptionPane.showMessageDialog(registerFrame, sb.toString());
				}else {
					try {
						userService.queryByName(userName);
						JOptionPane.showMessageDialog(registerFrame,"�û����Ѵ��ڣ�����");
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
