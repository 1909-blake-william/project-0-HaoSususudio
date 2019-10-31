package com.chipndale.driver;

import com.chipndale.prompts.MainLoginPrompt;
import com.chipndale.prompts.Prompt;

public class BankAppDriver {
  public static void main(String[] args) {
    Prompt p = new MainLoginPrompt();
    while (true) {
      p = p.run();
    }
  } 
}
