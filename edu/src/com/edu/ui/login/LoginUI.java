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

	/** ���ڱ��� */
	private String title = "��ѧ����ϵͳ";
	private String welcome = "��ӭ��½��ѧ����ϵͳ";
	/** ���ں����� */
	private Integer x;
	/** ���������� */
	private Integer y;
	/** ���ڿ�� */
	private Integer width;
	/** ���ڸ߶� */
	private Integer height;
	/** ���ڽŲ��ĸ߶� */
	private Integer footerHeight;
	/** �������м����� */
	private JPanel north;
	/** �ϲ����м����� */
	private JPanel south;
	/** �в����м����� */
	private JPanel center;
	/** �в��ı����м����� */
	private JPanel centerNorth;
	/** �в����в��м����� */
	private JPanel centerCenter;
	/** �в��ı����ı����м����� */
	private JPanel centerNorthNorth;
	/** �в��ı������в��м����� */
	private JPanel centerNorthCenter;
	/** �в��ı����ı����������м����� */
	private JPanel centerNorthNorthWest;
	/** �в��ı����ı������в��м����� */
	private JPanel centerNorthNorthCenter;
	/** �в��ı������в��������м����� */
	private JPanel centerNorthCenterWest;
	/** �в��ı������в����в��м����� */
	private JPanel centerNorthCenterCenter;
	/** ��ӭ���� */
	private JLabel labelWelcome;
	/** �û�����ǩ */
	private JLabel labelName;
	/** �û������ǩ */
	private JLabel labelPassword;
	/** �û����ı��� */
	private JTextField textFieldName;
	/** �û������ı��� */
	private JPasswordField textFieldPassword;
	/** ��¼��ť */
	private JButton buttonLogin;
	/** ע�ᰴť */
	private JButton buttonRegister;
	/** �����˵� */
	private JComboBox<String> jComboBox;
	/** ��¼���� */
	private JFrame loginFrame;
	/**�û�ҵ���ʵ��*/
	private UserService userService = new UserServiceImpl();
	public LoginUI() {
		// ��ʼ������
		init();
		// ����������С
		setDefaultSize();
		// ���ò���
		setDefaultLayout();
		// ���ñ���ɫ
		setBackGround();
		// ������������Ӳ���
		add();
		// ����͸��
		setDefaultOpaque();
		// ����Ĭ�ϵĴ�����ʽ
		setDefault();
		// ����¼�
		addListener();
	}

	/**
	 * ��ʼ�����������
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
		labelName = new JLabel("�û�����");
		labelPassword = new JLabel("���룺");
		textFieldName = new JTextField(20);
		textFieldPassword = new JPasswordField(20);
		textFieldPassword.setEchoChar('*');
		buttonLogin = new JButton("��¼");
		buttonRegister = new JButton("ע��");
		jComboBox = new JComboBox<String>();
	}

	/**
	 * �������������Ӳ���
	 */
	public void add() {
		// ���м������ŵ�����������
		centerNorthNorthWest.add(labelName);
		centerNorthNorthCenter.add(textFieldName);
		centerNorthCenterWest.add(labelPassword);
		centerNorthCenterCenter.add(textFieldPassword);

		jComboBox.addItem("����Ա");
		jComboBox.addItem("��ʦ");
		jComboBox.addItem("ѧ��");
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
	 * ���ô����С
	 */
	private void setDefaultSize() {
		// ��ȡ��Ļ�Ĵ�С
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = 600;
		height = 600;
		x = ((int) screensize.getWidth() - width) / 2;
		y = 300;
		footerHeight = 50;
		labelWelcome.setFont(new Font("����", Font.PLAIN, 50));
		labelName.setFont(new Font("����", Font.PLAIN, 30));
		labelPassword.setFont(new Font("����", Font.PLAIN, 30));
		textFieldName.setPreferredSize(new Dimension(200, 50));
		textFieldPassword.setPreferredSize(new Dimension(200, 50));
		textFieldName.setFont(new Font("����", Font.PLAIN, 30));
		textFieldPassword.setFont(new Font("����", Font.PLAIN, 30));
		buttonLogin.setPreferredSize(new Dimension(100, 60));
		buttonRegister.setPreferredSize(new Dimension(100, 60));
		jComboBox.setPreferredSize(new Dimension(160, 60));
		buttonLogin.setFont(new Font("����", Font.BOLD, 30));
		buttonRegister.setFont(new Font("����", Font.BOLD, 30));
		buttonLogin.setBorder(BorderFactory.createRaisedBevelBorder());
		buttonRegister.setBorder(BorderFactory.createRaisedBevelBorder());
		jComboBox.setFont(new Font("����", Font.BOLD, 30));
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
	 * ���ò���
	 */
	private void setDefaultLayout() {
		// ���ò���Ϊ�߽粼��
		centerNorthNorthWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorthCenterWest.setLayout(new FlowLayout(FlowLayout.RIGHT));
		centerNorth.setLayout(new BorderLayout());
		// centerCenter.setLayout(new BorderLayout());
		north.setLayout(new FlowLayout());
		center.setLayout(new BorderLayout());
		loginFrame.setLayout(new BorderLayout());
	}

	/**
	 * ���ñ���
	 */
	private void setBackGround() {
		/*
		 * north.setBackground(Color.red); south.setBackground(Color.blue);
		 * centerNorth.setBackground(Color.GREEN);
		 * centerCenter.setBackground(Color.red);
		 * centerNorthNorthCenter.setBackground(Color.black);
		 * centerNorthCenterCenter.setBackground(Color.blue);
		 */
		ImageIcon image = new ImageIcon("images\\background_login.jpg");// ���Ǳ���ͼƬ
		JLabel imgLabel = new JLabel();// ������ͼ���ڱ�ǩ�
		imgLabel.setBounds(0, 0, width, height);
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		imgLabel.setIcon(image);
		loginFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	}

	/**
	 * ����͸��
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
	 * ���ô���Ĭ����ʽ
	 */
	private void setDefault() {
		// ���ñ���
		loginFrame.setTitle(title);
		// ���������봰�ڴ�С
		loginFrame.setBounds(x, y, width, height);
		// ���ô��岻�ܸı��С
		loginFrame.setResizable(false);
		// ���ô�����ʾ
		loginFrame.setVisible(true);
		// ���õ��x��Ĺرշ�ʽΪ�˳�����
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ����¼�
	 */
	private void addListener() {
		// ����¼��ť����¼�
		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = textFieldName.getText();
				char[] ch = textFieldPassword.getPassword();
				String password = new String(ch);
				try {
					User user = userService.queryByName(userName);
					if(user.getUserPassword().equals(MD5Utils.encode(password))){
						JOptionPane.showMessageDialog(loginFrame, "��¼�ɹ�����");
					}else {
						JOptionPane.showMessageDialog(loginFrame, "������󣡣�");
					}
				} catch (BizException e1) {
					JOptionPane.showMessageDialog(loginFrame, "�û������ڣ���ע�ᣡ��");
				} catch (Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		// ��ע�ᰴť����¼�
		buttonRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginFrame.setVisible(false);
				new RegisterUI(loginFrame);
			}
		});

	}
}
