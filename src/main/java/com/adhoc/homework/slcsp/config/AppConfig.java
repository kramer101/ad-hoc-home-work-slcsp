package com.adhoc.homework.slcsp.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 App configuration.
 */

@Configuration
public class AppConfig {
  /**
   Load /resources.
   * */

  @Value("classpath:data")
  private Resource dataFolderResource;

  /**
  Bean that loads the data directory as DirectoryStream.
   * */
  @Bean
  public DirectoryStream<Path> dataDirectoryStream() {
    try {

      return Files.newDirectoryStream(Paths.get(dataFolderResource.getURI()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Bean
  public CsvMapper csvMapper() {
    return new CsvMapper();
  }
}
