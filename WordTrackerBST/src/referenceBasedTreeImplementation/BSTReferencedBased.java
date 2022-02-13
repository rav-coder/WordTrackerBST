package referenceBasedTreeImplementation;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import exceptions.TreeException;
import utilities.BSTreeADT;
import utilities.Iterator;

/**
 * Assignment Description:
 * 
 * @param <E> the data type the user chooses to create the BST with
 *
 * @Author: Group Auron
 */
public class BSTReferencedBased<E extends Comparable<? super E>> implements BSTreeADT<E>
{
	private BSTreeNode<E> root;

	/**
	 * Initializes the newly created BSTReferencedBased
	 */
	public BSTReferencedBased()
	{
		this.root = null;
	}

	/**
	 * 
	 * Initializes the newly created BSTReferencedBased
	 * 
	 * @param root
	 */
	public BSTReferencedBased(BSTreeNode<E> root)
	{
		super();
		this.root = root;
	}

	/**
	 * auto generated serial ID
	 */
	private static final long serialVersionUID = 3935482402216378061L;
	
	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * 
	 * @return node stored at the root of tree is returned
	 * @throws TreeException if the root is empty.
	 */
	@Override
	public BSTreeNode<E> getRoot() throws TreeException
	{
		if (this.root == null)
		{
			throw new TreeException("Null root node");
		} else
		{
			return root;
		}

	}
	
	/**
	 * Determines the row height of the tree and returns that value as an
	 * integer value.
	 * @return the height of the tree.
	 */
	@Override
	public int getHeight()
	{
		return this.root.getHeight();
	}
	
	/**
	 * The number of elements currently stored in the tree is counted and
	 * the value is returned.
	 * @return number of elements currently stored in tree.
	 */
	@Override
	public int size()
	{
		return this.root.getNumberNodes(root);
	}

	/**
	 * Checks if the tree is currently empty.
	 * 
	 * @return returns boolean true if the tree is empty otherwise false. Saurav
	 */
	@Override
	public boolean isEmpty()
	{
		return (this.root == null);
	}

	/**
	 * Clears all elements currently stored in tree and makes the tree empty. Saurav
	 */
	@Override
	public void clear()
	{
		this.root = null;

	}
	
