package com.adhoc.homework.slcsp.mapper.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Input file with zips POJO.
 * */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "zipcode", "rate"})
@ToString
public class SlcspInputFileItem {

  @JsonProperty("zipcode")
  private String zipcode;

  @JsonProperty("rate")
  private String rate;
}
