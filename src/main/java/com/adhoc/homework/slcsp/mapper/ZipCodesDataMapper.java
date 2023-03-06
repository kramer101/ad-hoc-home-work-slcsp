package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
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


  /**
   * Map zip codes to rate area data items.
   *
   * @param zipsDataItems zip code data items
   *
   * @return zip code to rate area map.
   */
  public Map<Integer, Set<StateRateAreaTuple>> toZipCodeByStateAndArea(
      final List<ZipsDataItem> zipsDataItems) {

    return zipsDataItems.stream()
        .collect(Collectors.groupingBy(zipsDataItem -> Integer.parseInt(zipsDataItem.getZipcode()),
            Collectors.mapping(this::toTuple, Collectors.toSet())));
  }


  private StateRateAreaTuple toTuple(final ZipsDataItem item) {
    return StateRateAreaTuple.builder()
        .state(item.getState())
        .rateArea(Integer.parseInt(item.getRateArea())).build();
  }
}
