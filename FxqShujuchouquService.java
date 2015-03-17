package com.sinodata.incity.business.zongheguanli.fxqshujuchouqu.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.sinodata.incity.business.zongheguanli.fxqshujuchouqu.dao.FxqShujuchouquDao;
import com.sinodata.incity.business.zongheguanli.fxqshujuchouqu.vo.FxqVO;
import com.sinodata.incity.common.exception.IncityException;
import com.sinodata.incity.common.services.AbstractServiceSupport;
import com.sinodata.incity.common.util.InitialConfig;
import com.sinodata.incity.common.util.MsgTool;
import com.sinodata.incity.common.util.SimpleNumberUtil;
import com.sinodata.incity.common.util.Toolkit;
import com.sinodata.incity.common.vo.PageVO;
import com.unify.cc.common.VO;
import com.unify.cc.common.VOList;

/**
 * @class FxqShujuchouquService
 * @author ������
 * @date Feb 10, 2011
 * @time 11:03:53 AM
 * @DESC ��ϴǮ���ݳ�ȡService
 * @version V1.0
 */
public class FxqShujuchouquService extends AbstractServiceSupport {
	private static final Logger _log = Logger
			.getLogger(FxqShujuchouquService.class);

	private FxqShujuchouquDao dao = new FxqShujuchouquDao();
	private List<FxqVO> fxqlist = new ArrayList<FxqVO>();

	/**
	 * ��ѯ��ϴǮ������Ϣ
	 * 
	 * @param fxqvo
	 * @param pagevo
	 * @return
	 */

	public List<FxqVO> getFxqListByRq(FxqVO fxqvo, PageVO pagevo) {
		VOList list;
		List fxqlist;
		try {
			list = dao.queryfxqlist(fxqvo, pagevo);
			if (list.pageCount == 0)
				pagevo.setAllpage(1);
			else
				pagevo.setAllpage(list.pageCount);
			fxqlist = Toolkit.getBeanList(FxqVO.class, list);
		} catch (Exception e) {
			_log.error("��ϴǮ��ѯʧ��:" + e.getMessage(), e);
			throw new IncityException("��ϴǮ��ѯʧ�ܣ�", e);
		}
		return fxqlist;
	}

	/**
	 * ���ɷ�ϴǮ���ݳ�ȡ�ļ�
	 * 
	 * @param fxqvo
	 * @return
	 */
	public String createfxqfile(FxqVO fxqvo) {
		try {
			VOList volist = dao.chouqufxqfile(fxqvo);
			/** *****��ϴǮ�����ļ�******* */
			String fhjgm = InitialConfig.getProp("FENHANG.JGM");
			String path = InitialConfig.getProp("bancs.file.fanxiqian");
			String filename = InitialConfig.getProp("FANXIQIAN.FILE")
					+ fxqvo.getHXRQ() + ".TXT";
			String succPath = path.substring(0, path.length() - 1) + ".succ\\";
			File fxqfile = new File(path + filename);
			File succFile = new File(succPath + filename);
			/** ******************* */
			String ret = writeFxqFile(volist, fxqvo.getHXRQ(), fxqfile,
					succFile, fhjgm);
			/** *******�жϳ�ʱ*********** */
			int overtime = Integer.parseInt(InitialConfig
					.getProp("FANXIQIAN.OVERTIME")) * 1000;
			int checktime = Integer.parseInt(InitialConfig
					.getProp("FANXIQIAN.CHECKTIME")) * 1000;
			int tempM = overtime / checktime + 1;
			for (int m = 0; m < tempM; m++) {
				Thread.sleep(checktime);
				boolean succExists = succFile.exists();
				if (succExists)
					return ret;
			}
//			fxqfile.delete();
			return "��ϴǮ���ݳ�ȡ�ļ����ͳ�ʱ�������·���";
		} catch (Exception e) {
			_log.error("��ϴǮ�ļ����ɴ���!", e);
			return "��ϴǮ�ļ����ɴ���!";
		}
	}

