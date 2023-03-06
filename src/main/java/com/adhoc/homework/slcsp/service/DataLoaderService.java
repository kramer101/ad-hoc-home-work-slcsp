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
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for loading data.
 */
@Service
public class DataLoaderService {

  private final FileReaderService fileReaderService;
  private final ZipCodesDataMapper zipCodesDataMapper;
  private final PlansDataMapper plansDataMapper;

  /**
   * Constructor with Spring's Autowire.
   */
  @Autowired
  public DataLoaderService(FileReaderService fileReaderService,
                           ZipCodesDataMapper zipCodesDataMapper, PlansDataMapper plansDataMapper) {
    this.fileReaderService = fileReaderService;
    this.zipCodesDataMapper = zipCodesDataMapper;
    this.plansDataMapper = plansDataMapper;
  }


  /**
   * Load and map zip codes data.
   *
   * @return mapped objects for the zip codes in scope (as defined by the input file)
   */
  public Map<Integer, Set<StateRateAreaTuple>> loadZipData() {
    List<ZipsDataItem> zipsDataItems = fileReaderService.readZipsDataFile();

    Map<Integer, Set<StateRateAreaTuple>> zipCodeByStateAndArea =
        zipCodesDataMapper.toZipCodeByStateAndArea(zipsDataItems);

    Set<Integer> zipsInScope = loadInputZips();
    zipCodeByStateAndArea.keySet().retainAll(zipsInScope);
    return zipCodeByStateAndArea;
  }

  public Map<StateRateAreaTuple, Set<Double>> loadPlanData() {
    List<PlansDataItem> plansDataItems = fileReaderService.readPlansDataFile();
    return plansDataMapper.toSilverRatesByStateAndRateArea(plansDataItems);
  }

  /**
   * Load and map the input zip codes.
   * TODO: move the mapping logic to a separate mapper
   *
   * @return valid (numerical) zip codes objects mapped as Integer
   */
  public Set<Integer> loadInputZips() {

    List<SlcspInputFileItem> slcspInputFileItems =
        fileReaderService.readSlcspInputFile().stream()
            .filter(slcspInputFileItem -> NumberUtils.isCreatable(slcspInputFileItem.getZipcode()))
            .toList();
    Set<Integer> result = new LinkedHashSet<>();

    for (SlcspInputFileItem slcspInputFileItem : slcspInputFileItems) {
      result.add(Integer.parseInt(slcspInputFileItem.getZipcode()));
    }

    return result;
  }

}
