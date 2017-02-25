package com.pk.exp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependentWorker {

  private static final Logger logger = LoggerFactory.getLogger(DependentWorker.class);


  public static void main(String[] args) {
    Thread t0 = new SupervisorThread();
    Thread t1 = new LabourThread(t0);

    t0.start();
    t1.start();
  }

  static class SupervisorThread extends Thread {
    @Override
    public void run() {
      try {
        logger.info("Supervisor started his work.");
        Thread.sleep(5000l);
        logger.info("Supervisor done with his work, so deligating his work to labour.");
      } catch (InterruptedException e) {
        logger.error("Thread interrupted. ", e);
      }
    }
  }

  static class LabourThread extends Thread {

    Thread primaryWorker;

    public LabourThread(Thread primaryWorker) {
      this.primaryWorker = primaryWorker;
    }

    @Override
    public void run() {
      try {
        logger.info("Waiting for Supervisor Worker to finish the work.");
        primaryWorker.join();
        logger.info("Labour started his work.");
      } catch (InterruptedException e) {
        logger.error("Thread interrupted. ", e);
      }
    }
  }
}
