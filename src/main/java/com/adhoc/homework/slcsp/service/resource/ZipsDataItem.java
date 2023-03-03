package com.adhoc.homework.slcsp.service.resource;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * Zips data file item POJO.
 * */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"zipcode", "state", "county_code", "name", "rate_area"})
public class ZipsDataItem {

  @Builder.Default
  private String zipcode = "-1";

  @Builder.Default
  private String state = StringUtils.EMPTY;

  @Builder.Default
  private String countyCode = StringUtils.EMPTY;

  @Builder.Default
  private String name = StringUtils.EMPTY; //county name

  @Builder.Default
  private String rateArea = "-1";
}
