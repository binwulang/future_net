package com.routesearch.route;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution_Dijstra_DFS2 extends Thread{
	private List<DirectedEdge> min_path=new ArrayList<>(); ;
	private int Min_Cost=Dijkstra.INFINTY;
	private Graph graph;
	private Set<Integer> IncVertexs;
	private boolean inc_node[];
	private int StartID;
	private int EndID;
	/*
	 * 
	 */
	/*Dijstra_DFS_SP1*/
	void Dijstra_DFS_SP1(int StartID, int EndID,List<DirectedEdge> path,int Cost,int inc_count){
		if(IncVertexs.size()==inc_count){
			graph.visit[EndID]=false;//不能经过EndID
			Dijstra_DFS_SP2(StartID,EndID,path,Cost);
			graph.visit[EndID]=true;//不能经过EndID
			return;
		}
		PD_Info[] info=new PD_Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new PD_Info(i);
		info[StartID].distTo=0;
		PriorityQueue<PD_Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			PD_Info temp=queue.poll();
			if(/*IncVertexs.contains(temp.id)*/inc_node[temp.id]==true&&
					Cost+info[temp.id].distTo<Min_Cost&&temp.id!=StartID){
				Deque<DirectedEdge> temp_path=new ArrayDeque<>();
				//IncVertexs.remove(temp.id);
				inc_count++;
				Cost+=info[temp.id].distTo;
				DirectedEdge e=info[temp.id].pathTo;
				graph.visit[e.SourceID]=true;
				temp_path.push(e);
				while(e.SourceID!=StartID){
					e=info[e.SourceID].pathTo;
					graph.visit[e.SourceID]=true;
					temp_path.push(e);
				}
				path.addAll(temp_path);
				Dijstra_DFS_SP1(temp.id,EndID,path,Cost,inc_count);
				Cost-=info[temp.id].distTo;
				e=info[temp.id].pathTo;
				graph.visit[e.SourceID]=false;
				while(e.SourceID!=StartID){
					e=info[e.SourceID].pathTo;
					graph.visit[e.SourceID]=false;
				}			
				//IncVertexs.add(temp.id);
				inc_count--;
				path.removeAll(temp_path);
			}
        	for(/*DirectedEdge e:graph.Graph_Map[temp.id]*/DirectedEdge e:graph.rever_Graph_Map[temp.id]){
        		if(graph.visit[e.DestinationID]==false&&info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=e;
        			queue.add(info[e.DestinationID]);
        		}
        	}
		}
	}
	/*
	 * 
	 */
	/*Dijstra_DFS_SP2*/
	void Dijstra_DFS_SP2(int StartID, int EndID,List<DirectedEdge> path,int Cost){
		PD_Info[] info=new PD_Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new PD_Info(i);
		info[StartID].distTo=0;
		PriorityQueue<PD_Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			PD_Info temp=queue.poll();
			if(temp.id==EndID&&Cost+info[temp.id].distTo<Min_Cost){
				Deque<DirectedEdge> temp_path=new ArrayDeque<>();	
				Cost+=info[temp.id].distTo;
				Min_Cost=Cost;
				DirectedEdge e=info[temp.id].pathTo;
				temp_path.push(e);
				while(e.SourceID!=StartID){
					e=info[e.SourceID].pathTo;
					temp_path.push(e);
				}
				path.addAll(temp_path);
				min_path.clear();
				min_path.addAll(path);
				Cost-=info[temp.id].distTo;			
				path.removeAll(temp_path);	
				return;
			}
        	for(/*DirectedEdge e:graph.Graph_Map[temp.id]*/DirectedEdge e:graph.rever_Graph_Map[temp.id]){
        		if(graph.visit[e.DestinationID]==false&&info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=e;
        			queue.add(info[e.DestinationID]);
        		}
        	}
		}	
	}
	public void FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		//this.StartID=StartID;
		//this.EndID=EndID;
		this.StartID=EndID;
		this.EndID=StartID;
		this.IncVertexs=IncVertexs;
		this.graph=graph;
		inc_node=new boolean[graph.V];
		for(int inc_id:IncVertexs)
			inc_node[inc_id]=true;
		this.start();//start the thread
		/*for test single thread
		List<DirectedEdge> path=new ArrayList<>();
		int Cost=0;
		graph.visit[EndID]=true;//不能经过EndID
		graph.visit[StartID]=true;////不能经过StartID
		Dijstra_DFS_SP1(StartID,EndID,path,Cost,0);*/
	}
	public String GetResult(){
		String Min_Cost_Path=Route.NO_PATH;
		int len=min_path.size();
		if(min_path.size()>0){
			Min_Cost_Path=String.valueOf(min_path.get(len-1).E_Name);
			for(int i=len-2;i>=0;i--)
				Min_Cost_Path+="|"+min_path.get(i).E_Name;
		}
		return Min_Cost_Path;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<DirectedEdge> path=new ArrayList<>();
		int Cost=0;
		graph.visit[EndID]=true;//不能经过EndID
		graph.visit[StartID]=true;////不能经过StartID
		Dijstra_DFS_SP1(StartID,EndID,path,Cost,0);
	}

}
