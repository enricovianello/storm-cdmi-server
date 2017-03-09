package it.grid.storm.spi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginConfiguration {

  private static final Logger log = LoggerFactory.getLogger(PluginConfiguration.class);

  private static final String CONFIG_FILENAME = "storm.properties";
  private static final String[] CONFIG_DIRS = {"", "./config"};

  public static final int DEFAULT_PORT = 9998;

  private String hostname = null;
  private int port;
  private String token = null;

  private static PluginConfiguration instance;

  static {
    Properties prop = loadPropertiesFromFile();
    instance = new PluginConfiguration(prop);
  }

  public static PluginConfiguration getInstance() {

    return instance;
  }

  private PluginConfiguration(Properties prop) {

    hostname = prop.getProperty("storm.hostname", null);
    if (hostname == null) {
      throw new IllegalArgumentException("storm.hostname value not found");
    }
    port = Integer.valueOf(prop.getProperty("storm.port", String.valueOf(DEFAULT_PORT)));
    token = prop.getProperty("storm.token", null);
    if (token == null) {
      throw new IllegalArgumentException("storm.token value not found");
    }
  }

  private static Properties loadPropertiesFromFile() {

    Properties prop = new Properties();
    for (String location : CONFIG_DIRS) {
      log.debug("Searching configuration file into {} ...", location);
      String filePath = location + File.separator + CONFIG_FILENAME;
      log.debug("Searching configuration file {} ...", filePath);
      InputStream input = null;
      try {
        input = new FileInputStream(filePath);
        log.info("Loading properties from file {}", filePath);
        prop.load(input);
      } catch (IOException ex) {
        log.debug("IOExcepption - {}", ex.getMessage());
        log.info("Skipping configuration file {} not found", filePath);
      } finally {
        if (input != null) {
          try {
            input.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return prop;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public String getToken() {
    return token;
  }

  @Override
  public String toString() {
    return "PluginConfiguration [hostname=" + hostname + ", port=" + port + ", token=" + token
        + "]";
  }

}
