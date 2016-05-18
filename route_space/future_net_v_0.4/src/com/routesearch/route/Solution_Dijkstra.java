package com.routesearch.route;

import java.util.*;

public class Solution_Dijkstra implements PathSolution{
	private String Min_Cost_Path=Route.NO_PATH;
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,Graph graph) {
		// TODO Auto-generated method stub
		if(IncVertexs!=null)return Min_Cost_Path;
		Info[] info=Dijkstra.DijkstraSP(StartID, EndID, graph);
        if(info[EndID].distTo!=Route.INFINTY){
        	int[] path=new int[graph.V];
        	int len=0;
        	path[len++]=EndID;
        	int ID=info[EndID].pathTo;
        	path[len++]=ID;
        	while(ID!=StartID){
        		ID=info[ID].pathTo;
        		path[len++]=ID;
        	}
        	Min_Cost_Path=graph.EdgeName.get(String.valueOf(path[len-1])+String.valueOf(path[len-2]));
        	System.out.printf("%d,%d,", path[len-1],path[len-2]);
        	len-=2;
        	while(len>0){
        		Min_Cost_Path+="|"+graph.EdgeName.get(String.valueOf(path[len])+String.valueOf(path[len-1]));
        		len--;
        		System.out.printf("%d,", path[len]);
        	}
        }
		return Min_Cost_Path;
	}
}

