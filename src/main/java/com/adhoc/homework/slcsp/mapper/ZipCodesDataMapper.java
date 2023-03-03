package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import com.adhoc.homework.slcsp.service.resource.ZipsDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper for data from zips.csv
 * */
@Component
public class ZipCodesDataMapper {


  public Map<Integer, List<StateRateAreaTuple>> toZipCodeByStateAndAreaInScope(
      final List<ZipsDataItem> zipsDataItems, final Set<Integer> zipCodesInScope) {

    Map<Integer, List<StateRateAreaTuple>> resultMap = zipsDataItems.stream()
        .collect(Collectors.groupingBy(zipsDataItem -> Integer.parseInt(zipsDataItem.getZipcode()),
            Collectors.mapping(this::toTuple, Collectors.toList())));

    resultMap.keySet().retainAll(zipCodesInScope);

    return resultMap;
  }


  private StateRateAreaTuple toTuple(final ZipsDataItem item) {
    return StateRateAreaTuple.builder()
        .state(item.getState())
        .rateArea(Integer.parseInt(item.getRateArea())).build();
  }
}
