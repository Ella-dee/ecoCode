package fr.cnumr.javascript;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.junit.Assert.assertEquals;

public class MyJavascriptRulesTest {

  private int NumberOfRuleInRepository = 1;

  @Test
  public void rules() {
    MyJavaScriptRules rulesDefinition = new MyJavaScriptRules();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository(MyJavaScriptRules.REPOSITORY_KEY);
    assertEquals(NumberOfRuleInRepository, repository.rules().size());
  }
}
