package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import com.adhoc.homework.slcsp.service.resource.PlansDataItem;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlansDataMapperTest {

  @Autowired
  private PlansDataMapper plansDataMapper;

  @Test
  public void should_Return_only_Silver() {
    List<PlansDataItem> inputItems = Lists.list(
            PlansDataItem.builder()
                .metalLevel("Silver")
                .state("AK")
                .rate(11.1)
                .rateArea(1)
            .build(),
        PlansDataItem.builder()
            .metalLevel("Gold")
            .state("AK")
            .rate(11.1)
            .rateArea(1)
            .build()
    );

    Map<StateRateAreaTuple, Set<Double>> silverRatesByStateAndRateArea =
        plansDataMapper.toSilverRatesByStateAndRateArea(inputItems);

    Assertions.assertEquals(1, silverRatesByStateAndRateArea.keySet().size());

  }

  @Test
  public void should_Return_Empty() {
    List<PlansDataItem> inputItems = Lists.list(
        PlansDataItem.builder()
            .metalLevel("Gold")
            .state("AK")
            .rate(11.1)
            .rateArea(1)
            .build()
    );

    Map<StateRateAreaTuple, Set<Double>> silverRatesByStateAndRateArea =
        plansDataMapper.toSilverRatesByStateAndRateArea(inputItems);

    Assertions.assertEquals(0, silverRatesByStateAndRateArea.keySet().size());

  }

  /**
   * "For example, if a rate area had silver plans with rates of
   * [197.3, 197.3, 201.1, 305.4, 306.7, 411.24],
   * the SLCSP for that rate area would be 201.1,
   * since it's the second lowest rate in that rate area."
   * */
  @Test
  public void should_Return_only_OneRate() {
    List<PlansDataItem> inputItems = Lists.list(
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.3)
            .rateArea(1)
            .build(),
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.3)
            .rateArea(1)
            .build()
    );

    Map<StateRateAreaTuple, Set<Double>> silverRatesByStateAndRateArea =
        plansDataMapper.toSilverRatesByStateAndRateArea(inputItems);

    Assertions.assertTrue(
        silverRatesByStateAndRateArea.entrySet().stream()
            .anyMatch(stateRateAreaTupleSetEntry ->
                stateRateAreaTupleSetEntry.getValue().size() == 1));


  }

  @Test
  public void should_Return_only_in_proper_order() {
    List<PlansDataItem> inputItems = Lists.list(
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.6)
            .rateArea(1)
            .build(),
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.3)
            .rateArea(1)
            .build(),
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.5)
            .rateArea(1)
            .build(),
        PlansDataItem.builder()
            .metalLevel("Silver")
            .state("AK")
            .rate(197.4)
            .rateArea(1)
            .build()
    );

    double expected = 197.4;
    Map<StateRateAreaTuple, Set<Double>> silverRatesByStateAndRateArea =
        plansDataMapper.toSilverRatesByStateAndRateArea(inputItems);

    StateRateAreaTuple key = StateRateAreaTuple.builder().state("AK").rateArea(1).build();
    Set<Double> value = silverRatesByStateAndRateArea.get(key);

    Iterator<Double> iterator = value.iterator();

    for (int i = 0; i < value.size(); i++) {
      Double rate = iterator.next();
      if (i == 1 && rate != expected) {
        Assertions.fail("Actual value " + rate);
      }
    }


  }

}