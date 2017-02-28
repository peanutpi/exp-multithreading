package com.pk.exp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizationTestApp {

  private static final Logger logger = LoggerFactory.getLogger(SynchronizationTestApp.class);

  public static void main(String[] args) {

    WorkerClass wClazz = new WorkerClass();

    Thread t0 = new WorkerClassAccessorThread(wClazz, "Pratik");
    Thread t1 = new WorkerClassAccessorThread(wClazz, "Jay");
    Thread t2 = new WorkerClassAccessorThread(wClazz, "Hen");
    Thread t3 = new WorkerClassAccessorThread(wClazz, "Moh");
    Thread t4 = new WorkerClassAccessorThread(wClazz, "Kabil");

    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();

  }

  static class WorkerClassAccessorThread extends Thread {

    private WorkerClass worker;
    private String name;

    public WorkerClassAccessorThread(WorkerClass worker, String name) {
      this.worker = worker;
      this.name = name;
    }

    @Override
    public void run() {
      worker.addName(name);
    }
  }


  static class WorkerClass {

    private String lastName;
    private Integer nameCount = 0;
    private List<String> nameList = new ArrayList<String>();

    /**
     * Try to remove the synchronized block & see the logs.
     * 
     * @param name
     */
    public void addName(String name) {

      synchronized (this) {
        lastName = name;
        nameCount++;
        logger.info("Name :: {} Count :: {}", lastName, nameCount);
      }
      nameList.add(name);
    }

  }

}
