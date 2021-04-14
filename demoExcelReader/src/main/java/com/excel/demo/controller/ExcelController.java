package com.excel.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.demo.excelresponse.SheetResponse;
import com.excel.demo.service.ExcelService;

@RestController
@RequestMapping("/api")
public class ExcelController {
	
	@Autowired
	ExcelService excelService;
	
	@PostMapping("read/excel")
	public SheetResponse readExcel(MultipartFile multipartFile) {
		return excelService.readData(multipartFile);
		
	}

}
