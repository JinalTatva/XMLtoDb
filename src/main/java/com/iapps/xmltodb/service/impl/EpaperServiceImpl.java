package com.iapps.xmltodb.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.dto.EpaperDto;
import com.iapps.xmltodb.model.Epaper;
import com.iapps.xmltodb.repository.EpaperRepository;
import com.iapps.xmltodb.service.EpaperService;
import com.iapps.xmltodb.xmlobject.EpaperRequest;

import lombok.extern.java.Log;

@Service
@Log
public class EpaperServiceImpl implements EpaperService {

	@Autowired
	private EpaperRepository epaperRepository; 
	
	@Override
	public Epaper uploadXmlToDB(MultipartFile file) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(EpaperRequest.class);
        
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        EpaperRequest epaperRequest= (EpaperRequest) jaxbUnmarshaller.unmarshal(file.getInputStream());
        
        Epaper epaper = new Epaper();
        epaper.setCreatedAt(Instant.now());
        epaper.setUploadedAt(Instant.now());
        epaper.setFileName(file.getName());
        epaper.setDpi(epaperRequest.getDeviceInfo().getScreenInfo().getDpi());
        epaper.setHeight(epaperRequest.getDeviceInfo().getScreenInfo().getHeight());
        epaper.setWidth(epaperRequest.getDeviceInfo().getScreenInfo().getWidth());
        epaper.setNewsPaperName(epaperRequest.getDeviceInfo().getName());
       
		return  epaperRepository.save(epaper);
	}

	@Override
	public Page<Epaper> getEpaperList(int page, int pageSize) throws Exception {
		Pageable pageable = PageRequest.of(page, pageSize);
		return epaperRepository.findAll(pageable);
	}
}
