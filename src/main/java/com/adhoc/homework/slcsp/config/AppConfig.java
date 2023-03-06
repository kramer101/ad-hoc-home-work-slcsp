package com.adhoc.homework.slcsp.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 App configuration.
 */

@Configuration
public class AppConfig {

  @Bean
  public CsvMapper csvMapper() {
    return CsvMapper.builder().build();
  }
}
