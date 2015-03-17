package com.alf.bean;

import java.util.Date;

import com.alf.Format;
import com.alf.Index;
import com.alf.LineBreak;
import com.alf.Separtor;

/**
 * 反洗钱数据
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
	
	// TODO : 按要求，重新修改此Bean
	
	/**
	 * 交易日期
	 */
	private Date jyrq;
	
	/**
	 * 交易时间
	 */
	private String jysj;
	
	/**
	 * 交易机构
	 */
	private String jyjg;
	
	/**
	 * 交易流水号
	 */
	private String jylsh;
	
	/**
	 * 分录序号
	 */
	private String flxh;
	
	@Index(1)
	public Date getJyrq() {
		return jyrq;
	}
	
	public void setJyrq(Date jyrq) {
		this.jyrq = jyrq;
	}
	
	@Index(2)
	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	
	@Index(3)
	public String getJylsh() {
		return jylsh;
	}
	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}
	
	@Index(4)
	public String getFlxh() {
		return flxh;
	}
	public void setFlxh(String flxh) {
		this.flxh = flxh;
	}
	
	@Index(5)
	public String getJyjg() {
		return jyjg;
	}
	public void setJyjg(String jyjg) {
		this.jyjg = jyjg;
	}
	
}
