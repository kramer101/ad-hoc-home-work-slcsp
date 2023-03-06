package com.adhoc.homework.slcsp.mapper.resource;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 State and Rate Area tuple.
 * */
@Builder
@EqualsAndHashCode
@ToString
//TODO: implement using Java 'record'
public class StateRateAreaTuple {

  private String state;
  private Integer rateArea;

}
