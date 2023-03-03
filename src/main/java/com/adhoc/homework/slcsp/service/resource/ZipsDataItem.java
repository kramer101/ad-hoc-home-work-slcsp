package com.adhoc.homework.slcsp.service.resource;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Zips data file item POJO.
 * */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"zipcode", "state", "county_code", "name", "rate_area"})
public class ZipsDataItem {
  private String zipcode;
  private String state;
  private String countyCode;
  private String name; //county name
  private Integer rateArea;
}
