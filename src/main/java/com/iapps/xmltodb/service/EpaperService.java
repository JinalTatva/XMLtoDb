package com.iapps.xmltodb.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.iapps.xmltodb.dto.EpaperDto;

/**
 * 
 * @author JinalTatva
 *
 */
public interface EpaperService {
	/**
	 * 
	 * @param file
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 * @throws Exception 
	 */
	public EpaperDto uploadXmlToDB(MultipartFile file) throws JAXBException, IOException, Exception;

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @param asc
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Page<EpaperDto> getEpaperList(Integer pageNo,
			Integer pageSize, String sortBy, Boolean asc, String search) throws Exception;
}
