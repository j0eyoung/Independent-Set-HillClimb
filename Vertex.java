/***************************
 * @author Edwin Young
 * Artificial Intelligence
 * Assignment 4 Question 3
 * Fall 2016
 **************************/


import java.util.ArrayList;

public class Vertex 
{
	int id = 0;
	ArrayList<Vertex> edges = new ArrayList<Vertex>();
	
	public Vertex(int in)
	{
		id = in;
	}
	
	void addEdge(Vertex v)
	{
		edges.add(v);
	}
}
