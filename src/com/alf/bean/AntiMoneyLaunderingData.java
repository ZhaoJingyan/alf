package com.alf.bean;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alf.Format;
import com.alf.Index;
import com.alf.LineBreak;
import com.alf.Separtor;

/**
 * 反洗钱数据。
 * 
 * @author Zhao Jinyan
 */
@LineBreak("|\n")
@Separtor("|")
@Format
public class AntiMoneyLaunderingData {
	
	/**
	 * 默认构造方法。
	 */
	public AntiMoneyLaunderingData(){
	}
	
	// 1 交易日期
	private Date hxrq;
	
	@Index(1)
	public Date getHxrq(){
		return this.hxrq;
	}
	
	// 2 交易时间
	private String jzsj;
	
	@Index(2)
	public String getJzsj(){
		return this.jzsj;
	}
	
	// 3 交易机构
	private String jyjg;
	
	@Index(3)
	public String getJyjg(){
		return this.jyjg;
	}
	
	// 4 交易流水号
	private String hxls;
	
	@Index(4)
	public String getHxls(){
		return this.hxls;
	}
	
	public void setHxls(String hxls){
		this.hxls = hxls;
	}
	
	// 5 分录序号，不填
	@Index(5)
	public String getFlxh(){
		return null;
	}
	
	// 6 本行账号
	private String bhzh;
	
	@Index(6)
	public String getBhzh(){
		return this.bhzh;
	}
	
	// 7 客户名或账户名
	private String bhhm;
	
	@Index(7)
	public String getBhhm(){
		return this.bhhm;
	}
	
	// 8 借贷标志
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
	
	// 9 币种
	private String bz;
	
	@Index(9)
	public String getBz(){
		return this.bz;
	}
	
	// 10 金额
	private double je;
	
	@Index(10)
	public double getJe(){
		return this.je;
	}
	
	// 11 现金转账标志
	private String thzh;
	
	@Index(11)
	public String getXjzzbz(){
		if("".equals(this.bhzh) || "".equals(this.thzh)){
			return "1";
		} else {
			return "2";
		}
	}
	
	// 12 摘要
	private String zy;
	
	@Index(12)
	public String getZy(){
		return retStr(replaceBlank(zy), 100);
	}
	
	// 13 交易对方类型
	@Index(13)
	public String getJydflx(){
		return "1";
	}
	
	// 14 交易对方账户类型
	@Index(14)
	public String Jydfzhlx(){
		return "0011";
	}
	
	// 15 交易对方账号
	@Index(15)
	public String getThzh(){
		return this.thzh;
	}
	
	// 16 交易对方名称
	private String thhm;
	
	@Index(16)
	public String getThhm(){
		return retStr(replaceBlank(thhm), 100);
	}
	
	// 17 交易对方证件类型
	@Index(17)
	public String getJydfzjhm(){
		return null;
	}
	
	// 18 交易对方证件号码
	@Index(18)
	public String getJydfyh(){
		return null;
	}
	
	// 19 交易对方银行行号，即对方金融机构的网点代码
	private String thjm;
	
	@Index(19)
	public String getThjm(){
		return this.thjm.length() > 12 ? thjm.substring(0, 11) : this.thjm;
	}
	
	// 20 交易方式
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
	
	// 21 涉外收支交易分类代码
	@Index(21)
	public String getSwszjyfldm(){
		return null;
	}
	
	// 22 是否结售汇 ‘1’：结售汇, ‘0’：不结结售汇
	@Index(22)
	public String getSfjsh(){
		return "0";
	}
	
	// 23 是否跨境交易 ‘1’：跨境 ‘0’不跨境
	@Index(23)
	public String getSfkjjy(){
		return "0";
	}
	
	// 24 交易来源
	@Index(24)
	public String getJyly(){
		return "032";
	}
	
	// 25 记录代码
	@Index(25)
	public String getJldm(){
		return null;
	}
	
	// 26 来源
	@Index(26)
	public String getLy(){
		return null;
	}
	
	// 27 业务参考号
	@Index(27)
	public String getYwckh(){
		return null;
	}
	
	// 28 对方科目
	@Index(28)
	public String getDfkm(){
		return null;
	}
	
	// 29 客户性质
	@Index(29)
	public String getKhxz(){
		return null;
	}
	
	// 30 业务类型
	@Index(30)
	public String getYwlx(){
		return null;
	}
	
	// 31 业务流水
	private String ywls;
	
	@Index(31)
	public String getYwls(){
		return this.ywls;
	}
	
	// 32 凭证号码
	private String zkh;
	
