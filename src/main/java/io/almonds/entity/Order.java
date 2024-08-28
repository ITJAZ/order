package io.almonds.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {

  private int orderNo;
  private String item;
  private String sugarLevel;
  private String iceLevel;
  private BigDecimal price;

  private static int orderNoCounter = 0;

  public Order(String item, String sugarLevel, String iceLevel, BigDecimal price) {
    this.orderNo = ++orderNoCounter;
    this.item = item;
    this.sugarLevel = sugarLevel;
    this.iceLevel = iceLevel;
    this.price = price;
  }

  public int getOrderNo() {
    return orderNo;
  }

  public String getItem() {
    return item;
  }

  public String getSugarLevel() {
    return sugarLevel;
  }

  public String getIceLevel() {
    return iceLevel;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public String toString() {
    StringBuffer orderAsString = new StringBuffer();

    orderAsString.append("orderNo: ").append(orderNo).append(System.lineSeparator());
    orderAsString.append("item: ").append(item).append(System.lineSeparator());
    orderAsString.append("sugarLevel: ").append(sugarLevel).append(System.lineSeparator());
    orderAsString.append("iceLevel: ").append(iceLevel).append(System.lineSeparator());
    orderAsString.append("price: ").append(price).append(System.lineSeparator());

    return orderAsString.toString();
  }

}
