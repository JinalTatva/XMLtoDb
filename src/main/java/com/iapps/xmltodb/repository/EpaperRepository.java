package com.iapps.xmltodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iapps.xmltodb.model.Epaper;

/**
 * 
 * @author JinalTatva
 *
 */
public interface EpaperRepository extends JpaRepository<Epaper, Long> {

	public Page<Epaper> findByNewsPaperNameLikeIgnoreCase(Pageable pageable, String newsPaperName);
}
