package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.service.DataLoaderService;
import com.adhoc.homework.slcsp.service.StateRateAreaTuple;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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

    Map<Integer, Set<StateRateAreaTuple>> zipCodesData = dataLoaderService.loadZipData();

    Set<Integer> zipCodesInScope = dataLoaderService.loadInputZips();
    Iterator<Integer> zipCodesInScopeIterator = zipCodesInScope.iterator();

    System.out.println("zipcode,rate"); //printing header

    while (zipCodesInScopeIterator.hasNext()) {
      Result result =
          getResultForZipCode(plansData, zipCodesData, zipCodesInScopeIterator);
      System.out.println(result.zipCodeFromInput() + "," + result.rate());
    }

  }

  private Result getResultForZipCode(Map<StateRateAreaTuple, Set<String>> plansData,
                                     Map<Integer, Set<StateRateAreaTuple>> zipCodesDataWithTuples,
                                     Iterator<Integer> zipCodesInScopeIterator) {
    String rate = "";

    Integer zipCodeFromInput = zipCodesInScopeIterator.next();
    SortedSet<Double> ratesForZip = new TreeSet<>();
    //rates from all the plans for this zip+state+rate area
    //only need unique values, hence using a set

    //we are only interested in zip codes from the input file
    Set<StateRateAreaTuple> matchingKeyTuplesForThisZipCode =
        zipCodesDataWithTuples.get(zipCodeFromInput);

    if (Objects.nonNull(matchingKeyTuplesForThisZipCode)) {

      logger.info("matchingKeyTupleForThisZipCode: " + matchingKeyTuplesForThisZipCode);
      matchingKeyTuplesForThisZipCode.forEach(stateRateAreaTuple -> {
        Set<String> plans = plansData.get(stateRateAreaTuple);
        if (Objects.nonNull(plans)) {
          //found plans by tulip (state+rate area)
          plans.forEach(rateAsStringFromCsv ->
              ratesForZip.add(Double.parseDouble(rateAsStringFromCsv))); //extract the rate amount
        }
      });
    }

    List<Double> ratesForZipAsList = new ArrayList<>(ratesForZip); //for ease of getting index(1)
    logger.info("ratesForZipAsList : " + ratesForZipAsList);
    if (ratesForZipAsList.size() > 1) {
      rate = ratesForZipAsList.get(1).toString();
    }
    return new Result(rate, zipCodeFromInput);
  }

  private record Result(String rate, Integer zipCodeFromInput) {
  }
}
