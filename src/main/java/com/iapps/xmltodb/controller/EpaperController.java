package com.iapps.xmltodb.controller;

import java.lang.System.Logger.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.dto.EpaperDto;
import com.iapps.xmltodb.service.EpaperService;

import lombok.extern.java.Log;

/**
 * 
 * @author JinalTatva
 *
 */
@RestController
@Log
@RequestMapping("/api")
public class EpaperController {

	@Autowired
	private EpaperService epaperService;

	/**
	 * 
	 * @param file
	 * @return ResponseEntity
	 */
	@PostMapping("/upload-file")
	public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) {
		log.info("Upload File" + file.getName());
		try {
			var epaperDto = epaperService.uploadXmlToDB(file);
			return ResponseEntity.status(HttpStatus.OK).body(epaperDto);
		} catch (Exception e) {
			e.printStackTrace();
			return (ResponseEntity<Object>) ResponseEntity.internalServerError();
//					(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @param asc
	 * @param search
	 * @return
	 */
	@GetMapping("/epaper-list")
	public ResponseEntity<Object> getEpaperList(
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "asc", defaultValue = "false", required = false) Boolean asc,
			@RequestParam(value = "search", defaultValue = "", required = false) String search) {

		log.info("get Epaper List");
		try {
			Page<EpaperDto> pages = epaperService.getEpaperList(pageNo, pageSize, sortBy, asc, search);
			return ResponseEntity.status(HttpStatus.OK).body(pages);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
