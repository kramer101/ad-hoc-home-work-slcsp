package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.service.DataLoaderService;
import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    Map<StateRateAreaTuple, Set<String>> plansData = dataLoaderService.loadPlanData();

    Map<Integer, List<StateRateAreaTuple>> zipCodesData = dataLoaderService.loadZipData();

    Set<Integer> zipCodesInScope = dataLoaderService.loadInputZips();
    Iterator<Integer> zipCodesInScopeIterator = zipCodesInScope.iterator();

    System.out.println("zipcode,rate");


    while (zipCodesInScopeIterator.hasNext()) {
      String rate = "";
      Integer zipCodeFromInput = zipCodesInScopeIterator.next();
      List<Double> ratesForZip = new ArrayList<>();

      List<StateRateAreaTuple> matchingKeyTupleForThisZipCode = zipCodesData.get(zipCodeFromInput);

      if (Objects.nonNull(matchingKeyTupleForThisZipCode)) {
        matchingKeyTupleForThisZipCode.forEach(stateRateAreaTuple -> {
          Set<String> rates = plansData.get(stateRateAreaTuple);
          if (Objects.nonNull(rates)) {
            rates.forEach(rateAsStringFromCsv ->
                ratesForZip.add(Double.parseDouble(rateAsStringFromCsv)));
          }
        });
      }

      ratesForZip.sort(Comparator.naturalOrder());

      if (ratesForZip.size() > 1) {
        rate = ratesForZip.get(1).toString();
      }
      System.out.println(zipCodeFromInput + "," + rate);
    }

  }
}
