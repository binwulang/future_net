package com.routesearch.route;

import java.util.Comparator;
import java.util.PriorityQueue;

//Dijkstra
class Info{
	int distTo;
	int pathTo;
	int id;
	Info(int id,int distTo){
		this.id=id;
		this.distTo=distTo;
		this.pathTo=0;
	}
}
public class Dijkstra {
	private static final int INFINTY=0x7fffffff;
	private static Comparator<Info> OrderIsdn =  new Comparator<Info>(){  
        public int compare(Info o1, Info o2) {  
            // TODO Auto-generated method stub  
            int numbera = o1.distTo;  
            int numberb = o2.distTo;  
            if(numberb < numbera)return 1;  
            else if(numberb>numbera)return -1;  
            else  return 0;  
        }  
    };
	static Info[] DijkstraSP(int StartID,Graph graph){
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,INFINTY);
		info[StartID].distTo=0;		
		info[StartID].pathTo=StartID;
        PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,OrderIsdn);
        queue.add(info[StartID]);
        Info temp;
        while(!queue.isEmpty()){
        	temp=queue.poll();
        	//if(temp.id==EndID&&IncVertexs==null)break;//当吐出的是EndID，则可结束.标准的Dijkstra,不需要经过指定的点
        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
        		if(info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=temp.id;
        			queue.add(info[e.DestinationID]);
        		}
        	}
        }
		return info;
	}
	static Info[] DijkstraSP(int StartID, int EndID,Graph graph){
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,INFINTY);
		info[StartID].distTo=0;	
		info[StartID].pathTo=StartID;
        PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,OrderIsdn);
        queue.add(info[StartID]);
        Info temp;
        while(!queue.isEmpty()){
        	temp=queue.poll();
        	if(temp.id==EndID)break;//当吐出的是EndID，则可结束.标准的Dijkstra,不需要经过指定的点
        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
        		if(info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=temp.id;
        			queue.add(info[e.DestinationID]);
        		}
        	}
        }
		return info;		
	}
}
