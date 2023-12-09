package org.honeywell.serviceimpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.honeywell.bean.CityBean;
import org.honeywell.service.HwReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HwReadServiceImpl implements HwReadService {

	Logger logger = LoggerFactory.getLogger(HwReadServiceImpl.class);
	
	@Value("${cities.file.path}")
	String citiesJsonPath;

	/**
	 * This method will return cities from a JSON file and returns a list of CityBean objects.
	 * @return A list of CityBean objects representing information about cities.
	 * @throws Exception If there is an issue reading the JSON file or processing its content.
	 */
	@Override
	public List<CityBean> getCitiesInfo() {
		logger.info("getCitiesInfo: retriving the cities information.");
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<CityBean>> typeReference = new TypeReference<>(){};
		List<CityBean> cities = new ArrayList<>();
		try
		{
			ClassPathResource resource = new ClassPathResource(citiesJsonPath);
			String jsonString = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
			cities =  objectMapper.readValue(jsonString, typeReference);
		} catch (Exception e) {
			logger.error("Exception while retriving the cities information: "+e.getMessage());
			cities = new ArrayList<>();
		}
		return cities;
	}
}
