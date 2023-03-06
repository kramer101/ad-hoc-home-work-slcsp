package com.adhoc.homework.slcsp.mapper;

import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ZipCodesDataMapperTest {

  @InjectMocks
  private ZipCodesDataMapper zipCodesDataMapper;

  @BeforeTestMethod
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void should_not_contain_duplicate_zipcodes() {

    List<ZipsDataItem> zipsDataItems = Lists.list(
        ZipsDataItem.builder().zipcode("111111").state("AR").rateArea("1").build(),
        ZipsDataItem.builder().zipcode("111111").state("AR").rateArea("1").build()
    );

    Map<Integer, Set<StateRateAreaTuple>> zipCodeByStateAndAreaInScope =
        zipCodesDataMapper.toZipCodeByStateAndArea(zipsDataItems);

    Assertions.assertEquals(1, zipCodeByStateAndAreaInScope.keySet().size());
  }


}