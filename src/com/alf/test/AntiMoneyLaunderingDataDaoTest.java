package com.alf.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.alf.AntiMoneyLaunderingDatesFile;
import com.alf.CreateAntiMoneyLaunderingDatasFile;
import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.dao.AntiMoneyLaunderingDataDao;

/**
 * Junit测试类。
 * 
 * @author Zhao Jinyan
 *
 */
public class AntiMoneyLaunderingDataDaoTest {

	private AntiMoneyLaunderingDataDao dao;
	private AntiMoneyLaunderingData data;
	
	@Before
	public void init(){
		try {
			this.dao = new AntiMoneyLaunderingDataDao();
			
			data = new AntiMoneyLaunderingData();
			data.setJyrq(new Date());
			data.setJysj("12:45");
			data.setJyjg("1752");
			data.setJylsh("20");
			data.setFlxh("ZH");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore("测试通过")
	@Test
	public void testGetAntiMoneyLaunderingDatas() {
		List<AntiMoneyLaunderingData> list = dao.getAllAntiMoneyLaunderingDatas();
		assertNotEquals(0, list.size());
	}
	
	@Ignore("测试通过")
	@Test
	public void testAddAntiMoneyLaunderingData(){

		
		int result = dao.addAntiMoneyLaunderingData(data);
		dao.commit();
		
		assertEquals(1, result);
	}
	
	@Ignore("测试通过")
	@Test()
	public void test3() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		AntiMoneyLaunderingDatesFile file = CreateAntiMoneyLaunderingDatasFile.create("D:\\ff.txt");
		file.addAntiMoneyLaunderingData(data);
		file.close();
	}
	
	@Ignore("测试通过")
	@Test
	public void test4(){
		StringBuffer buffer = new StringBuffer("12345");
		System.out.println(buffer.length());
		buffer.delete(0, buffer.length());
		System.out.println(buffer.length());
		System.out.println(buffer);
	}
	
	@After
	public void closeDao(){
		if(this.dao != null)
			this.dao.closeSqlSession();
	}
}
