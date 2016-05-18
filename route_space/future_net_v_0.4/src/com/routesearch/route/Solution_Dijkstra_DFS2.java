package com.routesearch.route;

import java.util.*;

import com.filetool.util.LogUtil;


public class Solution_Dijkstra_DFS2 implements PathSolution{
	private String Min_Cost_Path=Route.NO_PATH;
	private int Min_Cost=Route.INFINTY;
	private Graph graph;
	private int[] IncVertexs;
	
	void DijkstraSP1(int StartID, int EndID,List<Integer> path,int Cost,int index){
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;		
		PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();		
			if(temp.id==IncVertexs[index]/*&&Cost+info[temp.id].distTo<Min_Cost*/){	
				Cost+=info[temp.id].distTo;
				int ID=info[temp.id].pathTo;
				graph.visit[ID]=true;
				//Deque<Integer> temp_path=new ArrayDeque<>();
				//temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=true;
					//temp_path.push(ID);
				}
				//path.addAll(temp_path);
				
				index++;
				DijkstraSP1(StartID,EndID,path,Cost,index);
				
				ID=info[temp.id].pathTo;
				graph.visit[ID]=false;
				//temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=false;
					//temp_path.push(ID);
				}
				
				return;
			}
        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
        		if(graph.visit[e.DestinationID]==false&&info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=temp.id;
        			queue.add(info[e.DestinationID]);
        		}
        	}
		}
	}
	
	public String FindPath(int StartID, int EndID,int[] IncVertexs, Graph graph) {
		// TODO Auto-generated method stub
		this.graph=graph;
		this.IncVertexs=IncVertexs;
		List<Integer> path=new ArrayList<>();
		int Cost=0;
		//if(IncVertexs.size()>0){
			graph.visit[EndID]=true;//不能经过EndID
			DijkstraSP1(StartID,EndID,path,Cost,0);			
		//}
		//else{
			
		//}
		return Min_Cost_Path;
	}

	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		return null;
	}

}
