package com.edu.domain.response;

/**
* @Description: ϵͳ����״̬��
* @version: v1.0.0
* @author: չ��
* @date: 2018��4��13�� ����12:34:57
 */
public enum StatusCode {
	
	//-----------------��������״̬�뼰������ʾ--------------------------------
	
	// �ɹ�
	SUCESS(200, "�����ɹ�"), 

	// �������쳣
	INTERNAL_ERROR(500, "�������쳣,���Ժ�����..."),
	
	// ��������Դ�Ҳ���
	NOT_FOUND(404,"ҵ���쳣��Ҫ��������Դ������"),
	
	// ���������ݲ�����
	BASE_NOT_FOUND(404,"ҵ���쳣��Ҫ���������ݲ�����"),

	// �����쳣
	PARAMETER_ILLEGAL(-2, "��������쳣..."),
	
	MANAGER_TOKEN_VALIDATE(-3, "�û���Ϣ��ʧЧ,�����µ�¼"),
	SESSION_VALIDATE(-4, "session��ʧЧ,�����µ�¼"),
	
	// δ֪����
	UNKNOWN(-1, "ϵͳ��æ��������..."),
	
	ID_BLANK(1000 , "Ҫִ�д˲���,id��ֵ��Ϊ��"),
	
	UPLOAD_FILE_CONTENT_BLANK(1001 , "�ϴ����ļ�����Ϊ��"),
	
	TABLE_ANNOTATION_NOT_FOUND(1002 , "domainʵ���ϵ�tableע�ⲻ����,�����..."),
	
	QUERY_FIELDS_NOT_FOUND(1003 , "select�����û�в�ѯ���ֶ�"),
	
	QUERY_BY_ID_EXCEPTION(1003,"����id��ѯ���ݳ����쳣"),
	
	ENTITY_BLANK(1004,"Ҫִ�д˲���������Ķ�����Ϊ��"),
	
	INSERT_ENTITY_EXCEPTION(1005,"������ݶ���ʱ�����쳣"),
	
	INSERT_ENTITY_FIELD_NULL_EXCEPTION(1005,"������ݶ���ʱȱ����Ҫ��ӵ��ֶ�"),
	
	INSERT_ENTITY_VALUE_NULL_EXCEPTION(1005,"������ݶ���ʱȱ����Ҫ��ӵ��ֶ�ֵ"),
	
	INSERT_ALL_EXCEPTION(1006,"��������б�ʱ�����쳣"),
	
	UPDATE_ENTITY_EXCEPTION(1007,"����id�޸�����ʱ�����쳣"),
	
	UPDATE_ENTITY_ID_BLANK(1007,"����id�޸�ʱidΪ��"),
	
	DELETE_ENTITY_EXCEPTION(1007,"����idɾ������ʱ�����쳣"),
	
	
	USER_LOGIN_ERROR(1008,"�û������������"),
	
	CHANGE_IMAGE_SEQ_BLANK(1009,"�޸�ͼƬ������˳��ʱ��������Ϊ��"),
	CATEGORY_NAME_REPEAT(1010,"���������ظ�"),
	CATEGORY_NAME_NULL(1011,"��������Ϊ��"),
	CATEGORY_EXIST_SUB(1012,"�÷������ӷ����޷�ɾ��"),
	CATEGORY_USED(1013,"�÷�������ʹ���޷�ɾ��"),
	USER_NAME_NULL(1014,"�û���Ϊ��"),
	OLD_CODE_IS_NULL(1015,"������Ϊ��"),
	USER_NEW_PWD_NULL(1016,"������Ϊ��"),
	USER_PWD_SAME(1017,"�¾�����һ��"),
	PASSWORD_IS_NOT_TRUE(1018,"�������"),
	USER_CODE_IS_NULL(1019,"����Ϊ��"),
	USER_MAIL_ERROR(1120,"�����ʽ��������,����"),
	USER_PHONE_ERROR(1121,"�ֻ��Ÿ�ʽ��������,����"),
	USER_MAIL_EXIST(1122,"�������Ѿ�ע�ᣬ�뻻һ������"),
	USER_PHONE_EXIST(1123,"���ֻ������Ѿ�ע�ᣬ�뻻һ���ֻ�����"),
	USER_NAME_ERROR(1124,"�û�����������:����ĸ�����֡����֡��»��ߡ���������ҵ�һλ����������ĸ���ߺ��ֿ�ͷ������Ϊ2~18λ"),
	USER_PWD_ERROR(1125,"�������ʽ��������"),
	USER_NAME_EXIST(1126,"�û�����ע�������"),
	
	ARTICLE_TITLE_BLANK(1127,"���±��ⲻ��Ϊ��"),
	;
	private int code;
	
	private String message;
	
public static StatusCode setMemo(String tableName){
		
		switch (tableName) {
		case "t_category":
			return CATEGORY_NAME_REPEAT;
		default:
			break;
		}
		return UNKNOWN;
	}
	
	private StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
