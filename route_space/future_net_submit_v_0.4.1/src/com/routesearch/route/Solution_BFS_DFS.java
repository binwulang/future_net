package com.routesearch.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Solution_BFS_DFS extends Thread{
	private List<DirectedEdge> min_path=new ArrayList<>();
	private int Min_Cost=Dijkstra.INFINTY;
	private Graph graph;
	boolean []inc_node;
	private int StartID;
	private int EndID;
	private Set<Integer> IncVertexs;
	static int NO_PRE=0x7fffffff;
	public void FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		this.IncVertexs=IncVertexs;
		this.StartID=StartID;;
		this.graph=graph;
		this.EndID=EndID;
		//this.start();
	}
	@Override
	public void run() {
		
	}
}
