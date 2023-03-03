package com.adhoc.homework.slcsp.service.resource;

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
public class PlansDataItem {

  private String planId;
  private String state;
  private String metalLevel;
  private Double rate;
  private Integer rateArea;
}
