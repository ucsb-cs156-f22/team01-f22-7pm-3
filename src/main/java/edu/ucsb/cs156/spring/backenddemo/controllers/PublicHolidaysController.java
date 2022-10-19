package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="Public Holiday info from https://date.nager.at/Api")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays")
public class PublicHolidaysController {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	PublicHolidayQueryService publicHolidayQueryService;

	@ApiOperation(value = "Get public holidays for a given year and country")
	@GetMapping("/get")
	public ResponseEntity<String> getPublicHolidays(
		@ApiParam("2 letter country code, e.g. US, MX, CN") @RequestParam String countryCode,
		@ApiParam("year, e.g. 2012") @RequestParam String year
	) throws JsonProcessingException {
		log.info("getPublicHolidays: countryCode={} year={}", countryCode, year);
		String result = publicHolidayQueryService.getJSON(countryCode, year);
		return ResponseEntity.ok().body(result);
	}
    
}
