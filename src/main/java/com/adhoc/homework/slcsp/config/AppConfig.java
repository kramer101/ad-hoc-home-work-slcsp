package com.adhoc.homework.slcsp.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 App configuration.
 */

@Configuration
public class AppConfig {

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Value("target/classes/data")
  private FileSystemResource dataFolderResource;

  /**
  Bean that loads the data directory as DirectoryStream.
   * */
  @Bean
  public DirectoryStream<Path> dataDirectoryStream() {
    try {
      logger.info("Loading directory " + dataFolderResource.getURI());
      return Files.newDirectoryStream(Paths.get(dataFolderResource.getURI()));
    } catch (IOException e) {
      throw new RuntimeException("Error loading data directory", e);
    }
  }


  @Bean
  public CsvMapper csvMapper() {
    return new CsvMapper();
  }
}
