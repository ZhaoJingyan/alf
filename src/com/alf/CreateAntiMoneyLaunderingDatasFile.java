package com.alf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;

/**
 * 生成反洗钱文件。如果文件存在，则覆盖。
 * 
 * @author Zhao Jinyan
 *
 */
public class CreateAntiMoneyLaunderingDatasFile {

	/**
	 * 创建默认反洗钱文件。
	 * 
	 * @param path
	 *            反洗钱文件的路径
	 * @throws IOException
	 *             IO错误
	 * @throws ClassNotFoundException
	 *             找不到实现接口CellFormat的对象
	 * @throws IllegalAccessException
	 *             反射错误
	 * @throws InstantiationException
	 *             反射错误
	 */
	public static AntiMoneyLaunderingDatesFile create(String path)
			throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		return new DefaultAntiMoneyLaunderingDatesFile(path);
	}

	/**
	 * 默认反洗钱文件。
	 * 
	 * @author Zhao Jinyan
	 * @since {@link Comparable.alf.AntiMoneyLaunderingDatesFile}
	 *
	 */
	private static class DefaultAntiMoneyLaunderingDatesFile implements
			AntiMoneyLaunderingDatesFile {

		/**
		 * 初始化函数。
		 * 
		 * @param path 反洗钱文件路径
		 * @throws IOException IO异常
		 * @throws ClassNotFoundException 反射异常
		 * @throws IllegalAccessException 反射异常
		 * @throws InstantiationException 反射异常
		 */
		public DefaultAntiMoneyLaunderingDatesFile(String path)
				throws IOException, ClassNotFoundException,
				InstantiationException, IllegalAccessException {
			initializeIO(path); // 初始化读写IO。
			initializeConfiguration(); // 初始化反洗格式配置文件。
		}

		/**
		 * 在文件中添加一条AntiMoneyLaunderingData记录。
		 * 
		 * @param data
		 *            一条反些钱记录
		 */
		@Override
		public void addAntiMoneyLaunderingData(AntiMoneyLaunderingData data) {
			try {
				printline(data); // 将data转换为字符串数据，存入缓存。
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			write(); // 将缓存中的数据写入文件，并清空缓存。
		}

		/**
		 * 在文件中添加多条AntiMoneyLaunderingData记录。
		 * 
		 * @param datas
		 *            多条反洗钱记录
		 */
		@Override
		public void addAntiMoneyLaunderingDatas(
				List<AntiMoneyLaunderingData> datas) {
			try {
				for (AntiMoneyLaunderingData data : datas) {
					printline(data); // 将data转换为字符串数据，存入缓存。
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			write(); // 将缓存中的数据写入文件，并清空缓存。
		}

		/**
		 * 关闭文件IO。
		 */
		@Override
		public void close() {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 初始化系统读写IO。
		 * 
		 * @throws IOException
		 */
		private final void initializeIO(String path) throws IOException {
			writer = new BufferedWriter(new FileWriter(new File(path)));
			buffer = new StringBuffer();
			lineNumber = 0L;
			bufferLineNumber = 0L;
		}

		/**
		 * 初始化反洗格式配置文件。
		 * 
		 * @throws ClassNotFoundException
		 *             找不到单元格式类
		 * @throws IllegalAccessException
		 *             反射错误
		 * @throws InstantiationException
		 *             反射错误
		 */
		private final void initializeConfiguration()
				throws ClassNotFoundException, InstantiationException,
				IllegalAccessException {
			Class<?> clazz = AntiMoneyLaunderingData.class;
			cellFormat = getCellFormatFromAnnotation(clazz);
			separtor = getSepartorFromAnnotation(clazz);
			lineBreak = getLineBreakFromAnnotation(clazz);
			sequence = getSequenceFromAnnotation(clazz);
		}

		/**
		 * 
		 * @param clazz
		 *            提取注解的Class对象
		 * @return 单元格式
		 * @throws ClassNotFoundException
		 *             找不到单元格式类
		 * @throws InstantiationException
		 *             反射错误
		 * @throws IllegalAccessException
		 *             反射错误
		 */
		private final CellFormat getCellFormatFromAnnotation(Class<?> clazz)
				throws ClassNotFoundException, InstantiationException,
				IllegalAccessException {
			Format format = clazz.getAnnotation(Format.class);
			Class<?> cellFormatClass = Class.forName(format.value());
			cellFormat = (CellFormat) cellFormatClass.newInstance();
			return cellFormat;
		}

		/**
		 * 单元格式
		 */
		private CellFormat cellFormat;

		/**
		 * 通过注解，返回调用顺序。
		 * 
		 * @param clazz
		 *            提取注解的Class对象
		 * @return 返回调用顺序
		 * @since {@link com.alf.Index}
		 */
		private final List<SequenceCell> getSequenceFromAnnotation(
				Class<?> clazz) {
			Method[] methods = clazz.getMethods();
			LinkedList<SequenceCell> result = new LinkedList<SequenceCell>();

			/* 获取标注了Index注解的方法 */
			for (Method method : methods) {
				Index indexAnnotation = method.getAnnotation(Index.class);
				if (indexAnnotation != null)
					result.add(new SequenceCell(method, indexAnnotation.value()));
			}

			Collections.sort(result); // 排序

			return result;
		}

		/**
		 * 字段顺序
		 */
		private List<SequenceCell> sequence;

		/**
		 * 存储被调用的方法，和该方法的执行序号。 实现了Comparable接口，可是实现List<SequenceCell>的排序。
		 * 
		 * @author Zhao Jinyan
		 *
		 */
		private class SequenceCell implements Comparable<Object> {

			/**
			 * 构造方法。
			 * 
			 * @param method
			 *            方法
			 * @param index
			 *            方法对应的执行序号
			 */
			public SequenceCell(Method method, int index) {
				this.method = method;
				this.index = index;
			}

			/**
			 * 方法
			 */
			private Method method;

			/**
			 * 序号
			 */
			private int index;

			/**
			 * 排序，Collections.sort(List)内部回调。
			 * 
			 * @param object
			 *            被比较对象
			 * @return 权值差
			 */
			@Override
			public int compareTo(Object object) {
				SequenceCell sequenceCell = (SequenceCell) object;
				return index - sequenceCell.getIndex();
			}

			/**
			 * 获取序号。
			 * 
			 * @return 序号
			 */
			public int getIndex() {
				return this.index;
			}

			/**
			 * 获取方法。
			 * 
			 * @return 方法
			 */
			public Method getMethod() {
				return this.method;
			}
		}

		/**
		 * 根据注解获取换行符。
		 * 
		 * @param clazz
		 *            提取注解的Class对象
		 * @return 返回换行符，如果没有配置注解，返回”\n“
		 * @since {@link com.alf.LineBreak}
		 */
		private final String getLineBreakFromAnnotation(Class<?> clazz) {
			LineBreak lineBreakAnnotation = clazz
					.getAnnotation(LineBreak.class);
			if (lineBreakAnnotation == null)
				return "\n";
			else
				return lineBreakAnnotation.value();
		}

		/**
		 * 换行符
		 */
		private String lineBreak;

		/**
		 * 根据注解获取<b>分隔符</b>。
		 * 
		 * @param clazz
		 *            提取注解的Class对象
		 * @return 返回分隔符，如果没有配置注解，返回”,“
		 * @since {@link com.alf.Separtor}
		 */
		private final String getSepartorFromAnnotation(Class<?> clazz) {
			Separtor separtorAnnotation = clazz.getAnnotation(Separtor.class);
			if (separtorAnnotation == null)
				return ",";
			else
				return separtorAnnotation.value();
		}

		/**
		 * 分隔符
		 */
		private String separtor;

		/**
		 * 将AntiMoneyLaunderingData转换成一行字符窜，并打印到缓存中。
		 * 
		 * @param data
		 *            反洗钱数据
		 * @throws InvocationTargetException
		 *             反射调动错误
		 * @throws IllegalArgumentException
		 *             反射调动错误
		 * @throws IllegalAccessException
		 *             反射调动错误
		 */
		private void printline(AntiMoneyLaunderingData data)
				throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException {
			int length = sequence.size(); // 获取单元格
			int index = 0; // 计数游标

			// 根据sequence的顺序，将data写入buffer。
			for (SequenceCell sequenceCell : sequence) {
				Object object = sequenceCell.getMethod().invoke(data); // 执行data中对应的方法
				buffer.append(cellFormat.print(object, sequenceCell.getIndex())); // 将数据通过cellFormat转化后写入buffer。
				index++; // 计数游标，没写入一个单元，增加1。

				if (index == length) {// 如果是最后一个单元，将lineBreak写入buffer。
					buffer.append(lineBreak);
				} else { // 如果不是最后一个单元，将separtor写入buffer。
					buffer.append(separtor);
				}
			}

			bufferLineNumber++;
		}

		/**
		 * 字符串缓存。
		 */
		private StringBuffer buffer;

		/**
		 * IO读写
		 */
		private BufferedWriter writer;

		/**
		 * 缓存中的数据写入文件，并清空缓存。
		 */
		private void write() {
			try {
				writer.write(buffer.toString()); // 将buffer中的内容写入流。
				writer.flush(); // 刷新流。
				lineNumber += bufferLineNumber;
				buffer.delete(0, buffer.length()); // 清空缓存。
				bufferLineNumber = 0L;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 获取写入文件的行数。
		 * 
		 * @return 文件行数
		 */
		public long getLineNumber() {
			return this.lineNumber;
		}

		/**
		 * 行数据
		 */
		private long lineNumber;

		/**
		 * 缓冲区中的行数
		 */
		private long bufferLineNumber;
	}
}
