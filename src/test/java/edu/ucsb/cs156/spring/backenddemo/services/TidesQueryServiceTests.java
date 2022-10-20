package edu.ucsb.cs156.spring.backenddemo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import javax.print.attribute.standard.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(TidesQueryService.class)
public class TidesQueryServiceTests {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private TidesQueryService earthquakeQueryService;

    @Test
    public void test_getJSON() {
        String beginDate = "20220101";
        String endDate = "20220104";
        String station = "9411340";

        String expectedUrl = TidesQueryService.ENDPOINT.replace("{beginDate}", beginDate)
                .replace("{endDate}", endDate)
                .replace("{station}", station);

        String mockResult = "this is a mock result";

        this.mockRestServiceServer.expect(requestTo(expectedUrl))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(mockResult, MediaType.APPLICATION_JSON));

        String actualResult = earthquakeQueryService.getJSON(beginDate, endDate, station);
        assertEquals(actualResult, mockResult);
    }
}
