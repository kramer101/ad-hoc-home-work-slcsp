package com.adhoc.homework.slcsp.service;

import com.adhoc.homework.slcsp.mapper.PlansDataMapper;
import com.adhoc.homework.slcsp.mapper.ZipCodesDataMapper;
import com.adhoc.homework.slcsp.mapper.resource.PlansDataItem;
import com.adhoc.homework.slcsp.mapper.resource.SlcspInputFileItem;
import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
Service responsible for loading data.
*/
@Service
public class DataLoaderService {

  private final FileReaderService fileReaderService;
  private final ZipCodesDataMapper zipCodesDataMapper;
  private final PlansDataMapper plansDataMapper;

  @Autowired
  public DataLoaderService(FileReaderService fileReaderService,
                           ZipCodesDataMapper zipCodesDataMapper, PlansDataMapper plansDataMapper) {
    this.fileReaderService = fileReaderService;
    this.zipCodesDataMapper = zipCodesDataMapper;
    this.plansDataMapper = plansDataMapper;
  }


  public Map<Integer, Set<StateRateAreaTuple>> loadZipData() {
    List<ZipsDataItem> zipsDataItems = fileReaderService.readZipsDataFile();
    Set<Integer> zipsInScope = loadInputZips();
    return zipCodesDataMapper.toZipCodeByStateAndAreaInScope(zipsDataItems, zipsInScope);
  }


  public Map<StateRateAreaTuple, Set<String>> loadPlanData() {
    List<PlansDataItem> plansDataItems = fileReaderService.readPlansDataFile();
    return plansDataMapper.toSilverRatesByStateAndRateArea(plansDataItems);
  }


  public Set<Integer> loadInputZips() {

    List<SlcspInputFileItem> slcspInputFileItems = fileReaderService.readSlcspInputFile();
    Set<Integer> result = new LinkedHashSet<>();

    for (SlcspInputFileItem slcspInputFileItem : slcspInputFileItems) {
      result.add(Integer.parseInt(slcspInputFileItem.getZipcode()));
    }

    return result;
  }

}
