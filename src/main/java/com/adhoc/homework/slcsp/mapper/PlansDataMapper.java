package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import com.adhoc.homework.slcsp.service.resource.PlansDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Mapper for plans data.
 * */
@Component
public class PlansDataMapper {


  /**
   * Map to StateRateAreaTuple and set of rates.
   * */
  public Map<StateRateAreaTuple, Set<String>> toSilverRatesByStateAndRateArea(
      final List<PlansDataItem> plansDataItems) {
    return plansDataItems.stream()
        .filter(plansDataItem -> StringUtils
            .equalsIgnoreCase(plansDataItem.getMetalLevel(), "Silver"))
        .collect(Collectors.groupingBy(
            this::toTuple, Collectors.mapping(PlansDataItem::getRate, Collectors.toSet())));
  }

  private StateRateAreaTuple toTuple(final PlansDataItem item) {
    return StateRateAreaTuple.builder()
        .state(item.getState())
        .rateArea(Integer.parseInt(item.getRateArea())).build();
  }
}
