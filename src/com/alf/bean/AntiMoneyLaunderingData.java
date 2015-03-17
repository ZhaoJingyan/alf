package com.alf.bean;

import java.util.Date;

import com.alf.Format;
import com.alf.Index;
import com.alf.LineBreak;
import com.alf.Separtor;

/**
 * ��ϴǮ����
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
	
	// TODO : ��Ҫ�������޸Ĵ�Bean
	
	/**
	 * ��������
	 */
	private Date jyrq;
	
	/**
	 * ����ʱ��
	 */
	private String jysj;
	
	/**
	 * ���׻���
	 */
	private String jyjg;
	
	/**
	 * ������ˮ��
	 */
	private String jylsh;
	
	/**
	 * ��¼���
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
