package com.adhoc.homework.slcsp.service;


import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.adhoc.homework.slcsp.mapper.PlansDataMapper;
import com.adhoc.homework.slcsp.mapper.ZipCodesDataMapper;
import com.adhoc.homework.slcsp.mapper.resource.SlcspInputFileItem;
import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DataLoaderServiceTest {

  @Mock
  private FileReaderService fileReaderService;

  @Mock
  private ZipCodesDataMapper zipCodesDataMapper;

  @Mock
  private PlansDataMapper plansDataMapper;

  @InjectMocks
  private DataLoaderService dataLoaderService;

  @BeforeTestMethod
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void should_return_only_zips_in_scope() {

    when(fileReaderService.readSlcspInputFile()).thenReturn(List.of(
        SlcspInputFileItem.builder().zipcode("11111").build()
    )); //only one zip code is in scope

    when(fileReaderService.readZipsDataFile()).thenReturn(List.of(
       ZipsDataItem.builder().zipcode("11111").build(),
        ZipsDataItem.builder().zipcode("22222").build()
    ));

    Map<Integer, Set<StateRateAreaTuple>> zipCodeByStateAndArea = new HashMap<>();
    zipCodeByStateAndArea.put(11111, Set.of());
    zipCodeByStateAndArea.put(22222, Set.of());
    when(zipCodesDataMapper.toZipCodeByStateAndArea(anyList())).thenReturn(zipCodeByStateAndArea);

    Map<Integer, Set<StateRateAreaTuple>> integerSetMap = dataLoaderService.loadZipData();
    Assertions.assertEquals(1, integerSetMap.size());
  }

}