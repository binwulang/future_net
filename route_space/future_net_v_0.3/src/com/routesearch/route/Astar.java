package com.routesearch.route;

import java.util.Comparator;
import java.util.*;

class Astar_Info{
	int distTo;//到其实节点的实际距离
	int pathTo;//父亲
	int id;//自己的ID
	int fTo;//估价值
	Astar_Info(int id,int distTo){
		this.id=id;
		this.distTo=distTo;
		this.pathTo=0;
		this.fTo=distTo;
	}
}
public class Astar {
	static Comparator<Astar_Info> OrderIsdn =  new Comparator<Astar_Info>(){  
        public int compare(Astar_Info o1, Astar_Info o2) {  
            // TODO Auto-generated method stub  
            int numbera = o1.fTo;  
            int numberb = o2.fTo;  
            if(numberb < numbera)return 1;  
            else if(numberb>numbera)return -1;  
            else  return 0;  
        }  
    };
    static Astar_Info[] AstarSP(int StartID, int EndID,Graph graph){
    	Astar_Info[] info=new Astar_Info[graph.V];
    	boolean[] visit=new boolean[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Astar_Info(i,Route.INFINTY);
    	info[StartID].distTo=0;
    	info[StartID].pathTo=StartID;
    	info[StartID].fTo=0;//f(n)
    	PriorityQueue<Astar_Info> open=new PriorityQueue<>(graph.V,OrderIsdn);
    	open.add(info[StartID]);
    	Astar_Info temp;
    	while(!open.isEmpty()){
    		temp=open.poll();
    		if(temp.id==EndID){
    			//System.out.println("OK");
    			
            	//int[] path=new int[graph.V];
            	//int len=0;
            	//path[len++]=EndID;
    			System.out.printf(EndID+",");
            	int ID=info[EndID].pathTo;
            	//path[len++]=ID;
            	while(ID!=StartID){
            		System.out.printf(ID+",");
            		ID=info[ID].pathTo;
            		//path[len++]=ID;
            	}
            	System.out.println("");
    			break;
    		}
    		for(DirectedEdge e:graph.Graph_Map[temp.id]){
    			int Estimate_Value=graph.estimate_value[e.DestinationID];//求估价值
    			//int Estimate_Value=20;
    			if(open.contains(info[e.DestinationID])){//open 列表
    				if(Estimate_Value+info[e.DestinationID].distTo<info[e.DestinationID].fTo){//X的估价值小于OPEN表的估价值
    					info[e.DestinationID].pathTo=temp.id;//把n设置为X的父亲;
    					info[e.DestinationID].distTo=temp.distTo+e.Cost;
    					info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//取最小路径的估价值
    				}
    			}
    			else if(visit[e.DestinationID]==true){//close 
    				continue;
    			}
    			else{
    				info[e.DestinationID].pathTo=temp.id;//把n设置为X的父亲;
    				info[e.DestinationID].distTo=temp.distTo+e.Cost;
    				info[e.DestinationID].fTo=Estimate_Value+info[e.DestinationID].distTo;//求X的估价值
    				open.add(info[e.DestinationID]);//并将X插入OPEN表中;还没有排序
    			}
    		}
    		visit[temp.id]=true;//将n节点插入CLOSE表中;
    		//open.add(temp);//按照估价值将OPEN表中的节点排序;
    	}
		return info;
    }
}