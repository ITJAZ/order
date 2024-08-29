package io.almonds.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

import io.almonds.entity.Order;

class CsvExporter implements Exporter {

  @Override
  public void export(OutputStream os, Collection<Order> orders) {
    try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

      bufferedWriter.append(String.format("%10s", "Order No"));
      bufferedWriter.append(String.format("%10s", "Item"));
      bufferedWriter.append(String.format("%12s", "Sugar Level"));
      bufferedWriter.append(String.format("%10s", "Ice Level"));
      bufferedWriter.append(String.format("%10s", "Price"));
      bufferedWriter.newLine();
      for (Order order : orders) {
        bufferedWriter.append(String.format("%10s", order.getOrderNo()));
        bufferedWriter.append(String.format("%10s", order.getItem()));
        bufferedWriter.append(String.format("%12s", order.getSugarLevel()));
        bufferedWriter.append(String.format("%10s", order.getIceLevel()));
        bufferedWriter.append(String.format("%10s", order.getPrice().toString()));
        bufferedWriter.newLine();
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  @Override
  public String getExtensionName() {
    return "csv";
  }

}
