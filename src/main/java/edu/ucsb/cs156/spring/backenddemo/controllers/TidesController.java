package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api("Historical tide data from NOAA")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @ApiOperation("Get historical tide data from NOAA.")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
            @ApiParam("first date in range to query in yyyyMMdd format") @RequestParam String beginDate,
            @ApiParam("final date in range to query in yyyyMMdd format") @RequestParam String endDate,
            @ApiParam("NOAA station code") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides: beginDate={}, endDate={}, station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }
}
