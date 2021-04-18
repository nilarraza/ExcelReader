package com.excel.demo.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.demo.excelresponse.SheetResponse;
import com.excel.demo.excelresponse.TelecomAdSpendDto;
import com.excel.demo.model.TelecomAdSpend;
import com.excel.demo.repository.TelecomAdSpendRepository;
import com.excel.demo.util.ExcelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

@Service
public class ExcelService {

	@Autowired
	ExcelUtil excelUtil;
	
	@Autowired
	TelecomAdSpendRepository telecomAdSpendRepository;

	public String excelUrl = "D:\\test excel\\";

	public SheetResponse readData(MultipartFile multipartFile) {

		SheetResponse response;
		
		try {

			String filepath = excelUrl + multipartFile.getOriginalFilename();

			byte[] bytes = multipartFile.getBytes();
			java.nio.file.Path path = Paths.get(excelUrl + multipartFile.getOriginalFilename());
			Files.write(path, bytes);


			excelUtil.getSheetDetails(filepath);
		 response=	excelUtil.readExcelSheet(filepath);
		 
		 telecomAdSpendRepository.saveAll(response.getListSheetAdSpend().get(0).getListAdSpend());

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		return response;

	}

	public JSONArray getPocData() {
		
		List<TelecomAdSpend> adList= telecomAdSpendRepository.findAll();
		TelecomAdSpendDto dtoResponse=new TelecomAdSpendDto();
		List<Object> response= new ArrayList<Object>();
		//JsonArray jsonArray=new JsonArray();
		JSONArray jsonArray=new JSONArray();
		
		
		for(TelecomAdSpend adSpend : adList) {
			JSONObject json = new JSONObject(adSpend.getJsonString());  
			//System.out.println(json);
		//	System.out.println(adSpend.getJsonString());
			
			
			//dtoResponse.setId(adSpend.getId());
			//dtoResponse.setJsonObj(json);
			jsonArray.put(json.toMap());
			//response.add(json);
			
		}

		 return jsonArray;
	}

}
