package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import referenceBasedTreeImplementation.BSTReferencedBased;
import referenceBasedTreeImplementation.BSTreeNode;

/**
 * Assignment Description:
 *
 * @Author: YunZe (David) Wei - 861349
 */
public class BSTreeNodeTest
{
	BSTreeNode<Double> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		bst = new BSTreeNode<>(1.1);
		bst.setLeft(new BSTreeNode<>(0.9));
		bst.setRight(new BSTreeNode<>(1.2));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		bst = null;
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeNode#hasRightChild()}.
	 * Saurav
	 */
	@Test
	public void testHasRightChild()
	{
		assertTrue(bst.hasRightChild());
		assertFalse(bst.getLeft().hasRightChild());
		assertFalse(bst.getRight().hasRightChild());
		bst.getLeft().setRight(new BSTreeNode<>(0.9));
		assertTrue(bst.hasRightChild());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeNode#hasLeftChild()}.
	 * Saurav
	 */
	@Test
	public void testHasLeftChild()
	{
		assertTrue(bst.hasLeftChild());
		assertFalse(bst.getLeft().hasLeftChild());
		assertFalse(bst.getRight().hasLeftChild());
		bst.getLeft().setLeft(new BSTreeNode<>(0.9));
		assertTrue(bst.hasLeftChild());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeNode#isLeaf()}. David
	 */
	@Test
	public void testIsLeaf()
	{
		assertFalse(bst.isLeaf());

		bst.setLeft(null);
		bst.setRight(null);

		assertTrue(bst.isLeaf());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeNode#getNumberNodes()}.
	 * David
	 */
	@Test
	public void testGetNumberNodes()
	{
		assertFalse(bst.getNumberNodes(bst) == 1);
		assertTrue(bst.getNumberNodes(bst) == 3);
		assertFalse(bst.getNumberNodes(bst) == 2);

		bst.setLeft(null);
		bst.setRight(null);

		assertTrue(bst.getNumberNodes(bst) == 1);

		bst = null;

		try
		{
			bst.getNumberNodes(bst);
		} catch (NullPointerException e)
		{
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeNode#getHeight()}. Nevyn
	 */
	@Test
	public void testGetHeight()
	{
		{
			bst.setLeft(null);
			bst.setRight(null);
			assertFalse(bst.getHeight() == 1);
			assertTrue(bst.getHeight() == 0);
		}
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTreeNode#getHeight(referenceBasedTreeImplementation.BSTreeNode)}.
	 * Yisong
	 */
	@Test
	public void testGetHeightBSTreeNodeOfE()
	{
		BSTReferencedBased bst = new BSTReferencedBased<>();
		bst.add(10);
		bst.add(20);
		bst.add(21);
		bst.add(8);
		bst.add(6);
		bst.add(16);
		bst.add(23);
		bst.add(2);
		assertFalse(bst.getHeight() != 3);
		assertTrue(bst.getHeight() == 3);
	}

}
