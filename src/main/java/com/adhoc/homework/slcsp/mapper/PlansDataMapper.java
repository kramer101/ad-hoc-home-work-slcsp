package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.PlansDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * Mapper for plans data.
 * */
@Component
public class PlansDataMapper {


  /**
   * Map StateRateAreaTuple to a set of rates.
   * Rates with invalid non-numeric values will be filtered out.
   * */
  public Map<StateRateAreaTuple, Set<Double>> toSilverRatesByStateAndRateArea(
      final List<PlansDataItem> plansDataItems) {
    return plansDataItems.stream()
        .filter(plansDataItem -> StringUtils
            .equalsIgnoreCase(plansDataItem.getMetalLevel(), "Silver"))
        .filter(plansDataItem -> NumberUtils.isCreatable(plansDataItem.getRate()))
        .collect(Collectors.groupingBy(
            this::toTuple, Collectors.mapping(plansDataItem ->
                Double.parseDouble(plansDataItem.getRate()), Collectors.toSet())));
  }

  private StateRateAreaTuple toTuple(final PlansDataItem item) {
    return StateRateAreaTuple.builder()
        .state(item.getState())
        .rateArea(Integer.parseInt(item.getRateArea())).build();
  }
}
