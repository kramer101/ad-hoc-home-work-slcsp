package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.mapper.resource.SlcspResult;
import com.adhoc.homework.slcsp.mapper.resource.StateRateAreaTuple;
import com.adhoc.homework.slcsp.service.DataLoaderService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class containing the main logic of the solution.
 * */
@Component
public class SlcspResolver {

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());

  private final DataLoaderService dataLoaderService;

  @Autowired
  public SlcspResolver(DataLoaderService dataLoaderService) {
    this.dataLoaderService = dataLoaderService;
  }

  /**
   * Returns results as zip and the SLCSP rate.
   * */
  public Set<SlcspResult> getResults() {
    Set<SlcspResult> results = new LinkedHashSet<>();

    //read the slcsp.csv file
    Set<Integer> zipCodesInScope = dataLoaderService.loadInputZips();

    /*
    read the plans.csv file.
    Later, this will be used as a lookup by STATE+RATE_AREA to find any
    matching ZIP in zips.csv
    */
    Map<StateRateAreaTuple, Set<Double>> plansData = dataLoaderService.loadPlanData();

    //read the zips.csv data
    Map<Integer, Set<StateRateAreaTuple>> zipCodesData = dataLoaderService.loadZipData();

    //iterate in the order specified in slcsp.csv
    Iterator<Integer> zipCodesInScopeIterator = zipCodesInScope.iterator();
    while (zipCodesInScopeIterator.hasNext()) {
      results.add(
          getResultForZipCode(plansData, zipCodesData, zipCodesInScopeIterator)
      );
    }
    return results;
  }

  private SlcspResult getResultForZipCode(Map<StateRateAreaTuple, Set<Double>> plansData,
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

      logger.info("Matching key for zip code {}: {}", zipCodeFromInput, matchingKeyTuplesForThisZipCode);
      matchingKeyTuplesForThisZipCode.forEach(stateRateAreaTuple -> {
        Set<Double> plans = plansData.get(stateRateAreaTuple);
        if (Objects.nonNull(plans)) {
          //found plans by tulip (state+rate area)
          ratesForZip.addAll(plans); //extract the rate amount
        }
      });
    }

    List<Double> ratesForZipAsList = new ArrayList<>(ratesForZip); //for ease of getting index(1)
    logger.info("Rates for zip code {}: {}", zipCodeFromInput, ratesForZipAsList);
    if (ratesForZipAsList.size() > 1) {
      rate = ratesForZipAsList.get(1).toString();
    }
    return new SlcspResult(zipCodeFromInput, rate);
  }
}
