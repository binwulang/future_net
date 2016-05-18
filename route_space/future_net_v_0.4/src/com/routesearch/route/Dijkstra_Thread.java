package com.routesearch.route;

import java.util.PriorityQueue;
import java.util.Set;

class Dijkstra_Thread implements Runnable{
	Graph graph;
	int ID;
	Info[]info;
	//PriorityQueue<Info> queue;
	//Set<Integer> IncVertexs;
	Dijkstra_Thread(int ID,Graph graph){
		this.ID=ID;
		this.graph=graph;
		//this.queue=new PriorityQueue<>(IncVertexs.size(),Dijkstra.OrderIsdn);
		//this.IncVertexs=IncVertexs;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		info=Dijkstra.DijkstraSP(ID, graph);
		/*
		for(int i=0;i<info.length;i++)
			if(IncVertexs.contains(i)){
				queue.add(info[i]);
			}
		*/
	}
}