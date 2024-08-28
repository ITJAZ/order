package io.almonds.util;

import java.io.OutputStream;
import java.util.Collection;

import io.almonds.entity.Order;

public interface Exporter {

  static Exporter get(int mode) {
    if (mode == 1)
      return new CsvExporter();

    return null;
  }

  public void export(OutputStream os, Collection<Order> orders);

}
