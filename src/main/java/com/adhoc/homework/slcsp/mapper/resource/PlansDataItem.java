package com.adhoc.homework.slcsp.mapper.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Plans data item POJO.
* */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"plan_id", "state", "metal_level", "rate", "rate_area"})
//TODO: implement using Java 'record'
public class PlansDataItem {

  @JsonProperty("plan_id")
  private String planId;

  @JsonProperty("state")
  private String state;

  @JsonProperty("metal_level")
  private String metalLevel;

  @JsonProperty("rate")
  private String rate;

  @JsonProperty("rate_area")
  private String rateArea;
}
