package referenceBasedTreeImplementation;

import java.io.*;
import java.util.*;

import utilities.Word;

/**
 * Binary search tree node Class only accessible inside utilities package
 * 
 * @author 802302
 * @param <E>
 */
public class BSTreeNode<E> implements Serializable
{
	/**
	 * auto generated serial ID
	 */
	private static final long serialVersionUID = -7240148987303830641L;

	private E element;
	private BSTreeNode<E> left, right;
	
	/**
	 * build binary tree
	 * @param element of the node
	 */
	public BSTreeNode(E element)
	{
		this.element = element;
	}

	/**
	 * set binary tree
	 * @param elem
	 * @param left
	 * @param right
	 */
	public BSTreeNode(E elem, BSTreeNode<E> left, BSTreeNode<E> right)
	{
		this.element = elem;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return element of node
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * @param set element of node
	 */
	public void setElement(E element)
	{
		this.element = element;
	}

	/**
	 * @return left node connecting to this
	 */
	public BSTreeNode<E> getLeft()
	{
		return left;
	}

	/**
	 * @param left node to be set on left side
	 */
	public void setLeft(BSTreeNode<E> left)
	{
		this.left = left;
	}

	/**
	 * @return Right node connecting to this
	 */
	public BSTreeNode<E> getRight()
	{
		return right;
	}

	/**
	 * @param Right node to be set on Right side
	 */
	public void setRight(BSTreeNode<E> right)
	{
		this.right = right;
	}

	/**
	 * Check if the current node has a right child.
	 * 
	 * @return true if the current node has a right child else false
	 */
	public boolean hasRightChild()
	{
		return (this.right != null);
	}

	/**
	 * Check if the current node has a left child.
	 * 
	 * @return true if the current node has a left child else false
	 */
	public boolean hasLeftChild()
	{
		return (this.left != null);
	}

	/*
	 * David
	 */
	public boolean isLeaf()
	{
		if (left == null && right == null)
		{
			return true;
		}

		return false;
	}

	/*
	 * David
	 */
	public int getNumberNodes(BSTreeNode<E> root)
	{
		if (root == null)
		{
			return 0;
		}
		// else if(this.getLeft() == null) {
		// return 1;
		// }else if(this.getRight() == null) {
		// return 1;
		// }

		return 1 + getNumberNodes(root.left) + getNumberNodes(root.right);
	}

	/*
	 * Nevyn
	 */
	public int getHeight()
	{
		return getHeight(this);
	}

	/*
	 * Nevyn
	 */
	public int getHeight(BSTreeNode<E> node)
	{
		if (node == null)
			return -1;
		else
		{
			int lHeight = getHeight(node.left);

			int rHeight = getHeight(node.right);
			if (lHeight > rHeight)
				return lHeight + 1;
			else
				return rHeight + 1;
		}
	}
	
	@Override
	public String toString()
	{
		return "BSTreeNode [element=" + element + "]";
	}
}
