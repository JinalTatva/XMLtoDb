package com.iapps.xmltodb.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iapps.xmltodb.model.Epaper;

public interface EpaperRepository extends JpaRepository<Epaper,Long> {

}