	/**
	 * Checks the current tree to see if the element passed in is stored in
	 * the tree. If the element is found in the tree the method returns true
	 * and if the element is not in the tree the method returns false.
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and
	 * false if the element is not found in the tree
	 * @throws TreeException if the tree is empty.
	 */
	@Override
	public boolean contains(E entry) throws TreeException
	{
		if (isEmpty())
		{
			throw new TreeException();
		}
		if (entry == null)
		{
			return false;
		}
		BSTreeNode<E> checknode = searchAt(root, entry);

		if (checknode == null)
		{
			return false;
		}

		if (checknode.getElement().equals(entry))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	/**
	 * Retrieves a node from the tree given the object to search for.
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws TreeException if the tree is empty
	 */
	@Override
	public BSTreeNode<E> search(E entry) throws TreeException
	{
		if (isEmpty())
			throw new TreeException();
		return searchAt(root, entry);
	}
	
	/**
	 * 
	 * searches the BST for an element
	 * 
	 * @param node root node to start the search at
	 * @param entry the element we are trying to search for
	 * @return the found node or null if entry is not found
	 */
	private BSTreeNode<E> searchAt(BSTreeNode<E> node, E entry)
	{
		if (node != null)
		{
			if (entry.compareTo(node.getElement()) == 0)
			{
				return node;
			} else if (entry.compareTo(node.getElement()) < 0)
			{
				return searchAt(node.getLeft(), entry);
			} else
			{
				return searchAt(node.getRight(), entry);
			}
		} else
		{
			return null;
		}
	}
	
	/**
	 * Adds a new element to the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being added is null
	 */
	@Override
	public boolean add(E newEntry) throws NullPointerException
	{
		if (newEntry == null)
		{
			throw new NullPointerException();
		}
		if (isEmpty())
		{
			root = new BSTreeNode<E>(newEntry);
			return true;
		} else if (!isEmpty())
		{
			addNode(newEntry, root);
			return true;
		} else
		{
			return false;
		}

	}
	
	/**
	 * adds new BST node
	 * 
	 * @param e element to add
	 * @param node root node to compare element with to see where to add it
	 */
	private void addNode(E e, BSTreeNode<E> node)
	{
		if (e.compareTo(node.getElement()) <= 0)
		{
			if (node.getLeft() == null)
			{
				node.setLeft(new BSTreeNode<E>(e));
			} else
			{
				addNode(e, node.getLeft());
			}
		} else
		{
			if (node.getRight() == null)
			{
				node.setRight(new BSTreeNode<E>(e));
			} else
			{
				addNode(e, node.getRight());
			}
		}

	}
	
	/**
	 * Generates an in-order iteration over the contents of the tree. Elements
	 * are in their natural order.
	 * @return an iterator with the elements in the natural order
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> inorderIterator()
	{
		return BSTInOrderIterator.getInstance(this);
	}
	
	/**
	 * Generates an in-order iteration over the contents of the tree. Elements
	 * are in their natural order.
	 * @return an iterator with the elements in the natural order
	 */
	@SuppressWarnings("rawtypes")
	private static class BSTInOrderIterator<E extends Comparable<? super E>> implements Iterator<E>
	{
		private static BSTReferencedBased inner;
		protected Stack<BSTreeNode<E>> current = new Stack<BSTreeNode<E>>();
		private static BSTInOrderIterator single_instance = null;
		
		/**
		 * 
		 * @param outerinstance
		 *            the BST class calling this
		 * @return a single instance of in order iterator
		 */
		public static BSTInOrderIterator getInstance(BSTReferencedBased inner1)
		{
			inner = inner1;
			if (single_instance == null)
			{
				single_instance = new BSTInOrderIterator();
			}
			return single_instance;
		}
		
		/**
		 * Returns <code>true</code> if the iteration has more elements. (In other words, returns
		 * <code>true</code> if <code>next()</code> would return an element rather than throwing
		 * an exception.)
		 * 
		 * @return <code>true</code> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext()
		{
			return (inner.root != null);
		}
		
		/**
		 * Find the left leaf node and push
		 * 
		 * @param node
		 *            the node being sent
		 */
		private void leftLeafPush(BSTreeNode<E> node)
		{
			if (node != null)
			{
				current.push(node);
				leftLeafPush(node.getLeft());
			}
		}
		
		/**
		 * Returns the next element in the iteration.
		 * 
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException
		 *             If the iteration has no more elements.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E next() throws NoSuchElementException
		{
			if (current.empty())
			{
				leftLeafPush(inner.root);
			}
			BSTreeNode<E> node = current.pop();
			E result = node.getElement();
			if (node.getRight() != null)
			{
				BSTreeNode<E> right = node.getRight();
				leftLeafPush(right);
			}
			if (current.empty())
			{
				inner.root = null;
			}
			return result;
		}
	}
	
	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements
	 * are order in such a way as the root element is first.
	 * @return an iterator with the elements in a root element first order
	 */
	@Override
	public Iterator<E> preorderIterator()
	{
		return BSTPreOrderIterator.getInstance(this);
	}

	/**
	 * Generates a pre order iteration of any BST that calls this method
	 *
	 * @return a pre order iteration of the BST
	 */
	private static class BSTPreOrderIterator<E extends Comparable<? super E>> implements Iterator<E>
	{

		private static BSTReferencedBased outer;
		protected Stack<BSTreeNode<E>> current = new Stack<BSTreeNode<E>>();
		protected Stack<Boolean> currentRightChild = new Stack<Boolean>();
		private static BSTPreOrderIterator single_instance = null;

		/**
		 * 
		 * @param outerinstance
		 *            the BST class calling this
		 * @return a single instance of pre order iterator
		 */
		public static BSTPreOrderIterator getInstance(BSTReferencedBased outerinstance)
		{
			outer = outerinstance;
			if (single_instance == null)
			{
				single_instance = new BSTPreOrderIterator();
			}
			return single_instance;
		}

		/**
		 * Returns <code>true</code> if the iteration has more elements. (In other words, returns
		 * <code>true</code> if <code>next()</code> would return an element rather than throwing
		 * an exception.)
		 * 
		 * @return <code>true</code> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext()
		{
			return (outer.root != null);
		}

		/**
		 * Returns the next element in the iteration.
		 * 
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException
		 *             If the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException
		{
			// visiting (V)
			if (current.empty())
			{
				current.push(outer.root);
			}

			BSTreeNode<E> checknode = current.pop();

			// visit left subtree first then right subtree (LR)
			if (checknode.hasRightChild() && checknode.getRight() != null)
			{
				current.push(checknode.getRight());
			}
			if (checknode.hasLeftChild() && checknode.getLeft() != null)
			{
				current.push(checknode.getLeft());
			}
			
			if (current.empty()) {
				outer.root = null;
			}

			return checknode.getElement();

		}

	}

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements are order in
	 * such a way as the root element is last.
	 * 
	 * @return an iterator with the elements in a root element last order Saurav
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> postorderIterator()
	{
		return BSTPostOrderIterator.getInstance(this);
	}

	@SuppressWarnings("rawtypes")
	private static class BSTPostOrderIterator<E extends Comparable<? super E>> implements Iterator<E>
	{
		private static BSTReferencedBased outer;
		protected Stack<BSTreeNode<E>> current = new Stack<BSTreeNode<E>>();
		protected Stack<Boolean> currentRightChild = new Stack<Boolean>();
		private static BSTPostOrderIterator single_instance = null;

		/**
		 * 
		 * @param outer1
		 *            the outer class BSTNode instance
		 * @return the single instance of post order iterator
		 */
		public static BSTPostOrderIterator getInstance(BSTReferencedBased outer1)
		{
			outer = outer1;
			if (single_instance == null)
			{
				single_instance = new BSTPostOrderIterator();
			}
			return single_instance;
		}

		/**
		 * Returns <code>true</code> if the iteration has more elements. (In other words, returns
		 * <code>true</code> if <code>next()</code> would return an element rather than throwing
		 * an exception.)
		 *
		 * @return <code>true</code> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext()
		{
			return (outer.root != null);
		}

		/**
		 * Find the left leaf node and push
		 * 
		 * @param node
		 *            the node being sent
		 */
		private void leftLeafPush(BSTreeNode<E> node)
		{
			if (node != null)
			{
				current.push(node);
				currentRightChild.push(false);
				leftLeafPush(node.getLeft());
			}
		}

		/**
		 * Returns the next element in the post-order iteration.
		 *
		 * @return The next element in the post-order iteration.
		 * @throws NoSuchElementException
		 *             If the iteration has no more elements.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E next() throws NoSuchElementException
		{
			if (current.empty())
			{
				leftLeafPush(outer.root);
			}
			if ((current.peek().getRight() == null) || (currentRightChild.peek()))
			{
				E result = current.pop().getElement();
				currentRightChild.pop();
				if (current.empty())
				{
					outer.root = null;
				}
				return result;
			} else
			{
				if (currentRightChild.pop())
				{
					assert (false);
				}
				currentRightChild.push(true);
				BSTreeNode<E> right = current.peek().getRight();
				assert (right != null);
				leftLeafPush(right);
				return next();
			}
		}
	}

}
