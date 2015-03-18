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
 * @author 霍景龙
 * @date Feb 10, 2011
 * @time 11:03:53 AM
 * @DESC 反洗钱数据抽取Service
 * @version V1.0
 */
public class FxqShujuchouquService extends AbstractServiceSupport {
	private static final Logger _log = Logger
			.getLogger(FxqShujuchouquService.class);

	private FxqShujuchouquDao dao = new FxqShujuchouquDao();
	private List<FxqVO> fxqlist = new ArrayList<FxqVO>();

	/**
	 * 查询反洗钱数据信息
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
			_log.error("反洗钱查询失败:" + e.getMessage(), e);
			throw new IncityException("反洗钱查询失败！", e);
		}
		return fxqlist;
	}

	/**
	 * 生成反洗钱数据抽取文件
	 * 
	 * @param fxqvo
	 * @return
	 */
	public String createfxqfile(FxqVO fxqvo) {
		try {
			VOList volist = dao.chouqufxqfile(fxqvo);
			/** *****反洗钱数据文件******* */
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
			/** *******判断超时*********** */
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
			return "反洗钱数据抽取文件发送超时，请重新发送";
		} catch (Exception e) {
			_log.error("反洗钱文件生成错误!", e);
			return "反洗钱文件生成错误!";
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param fxqvo
	 * @param changedate
	 *            交易日期
	 * @param file
	 *            反洗钱数据抽取文件
	 * @param succFile
	 *            已成功的文件
	 * @param info
	 *            结果消息VO
	 * @throws Exception
	 */
	private String writeFxqFile(VOList list, String changedate, File file,
			File succFile, String fhjgm) throws Exception {
		// FileOutputStream fos=null;
		BufferedWriter writer = null;
		try {
			boolean isExists = succFile.exists();
			if (isExists) {// 反洗钱已经成功
				return "日反洗钱文件已经生成并且发送成功！请勿重复操作！";

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

					// ************1 必输项 ***交易日期 Date 以实际会计日期为准
					// 按以下格式提供yyyy/mm/dd
					String gzrq = vo.getAttr("hxrq");
					String tempgzrq = gzrq.substring(0, 4) + "/"
							+ gzrq.substring(4, 6) + "/" + gzrq.substring(6, 8);
					su.append(tempgzrq).append("|");
					// ************2 交易时间 ****** Char(8) 系统记录的交易时间
					// 按以下格式提供hh:mm:ss
					su.append(vo.getAttr("JZSJ")).append("|");
					// ************3 处理行机构码 Char(4)
					su.append(vo.getAttr("JYJG")).append("|");
					// ************4 交易流水号 Char(64)
					// 该笔交易在业务系统内的流水号12，前7外围流水号，3位外围编码032，2位渠道号
					su.append(vo.getAttr("HXLS")).append("|");
					String ywlx = vo.getAttr("ywlx");

					// ************5 分录序号不填
					su.append("|");
					// ************6 账号本行账号
					su.append(vo.getAttr("BHZH")).append("|");
					// ************7 客户名或账户名
					su.append(vo.getAttr("BHHM")).append("|");
					/** ************8 借贷标志 借或贷 所有业务系统按以下规则赋值：1 借、2 贷
					 * 提出贷记：1 借客户贷清算
					 * 提出借记：2 贷客户借清算
					 * 提入贷记：2
					 * 提入借记：1
					 * 
					 * */
					String jdbs = vo.getAttr("jdbs");
					if("wzd".equals(ywlx)||"wtd".equals(ywlx)){
						jdbs = "1";
					}else if("wzj".equals(ywlx)||"wtj".equals(ywlx)){
						jdbs = "2";
					}
					su.append(jdbs).append("|");
					// ************9 币种
					su.append(vo.getAttr("bz")).append("|");
					// ************10 金额
					String money = SimpleNumberUtil.toDataString("(19,2)", vo
							.getAttr("je"));
					su.append(money).append("|");

					// ************11 现金转帐标志:现金交易，转帐交易 1 现金、2 转帐
					if ("".equals(vo.getAttr("BHZH"))
							|| "".equals(vo.getAttr("THZH"))) {
						su.append("1|");
					} else {
						su.append("2|");
					}
					// ************12 摘要
					String zy = retStr(MsgTool.replaceBlank(vo.getAttr("zy")),
							100);
					su.append(zy).append("|");
					// ************13 交易对方类型
					su.append("1|");
					// ************14 交易对方账户类型
					su.append("0011|");
					// ************15 交易对方账号Char(32)
					su.append(vo.getAttr("THZH")).append("|");
					// ************16 交易对方名称 Char(100)
					su.append(
							retStr(MsgTool.replaceBlank(vo.getAttr("THHM")),
									100)).append("|");
					// ************17 交易对方证件类型 Char(2)
					su.append("|");
					// ************18 交易对方证件号码
					su.append("|");
					// ************19 交易对方银行行号，即对方金融机构的网点代码 Char(12)
					String bankname = vo.getAttr("THJM");
					su.append(
							(bankname.length() > 12 ? bankname.substring(0, 11)
									: bankname)).append("|");

					// ************20 交易方式
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
					// ***********21 涉外收支交易分类代码Char(4)
					su.append("|");
					// ***********22 是否结售汇 ‘1’：结售汇, ‘0’：不结结售汇
					su.append("0|");
					// ***********23 是否跨境交易 ‘1’：跨境 ‘0’不跨境
					su.append("0|");
					// ***********24 交易来源Char(6) 3位外围编码
					su.append("032|");
					// ***********25 记录代码Char(9)
					su.append("|");
					// ***********26 来源Char(10)
					su.append("|");
					// ***********27 业务参考号 Char(20)
					// 对于外币账户变动数据，取交易流水号；对于结售汇数据，取业务参考号
					su.append("|");
					// ***********28 对方科目 Char(10)
					su.append("|");
					// ***********29 客户性质 Char(1)
					su.append("|");
					// ***********30 业务类型 Char(10) 结售汇业务类型
					su.append("|");
					// ***********31 业务流水Char(64) 其它唯一标识
					su.append(vo.getAttr("ywls")).append("|");
					//***********32 凭证号码
					su.append(vo.getAttr("ZKH")).append("|");

					// ***********33 摘要
					su
							.append(
									retStr(MsgTool.replaceBlank(vo
											.getAttr("zy")), 120)).append("|");
					// ***********34 余额 当前卡内主账户可用余额
					su.append("|");
					// ************35 客户类型 个人、单位
					String acctype = getAccountType(vo.getAttr("BHZH"));
					if (acctype.equals("card")) {// 借记卡
						su.append("01").append("|");
					} else {
						su.append("02").append("|");
					}

					// ***********36 交易分行机构码Char(12)
					su.append(fhjgm).append("|");
					// ***********37交易渠道 Char(2)
					if ("lzj".equals(ywlx) || "lzd".equals(ywlx)||"ltj".equals(ywlx)||"ltd".equals(ywlx)) {
						su.append("08|");
					} else {
						su.append("00|");
					}
					// ************38
					/*
					 * 交易对方银行行号类型 即对方金融机构的网点代码类型 Char(2)
					 * 11：现代化支付系统行号；12：人民币结算帐户管理系统行号；13: 银行内部机构号；14：其他
					 */
					su.append("14|");
					// ************39 交易对方银行名称 即对方金融机构的网点名称 Char(64)
					su
							.append(
									retStr(MsgTool.replaceBlank(vo
											.getAttr("THMC")), 64)).append("|");
					;

					// *************40 客户号 Number(17)
					su.append("|");
					// *************41 交易对方客户号 Number(17)
					su.append("|");
					su.append("\r\n");
					_log.info(su.toString());
					writer.write(su.toString());
				}
			}
			writer.flush();
			_log.info(file+"反洗钱文件生成完毕");
			return "";
		} catch (Exception e) {
			_log.error(changedate + "日，同城生成反洗钱数据抽取文件失败，失败原因:" + e.getMessage(),
					e);
			if (file != null) {
				file.delete();
			}
			return changedate + "日，同城生成反洗钱数据抽取文件失败!";
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 判断账户类型
	 * 
	 * @param account
	 * @return
	 */
	private String getAccountType(String account) {
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

	private String retStr(String inputStr, int len) {

		// 如果输入的字符串为空或者是"",则直接输出"";
		if (inputStr == null || inputStr.equals(""))
			return "";
		// 如果len为0或大于总字节数
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
