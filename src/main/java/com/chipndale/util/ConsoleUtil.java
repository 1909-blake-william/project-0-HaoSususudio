package com.chipndale.util;

import com.chipndale.models.IHavingTableHeader;

public class ConsoleUtil {

  public static void echo(String message) {
    System.out.println(">> " + message);
  }

  public static void hLine(int length) {
    StringBuilder hl = new StringBuilder();
    for (int i = 0; i < length; i++) {
      hl.append('-');
    }
    System.out.println(hl);
  }

  public static String padRToLen(String str, int length) {
    return String.format("%0$-" + length + "s", str);
  }
  
  public static String padLToLen(String str, int length) {
    return String.format( "%0$" + length + "s", str);
  }

  public static <T extends IHavingTableHeader> void printAsTable(Iterable<T> iter) {
    boolean isTableHeaderPrinted = false;
    for (IHavingTableHeader T : iter) {
      if (!isTableHeaderPrinted) {
        System.out.println(T.toTableHeader());
        hLine(T.toTableHeader().length());
        isTableHeaderPrinted = true;
      }
      System.out.println(T);
    }
  }

  public static void printAsTable(IHavingTableHeader cndModel) {
    System.out.println();
    System.out.println(cndModel.toTableHeader());
    hLine(cndModel.toTableHeader().length());
    System.out.println(cndModel);
    System.out.println();
  }

}
