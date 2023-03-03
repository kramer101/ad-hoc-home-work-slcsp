package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.service.DataLoaderService;
import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 Entry point class.
 */
@Component
public class Main implements CommandLineRunner {
  private final Logger logger = LoggerFactory.getLogger(getClass().getName());

  private final DataLoaderService dataLoaderService;

  @Autowired
  public Main(DataLoaderService dataLoaderService) {
    this.dataLoaderService = dataLoaderService;
  }

  @Override
  public void run(String... args) throws Exception {

    Map<StateRateAreaTuple, Set<String>> plansData =
        dataLoaderService.loadPlanData();

    Map<Integer, List<StateRateAreaTuple>> zipCodesData = dataLoaderService.loadZipData();

    Set<Integer> zipCodesInScope = dataLoaderService.loadInputZips();
    Iterator<Integer> zipCodesInScopeIterator = zipCodesInScope.iterator();

    System.out.println("zipcode,rate");

    while (zipCodesInScopeIterator.hasNext()) {
      Integer zipCodeFromInput = zipCodesInScopeIterator.next();
      System.out.println(zipCodeFromInput + ",");
    }


  }
}
