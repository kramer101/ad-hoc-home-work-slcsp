package com.adhoc.homework.slcsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
Service responsible for loading data.
*/
@Service
public class DataLoaderService {



  public List<Integer> loadInputZips() {

    return new ArrayList<>();
  }

  public Map<Integer, List<StateRateAreaTuple>> loadZipData() {
    return new HashMap<>();
  }


  public Map<StateRateAreaTuple, Set<Double>> loadPlanData() {
    return new HashMap<>();
  }

}
