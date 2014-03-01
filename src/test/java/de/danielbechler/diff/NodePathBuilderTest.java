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

package de.danielbechler.diff;

import de.danielbechler.diff.bean.BeanPropertyElement;
import de.danielbechler.diff.collection.CollectionItemElement;
import de.danielbechler.diff.map.MapKeyElement;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Daniel Bechler
 */
public class NodePathBuilderTest
{
	@Test
	public void testWithRoot()
	{
		final NodePath nodePath = NodePath.createBuilder()
				.withRoot()
				.build();
		assertThat(nodePath.getElements()).containsOnly(RootElement.getInstance());
	}

	@Test
	public void testWithElement()
	{
		final CollectionItemElement element = new CollectionItemElement("foo");
		final NodePath nodePath = NodePath.createBuilder()
				.withRoot()
				.withElement(element)
				.build();
		assertThat(nodePath.getElements()).containsSequence(
				RootElement.getInstance(),
				element);
	}

	@Test
	public void testWithPropertyName()
	{
		final NodePath nodePath = NodePath.createBuilder()
				.withRoot()
				.withPropertyName("foo", "bar")
				.build();
		assertThat(nodePath.getElements()).containsSequence(
				RootElement.getInstance(),
				new BeanPropertyElement("foo"),
				new BeanPropertyElement("bar")
		);
	}

	@Test
	public void testWithMapKey()
	{
		final NodePath nodePath = NodePath.createBuilder()
				.withRoot()
				.withMapKey("foo")
				.build();
		assertThat(nodePath.getElements()).containsSequence(RootElement.getInstance(), new MapKeyElement("foo"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testWithMapKey_throws_exception_when_key_is_null()
	{
		NodePath.createBuilder().withRoot().withMapKey(null).build();
	}

	@Test
	public void testWithCollectionItem()
	{
		final NodePath nodePath = NodePath.createBuilder()
				.withRoot()
				.withCollectionItem("foo")
				.build();
		assertThat(nodePath.getElements()).containsSequence(RootElement.getInstance(), new CollectionItemElement("foo"));
	}

	@Test
	public void testWithPropertyPath()
	{
		final NodePath nodePath = NodePath.createBuilder()
				.withPropertyPath(NodePath
						.buildWith("foo"))
				.build();
		assertThat(nodePath.getElements()).containsSequence(RootElement.getInstance(), new BeanPropertyElement("foo"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testWithPropertyPath_throws_exception_when_property_path_is_null()
	{
		NodePath.createBuilder().withPropertyPath(null).build();
	}

	@Test
	public void testBuild_with_one_root_element_should_succeed() throws Exception
	{
		final NodePath nodePath = NodePath.createBuilder().withRoot().build();
		assertThat(nodePath.getElements()).containsOnly(RootElement.getInstance());
	}
}
