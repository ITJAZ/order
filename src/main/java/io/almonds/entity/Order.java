package io.almonds.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {

  private final int orderNo;
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

  public void setItem(String item) {
    this.item = item;
  }

  public void setSugarLevel(String sugarLevel) {
    this.sugarLevel = sugarLevel;
  }

  public void setIceLevel(String iceLevel) {
    this.iceLevel = iceLevel;
  }

  public void setPrice(BigDecimal price) {
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
    StringBuffer orderAsString = new StringBuffer(System.lineSeparator());

    orderAsString.append("orderNo: ").append(orderNo).append("|");
    orderAsString.append("item: ").append(item).append("|");
    orderAsString.append("sugarLevel: ").append(sugarLevel).append("|");
    orderAsString.append("iceLevel: ").append(iceLevel).append("|");
    orderAsString.append("price: ").append(price).append(System.lineSeparator());

    return orderAsString.toString();
  }

}
