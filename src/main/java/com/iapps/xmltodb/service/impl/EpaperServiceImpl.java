package com.iapps.xmltodb.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.dto.EpaperDto;
import com.iapps.xmltodb.model.Epaper;
import com.iapps.xmltodb.repository.EpaperRepository;
import com.iapps.xmltodb.service.EpaperService;
import com.iapps.xmltodb.util.EpaperConverter;
import com.iapps.xmltodb.xmlobject.EpaperRequest;

import lombok.extern.java.Log;

/**
 * 
 * @author JinalTatva
 *
 */
@Service
@Transactional
@Log
public class EpaperServiceImpl implements EpaperService {

	@Autowired
	private EpaperRepository epaperRepository;

	@Override
	public EpaperDto uploadXmlToDB(MultipartFile file) throws Exception {
		log.info("uploadXmlToDB -> Unmarshalling file and saving");
		Epaper epaper = new Epaper();
		if (file.getContentType().contains("text/xml") || file.getContentType().contains("application/xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(EpaperRequest.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			EpaperRequest epaperRequest = (EpaperRequest) jaxbUnmarshaller.unmarshal(file.getInputStream());

			 epaper = Epaper.builder()
	        .newsPaperName(epaperRequest.getDeviceInfo().getName())
	        .height(epaperRequest.getDeviceInfo().getScreenInfo().getHeight())
	        .width(epaperRequest.getDeviceInfo().getScreenInfo().getWidth())
	        .dpi(epaperRequest.getDeviceInfo().getScreenInfo().getDpi())
	        .createdAt(Instant.now())
	        .uploadedAt(Instant.now())
	        .fileName(file.getName())
	        .build();
		}else {
			throw new Exception("Invalid File");
		}
		
		return EpaperConverter.convert(epaperRepository.save(epaper));
	}

	@Override
	public Page<EpaperDto> getEpaperList(Integer pageNo,
			Integer pageSize, String sortBy, Boolean asc, String search) throws Exception {

		
		Pageable pageable = PageRequest.of
				(pageNo == null ? 0 : pageNo,
				pageSize == null ? (Integer.MAX_VALUE - 1) : pageSize == Integer.MAX_VALUE ? (pageSize - 1) : pageSize,
				Sort.by(asc == null || asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy == null ? "id" : sortBy));
		if(!StringUtils.isEmpty(search)) {
			return epaperRepository.findByNewsPaperNameLikeIgnoreCase(pageable,"%"+search+"%").map(EpaperConverter::convert);
		}
		else
			return epaperRepository.findAll(pageable).map(EpaperConverter::convert);

	}
}