	@Index(32)
	public String getZkh(){
		return this.zkh;
	}
	
	// 33 摘要
	@Index(33)
	public String getZy2(){
		retStr(replaceBlank(zy), 120);
		return this.zy;
	}
	
	// 34 余额
	@Index(34)
	public Double getYe(){
		return null;
	}
	
	// 35 客户类型
	@Index(35)
	public String getKhlx(){
		String acctype = getAccountType(bhzh);
		if(acctype.equals("card")){
			return "01";
		} else {
			return "02";
		}
	}
	
	private final String getAccountType(String account) {
		if (account.startsWith("622")) {
			// 卡账号
			return "card";
		} else if (account.startsWith("7") && account.length() == 16) {
			// 内部户账号
			return "inner";
		} else {
			// 客户账号
			return "customer";
		}
	}
	
	// 36 交易分行机构码
	@Index(36)
	public String getFhjgm(){
		return "1700";
	}
	
	// 37 交易渠道
	@Index(37)
	public String getJyqd(){
		if("lzj".equals(ywlx) || "lzd".equals(ywlx) || "ltj".equals(ywlx) || "ltd".equals(ywlx)){
			return "08";
		} else {
			return "00";
		}
	}
	
	// 38 交易对方银行行号类型
	@Index(38)
	public String getJydfyhhhlx(){
		return "14";
	}
	
	// 39 交易对方银行名称
	private String thmc;
	
	@Index(39)
	public String getJydfyhmc(){
		retStr(replaceBlank(thmc), 64);
		return thmc;
	}
	
	// 40 客户号
	@Index(40)
	public String getKhh(){
		return null;
	}
	
	// 41 交易对方客户号
	@Index(41)
	public String getJydfkhh(){
		return null;
	}
	
	public void setHxrq(Date hxrq) {
		this.hxrq = hxrq;
	}

	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}

	public void setJyjg(String jyjg) {
		this.jyjg = jyjg;
	}

	public void setBhzh(String bhzh) {
		this.bhzh = bhzh;
	}

	public void setBhhm(String bhhm) {
		this.bhhm = bhhm;
	}

	public void setJdbs(String jdbs) {
		this.jdbs = jdbs;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public void setJe(double je) {
		this.je = je;
	}

	public void setThzh(String thzh) {
		this.thzh = thzh;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public void setThhm(String thhm) {
		this.thhm = thhm;
	}

	public void setThjm(String thjm) {
		this.thjm = thjm;
	}

	public void setBhpzlx(String bhpzlx) {
		this.bhpzlx = bhpzlx;
	}

	public void setYwls(String ywls) {
		this.ywls = ywls;
	}

	public void setZkh(String zkh) {
		this.zkh = zkh;
	}

	public void setThmc(String thmc) {
		this.thmc = thmc;
	}

	// 截断字符串
	private String retStr(String inputStr, int len) {

		// 如果输入的字符串为空或者是"",则直接输出"";
		if (inputStr == null || inputStr.equals(""))
			return "";
		// 如果len为0或大于总字节数
		if (len == 0 || len > strTotalLen(inputStr))
			return inputStr;

		char[] chr = inputStr.toCharArray();

		String str = "";
		int count = 0;
		for (char cc : chr) {
			if (count < len) {
				if (strIfChinese(cc)) {
					if (count + 1 == len)
						return str;
					count = count + 2;
					str = str + String.valueOf(cc);
				} else {
					count = count + 1;
					str = str + String.valueOf(cc);
				}
			}
		}
		return str;
	}	
	
	static {
		p = Pattern.compile("\\s*|\t|\r|\n");
	}
	
	private static Pattern p;
	
	/**
	 * 字符转换。
	 * 
	 * @param src 原字符串
	 * @return 特殊字符转换
	 */
	public static String replaceBlank(String src){
		Matcher m = p.matcher(src);
		String after = m.replaceAll("");
		return after;
	}
	
	/**
	 * 计算字符串长度。一个中文字符算两个字符。
	 * 
	 * @param src 原字符串
	 * @return 长度
	 */
	public static int strTotalLen(String src){
		int len = 0;
		char[] chars = src.toCharArray();
		for(char c : chars ){
			if(!strIfChinese(c)){
				len += 1;
			} else {
				len += 2;
			}
		}
		return len;
	}
	
	/**
	 * 判断字符是否为中文字符（即两个字节的字符）。
	 * 
	 * @param c 字符
	 * @return 结果
	 */
	public static boolean strIfChinese(char c){
		String cStr = String.valueOf(c);
		return cStr.getBytes().length > 1 ? true : false;
	}
}
