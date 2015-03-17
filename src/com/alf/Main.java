package com.alf;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.service.AntiMoneyLaunderingDataService;

/**
 * 入口类。
 * 
 * @author Zhao Jinyan
 *
 */
public class Main {

	/**
	 * 主方法。
	 * 
	 * @param args
	 *            命令行参数
	 */
	public static void main(String[] args) {

		/* 初始化，如果失败，则结束程序。 */
		if (!initialize()) {
			System.out.println("程序初始化失败!");
			return;
		} else {
			System.out.println("程序初始化成功...");
		}

		/* 获取当天的反洗钱数据 */
		List<AntiMoneyLaunderingData> datas = service
				.queryAntiMoneyLaunderingData(new Date());

		/* 将返现前数据写入文件中 */
		file.addAntiMoneyLaunderingDatas(datas);

		/* 显示打印信息 */
		System.out.printf("共打印行数: %d\n", file.getLineNumber());

		/* 关闭程序 */
		close();
		System.out.println("程序结束.");
	}

	/**
	 * 初始化程序，并返回初始化结果。
	 * <ul>
	 * <li>true，初始化成功</li>
	 * <li>false，初始化失败</li>
	 * </ul>
	 * 
	 * @return 初始化结果
	 */
	public static boolean initialize() {
		boolean result = true;

		try {
			/* 初始化反洗钱文件读写类 */
			file = CreateAntiMoneyLaunderingDatasFile.create(getFileName());
			/* 初始化数据操作服务 */
			service = new AntiMoneyLaunderingDataService();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException e) {
			e.printStackTrace();
			result = false; // 发生错误
		}

		return result;
	}

	/**
	 * 关闭程序，释放资源。
	 */
	public static void close() {
		/* 关闭反洗钱文件读写类 */
		file.close();

		/* 关闭数据操作服务 */
		service.close();
	}

	/**
	 * 获取返现文件生成路径。
	 * 
	 * @return 文件路径
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
