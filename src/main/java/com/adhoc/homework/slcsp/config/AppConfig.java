package com.adhoc.homework.slcsp.config;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

  /**
   * Builds the output number formatter.
   *
   * @return output number formatter
   */
  @Bean
  public NumberFormat outputRateFormat() {
    NumberFormat numberFormat = new DecimalFormat("#.##");
    numberFormat.setMinimumFractionDigits(2);
    numberFormat.setMaximumFractionDigits(2);
    return numberFormat;
  }
}
