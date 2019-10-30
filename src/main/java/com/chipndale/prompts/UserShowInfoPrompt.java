package com.chipndale.prompts;

import com.chipndale.actions.UserActions;

public class UserShowInfoPrompt implements Prompt {
  @Override
  public Prompt run() {
    UserActions.showUserInfo();
    return new UserMainActionsPrompt();
  }

}
