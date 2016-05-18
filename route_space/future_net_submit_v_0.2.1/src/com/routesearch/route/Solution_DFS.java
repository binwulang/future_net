package com.routesearch.route;

import java.util.*;


public class Solution_DFS extends Thread{
	private List<DirectedEdge> min_path=new ArrayList<>(); ;
	private int Min_Cost=0x7fffffff;
	private Graph graph;
	private Set<Integer> IncVertexs;
	private int StartID;
	private int EndID;
	
	public void DFS_SP(int StartID,int EndID,Stack<DirectedEdge> path,int Cost,int inc_count){
		if(StartID==EndID&&Cost<Min_Cost&&IncVertexs.size()==inc_count){
			Min_Cost=Cost;
			min_path.clear();
			min_path.addAll(path);
		}
		boolean flag=false;
		for(DirectedEdge e:graph.Graph_Map[StartID]){
			Cost+=e.Cost;
			if(!graph.visit[e.DestinationID]&&Cost<Min_Cost){
				path.push(e);
				graph.visit[e.DestinationID]=true;
				if(IncVertexs.contains(e.DestinationID)){
					//IncVertexs.remove(e.DestinationID);
					inc_count++;
					flag=true;
				}
				DFS_SP(e.DestinationID,EndID,path,Cost,inc_count);
				if(flag==true){
					//IncVertexs.add(e.DestinationID);
					inc_count--;
					flag=false;
				}
				graph.visit[e.DestinationID]=false;
				path.pop();
			}
			Cost-=e.Cost;
		}
	}

	public void FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		this.StartID=StartID;
		this.IncVertexs=IncVertexs;
		this.graph=graph;
		this.EndID=EndID;
		this.start();//start the thread
	}
	public String GetResult(){
		String Min_Cost_Path=Route.NO_PATH;
		if(min_path.size()>0){
			Min_Cost_Path=String.valueOf(min_path.get(0).E_Name);
			min_path.remove(0);
			for(DirectedEdge e:min_path)
			Min_Cost_Path+="|"+e.E_Name;
		}
		return Min_Cost_Path;	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Stack<DirectedEdge> path=new Stack<>();
		int Cost=0;
		graph.visit[StartID]=true;////不能经过StartID
		DFS_SP(StartID,EndID,path,Cost,0);	
	}
}
