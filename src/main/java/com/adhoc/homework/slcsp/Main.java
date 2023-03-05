package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.mapper.resource.SlcspResult;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 Entry point class.
 */
@Component
public class Main implements CommandLineRunner {

  private final SlcspResolver slcspResolver;

  public Main(SlcspResolver slcspResolver) {
    this.slcspResolver = slcspResolver;
  }

  @Override
  public void run(String... args) throws Exception {

    Set<SlcspResult> results = slcspResolver.getResults();

    System.out.println("------------------RESULTS START------------------");
    System.out.println("zipcode,rate"); //printing header
    for (SlcspResult slcspResult : results) {
      System.out.println(slcspResult.zipCodeFromInput() + "," + slcspResult.rate());
    }
    System.out.println("------------------RESULTS END------------------");
  }
}
