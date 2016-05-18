package com.routesearch.route;

import java.util.*;

import com.filetool.util.LogUtil;


public class Solution_Dijkstra_DFS implements PathSolution{
	private String Min_Cost_Path=Route.NO_PATH;
	private int Min_Cost=Route.INFINTY;
	private Graph graph;
	private Set<Integer> IncVertexs;
	private List<Integer> min_path=new ArrayList<>();
	void DijkstraSP1(int StartID, int EndID,List<Integer> path,int Cost){
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;		
		PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();			
			if(IncVertexs.contains(temp.id)&&Cost+info[temp.id].distTo<Min_Cost){				
				Deque<Integer> temp_path=new ArrayDeque<>();
				IncVertexs.remove(temp.id);
				Cost+=info[temp.id].distTo;
				int ID=info[temp.id].pathTo;
				graph.visit[ID]=true;
				temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=true;
					temp_path.push(ID);
				}
				path.addAll(temp_path);
				
				if(IncVertexs.size()>0&&Cost<Min_Cost){
					DijkstraSP1(temp.id,EndID,path,Cost);
				}
				else if(Cost<Min_Cost){
					graph.visit[EndID]=false;//经过EndID
					DijkstraSP2(temp.id,EndID,path,Cost);
					graph.visit[EndID]=true;
				}
				
				IncVertexs.add(temp.id);
				Cost-=info[temp.id].distTo;
				ID=info[temp.id].pathTo;
				graph.visit[ID]=false;
				temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=false;
					temp_path.push(ID);
				}
				path.removeAll(temp_path);
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
	
	void DijkstraSP2(int StartID, int EndID,List<Integer> path,int Cost){
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,Route.INFINTY);
		info[StartID].distTo=0;		
		PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Dijkstra.OrderIsdn);
		queue.add(info[StartID]);
		while(!queue.isEmpty()){
			Info temp=queue.poll();
			if(temp.id==EndID&&Cost+info[temp.id].distTo<Min_Cost){
				Deque<Integer> temp_path=new ArrayDeque<>();
				Cost+=info[temp.id].distTo;
				Min_Cost=Cost;
				int ID=info[temp.id].pathTo;
				graph.visit[ID]=true;
				temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					graph.visit[ID]=true;
					temp_path.push(ID);
				}
				path.addAll(temp_path);
				path.add(EndID);
				min_path.addAll(path);
				path.remove(temp_path);
				path.remove(Integer.valueOf(EndID));
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
	@Override
	public String FindPath(int StartID, int EndID,Set<Integer> IncVertexs, Graph graph) {
		// TODO Auto-generated method stub
		this.graph=graph;
		this.IncVertexs=IncVertexs;
		List<Integer> path=new ArrayList<>();
		int Cost=0;
		graph.visit[EndID]=true;//不能经过EndID
		DijkstraSP1(StartID,EndID,path,Cost);		
		//LogUtil.printLog(min_path.toString()+Min_Cost+"\n");
		if(min_path.size()>0){
			Min_Cost_Path="";
			Integer pre_key=min_path.get(1);
			Min_Cost_Path=graph.EdgeName.get(min_path.get(0).toString()+pre_key.toString());
			//for(int i=2;i<min_path.size();i++){
			//	Min_Cost_Path+="|"+graph.EdgeName.get(pre_key.toString()+min_path.get(i).toString());
			//	pre_key=min_path.get(i);
			//}
		}
		return Min_Cost_Path;
	}

}
