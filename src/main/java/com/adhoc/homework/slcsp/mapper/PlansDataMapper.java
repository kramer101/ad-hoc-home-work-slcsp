package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import com.adhoc.homework.slcsp.service.resource.PlansDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PlansDataMapper {


  public Map<StateRateAreaTuple, Set<Double>> toSilverRatesByStateAndRateArea(
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
        .rateArea(item.getRateArea()).build();
  }
}
