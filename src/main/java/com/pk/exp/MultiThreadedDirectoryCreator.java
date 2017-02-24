package com.pk.exp;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiThreadedDirectoryCreator {

  static String baseDir = "/home/dev/base";

  private static final Logger logger = LoggerFactory.getLogger(MultiThreadedDirectoryCreator.class);

  public static void main(String[] args) {

    logger.info("Starting threads for creating directory.");
    for (int i = 0; i < 100; i++) {
      Thread thread = new DirCreatorThread(baseDir + "/" + i);
      thread.start();
    }
    logger.info("All thread started.");
  }

  static class DirCreatorThread extends Thread {

    private String dir;

    public DirCreatorThread(String dir) {
      this.dir = dir;
    }

    @Override
    public void run() {
      Path dirPath = Paths.get(dir);
      try {
        logger.info("Creating directory " + dir);
        Files.createDirectory(dirPath);
        logger.debug("Directory {} created : {}", dirPath);
      } catch (FileAlreadyExistsException e) {
        logger.info("Directory {} already there.");
      } catch (AccessDeniedException e) {
        logger.info("You don't have permission to create directory : {}", dirPath);
      } catch (IOException e) {
        logger.error("Directory {} creation failed with exception ", dirPath, e);
      }
    }
  }

}
