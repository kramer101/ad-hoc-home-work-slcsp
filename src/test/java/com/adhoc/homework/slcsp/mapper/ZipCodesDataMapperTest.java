package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ZipCodesDataMapperTest {

  @Autowired
  private ZipCodesDataMapper zipCodesDataMapper;


  @Test
  public void should_not_return_zipcodes_not_in_scope() {

    Set<Integer> zipCodesInScope = Sets.set(
        111111
    );

    List<ZipsDataItem> zipsDataItems = Lists.list(
        ZipsDataItem.builder().zipcode("111111").build(),
        ZipsDataItem.builder().zipcode("222222").build()
    );

    Map<Integer, List<StateRateAreaTuple>> zipCodeByStateAndAreaInScope =
        zipCodesDataMapper.toZipCodeByStateAndAreaInScope(zipsDataItems, zipCodesInScope);

    Assertions.assertEquals(1, zipCodeByStateAndAreaInScope.keySet().size());
  }


  @Test
  public void should_not_contain_duplicate_zipcodes() {

    Set<Integer> zipCodesInScope = Sets.set(
        111111
    );

    List<ZipsDataItem> zipsDataItems = Lists.list(
        ZipsDataItem.builder().zipcode("111111").state("AR").rateArea("1").build(),
        ZipsDataItem.builder().zipcode("111111").state("AR").rateArea("1").build()
    );

    Map<Integer, List<StateRateAreaTuple>> zipCodeByStateAndAreaInScope =
        zipCodesDataMapper.toZipCodeByStateAndAreaInScope(zipsDataItems, zipCodesInScope);

    Assertions.assertEquals(1, zipCodeByStateAndAreaInScope.keySet().size());
  }


}