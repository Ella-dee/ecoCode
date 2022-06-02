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
package fr.cnumr.javascript.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.expression.UnaryExpressionTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

@Rule(
        key = "D10",
        priority = Priority.MINOR,
        name = "Developpement",
        tags = {"bug"}
)
public class PrefixAssignmentCheck extends DoubleDispatchVisitorCheck {

  private static final String MESSAGE = "Remove the usage of $i++. prefer ++$i";

  private static final Kind[] INCREMENT_DECREMENT = {
          Kind.POSTFIX_INCREMENT,
          Kind.POSTFIX_DECREMENT};


  @Override
  public void visitUnaryExpression(UnaryExpressionTree tree) {
    if (tree.is(INCREMENT_DECREMENT)) {
        addIssue(tree, String.format(MESSAGE, tree.toString()));
    }
  }


}
