package com.routesearch.route;

import java.util.*;
class DirectedEdge{
	String Name;
	int Cost;
	int SourceID;
	int DestinationID;
	public DirectedEdge(){
		
	}
	public DirectedEdge(int SourceID,int DestinationID,String Name,int Cost){
		this.SourceID=SourceID;
		this.DestinationID=DestinationID;
		this.Name=Name;
		this.Cost=Cost;
	}
}
public class Graph {
	HashMap<Integer,Set<DirectedEdge>> Graph_Map;
	private int V;//������Ŀ
	private int E;//����Ŀ ֻ��������>0�Ķ���
	Graph(int V,int E,HashMap<Integer,Set<DirectedEdge>> Graph_Map){
		this.V=V;
		this.E=E;
		this.Graph_Map=Graph_Map;
	}
	Graph(int max_vertex){
		E=0;
		V=0;
		Graph_Map=new HashMap<Integer,Set<DirectedEdge>>();
	}
	public void addEdge(DirectedEdge e){
		if(!Graph_Map.containsKey(e.SourceID)){
			Graph_Map.put(e.SourceID, new HashSet<DirectedEdge>());
		}
		Set<DirectedEdge> link_nbr=Graph_Map.get(e.SourceID);
		link_nbr.add(e);
		E++;
		V=Graph_Map.size();
	}
}
