package com.routesearch.route;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.filetool.util.LogUtil;

public class Solution_Astar_DFS implements PathSolution{
	private String Min_Cost_Path=Route.NO_PATH;
	private Graph graph;
	private Set<Integer> IncVertexs;
	
	void AstarSP1(int StartID, int EndID,List<Integer> path){
		Astar_Info[] info=new Astar_Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Astar_Info(i,Route.INFINTY);
		boolean[] visit=new boolean[graph.V];
		for(Integer id:path)
			visit[id]=true;
		
    	info[StartID].distTo=0;
    	info[StartID].pathTo=StartID;
    	info[StartID].fTo=0;//f(n)
    	PriorityQueue<Astar_Info> open=new PriorityQueue<>(graph.V,Astar.OrderIsdn);
    	open.add(info[StartID]);
    	Astar_Info temp;
    	while(!open.isEmpty()){
    		temp=open.poll();
    		if(IncVertexs.contains(temp.id)){
				Deque<Integer> temp_path=new ArrayDeque<>();
				IncVertexs.remove(temp.id);	
				//Cost+=info[temp.id].distTo;
				int ID=info[temp.id].pathTo;
				//graph.visit[ID]=true;
				temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					//graph.visit[ID]=true;
					temp_path.push(ID);
				}
				path.addAll(temp_path);
				
				
				if(IncVertexs.size()>0){
					AstarSP1(temp.id,EndID,path);
				}
				else{
					AstarSP2(temp.id,EndID,path);
				}
						
				IncVertexs.add(temp.id);
				ID=info[temp.id].pathTo;
				//graph.visit[ID]=false;
				temp_path.push(ID);
				while(ID!=StartID){
					ID=info[ID].pathTo;
					//graph.visit[ID]=false;
					temp_path.push(ID);
				}
				path.removeAll(temp_path);
    		}
    		for(DirectedEdge e:graph.Graph_Map[temp.id]){
    			int Estimate_Value=graph.estimate_value[e.DestinationID];//�����ֵ
    			//int Estimate_Value=20;
    			if(open.contains(info[e.DestinationID])){//open �б�
    				if(Estimate_Value+info[e.DestinationID].distTo<info[e.DestinationID].fTo){//X�Ĺ���ֵС��OPEN��Ĺ���ֵ
    					info[e.DestinationID].pathTo=temp.id;//��n����ΪX�ĸ���;
    					info[e.DestinationID].distTo=temp.distTo+e.Cost;
    					info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//ȡ��С·���Ĺ���ֵ
    				}
    			}
    			else if(visit[e.DestinationID]==true){//close 
    				continue;
    			}
    			else{
    				info[e.DestinationID].pathTo=temp.id;//��n����ΪX�ĸ���;
    				info[e.DestinationID].distTo=temp.distTo+e.Cost;
    				info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//��X�Ĺ���ֵ
    				open.add(info[e.DestinationID]);//����X����OPEN����;��û������
    			}
    		}
    		visit[temp.id]=true;//��n�ڵ����CLOSE����;
    	}
	}
	
	void AstarSP2(int StartID, int EndID,List<Integer> path){
		Astar_Info[] info=new Astar_Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Astar_Info(i,Route.INFINTY);
		boolean[] visit=new boolean[graph.V];
		for(Integer id:path)
			visit[id]=true;
		
    	info[StartID].distTo=0;
    	info[StartID].pathTo=StartID;
    	info[StartID].fTo=0;//f(n)
    	PriorityQueue<Astar_Info> open=new PriorityQueue<>(graph.V,Astar.OrderIsdn);
    	open.add(info[StartID]);
    	Astar_Info temp;
    	while(!open.isEmpty()){
    		temp=open.poll();
    		if(temp.id==EndID){
				Deque<Integer> temp_path=new ArrayDeque<>();
				//Cost+=info[temp.id].distTo;
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
				LogUtil.printLog(path.toString());
				//this.path.addAll(path);
				path.remove(temp_path);
				path.remove(Integer.valueOf(EndID));
    		}
    		for(DirectedEdge e:graph.Graph_Map[temp.id]){
    			int Estimate_Value=graph.estimate_value[e.DestinationID];//�����ֵ
    			//int Estimate_Value=20;
    			if(open.contains(info[e.DestinationID])){//open �б�
    				if(Estimate_Value+info[e.DestinationID].distTo<info[e.DestinationID].fTo){//X�Ĺ���ֵС��OPEN��Ĺ���ֵ
    					info[e.DestinationID].pathTo=temp.id;//��n����ΪX�ĸ���;
    					info[e.DestinationID].distTo=temp.distTo+e.Cost;
    					info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//ȡ��С·���Ĺ���ֵ
    				}
    			}
    			else if(visit[e.DestinationID]==true){//close 
    				continue;
    			}
    			else{
    				info[e.DestinationID].pathTo=temp.id;//��n����ΪX�ĸ���;
    				info[e.DestinationID].distTo=temp.distTo+e.Cost;
    				info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//��X�Ĺ���ֵ
    				open.add(info[e.DestinationID]);//����X����OPEN����;��û������
    			}
    		}
    		visit[temp.id]=true;//��n�ڵ����CLOSE����;
    	}
		
	}
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs, Graph graph) {
		// TODO Auto-generated method stub
		this.graph=graph;
		this.IncVertexs=IncVertexs;
		List<Integer> path=new ArrayList<>();
		//int Cost=0;
		graph.visit[EndID]=true;//���ܾ���EndID
		AstarSP1(StartID,EndID,path);
		return Min_Cost_Path;
	}

}
