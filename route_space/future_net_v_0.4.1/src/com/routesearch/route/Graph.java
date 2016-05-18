package com.routesearch.route;

import java.util.*;
class DirectedEdge{//边的定义
	int Cost;
	int SourceID;
	int DestinationID;
	int E_Name;
	public DirectedEdge(){
		
	}
	public DirectedEdge(int SourceID,int DestinationID,int Name,int Cost){
		this.SourceID=SourceID;
		this.DestinationID=DestinationID;
		this.Cost=Cost;
		this.E_Name=Name;
	}
}
public class Graph{
	Set<DirectedEdge>[] Graph_Map;//链接表
	//Map<String,String> EdgeName;//需要放在HashMap里面吗?
	//Set<Integer> Vertexs;//顶点集合
	//boolean[] visit;
	int V;//顶点数目
	int E;//边数目
	int MAX_ID;
	int MaxID=0;
	{
		//EdgeName=new HashMap<String,String>();
	}
	Graph(int E,HashSet<DirectedEdge>[] Graph_Map){
		//this.Vertexs=Vertexs;
		this.E=E;
		this.Graph_Map=Graph_Map;
		this.V=0;
		//this.visit=new boolean[this.V];	
	}

	//@SuppressWarnings("unchecked")
	Graph(int max_vertex){
		E=0;
		V=0;
		MAX_ID=0;
		//Vertexs=new HashSet<Integer>();
		//visit=new boolean[max_vertex];
		Graph_Map=(Set<DirectedEdge>[])new Set[max_vertex];
		for(int i=0;i<max_vertex;i++)
			Graph_Map[i]=new HashSet<DirectedEdge>();		
	}
	public void addEdge(DirectedEdge e){
		Graph_Map[e.SourceID].add(e);
		E++;
		//Vertexs.add(e.SourceID);
		//Vertexs.add(e.DestinationID);
		//V=Vertexs.size();
		MAX_ID=MAX_ID>e.SourceID?MAX_ID:e.SourceID;
		MAX_ID=MAX_ID>e.DestinationID?MAX_ID:e.DestinationID;
		V=MAX_ID+1;
	}
	
	static Graph CreatGraph(String graphContent){
		Graph graph=new Graph(Route.MAX_VERTEX);
		DirectedEdge edge=null;
		char[] graphcontent=graphContent.toCharArray();
    	int count=0;
    	int index=0;
		for(int i=0;i<graphcontent.length;i++){
			if(graphcontent[i]==','||graphcontent[i]=='\n'){
				count++;
    			switch(count){
    			case 1://Edge name
    				edge=new DirectedEdge();
    				edge.E_Name=Integer.parseInt(graphContent.substring(index, i));
    				index=i+1;
    				break;
    			case 2://SourceID
    				edge.SourceID=Integer.parseInt(graphContent.substring(index, i));
    				index=i+1;
    				break;
    			case 3://DestinationID
    				edge.DestinationID=Integer.parseInt(graphContent.substring(index, i));
    				index=i+1;
    				break;
    			case 4://Cost
    				edge.Cost=Integer.parseInt(graphContent.substring(index, i));
    				index=i+1;
    				count=0;
    				graph.addEdge(edge);
    				break;
    			}				
			}
		}
		return graph;
	}
	static void AnalysisCondition(String Condition){
		if(Condition==null)return;
		char[] condition=Condition.toCharArray();
		int i;
		int count=0;
		int index=0;
		for(i=0;i<condition.length;i++){
			if(condition[i]==','){
				count++;
				if(count==1){
					Route.StartID=Integer.parseInt(Condition.substring(index, i));
					index=i+1;
				}
				else if(count==2){
					Route.EndID=Integer.parseInt(Condition.substring(index, i));
					index=i+1;
					break;
				}
			}
		}
		if(condition[i]=='\n')return;//无条件顶点
		for(;i<condition.length;i++){
			if(condition[i]=='|'||condition[i]=='\n'){
				Route.IncVertexs.add(Integer.valueOf(Condition.substring(index, i)));
				index=i+1;
			}
		}
	}
}
