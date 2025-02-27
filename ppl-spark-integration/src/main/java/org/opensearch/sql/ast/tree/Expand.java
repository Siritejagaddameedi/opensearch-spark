/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.sql.ast.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.opensearch.sql.ast.AbstractNodeVisitor;
import org.opensearch.sql.ast.Node;
import org.opensearch.sql.ast.expression.Field;
import org.opensearch.sql.ast.expression.UnresolvedAttribute;
import org.opensearch.sql.ast.expression.UnresolvedExpression;

import java.util.List;
import java.util.Optional;

/** Logical plan node of Expand */
@RequiredArgsConstructor
public class Expand extends UnresolvedPlan {
  private UnresolvedPlan child;

  @Getter
  private final Field field;
  @Getter
  private final Optional<UnresolvedExpression> alias;
  
  @Override
  public Expand attach(UnresolvedPlan child) {
    this.child = child;
    return this;
  }

  @Override
  public List<? extends Node> getChild() {
    return child == null ? List.of() : List.of(child);
  }

  @Override
  public <T, C> T accept(AbstractNodeVisitor<T, C> nodeVisitor, C context) {
    return nodeVisitor.visitExpand(this, context);
  }
}
