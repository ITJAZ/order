package io.almonds.util;

import java.io.OutputStream;
import java.util.Collection;

import io.almonds.entity.Order;

public interface Exporter {

  static Exporter get(int mode) {
    if (mode == 1)
      return new CsvExporter();
    if (mode == 2)
      return new ExcelExporter();

    return null;
  }

  void export(OutputStream os, Collection<Order> orders);

  String getExtensionName();

}
