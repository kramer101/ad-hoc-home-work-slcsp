package com.adhoc.homework.slcsp.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FileReaderServiceTest {

  @Autowired
  private FileReaderService fileReaderService;

  @Mock
  private DirectoryStream<Path> dataDirectoryStream;

  @Mock
  private CsvMapper csvMapper;


  @Test
  public void should_Throw_Exception_If_Slcsp_File_Not_Found() {

    List<Path> files = List.of();
    Mockito.when(dataDirectoryStream.spliterator()).thenReturn(files.spliterator());
    fileReaderService.readSlcspInputFile();
  }

}