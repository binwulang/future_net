package com.routesearch.route;

import java.util.*;

public class Solution_DFS implements PathSolution{
	private static final String NO_PATH="NA";
	private int Min_Cost=0x7fffffff;
	private String Min_Cost_Path=NO_PATH;
	private List<Integer> path=new ArrayList<>();
	public void DFS(int StartID, int EndID,Set<Integer> IncVertexs,Graph graph,List<Integer> temp,int Cost){
		if(StartID==EndID&&Cost<Min_Cost&&temp.containsAll(IncVertexs)){
			Min_Cost=Cost;
			path.clear();
			path.addAll(temp);
			return;
		}
		if(graph.Graph_Map[StartID].size()==0)return;
		for(DirectedEdge e:graph.Graph_Map[StartID]){
			Cost+=e.Cost;
			if(Cost<Min_Cost&&!graph.visit[e.DestinationID]){
				temp.add(e.DestinationID);
				graph.visit[e.DestinationID]=true;
				DFS(e.DestinationID,EndID,IncVertexs,graph,temp,Cost);
				graph.visit[e.DestinationID]=false;
				temp.remove(temp.size()-1);
			}
			Cost-=e.Cost;
		}
	}
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,Graph graph) {
		// TODO Auto-generated method stub
		int Cost=0;
		List<Integer> temp=new ArrayList<>();
		//temp.add(StartID);
		graph.visit[StartID]=true;
		DFS(StartID,EndID,IncVertexs,graph,temp,Cost);
		if(!path.isEmpty()){
			String key=String.valueOf(StartID);
			String key_temp=path.get(0).toString();
			Min_Cost_Path=graph.EdgeName.get(key+key_temp);
			path.remove(0);
			key=key_temp;
			for(Integer ID:path){
				key_temp=ID.toString();
				Min_Cost_Path+="|"+graph.EdgeName.get(key+key_temp);
				key=key_temp;
			}
		}
		return Min_Cost_Path;
	}
}
