package com.adhoc.homework.slcsp.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 State and Rate Area tuple.
 * */
@Builder
@EqualsAndHashCode
@ToString
public class StateRateAreaTuple {

  private String state;
  private Integer rateArea;

}
