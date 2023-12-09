package org.honeywell.controller;


import java.util.List;

import org.honeywell.bean.CityBean;
import org.honeywell.constants.HwReadConstants;
import org.honeywell.service.HwReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class HwReadController {

	Logger logger = LoggerFactory.getLogger(HwReadController.class);
	
	@Autowired
	HwReadService readService;

	@GetMapping("/getcities")
	public  ResponseEntity<?> getCitiesInformation() throws Exception
	{
		ResponseEntity<?> response = null;
		try
		{
			logger.info("getCitiesInformation: in cities api.");
			List<CityBean> citiesinfo = readService.getCitiesInfo();
			if (!citiesinfo.isEmpty())
			{
				response = new ResponseEntity<>(citiesinfo, HttpStatus.OK);
			}else {
				response = new ResponseEntity<>(HwReadConstants.CITIES_NOT_FOUND, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(HwReadConstants.CITIES_EXCEPTION+e.getMessage());
			response = new ResponseEntity<>(HwReadConstants.CITIES_EXCEPTION+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
