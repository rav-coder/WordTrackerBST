package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTReferencedBased;
import referenceBasedTreeImplementation.BSTreeNode;

/**
 * Assignment Description:
 *
 * @Author: YunZe (David) Wei - 861349
 */
public class BSTReferencedBasedTest
{
	BSTReferencedBased<Double> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		bst = new BSTReferencedBased<>();
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
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#getRoot()}.
	 * David
	 */
	@Test
	public void testGetRoot()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);

		try
		{
			assertTrue(bst.getRoot().getElement() == 1.1);
		} catch (TreeException e)
		{
			assertTrue(false);
			e.printStackTrace();
		}

		bst = new BSTReferencedBased<>();

		try
		{
			bst.getRoot();
		} catch (TreeException e)
		{
			assertTrue(true);
		}

	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#getHeight()}. Nevyn
	 */
	@Test
	public void testGetHeight()
	{
		bst.add(10.0);
		bst.add(20.0);
		bst.add(21.0);
		bst.add(8.0);
		bst.add(6.0);
		bst.add(16.0);
		bst.add(23.0);
		bst.add(2.0);
		assertFalse(bst.getHeight() != 3);
		assertTrue(bst.getHeight() == 3);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#size()}.
	 * Nevyn
	 */
	@Test
	public void testSize()
	{
		{
			bst.add(1.0);
			assertEquals(1, bst.size());
			bst.add(2.0);
			assertEquals(2, bst.size());
			bst.add(3.0);
			assertEquals(3, bst.size());
			bst.add(4.0);
			assertEquals(4, bst.size());
		}
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#isEmpty()}.
	 * Saurav
	 */
	@Test
	public void testIsEmpty()
	{
		assertTrue(bst.isEmpty());
		bst.add(10.0);
		assertFalse(bst.isEmpty());
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTReferencedBased#clear()}.
	 * Saurav
	 */
	@Test
	public void testClear()
	{
		bst.add(10.0);
		bst.add(10.0);
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#contains(java.lang.Comparable)}.
	 * David
	 */
	@Test
	public void testContains()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);

		try
		{
			assertTrue(bst.contains(2.2));
			assertTrue(bst.contains(1.1));
			assertTrue(bst.contains(0.9));
			assertFalse(bst.contains(1.0));
			assertFalse(bst.contains(null));
		} catch (TreeException e)
		{
			assertTrue(false);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#search(java.lang.Comparable)}.
	 * YiSong
	 */
	@Test
	public void testSearch()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);
		bst.add(1.0);
		bst.add(1.9);
		bst.add(3.1);
		bst.add(0.5);
		bst.add(4.5);

		try
		{
			assertEquals((Double) 1.1, bst.search(1.1).getElement());
			assertEquals((Double) 2.2, bst.search(2.2).getElement());
			assertEquals((Double) 0.9, bst.search(0.9).getElement());
			assertEquals((Double) 0.8, bst.search(0.8).getElement());
			assertEquals((Double) 5.5, bst.search(5.5).getElement());
			assertEquals((Double) 1.0, bst.search(1.0).getElement());
			assertEquals((Double) 1.9, bst.search(1.9).getElement());
			assertEquals((Double) 3.1, bst.search(3.1).getElement());
			assertEquals((Double) 0.5, bst.search(0.5).getElement());
			assertEquals((Double) 4.5, bst.search(4.5).getElement());
			assertNull(bst.search(4.1));

		} catch (TreeException e)
		{

			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#add(java.lang.Comparable)}.
	 * YiSong
	 */
	@Test
	public void testAdd()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);
		bst.add(1.0);
		bst.add(1.9);
		bst.add(3.1);
		bst.add(0.5);
		bst.add(4.5);
		assertEquals(10, bst.size());
		try
		{
			bst.add(null);
			fail("Not yet implemented");
		} catch (NullPointerException e)
		{
			// pass
		}
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#inorderIterator()}. Yisong
	 */
	@Test
	public void testInorderIterator()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);
		bst.add(1.0);
		bst.add(1.9);
		utilities.Iterator<Double> it = bst.inorderIterator();
		assertTrue(it.hasNext());
		assertEquals((Double) 0.8, it.next());
		assertEquals((Double) 0.9, it.next());
		assertEquals((Double) 1.0, it.next());
		assertEquals((Double) 1.1, it.next());
		assertEquals((Double) 1.9, it.next());
		assertEquals((Double) 2.2, it.next());
		assertEquals((Double) 5.5, it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#preorderIterator()}.
	 * 
	 */
	@Test
	public void testPreorderIterator()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);
		bst.add(1.0);
		bst.add(1.9);
		utilities.Iterator<Double> it = bst.preorderIterator();
		assertTrue(it.hasNext());
		assertEquals((Double) 1.1, it.next());
		assertEquals((Double) 0.9, it.next());
		assertEquals((Double) 0.8, it.next());
		assertEquals((Double) 1.0, it.next());
		assertEquals((Double) 2.2, it.next());
		assertEquals((Double) 1.9, it.next());
		assertEquals((Double) 5.5, it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Test method for
	 * {@link referenceBasedTreeImplementation.BSTReferencedBased#postorderIterator()}.
	 * 
	 */
	@Test
	public void testPostorderIterator()
	{
		bst.add(1.1);
		bst.add(2.2);
		bst.add(0.9);
		bst.add(0.8);
		bst.add(5.5);
		bst.add(1.0);
		bst.add(1.9);
		utilities.Iterator<Double> it = bst.postorderIterator();
		assertTrue(it.hasNext());
		assertEquals((Double) 0.8, it.next());
		assertEquals((Double) 1.0, it.next());
		assertEquals((Double) 0.9, it.next());
		assertEquals((Double) 1.9, it.next());
		assertEquals((Double) 5.5, it.next());
		assertEquals((Double) 2.2, it.next());
		assertEquals((Double) 1.1, it.next());
		assertFalse(it.hasNext());
	}

}
