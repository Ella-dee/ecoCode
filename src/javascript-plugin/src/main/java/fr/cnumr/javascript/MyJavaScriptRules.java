/*
 * SonarQube JavaScript Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package fr.cnumr.javascript;

import com.google.common.collect.ImmutableList;
import fr.cnumr.javascript.checks.PrefixAssignmentCheck;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.javascript.api.CustomRuleRepository;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.util.*;

/**
 * Extension point to define a JavaScript rule repository.
 */
public class MyJavaScriptRules implements RulesDefinition, CustomRuleRepository {

  public static final String LANGUAGE = "fr/cnumr/javascript";
  public static final String NAME = "MyCompany Custom Repository";
  public static final String RESOURCE_BASE_PATH = "fr/cnumr/l10n/javascript/rules/custom";
  public static final String REPOSITORY_KEY = "cnumr-javascript";
  private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();

  /**
   * Provide the repository key
   */
  @Override
  public String repositoryKey() {
    return REPOSITORY_KEY;
  }

  /**
   * Provide the list of checks class that implements rules
   * to be part of the rule repository
   */
  @Override
  public ImmutableList<Class> checkClasses() {
    return ImmutableList.of(PrefixAssignmentCheck.class);
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, LANGUAGE).setName(NAME);

    RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH);

    ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(checkClasses()));

    setTemplates(repository);

    repository.done();
  }

  private static void setTemplates(NewRepository repository) {
    RULE_TEMPLATES_KEY.stream()
            .map(repository::rule)
            .filter(Objects::nonNull)
            .forEach(rule -> rule.setTemplate(true));
  }

}
