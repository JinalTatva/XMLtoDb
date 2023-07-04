package com.iapps.xmltodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.model.Epaper;
import com.iapps.xmltodb.service.EpaperService;

import lombok.extern.java.Log;

/**
 * 
 * @author LPT163
 *
 */
@RestController
@Log
public class EpaperController {

	@Autowired
	private EpaperService epaperService;

	/**
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/upload-file")
	public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) {
		log.info("Upload File" + file.getName());
		try {
			var epaper = epaperService.uploadXmlToDB(file);
			return ResponseEntity.ok(epaper);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/files")
	public ResponseEntity<Object> getEpaperList(@RequestParam int page,@RequestParam int pageSize) {

		log.info("get Files");
		try {
			Page<Epaper> pages = epaperService.getEpaperList(page,pageSize);
			return ResponseEntity.ok(pages);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
