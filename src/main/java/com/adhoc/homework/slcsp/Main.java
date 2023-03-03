package com.adhoc.homework.slcsp;

import com.adhoc.homework.slcsp.service.DataLoaderService;
import com.adhoc.homework.slcsp.service.FileReaderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 Entry point class.
 */
@Component
public class Main implements CommandLineRunner {
  protected final Log logger = LogFactory.getLog(getClass());
  private final FileReaderService fileReaderService;

  @Autowired
  public Main(FileReaderService fileReaderService) {
    this.fileReaderService = fileReaderService;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.debug("Running app");
    fileReaderService.readSlcspInputFile();
  }
}
