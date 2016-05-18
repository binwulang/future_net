package com.routesearch.route;

import java.util.*;
class DirectedEdge{//�ߵĶ���
	int Cost;
	int SourceID;
	int DestinationID;
	public DirectedEdge(){
		
	}
	public DirectedEdge(int SourceID,int DestinationID,String Name,int Cost){
		this.SourceID=SourceID;
		this.DestinationID=DestinationID;
		this.Cost=Cost;
	}
}
public class Graph{
	HashSet<DirectedEdge>[] Graph_Map;//���ӱ�
	HashMap<String,String> EdgeName;
	Set<Integer> Vertexs;//���㼯��
	boolean[] visit;
	int V;//������Ŀ
	int E;//����Ŀ
	Graph(Set<Integer> Vertexs,int E,HashSet<DirectedEdge>[] Graph_Map){
		this.Vertexs=Vertexs;
		this.E=E;
		this.Graph_Map=Graph_Map;
		this.V=Vertexs.size();
		this.visit=new boolean[this.V];
		this.EdgeName=new HashMap<>();
	}

	//@SuppressWarnings("unchecked")
	Graph(int max_vertex){
		E=0;
		V=0;
		Vertexs=new HashSet<>();
		visit=new boolean[max_vertex];
		this.EdgeName=new HashMap<>();
		Graph_Map=(HashSet<DirectedEdge>[])new HashSet[max_vertex];
		for(int i=0;i<max_vertex;i++)
			Graph_Map[i]=new HashSet<DirectedEdge>();		
	}
	public void addEdge(DirectedEdge e,String E_Name){
		Graph_Map[e.SourceID].add(e);
		E++;
		Vertexs.add(e.SourceID);
		Vertexs.add(e.DestinationID);
		V=Vertexs.size();
		String key=String.valueOf(e.SourceID)+String.valueOf(e.DestinationID);
		EdgeName.put(key, E_Name);
	}
}
