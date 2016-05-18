package com.routesearch.route;

import java.util.*;

public class Solution_DFS implements PathSolution{
	private static final String NO_PATH="NA";
	private int Min_Cost=0x7fffffff;
	private String Min_Cost_Path=NO_PATH;
	private List<Integer> path=new ArrayList<>();
	public void DFS(int StartID, int EndID,Set<Integer> IncVertexs,Graph graph,Stack<Integer> temp,int Cost){
		if(StartID==EndID&&Cost<Min_Cost&&IncVertexs.size()==0){
			Min_Cost=Cost;
			path.clear();
			path.addAll(temp);
			return;
		}
		if(graph.Graph_Map[StartID].size()==0)return;
		boolean flag=false;
		for(DirectedEdge e:graph.Graph_Map[StartID]){
			Cost+=e.Cost;
			if(Cost<Min_Cost&&!graph.visit[e.DestinationID]){
				if(IncVertexs.contains(e.DestinationID)){
					IncVertexs.remove(e.DestinationID);
					flag=true;
				}
				temp.push(e.E_Name);
				graph.visit[e.DestinationID]=true;
				DFS(e.DestinationID,EndID,IncVertexs,graph,temp,Cost);
				graph.visit[e.DestinationID]=false;
				if(flag==true){
					IncVertexs.add(e.DestinationID);
					flag=false;
				}
				
				temp.pop();
			}
			Cost-=e.Cost;
		}
	}
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,Graph graph) {
		// TODO Auto-generated method stub
		int Cost=0;
		Stack<Integer> temp=new Stack<>();
		//temp.add(StartID);
		graph.visit[StartID]=true;
		DFS(StartID,EndID,IncVertexs,graph,temp,Cost);
		if(!path.isEmpty()){
			Min_Cost_Path=path.get(0).toString();
			path.remove(0);
			for(Integer path_id:path)
				Min_Cost_Path+="|"+path_id;
		}
		return Min_Cost_Path;
	}
}
