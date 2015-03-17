* from (
	(
		select 
			'wzj'       as ywlx,
			wz.YWLS     as YWLS,
			wz.HXRQ     as HXRQ,
			hx.JZSJ     as JYSJ,
			wz.CZJG     as JYJG,
			hx.wwh      as HXLS, 
			wz.xzh      AS BHZH,
			wz.xzhm     as BHHM,
			wz.fkrzh    as THZH,
			wz.FKR      as THHM, 
			wz.TCH      as BHJG,
			wz.tchm     as BHMC,
			wz.TRH      as THJM,
			wz.TRHM     as THMC, 
			wz.BZ       as BZ,
			wz.JE       AS JE,
			wz.ZKH,
			wz.zy,
			NOTEDES.des as PZLX,
			wz.jdbs,
			wz.bhpzlx   as bhpzlx 
		from
			tc_yw_pjdjb wz 
			left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX 
		where  
			hx.jym not in ('020045','020047') 
			and (wz.hxzt='1') 
			and wz.JDBS='1' 
			and wz.djblx='ssywwz' 
			and wz.HXRQ = '?'
	) union ( 
		select 
			'wzd' as ywlx,
			wz.YWLS as YWLS,
			wz.HXRQ as HXRQ,
			hx.JZSJ as JYSJ,
			wz.CZJG as JYJG,
			hx.wwh as HXLS, 
			wz.xzh AS BHZH,
			wz.xzhm as BHHM,wz.skrzh as THZH,wz.SKR as THHM, wz.TCH as BHJG,wz.tchm as BHMC,wz.TRH as THJM,wz.TRHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx 
		from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX
		where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.JDBS='2' and wz.djblx='ssywwz' and wz.HXRQ = '?' 
	) union ( select 'lzj' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM,wz.skrzh as THZH,wz.SKR as THHM, wz.TRH as BHJG,wz.trhm as BHMC,wz.TCH as THJM,wz.TCHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.JDBS='1' and wz.djblx='ssywlz' and wz.HXRQ = '?' ) union ( select 'lzd' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM,wz.fkrzh as THZH,wz.FKR as THHM, wz.TRH as BHJG,wz.trhm as BHMC,wz.TCH as THJM,wz.TCHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.djblx='ssywlz' and wz.JDBS='2'  and wz.HXRQ = '?' ) union ( select 'wtj' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM,wz.fkrzh as THZH,wz.FKR as THHM, wz.TCH as BHJG,wz.tchm as BHMC,wz.TRH as THJM,wz.TRHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.djblx='tpywwz' and wz.JDBS='1'  and wz.HXRQ = '?' ) union ( select 'wtd' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM, wz.skrzh as THZH,wz.SKR as THHM, wz.TCH as BHJG,wz.tchm as BHMC,wz.TRH as THJM,wz.TRHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.JDBS='2' and wz.djblx='tpywwz' and wz.HXRQ = '?' ) union ( select 'ltj' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM,wz.skrzh as THZH,wz.SKR as THHM, wz.TRH as BHJG,wz.trhm as BHMC,wz.TCH as THJM,wz.TCHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1') and wz.JDBS='1' and wz.djblx='tpywlz' and wz.HXRQ = '?' ) union ( select 'ltd' as ywlx ,wz.YWLS as YWLS,wz.HXRQ as HXRQ,hx.JZSJ as JYSJ,wz.CZJG as JYJG,hx.wwh as HXLS, wz.xzh AS BHZH,wz.xzhm as BHHM,wz.fkrzh as THZH,wz.FKR as THHM, wz.TRH as BHJG,wz.trhm as BHMC,wz.TCH as THJM,wz.TCHM as THMC, wz.BZ,wz.JE,wz.ZKH,wz.zy,NOTEDES.des as PZLX ,wz.jdbs ,wz.bhpzlx as bhpzlx from tc_yw_pjdjb wz left outer join HXJYRZ  hx on hx.YWLS = wz.ywls and hx.XYM='08' and hx.jylx='0' left outer join TC_TRANSNOTE NOTEDES on NOTEDES.id = wz.TCPZLX where  hx.jym not in ('020045','020047') and (wz.hxzt='1' ) and wz.djblx='tpywlz' and wz.JDBS='2'  and wz.HXRQ = '?'))fxq
