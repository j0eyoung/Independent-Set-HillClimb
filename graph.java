/***************************
 * @author Edwin Young
 * Artificial Intelligence
 * Assignment 4 Question 3
 * Fall 2016
 **************************/



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class graph 
{
	static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	static int temp1, temp2, lines, v_vertices, v_edges;
	static int[][] useableArray;
	static String fileName = "ran50.txt";
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		fileName = args[0];
		solveProblem();
	}
	
	static void countLines()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
	        String line = br.readLine();
	        while((line = br.readLine()) != null){
	        	lines++;
	        }
			}catch(Exception e){
        	
        }
	}

	static void solveProblem()
	{
		System.out.println("Maximum Independent Set using Hill-Climbing Search");
		System.out.println("--------------------------------------------------");
		countLines();
		useableArray = readFileInformation();
		createGraph(useableArray);
		ArrayList<Integer> solution = hillClimb();
		System.out.print(solution);
		System.out.println();
		System.out.println("Size: " + solution.size());
		System.out.println("--------------------------------------------------");
	}
	
	 static void addEdges(int v1, int v2)
	 {
		vertices.get(v1).addEdge(vertices.get(v2));
		vertices.get(v2).addEdge(vertices.get(v1));
	}
	
	 static void createGraph(int[][] n)
	 {
		for(int i = 0; i < v_vertices; i++)
		{
			vertices.add(new Vertex(i));
		}
		
		for(int i = 1; i < v_edges; i++)
		{
			addEdges(n[i][0], n[i][1]);
		}
	}
	
	 static boolean isIndependent(ArrayList<Integer> array)
	 {
		boolean output = false;
		Vertex vert;
		
		for(int i = 0; i < array.size(); i++)
		{
			vert = vertices.get(array.get(i));
			for(int j = 0; j < vert.edges.size(); j++)
			{
				if(array.contains(vert.edges.get(j).id))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	static ArrayList<Integer> hillClimb()
	{
		Vertex initialState;
		int temp;
		ArrayList<Integer> output = new ArrayList<Integer>();
		ArrayList<Integer> holding = new ArrayList<Integer>();
		
		for(int i = 0; i < vertices.size(); i++)
		{
			holding.clear();
			initialState = vertices.get((int)(Math.floor(Math.random() * vertices.size())));
			holding.add(initialState.id);
		
			for(int j = 0; j < vertices.size(); j++)
			{
				temp = (int)(Math.floor(Math.random() * (vertices.size() - 1)));
				if(holding.contains(temp))
				{
					j--;
					continue;
				}
				holding.add(temp);
				if(isIndependent(holding) != true)
				{
					holding.remove(holding.size()-1);
				}
			}
			if(holding.size() > output.size())
			{
				output.clear();
				output.addAll(holding);
			}
		}
		return output;
	}
	
	 static int[][] readFileInformation()
	 {
		int[][] array = new int[lines][2]; 
		int i = 0;
		
		try 
		{
	        BufferedReader br = new BufferedReader(new FileReader(fileName));
	        String line = br.readLine();
		    String[] par;
		    par = line.split("\t");
		    v_vertices = Integer.parseInt(par[0]);
	    	v_edges = Integer.parseInt(par[1]);
	    	
		    while((line = br.readLine()) != null)
		    {
		    	par = line.split("\t");
		    	temp1 = Integer.parseInt(par[0]);
		    	temp2 = Integer.parseInt(par[1]);
		    	array[i][0]=temp1;
		    	array[i][1]=temp2;
		        i++;        
		    }  
		}catch (FileNotFoundException ex) 
		{
	        ex.printStackTrace();
	    } catch (IOException ex) 
		{
	        ex.printStackTrace();
	    } 
		return array;
	}
	
}