package com.alf.bean;

import java.util.Date;

import com.alf.Format;
import com.alf.Index;
import com.alf.LineBreak;
import com.alf.Separtor;

/**
 * ��ϴǮ���ݡ�
 * 
 * @author Zhao Jinyan
 */
@LineBreak("|\n")
@Separtor("|")
@Format
public class AntiMoneyLaunderingData {
	
	/**
	 * Ĭ�Ϲ��췽����
	 */
	public AntiMoneyLaunderingData(){
	}
	
	// 1 ��������
	private Date hxrq;
	
	@Index(1)
	public Date getHxrq(){
		return this.hxrq;
	}
	
	// 2 ����ʱ��
	private String jzsj;
	
	@Index(2)
	public String getJzsj(){
		return this.jzsj;
	}
	
	// 3 ���׻���
	private String jyjg;
	
	@Index(3)
	public String getJyjg(){
		return this.jyjg;
	}
	
	// 4 ������ˮ��
	private String hxls;
	
	@Index(4)
	public String getHxls(){
		return this.hxls;
	}
	
	public void setHxls(String hxls){
		this.hxls = hxls;
	}
	
	// 5 ��¼��ţ�����
	@Index(5)
	public String getFlxh(){
		return null;
	}
	
	// 6 �����˺�
	private String bhzh;
	
	@Index(6)
	public String getBhzh(){
		return this.bhzh;
	}
	
	// 7 �ͻ������˻���
	private String bhhm;
	
	@Index(7)
	public String getBhhm(){
		return this.bhhm;
	}
	
	// 8 �����־
	private String jdbs;
	private String ywlx;
	
	@Index(8)
	public String getJdbs(){
		if("wzd".equals(ywlx) || "wtd".equals(ywlx)){
			return "1";
		} else if ("wzj".equals(ywlx)||"wtj".equals(ywlx)){
			return "2";
		}
		return this.jdbs;
	}
	
	// 9 ����
	private String bz;
	
	@Index(9)
	public String getBz(){
		return this.bz;
	}
	
	// 10 ���
	private double je;
	
	@Index(10)
	public double getJe(){
		return this.je;
	}
	
	// 11 �ֽ�ת�˱�־
	private String thzh;
	
	@Index(11)
	public String getXjzzbz(){
		if("".equals(this.bhzh) || "".equals(this.thzh)){
			return "1";
		} else {
			return "2";
		}
	}
	
	// 12 ժҪ
	private String zy;
	
	@Index(12)
	public String getZy(){
		// TODO : ��Ҫ�ı�������
		return this.zy;
	}
	
	// 13 ���׶Է�����
	@Index(13)
	public String getJydflx(){
		return "1";
	}
	
	// 14 ���׶Է��˻�����
	@Index(14)
	public String Jydfzhlx(){
		return "0011";
	}
	
	// 15 ���׶Է��˺�
	@Index(15)
	public String getThzh(){
		return this.thzh;
	}
	
	// 16 ���׶Է�����
	private String thhm;
	
	@Index(16)
	public String getThhm(){
		// TODO : ��Ҫ�ı�������
		return this.thhm;
	}
	
	// 17 ���׶Է�֤������
	@Index(17)
	public String getJydfzjhm(){
		return null;
	}
	
	// 18 ���׶Է�֤������
	@Index(18)
	public String getJydfyh(){
		return null;
	}
	
	// 19 ���׶Է������кţ����Է����ڻ������������
	private String thjm;
	
	@Index(19)
	public String getThjm(){
		return this.thjm.length() > 12 ? thjm.substring(0, 11) : this.thjm;
	}
	
	// 20 ���׷�ʽ
	private String bhpzlx;
	
	@Index(20)
	public String getJsfs(){
		String result = null;
		if("CNY".equals(bz)){
			if("308".equals(bhpzlx)){
				result = "00";
			} else if ("310".equals(bhpzlx)){
				result = "01";
			} else if ("306".equals(bhpzlx)){
				result = "03";
			} else if ("602".equals(bhpzlx)){
				result = "04";
			} else if ("608".equals(bhpzlx)){
				result = "04";
			} else if ("87".equals(bhpzlx)){
				result = "05";
			} else {
				result = "51";
			}
		} else {
			result = "0107";
		}
		return result;
	}
	
	// 21 ������֧���׷������
	@Index(21)
	public String getSwszjyfldm(){
		return null;
	}
	
	// 22 �Ƿ���ۻ� ��1�������ۻ�, ��0����������ۻ�
	@Index(22)
	public String getSfjsh(){
		return "0";
	}
	
	// 23 �Ƿ�羳���� ��1�����羳 ��0�����羳
	@Index(23)
	public String getSfkjjy(){
		return "0";
	}
	
	// 24 ������Դ
	@Index(24)
	public String getJyly(){
		return "032";
	}
	
	// 25 ��¼����
	@Index(25)
	public String getJldm(){
		return null;
	}
	
	// 26 ��Դ
	@Index(26)
	public String getLy(){
		return null;
	}
	
	// 27 ҵ��ο���
	@Index(27)
	public String getYwckh(){
		return null;
	}
	
	// 28 �Է���Ŀ
	@Index(28)
	public String getDfkm(){
		return null;
	}
	
	// 29 �ͻ�����
	@Index(29)
	public String getKhxz(){
		return null;
	}
	
	// 30 ҵ������
	@Index(30)
	public String getYwlx(){
		return null;
	}
	
	// 31 ҵ����ˮ
	private String ywls;
	
	@Index(31)
	public String getYwls(){
		return this.ywls;
	}
	
	// 32 ƾ֤����
	private String zkh;
	
	@Index(32)
	public String getZkh(){
		return this.zkh;
	}
	
	// 33 ժҪ
	@Index(33)
	public String getZy2(){
		// TODO : ��Ҫ�ı�������
		return this.zy;
	}
	
	// 34 ���
	@Index(34)
	public Double getYe(){
		return null;
	}
	
	// 35 �ͻ�����
	@Index(35)
	public String getKhlx(){
		// TODO : ������Ҫ�ӹ�
		return null;
	}
	
	// 36 ���׷��л�����
	@Index(36)
	public String getFhjgm(){
		return "1700";
	}
	
	// 37 ��������
	@Index(37)
	public String getJyqd(){
		if("lzj".equals(ywlx) || "lzd".equals(ywlx) || "ltj".equals(ywlx) || "ltd".equals(ywlx)){
			return "08";
		} else {
			return "00";
		}
	}
	
	// 38 ���׶Է������к�����
	@Index(38)
	public String getJydfyhhhlx(){
		return "14";
	}
	
	// 39 ���׶Է���������
	private String thmc;
	
	@Index(39)
	public String getJydfyhmc(){
		// TODO : ��Ҫ�ı�������
		return thmc;
	}
	
	// 40 �ͻ���
	@Index(40)
	public String getKhh(){
		return null;
	}
	
	// 41 ���׶Է��ͻ���
	@Index(41)
	public String getJydfkhh(){
		return null;
	}
}
