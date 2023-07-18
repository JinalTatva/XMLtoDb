package com.iapps.xmltodb;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import com.iapps.xmltodb.controller.EpaperController;
import com.iapps.xmltodb.dto.EpaperDto;
import com.iapps.xmltodb.service.EpaperService;
import com.iapps.xmltodb.util.EpaperConverter;

@SpringBootTest
class XmLtoDbTests {

	@Autowired
	private EpaperConverter epaperConverter;

	@Autowired
	private EpaperController epaperController;
	
	@Autowired
	private EpaperService epaperService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void validXml_ThenTrue() throws IOException, SAXException {
		Assert.assertTrue(epaperConverter.isValid("epaperRequest.xsd", "EpaperRequest.xml"));
	}

	@Test
	public void invalidXML_ThenFalse() throws IOException, SAXException {
		Assert.assertFalse(epaperConverter.isValid("epaperRequest.xsd", "EpaperRequestInvalid.xml"));
	}

	@Test
	public void validSortData_ThenNotNull() throws Exception {
		Page<EpaperDto> result = epaperService.getEpaperList(0, 10, "id", false, "");
		Assert.assertEquals(ResponseEntity.status(HttpStatus.OK).body(result), epaperController.getEpaperList(0, 10, "id", false, ""));
	}
}
