package com.excel.demo.service;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.demo.excelresponse.SheetResponse;
import com.excel.demo.util.ExcelUtil;

@Service
public class ExcelService {

	@Autowired
	ExcelUtil excelUtil;

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

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		return response;

	}

}
