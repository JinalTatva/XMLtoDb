package com.iapps.xmltodb.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.dto.EpaperDto;
import com.iapps.xmltodb.model.Epaper;

public interface EpaperService {

	Epaper uploadXmlToDB(MultipartFile file) throws JAXBException, IOException;

	Page<Epaper> getEpaperList(int page, int pageSize) throws Exception;
}
