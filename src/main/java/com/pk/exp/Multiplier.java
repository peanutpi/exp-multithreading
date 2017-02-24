package com.pk.exp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Multiplier {
  static float[] a = {1, 2.22f, 3, 4, 5, 6, 7, 8, 9, 10};
  static float[] b = {10, 9, 8, 7, 6, 5, 4.56f, 3, 2, 1.1f};
  static float[] c = new float[10];

  static final Logger logger = LoggerFactory.getLogger(Multiplier.class);

  public static void main(String[] args) {

    if (a.length != b.length) {
      throw new IllegalStateException("both arrays shoud be of same length");
    }

    c = new float[a.length];
    for (int i = 0; i < a.length; i++) {
      Thread t = new MultiplierThread(i);
      t.start();
    }
    logger.info("All thread started successfully.");
  }

  static class MultiplierThread extends Thread {

    int index;

    public MultiplierThread(int index) {
      this.index = index;
    }

    @Override
    public void run() {
      c[index] = a[index] * b[index];
      logger.info("{} x {} = {}", a[index], b[index], c[index]);
    }
  }
}
