package com.adhoc.homework.slcsp.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 State and Rate Area tuple.
 * */
@Builder
@EqualsAndHashCode
public class StateRateAreaTuple {

  private String state;
  private Integer rateArea;

}
