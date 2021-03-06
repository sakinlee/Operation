package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DAO.CurrencyRepository;
import com.example.demo.Entity.CurrencyJPA;
import com.example.demo.Fegin.FeginClientService;
import com.example.demo.Utils.DataUtils;

@Controller
@RequestMapping("/TestService")
public class ServiceController {

	@Autowired
	CurrencyRepository CurrencyRepository;

	@Autowired
	DataSource dataSource;

	@Autowired
	FeginClientService feginClientService;

	@RequestMapping(value = "/currentprice", method = RequestMethod.GET)
	@ResponseBody
	public String currentprice() {
		return feginClientService.currentprice();
	}

	@RequestMapping(value = "/currentpriceTidy", method = RequestMethod.GET)
	@ResponseBody
	public String currentpriceTidy() {
		String jsonStr = feginClientService.currentprice();

		List<CurrencyJPA> CJPAList = new ArrayList<CurrencyJPA>();
		if (StringUtils.isNotBlank(jsonStr)) {
			JSONObject j = new JSONObject(jsonStr);
			Date d = null;
			String chartName = null;
			if (j.has("time")) {
				JSONObject time = new JSONObject(j.getString("time"));
				if (time.has("updatedISO")) {
					d = javax.xml.bind.DatatypeConverter.parseDateTime(time.getString("updatedISO")).getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("日期轉換:"+sdf.format(d));
				}
			}

			if (j.has("chartName")) {
				chartName = j.getString("chartName");
			}

			if (j.has("bpi")) {
				JSONObject bpi = new JSONObject(j.getString("bpi"));
				Iterator<String> keys = bpi.keys();
				while (keys.hasNext()) {
					String key = keys.next();
					if (bpi.get(key) instanceof JSONObject) {
						JSONObject bpi_keyJsonObj = new JSONObject(bpi.getString(key));
						CurrencyJPA CJPA = new CurrencyJPA();
						if (bpi_keyJsonObj.has("code")) {
							CJPA.setCode(bpi_keyJsonObj.getString("code"));
						}

						if (bpi_keyJsonObj.has("symbol")) {
							CJPA.setSymbol(bpi_keyJsonObj.getString("symbol"));
						}

						if (bpi_keyJsonObj.has("rate")) {
							CJPA.setRate(bpi_keyJsonObj.getString("rate"));
						}

						if (bpi_keyJsonObj.has("rate_float")) {
							CJPA.setRateFloat(bpi_keyJsonObj.get("rate").toString());
						}

						if (bpi_keyJsonObj.has("description")) {
							CJPA.setDescription(bpi_keyJsonObj.getString("description"));
						}

						CJPA.setChartName(chartName);
						CJPA.setCreateDate(d);
						CJPA.setUpdateDate(d);
						CJPAList.add(CJPA);
					}
				}

			}

		}

		return DataUtils.Object2Json(CJPAList);
	}

	@RequestMapping(value = "/insertCurrencyTable", method = RequestMethod.GET)
	@ResponseBody
	public int insertCurrencyTable() {
		Date d = new Date();
		CurrencyJPA CJPA = new CurrencyJPA();
		CJPA.setChartName("Bitcoin");
		CJPA.setCode("USD");
		CJPA.setSymbol("&#36;");
		CJPA.setRate("44,626.2263");
		CJPA.setDescription("United States Dollar");
		CJPA.setRateFloat("44626.2263");
		CJPA.setCreateDate(d);
		CJPA.setUpdateDate(d);
		return CurrencyRepository.insertCurrencyTable(CJPA);
	}

	@RequestMapping(value = "/updateCurrencyTable", method = RequestMethod.GET)
	@ResponseBody
	public int updateCurrencyTable() {
		Date d = new Date();
		CurrencyJPA CJPA = new CurrencyJPA();
		CJPA.setChartName("Bitcoin1");
		CJPA.setCode("USD1");
		CJPA.setSymbol("&#36;");
		CJPA.setRate("44,626.4444");
		CJPA.setDescription("United States Dollar1");
		CJPA.setRateFloat("44626.4444");
		CJPA.setUpdateDate(d);
		return CurrencyRepository.updateCurrencyTable(CJPA);
	}

	@RequestMapping(value = "/deleteCurrencyTable", method = RequestMethod.GET)
	@ResponseBody
	public int deleteCurrencyTable() {
		return CurrencyRepository.deleteCurrencyTable();
	}

	@RequestMapping(value = "/selectCurrencyTable", method = RequestMethod.GET)
	@ResponseBody
	public String selectCurrencyTable() {
		List<CurrencyJPA> list = CurrencyRepository.selectCurrencyTable();
		if (list == null) {
			list = new ArrayList<CurrencyJPA>();
		}

		return DataUtils.Object2Json(list);
	}

}
