package com.adhoc.homework.slcsp.service;

import com.adhoc.homework.slcsp.mapper.resource.PlansDataItem;
import com.adhoc.homework.slcsp.mapper.resource.SlcspInputFileItem;
import com.adhoc.homework.slcsp.mapper.resource.ZipsDataItem;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

/**
 * Service responsible for reading data from files.
 */
@Service
public class FileReaderService {

  @Value("target/classes/data")
  private FileSystemResource dataFolderResource;

  private final CsvMapper csvMapper;


  @Autowired
  public FileReaderService(CsvMapper csvMapper) {
    this.csvMapper = csvMapper;
  }

  /**
   * Read contents of slcsp.csv keeping lines in the provided order.
   */
  public List<SlcspInputFileItem> readSlcspInputFile() {

    try {

      List<Path> files = findFile("slcsp.csv");
      assertFileFound(files);

      List<SlcspInputFileItem> inputFileDataItems = new LinkedList<>();

      try (Stream<String> lines = Files.lines(files.get(0))) {
        lines.forEachOrdered(line -> {
          inputFileDataItems.add(
              SlcspInputFileItem.builder()
                  .zipcode(line.split(",")[0])
                  .build()
          );
        });
      }

      inputFileDataItems.remove(0); //removing header
      return inputFileDataItems;

    } catch (Exception e) {
      throw new RuntimeException("Error while reading data from slcsp.csv", e);
    }

  }

  public List<ZipsDataItem> readZipsDataFile() {
    try {
      List<Path> files = findFile("zips.csv");
      assertFileFound(files);

      return getFileLines(files, ZipsDataItem.class);
    } catch (IOException e) {
      throw new RuntimeException("Error while reading data from zips.csv", e);
    }

  }

  public List<PlansDataItem> readPlansDataFile() {
    try {
      List<Path> files = findFile("plans.csv");
      assertFileFound(files);

      return getFileLines(files, PlansDataItem.class);
    } catch (IOException e) {
      throw new RuntimeException("Error while reading data from plans.csv", e);
    }
  }


  private void assertFileFound(final List<Path> files) {
    if (CollectionUtils.isEmpty(files)) {
      throw new RuntimeException("Unable to find the file");
    }
  }


  private <T> List<T> getFileLines(final List<Path> files, Class<T> clazz) throws IOException {

    ObjectReader objectReader = csvMapper.readerWithTypedSchemaFor(clazz);

    try (MappingIterator<T> objectMappingIterator =
             objectReader.readValues(files.get(0).toFile())) {
      List<T> lines = objectMappingIterator.readAll();

      lines.remove(0); //removing header
      return lines;
    }
  }


  private List<Path> findFile(final String fileName) throws IOException {

    try (DirectoryStream<Path> directoryStream =
             Files.newDirectoryStream(Paths.get(dataFolderResource.getURI()))) {

      return StreamSupport.stream(directoryStream.spliterator(), false)
          .filter(path -> path.toFile().exists()
              && path.toFile().getName().equals(fileName))
          .toList();
    }

  }

}
