package com.alf;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.service.AntiMoneyLaunderingDataService;

/**
 * ����ࡣ
 * 
 * @author Zhao Jinyan
 *
 */
public class Main {

	/**
	 * ��������
	 * 
	 * @param args
	 *            �����в���
	 */
	public static void main(String[] args) {

		/* ��ʼ�������ʧ�ܣ���������� */
		if (!initialize()) {
			System.out.println("�����ʼ��ʧ��!");
			return;
		} else {
			System.out.println("�����ʼ���ɹ�...");
		}

		/* ��ȡ����ķ�ϴǮ���� */
		List<AntiMoneyLaunderingData> datas = service
				.queryAntiMoneyLaunderingData(new Date());

		/* ������ǰ����д���ļ��� */
		file.addAntiMoneyLaunderingDatas(datas);

		/* ��ʾ��ӡ��Ϣ */
		System.out.printf("����ӡ����: %d\n", file.getLineNumber());

		/* �رճ��� */
		close();
		System.out.println("�������.");
	}

	/**
	 * ��ʼ�����򣬲����س�ʼ�������
	 * <ul>
	 * <li>true����ʼ���ɹ�</li>
	 * <li>false����ʼ��ʧ��</li>
	 * </ul>
	 * 
	 * @return ��ʼ�����
	 */
	public static boolean initialize() {
		boolean result = true;

		try {
			/* ��ʼ����ϴǮ�ļ���д�� */
			file = CreateAntiMoneyLaunderingDatasFile.create(getFileName());
			/* ��ʼ�����ݲ������� */
			service = new AntiMoneyLaunderingDataService();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException e) {
			e.printStackTrace();
			result = false; // ��������
		}

		return result;
	}

	/**
	 * �رճ����ͷ���Դ��
	 */
	public static void close() {
		/* �رշ�ϴǮ�ļ���д�� */
		file.close();

		/* �ر����ݲ������� */
		service.close();
	}

	/**
	 * ��ȡ�����ļ�����·����
	 * 
	 * @return �ļ�·��
	 */
	public static String getFileName() {
		String path = "D:\\ZSSJYLS_JGM_%s.TXT";
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		path = String.format(path, dateFormat.format(new Date()));
		return path;
	}

	private static AntiMoneyLaunderingDatesFile file;
	private static AntiMoneyLaunderingDataService service;
}
