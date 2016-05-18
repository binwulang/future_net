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
	Set<DirectedEdge>[] Graph_Map;//���ӱ�
	Map<String,String> EdgeName;//��Ҫ����HashMap������?
	Set<Integer> Vertexs;//���㼯��
	boolean[] visit;
	int V;//������Ŀ
	int E;//����Ŀ
	{
		EdgeName=Collections.synchronizedMap(new HashMap<String,String>());
	}
	Graph(Set<Integer> Vertexs,int E,HashSet<DirectedEdge>[] Graph_Map){
		this.Vertexs=Vertexs;
		this.E=E;
		this.Graph_Map=Graph_Map;
		this.V=Vertexs.size();
		this.visit=new boolean[this.V];	
	}

	//@SuppressWarnings("unchecked")
	Graph(int max_vertex){
		E=0;
		V=0;
		Vertexs=Collections.synchronizedSet(new HashSet<Integer>());
		visit=new boolean[max_vertex];
		Graph_Map=(Set<DirectedEdge>[])new Set[max_vertex];
		for(int i=0;i<max_vertex;i++)
			Graph_Map[i]=Collections.synchronizedSet(new HashSet<DirectedEdge>());		
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
