package com.routesearch.route;

class Dijkstra_Thread implements Runnable{
	Graph graph;
	int ID;
	Info[]info;
	Dijkstra_Thread(int ID,Graph graph){
		this.ID=ID;
		this.graph=graph;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		info=Dijkstra.DijkstraSP(ID, graph);
	}
}