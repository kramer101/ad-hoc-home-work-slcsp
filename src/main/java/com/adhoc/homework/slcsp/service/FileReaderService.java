package com.adhoc.homework.slcsp.service;

import com.adhoc.homework.slcsp.service.resource.PlansDataItem;
import com.adhoc.homework.slcsp.service.resource.SlcspInputFileItem;
import com.adhoc.homework.slcsp.service.resource.ZipsDataItem;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for reading data from files.
 * */
@Service
public class FileReaderService {

  private final DirectoryStream<Path> dataDirectoryStream;
  private final CsvMapper csvMapper;


  @Autowired
  public FileReaderService(DirectoryStream<Path> dataDirectoryStream, CsvMapper csvMapper) {
    this.dataDirectoryStream = dataDirectoryStream;
    this.csvMapper = csvMapper;
  }

  /**
   * Read contents of slcsp.csv.
   * */
  public List<SlcspInputFileItem> readSlcspInputFile() {

    try {

      List<Path> files = findFile("slcsp.csv");
      assertFileFound(files);

      return getFileLines(files, SlcspInputFileItem.class);
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


  private <T>  List<T> getFileLines(final List<Path> files, Class<T> clazz) throws IOException {
    ObjectReader objectReader = csvMapper.readerWithTypedSchemaFor(clazz);
    try (MappingIterator<T> objectMappingIterator =
             objectReader.readValues(files.get(0).toFile())) {
      return objectMappingIterator.readAll();
    }
  }


  private List<Path> findFile(final String fileName) {
    return StreamSupport.stream(dataDirectoryStream.spliterator(), false)
        .filter(path -> path.toFile().exists()
            && path.toFile().getName().equals(fileName))
        .toList();
  }

}
