package it.grid.storm.spi;

import java.util.ArrayList;
import java.util.List;

import org.indigo.cdmi.BackEndException;
import org.indigo.cdmi.BackendCapability;
import org.indigo.cdmi.CdmiObjectStatus;
import org.indigo.cdmi.spi.StorageBackend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.grid.storm.spi.config.PluginConfiguration;

public class StormStorageBackend implements StorageBackend {

  private static final Logger log = LoggerFactory.getLogger(StormStorageBackend.class);

  PluginConfiguration conf = PluginConfiguration.getInstance();

  public StormStorageBackend() {

    log.info("StormStorageBackend");
    log.info("Configuration: {}", conf);
  }

  @Override
  public List<BackendCapability> getCapabilities() throws BackEndException {

    return new ArrayList<BackendCapability>();
  }

  @Override
  public CdmiObjectStatus getCurrentStatus(String stfn) throws BackEndException {

    throw new BackEndException("not implemented");
  }

  @Override
  public void updateCdmiObject(String path, String targetCapabilitiesURI) throws BackEndException {

    throw new BackEndException("Not implemented");
  }

}