	/**
	 * д���ļ�
	 * 
	 * @param fxqvo
	 * @param changedate
	 *            ��������
	 * @param file
	 *            ��ϴǮ���ݳ�ȡ�ļ�
	 * @param succFile
	 *            �ѳɹ����ļ�
	 * @param info
	 *            �����ϢVO
	 * @throws Exception
	 */
	private String writeFxqFile(VOList list, String changedate, File file,
			File succFile, String fhjgm) throws Exception {
		// FileOutputStream fos=null;
		BufferedWriter writer = null;
		try {
			boolean isExists = succFile.exists();
			if (isExists) {// ��ϴǮ�Ѿ��ɹ�
				return "�շ�ϴǮ�ļ��Ѿ����ɲ��ҷ��ͳɹ��������ظ�������";

			}
			writer = new BufferedWriter(new FileWriter(file));
			if (list != null && list.listData.size() > 0) {
				for (Iterator itor = list.listData.iterator(); itor.hasNext();) {

					StringBuffer su = new StringBuffer();
					VO vo = (VO) itor.next();
					if ("".equals(vo.getAttr("HXLS"))
							|| vo.getAttr("HXLS") == null) {
						continue;
					}

					// ************1 ������ ***�������� Date ��ʵ�ʻ������Ϊ׼
					// �����¸�ʽ�ṩyyyy/mm/dd
					String gzrq = vo.getAttr("hxrq");
					String tempgzrq = gzrq.substring(0, 4) + "/"
							+ gzrq.substring(4, 6) + "/" + gzrq.substring(6, 8);
					su.append(tempgzrq).append("|");
					// ************2 ����ʱ�� ****** Char(8) ϵͳ��¼�Ľ���ʱ��
					// �����¸�ʽ�ṩhh:mm:ss
					su.append(vo.getAttr("JZSJ")).append("|");
					// ************3 �����л����� Char(4)
					su.append(vo.getAttr("JYJG")).append("|");
					// ************4 ������ˮ�� Char(64)
					// �ñʽ�����ҵ��ϵͳ�ڵ���ˮ��12��ǰ7��Χ��ˮ�ţ�3λ��Χ����032��2λ������
					su.append(vo.getAttr("HXLS")).append("|");
					String ywlx = vo.getAttr("ywlx");

					// ************5 ��¼��Ų���
					su.append("|");
					// ************6 �˺ű����˺�
					su.append(vo.getAttr("BHZH")).append("|");
					// ************7 �ͻ������˻���
					su.append(vo.getAttr("BHHM")).append("|");
					/** ************8 �����־ ���� ����ҵ��ϵͳ�����¹���ֵ��1 �衢2 ��
					 * ������ǣ�1 ��ͻ�������
					 * �����ǣ�2 ���ͻ�������
					 * ������ǣ�2
					 * �����ǣ�1
					 * 
					 * */
					String jdbs = vo.getAttr("jdbs");
					if("wzd".equals(ywlx)||"wtd".equals(ywlx)){
						jdbs = "1";
					}else if("wzj".equals(ywlx)||"wtj".equals(ywlx)){
						jdbs = "2";
					}
					su.append(jdbs).append("|");
					// ************9 ����
					su.append(vo.getAttr("bz")).append("|");
					// ************10 ���
					String money = SimpleNumberUtil.toDataString("(19,2)", vo
							.getAttr("je"));
					su.append(money).append("|");

					// ************11 �ֽ�ת�ʱ�־:�ֽ��ף�ת�ʽ��� 1 �ֽ�2 ת��
					if ("".equals(vo.getAttr("BHZH"))
							|| "".equals(vo.getAttr("THZH"))) {
						su.append("1|");
					} else {
						su.append("2|");
					}
					// ************12 ժҪ
					String zy = retStr(MsgTool.replaceBlank(vo.getAttr("zy")),
							100);
					su.append(zy).append("|");
					// ************13 ���׶Է�����
					su.append("1|");
					// ************14 ���׶Է��˻�����
					su.append("0011|");
					// ************15 ���׶Է��˺�Char(32)
					su.append(vo.getAttr("THZH")).append("|");
					// ************16 ���׶Է����� Char(100)
					su.append(
							retStr(MsgTool.replaceBlank(vo.getAttr("THHM")),
									100)).append("|");
					// ************17 ���׶Է�֤������ Char(2)
					su.append("|");
					// ************18 ���׶Է�֤������
					su.append("|");
					// ************19 ���׶Է������кţ����Է����ڻ������������ Char(12)
					String bankname = vo.getAttr("THJM");
					su.append(
							(bankname.length() > 12 ? bankname.substring(0, 11)
									: bankname)).append("|");

					// ************20 ���׷�ʽ
					String bz = vo.getAttr("bz");
					if ("CNY".equals(bz)) {
						if ("308".equals(vo.getAttr("bhpzlx"))) {
							su.append("00").append("|");
						} else if ("310".equals(vo.getAttr("bhpzlx"))) {
							su.append("01").append("|");
						} else if ("306".equals(vo.getAttr("bhpzlx"))) {
							su.append("03").append("|");
						} else if ("602".equals(vo.getAttr("bhpzlx"))) {
							su.append("04").append("|");
						} else if ("608".equals(vo.getAttr("bhpzlx"))) {
							su.append("04").append("|");
						} else if ("87".equals(vo.getAttr("bhpzlx"))) {
							su.append("05").append("|");
						} else {
							su.append("51").append("|");
						}
					} else {
						su.append("0107").append("|");
					}
					// ***********21 ������֧���׷������Char(4)
					su.append("|");
					// ***********22 �Ƿ���ۻ� ��1�������ۻ�, ��0����������ۻ�
					su.append("0|");
					// ***********23 �Ƿ�羳���� ��1�����羳 ��0�����羳
					su.append("0|");
					// ***********24 ������ԴChar(6) 3λ��Χ����
					su.append("032|");
					// ***********25 ��¼����Char(9)
					su.append("|");
					// ***********26 ��ԴChar(10)
					su.append("|");
					// ***********27 ҵ��ο��� Char(20)
					// ��������˻��䶯���ݣ�ȡ������ˮ�ţ����ڽ��ۻ����ݣ�ȡҵ��ο���
					su.append("|");
					// ***********28 �Է���Ŀ Char(10)
					su.append("|");
					// ***********29 �ͻ����� Char(1)
					su.append("|");
					// ***********30 ҵ������ Char(10) ���ۻ�ҵ������
					su.append("|");
					// ***********31 ҵ����ˮChar(64) ����Ψһ��ʶ
					su.append(vo.getAttr("ywls")).append("|");
					//***********32 ƾ֤����
					su.append(vo.getAttr("ZKH")).append("|");

					// ***********33 ժҪ
					su
							.append(
									retStr(MsgTool.replaceBlank(vo
											.getAttr("zy")), 120)).append("|");
					// ***********34 ��� ��ǰ�������˻��������
					su.append("|");
					// ************35 �ͻ����� ���ˡ���λ
					String acctype = getAccountType(vo.getAttr("BHZH"));
					if (acctype.equals("card")) {// ��ǿ�
						su.append("01").append("|");
					} else {
						su.append("02").append("|");
					}

					// ***********36 ���׷��л�����Char(12)
					su.append(fhjgm).append("|");
					// ***********37�������� Char(2)
					if ("lzj".equals(ywlx) || "lzd".equals(ywlx)||"ltj".equals(ywlx)||"ltd".equals(ywlx)) {
						su.append("08|");
					} else {
						su.append("00|");
					}
					// ************38
					/*
					 * ���׶Է������к����� ���Է����ڻ���������������� Char(2)
					 * 11���ִ���֧��ϵͳ�кţ�12������ҽ����ʻ�����ϵͳ�кţ�13: �����ڲ������ţ�14������
					 */
					su.append("14|");
					// ************39 ���׶Է��������� ���Է����ڻ������������� Char(64)
					su
							.append(
									retStr(MsgTool.replaceBlank(vo
											.getAttr("THMC")), 64)).append("|");
					;

					// *************40 �ͻ��� Number(17)
					su.append("|");
					// *************41 ���׶Է��ͻ��� Number(17)
					su.append("|");
					su.append("\r\n");
					_log.info(su.toString());
					writer.write(su.toString());
				}
			}
			writer.flush();
			_log.info(file+"��ϴǮ�ļ��������");
			return "";
		} catch (Exception e) {
			_log.error(changedate + "�գ�ͬ�����ɷ�ϴǮ���ݳ�ȡ�ļ�ʧ�ܣ�ʧ��ԭ��:" + e.getMessage(),
					e);
			if (file != null) {
				file.delete();
			}
			return changedate + "�գ�ͬ�����ɷ�ϴǮ���ݳ�ȡ�ļ�ʧ��!";
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * �ж��˻�����
	 * 
	 * @param account
	 * @return
	 */
	private String getAccountType(String account) {
		if (account.startsWith("622")) {
			// ���˺�
			return "card";
		} else if (account.startsWith("7") && account.length() == 16) {
			// �ڲ����˺�
			return "inner";
		} else {
			// �ͻ��˺�
			return "customer";
		}
	}

	private String retStr(String inputStr, int len) {

		// ���������ַ���Ϊ�ջ�����"",��ֱ�����"";
		if (inputStr == null || inputStr.equals(""))
			return "";
		// ���lenΪ0��������ֽ���
		if (len == 0 || len > MsgTool.strTotalLen(inputStr))
			return inputStr;

		char[] chr = inputStr.toCharArray();

		String str = "";
		int count = 0;
		for (char cc : chr) {
			if (count < len) {
				if (MsgTool.strIfChinese(cc)) {
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


	public List<FxqVO> getFxqlist() {
		return fxqlist;
	}

	public void setFxqlist(List<FxqVO> fxqlist) {
		this.fxqlist = fxqlist;
	}
}
