package com.example.demo.Utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataUtils {

	private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);

	/**
	 * 物件轉JSON功能
	 * 
	 * @param obj
	 * @return
	 */
	public static String Object2Json(Object obj) {

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = "";
		logger.info("=====物件的內容:" + obj.toString() + ";=====");
		try {
			jsonInString = mapper.writeValueAsString(obj);

		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return jsonInString;

	}

	/**
	 * JSON轉物件功能
	 * 
	 * @param json
	 * @param responceClass
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T json2Object(String json, Class<T> responceClass)
			throws JsonParseException, JsonMappingException, Exception {
		logger.info("=====JSON的內容:" + json + ";=====");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, responceClass);
	}

}
