package com.adhoc.homework.slcsp.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataLoaderServiceTest {


  @Autowired
  private DataLoaderService dataLoaderService;

  @BeforeTestMethod
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loadZipData() {
  }

  @Test
  public void loadPlanData() {
  }
}