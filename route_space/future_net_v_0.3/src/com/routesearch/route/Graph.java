package com.routesearch.route;

import java.util.*;
class DirectedEdge{//�ߵĶ���
	int Cost;
	int SourceID;
	int DestinationID;
	int E_Name;
	public DirectedEdge(){
		
	}
	public DirectedEdge(int E_Name,int SourceID,int DestinationID,String Name,int Cost){
		this.E_Name=E_Name;
		this.SourceID=SourceID;
		this.DestinationID=DestinationID;
		this.Cost=Cost;
	}
}
public class Graph{
	Set<DirectedEdge>[] Graph_Map;//���ӱ�
	Map<String,String> EdgeName;//��Ҫ����HashMap������?
	//Set<Integer> Vertexs;//���㼯��
	boolean[] visit;
	int[] estimate_value;
	int V;//������Ŀ
	int E;//����Ŀ
	{
		EdgeName=Collections.synchronizedMap(new HashMap<String,String>());
		//Vertexs=new HashSet<Integer>();
	}
	/*
	Graph(Set<Integer> Vertexs,int E,HashSet<DirectedEdge>[] Graph_Map){
		this.Vertexs=Vertexs;
		this.E=E;
		this.Graph_Map=Graph_Map;
		this.V=Vertexs.size();
		this.visit=new boolean[this.V];	
	}
*/
	//@SuppressWarnings("unchecked")
	Graph(int max_vertex){
		E=0;
		V=0;
		estimate_value=new int[max_vertex];
		visit=new boolean[max_vertex];
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
		V=V>e.SourceID?V:e.SourceID;
		V=V>e.DestinationID?V:e.DestinationID;
		V++;
		String key=String.valueOf(e.SourceID)+String.valueOf(e.DestinationID);
		EdgeName.put(key, String.valueOf(e.E_Name));
		
		if(e.Cost>estimate_value[e.SourceID])
			estimate_value[e.SourceID]=e.Cost;
	}
}
