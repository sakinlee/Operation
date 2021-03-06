package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.ServiceController;

@RunWith(SpringRunner.class)
@SpringBootTest
//(classes = OperationApplication.class)
public class OperationApplicationTests {
	
	@Autowired
	private ServiceController serviceController;
	
	@Test
	public void contextLoads()  throws Exception{
		System.out.println("測試開始");
		

		System.out.println("功課2.寫入筆數="+serviceController.insertCurrencyTable());
		System.out.println("功課1.確認內容="+serviceController.selectCurrencyTable());
		System.out.println("功課3.更新筆數="+serviceController.updateCurrencyTable());
		System.out.println("功課3.[再確認]="+serviceController.selectCurrencyTable());
		System.out.println("功課4.刪除筆數="+serviceController.deleteCurrencyTable());
		System.out.println("功課4.[再確認]="+serviceController.selectCurrencyTable());
		System.out.println("功課5.API資訊="+serviceController.currentprice());
		System.out.println("功課6.整理資訊="+serviceController.currentpriceTidy());
				
		
	}

}
