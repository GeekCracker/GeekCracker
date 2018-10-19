package com.edu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

/***
 * @Description: MD5������
 * @version: v1.0.0
 * @author: չ��
 * @date: 2018��4��19�� ����4:02:34
 */
public class MD5Utils {

	/**
	 * ����
	 */
	public static String encode(String text) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(text.getBytes()); // ���ı����м���
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				int i = b & 0xff; // ȡ�ֽڵĺ��λ��Чֵ
				String s = Integer.toHexString(i);
				if (s.length() < 2) {
					s = "0" + s;
				}
				sb.append(s);
			}
			// ���ܵĽ��
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// �Ҳ������㷨,һ�㲻���������
			throw new RuntimeException(e);
		}
	}
}
