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
 * ���ɷ�ϴǮ�ļ�������ļ����ڣ��򸲸ǡ�
 * 
 * @author Zhao Jinyan
 *
 */
public class CreateAntiMoneyLaunderingDatasFile {

	/**
	 * ����Ĭ�Ϸ�ϴǮ�ļ���
	 * 
	 * @param path
	 *            ��ϴǮ�ļ���·��
	 * @throws IOException
	 *             IO����
	 * @throws ClassNotFoundException
	 *             �Ҳ���ʵ�ֽӿ�CellFormat�Ķ���
	 * @throws IllegalAccessException
	 *             �������
	 * @throws InstantiationException
	 *             �������
	 */
	public static AntiMoneyLaunderingDatesFile create(String path)
			throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		return new DefaultAntiMoneyLaunderingDatesFile(path);
	}

	/**
	 * Ĭ�Ϸ�ϴǮ�ļ���
	 * 
	 * @author Zhao Jinyan
	 * @since {@link Comparable.alf.AntiMoneyLaunderingDatesFile}
	 *
	 */
	private static class DefaultAntiMoneyLaunderingDatesFile implements
			AntiMoneyLaunderingDatesFile {

		/**
		 * ��ʼ��������
		 * 
		 * @param path ��ϴǮ�ļ�·��
		 * @throws IOException IO�쳣
		 * @throws ClassNotFoundException �����쳣
		 * @throws IllegalAccessException �����쳣
		 * @throws InstantiationException �����쳣
		 */
		public DefaultAntiMoneyLaunderingDatesFile(String path)
				throws IOException, ClassNotFoundException,
				InstantiationException, IllegalAccessException {
			initializeIO(path); // ��ʼ����дIO��
			initializeConfiguration(); // ��ʼ����ϴ��ʽ�����ļ���
		}

		/**
		 * ���ļ������һ��AntiMoneyLaunderingData��¼��
		 * 
		 * @param data
		 *            һ����ЩǮ��¼
		 */
		@Override
		public void addAntiMoneyLaunderingData(AntiMoneyLaunderingData data) {
			try {
				printline(data); // ��dataת��Ϊ�ַ������ݣ����뻺�档
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			write(); // �������е�����д���ļ�������ջ��档
		}

		/**
		 * ���ļ�����Ӷ���AntiMoneyLaunderingData��¼��
		 * 
		 * @param datas
		 *            ������ϴǮ��¼
		 */
		@Override
		public void addAntiMoneyLaunderingDatas(
				List<AntiMoneyLaunderingData> datas) {
			try {
				for (AntiMoneyLaunderingData data : datas) {
					printline(data); // ��dataת��Ϊ�ַ������ݣ����뻺�档
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			write(); // �������е�����д���ļ�������ջ��档
		}

		/**
		 * �ر��ļ�IO��
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
		 * ��ʼ��ϵͳ��дIO��
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
		 * ��ʼ����ϴ��ʽ�����ļ���
		 * 
		 * @throws ClassNotFoundException
		 *             �Ҳ�����Ԫ��ʽ��
		 * @throws IllegalAccessException
		 *             �������
		 * @throws InstantiationException
		 *             �������
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
		 *            ��ȡע���Class����
		 * @return ��Ԫ��ʽ
		 * @throws ClassNotFoundException
		 *             �Ҳ�����Ԫ��ʽ��
		 * @throws InstantiationException
		 *             �������
		 * @throws IllegalAccessException
		 *             �������
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
		 * ��Ԫ��ʽ
		 */
		private CellFormat cellFormat;

		/**
		 * ͨ��ע�⣬���ص���˳��
		 * 
		 * @param clazz
		 *            ��ȡע���Class����
		 * @return ���ص���˳��
		 * @since {@link com.alf.Index}
		 */
		private final List<SequenceCell> getSequenceFromAnnotation(
				Class<?> clazz) {
			Method[] methods = clazz.getMethods();
			LinkedList<SequenceCell> result = new LinkedList<SequenceCell>();

			/* ��ȡ��ע��Indexע��ķ��� */
			for (Method method : methods) {
				Index indexAnnotation = method.getAnnotation(Index.class);
				if (indexAnnotation != null)
					result.add(new SequenceCell(method, indexAnnotation.value()));
			}

			Collections.sort(result); // ����

			return result;
		}

		/**
		 * �ֶ�˳��
		 */
		private List<SequenceCell> sequence;

		/**
		 * �洢�����õķ������͸÷�����ִ����š� ʵ����Comparable�ӿڣ�����ʵ��List<SequenceCell>������
		 * 
		 * @author Zhao Jinyan
		 *
		 */
		private class SequenceCell implements Comparable<Object> {

			/**
			 * ���췽����
			 * 
			 * @param method
			 *            ����
			 * @param index
			 *            ������Ӧ��ִ�����
			 */
			public SequenceCell(Method method, int index) {
				this.method = method;
				this.index = index;
			}

			/**
			 * ����
			 */
			private Method method;

			/**
			 * ���
			 */
			private int index;

			/**
			 * ����Collections.sort(List)�ڲ��ص���
			 * 
			 * @param object
			 *            ���Ƚ϶���
			 * @return Ȩֵ��
			 */
			@Override
			public int compareTo(Object object) {
				SequenceCell sequenceCell = (SequenceCell) object;
				return index - sequenceCell.getIndex();
			}

			/**
			 * ��ȡ��š�
			 * 
			 * @return ���
			 */
			public int getIndex() {
				return this.index;
			}

			/**
			 * ��ȡ������
			 * 
			 * @return ����
			 */
			public Method getMethod() {
				return this.method;
			}
		}

		/**
		 * ����ע���ȡ���з���
		 * 
		 * @param clazz
		 *            ��ȡע���Class����
		 * @return ���ػ��з������û������ע�⣬���ء�\n��
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
		 * ���з�
		 */
		private String lineBreak;

		/**
		 * ����ע���ȡ<b>�ָ���</b>��
		 * 
		 * @param clazz
		 *            ��ȡע���Class����
		 * @return ���طָ��������û������ע�⣬���ء�,��
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
		 * �ָ���
		 */
		private String separtor;

		/**
		 * ��AntiMoneyLaunderingDataת����һ���ַ��ܣ�����ӡ�������С�
		 * 
		 * @param data
		 *            ��ϴǮ����
		 * @throws InvocationTargetException
		 *             �����������
		 * @throws IllegalArgumentException
		 *             �����������
		 * @throws IllegalAccessException
		 *             �����������
		 */
		private void printline(AntiMoneyLaunderingData data)
				throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException {
			int length = sequence.size(); // ��ȡ��Ԫ��
			int index = 0; // �����α�

			// ����sequence��˳�򣬽�dataд��buffer��
			for (SequenceCell sequenceCell : sequence) {
				Object object = sequenceCell.getMethod().invoke(data); // ִ��data�ж�Ӧ�ķ���
				buffer.append(cellFormat.print(object, sequenceCell.getIndex())); // ������ͨ��cellFormatת����д��buffer��
				index++; // �����α꣬ûд��һ����Ԫ������1��

				if (index == length) {// ��������һ����Ԫ����lineBreakд��buffer��
					buffer.append(lineBreak);
				} else { // ����������һ����Ԫ����separtorд��buffer��
					buffer.append(separtor);
				}
			}

			bufferLineNumber++;
		}

		/**
		 * �ַ������档
		 */
		private StringBuffer buffer;

		/**
		 * IO��д
		 */
		private BufferedWriter writer;

		/**
		 * �����е�����д���ļ�������ջ��档
		 */
		private void write() {
			try {
				writer.write(buffer.toString()); // ��buffer�е�����д������
				writer.flush(); // ˢ������
				lineNumber += bufferLineNumber;
				buffer.delete(0, buffer.length()); // ��ջ��档
				bufferLineNumber = 0L;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * ��ȡд���ļ���������
		 * 
		 * @return �ļ�����
		 */
		public long getLineNumber() {
			return this.lineNumber;
		}

		/**
		 * ������
		 */
		private long lineNumber;

		/**
		 * �������е�����
		 */
		private long bufferLineNumber;
	}
}
