package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.service.DataLoaderService;
import com.adhoc.homework.slcsp.service.FileReaderService;
import com.adhoc.homework.slcsp.service.resource.SlcspInputFileItem;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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

  private final FileReaderService fileReaderService;
  private final DataLoaderService dataLoaderService;

  @Autowired
  public Main(FileReaderService fileReaderService, DataLoaderService dataLoaderService) {
    this.fileReaderService = fileReaderService;
    this.dataLoaderService = dataLoaderService;
  }

  @Override
  public void run(String... args) throws Exception {

    List<SlcspInputFileItem> slcspInputFileItems = fileReaderService.readSlcspInputFile();

    slcspInputFileItems.remove(0); //remove the header line
    Set<Integer> zipCodesInScopeAsIntegers = slcspInputFileItems.stream()
        .filter(Objects::nonNull)
        .map(slcspInputFileItem -> Integer.parseInt(slcspInputFileItem.getZipcode()))
        .collect(Collectors.toSet());






  }
}
