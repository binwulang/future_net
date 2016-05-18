package com.routesearch.route;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;


public class Solution_BFS_DFS{
	private String Min_Cost_Path=Route.NO_PATH;
	private int Min_Cost=Route.INFINTY;
	private Graph graph;
	private int[] inc;
	private Set<Integer> IncVertexs;
	private boolean flag=false;
	
	private void BFSSP(int StartID, int EndID,Stack<Integer> path){
		boolean flag=false;
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;	
		PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();
			if(IncVertexs.contains(temp.id)){
				System.out.printf("%d,",temp.id);
				path.add(temp.id);
				IncVertexs.remove(temp.id);
				int ID=info[temp.id].pathTo;
				graph.visit[ID]=true;
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=true;
				}
				BFSSP(temp.id,EndID,path);	
				ID=info[temp.id].pathTo;
				graph.visit[ID]=false;
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=false;
				}
				IncVertexs.add(temp.id);
				path.pop();
				
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
	private void BFSSP(int StartID, int EndID,int index,List<Integer> path){
		if(index==inc.length){
			graph.visit[inc[index-1]]=true;
			path.add(inc[index-1]);
			return;
		}
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;	
		PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();
			if(inc[index]==temp.id){
				Deque<Integer> temp_path=new ArrayDeque<>();
				int ID=info[temp.id].pathTo;
				temp_path.push(ID);
				graph.visit[ID]=true;
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=true;
					temp_path.push(ID);
				}
				path.addAll(temp_path);
				BFSSP(temp.id,EndID,index+1,path);
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
	/*
	private void BFSSP2(int StartID, int EndID,List<Integer> path){
		//System.err.println("OK1");
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;	
		Deque<Info> queue=new ArrayDeque<>();
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();
			if(temp.id==EndID){
				System.err.println("OK2");
			}
        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
        		if(graph.visit[e.DestinationID]==false&&info[e.DestinationID].distTo==Min_Cost){
        			//queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+1;
        			info[e.DestinationID].pathTo=temp.id;
        			queue.add(info[e.DestinationID]);
        			//graph.visit[e.DestinationID]=true;
        		}
        	}
		}
	}*/
	public List<Integer> FindPath(int StartID, int EndID, int[] IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		this.graph=graph;
		this.inc=IncVertexs;
		List<Integer> path=new Stack<>();
		graph.visit[EndID]=true;//不能经过EndID
		BFSSP(StartID,EndID,0,path);
		return path;
	}
	public Stack<Integer> FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		this.graph=graph;
		this.IncVertexs=IncVertexs;
		Stack<Integer> path=new Stack<>();
		//IncVertexs.remove(PreIncVertexs);
		graph.visit[EndID]=true;//不能经过EndID
		BFSSP(StartID,EndID,path);
		return path;
	}
}
