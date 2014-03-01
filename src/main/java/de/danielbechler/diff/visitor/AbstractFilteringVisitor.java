/*
 * Copyright 2012 Daniel Bechler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.danielbechler.diff.visitor;

import de.danielbechler.diff.DiffNode;
import de.danielbechler.diff.Visit;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Daniel Bechler
 */
public abstract class AbstractFilteringVisitor implements DiffNode.Visitor
{
	private final Collection<DiffNode> matches = new LinkedList<DiffNode>();

	protected abstract boolean accept(final DiffNode node);

	protected void onAccept(final DiffNode node, final Visit visit)
	{
		matches.add(node);
	}

	protected void onDismiss(final DiffNode node, final Visit visit)
	{
	}

	public void accept(final DiffNode node, final Visit visit)
	{
		if (accept(node))
		{
			onAccept(node, visit);
		}
		else
		{
			onDismiss(node, visit);
		}
	}

	public Collection<DiffNode> getMatches()
	{
		return matches;
	}
}
