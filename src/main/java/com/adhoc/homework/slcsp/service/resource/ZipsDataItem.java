package com.adhoc.homework.slcsp.service.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("zipcode")
  @Builder.Default
  private String zipcode = "-1";

  @JsonProperty("state")
  @Builder.Default
  private String state = StringUtils.EMPTY;

  @JsonProperty("county_code")
  @Builder.Default
  private String countyCode = StringUtils.EMPTY;

  @JsonProperty("name")
  @Builder.Default
  private String name = StringUtils.EMPTY; //county name

  @JsonProperty("rate_area")
  @Builder.Default
  private String rateArea = "-1";
}
