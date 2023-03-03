package com.adhoc.homework.slcsp.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 App configuration.
 */

@Configuration
public class AppConfig {

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());



  @Bean
  public CsvMapper csvMapper() {
    return CsvMapper.builder().build();
  }
}
