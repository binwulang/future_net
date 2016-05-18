package com.routesearch.route;

import java.util.*;

//Dijkstra
public class Solution_Dijkstra implements PathSolution{
	private static final String NO_PATH="NA";
	private static final int INFINTY=0x7fffffff;
	private String Min_Cost_Path=NO_PATH;
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
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,Graph graph) {
		// TODO Auto-generated method stub
		Info[] info=new Info[graph.V];
		for(int i=0;i<info.length;i++)
			info[i]=new Info(i,INFINTY);
		info[StartID].distTo=0;	
        Comparator<Info> OrderIsdn =  new Comparator<Info>(){  
            public int compare(Info o1, Info o2) {  
                // TODO Auto-generated method stub  
                int numbera = o1.distTo;  
                int numberb = o2.distTo;  
                if(numberb < numbera)return 1;  
                else if(numberb>numbera)return -1;  
                else  return 0;  
            }  
        };
        PriorityQueue<Info> queue=new PriorityQueue<>(OrderIsdn);
        queue.add(info[StartID]);
        Info temp;
        while(!queue.isEmpty()){
        	temp=queue.poll();
        	if(temp.id==EndID)break;
        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
        		if(info[e.DestinationID].distTo>temp.distTo+e.Cost){
        			queue.remove(info[e.DestinationID]);
        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
        			info[e.DestinationID].pathTo=temp.id;
        			queue.add(info[e.DestinationID]);
        		}
        	}
        }
        if(info[EndID].distTo!=INFINTY){
        	int[] path=new int[graph.V];
        	int len=0;
        	path[len++]=EndID;
        	int ID=info[EndID].pathTo;
        	path[len++]=ID;
        	while(ID!=StartID){
        		ID=info[ID].pathTo;
        		path[len++]=ID;
        	}
        	Min_Cost_Path=graph.EdgeName.get(String.valueOf(path[len-1])+String.valueOf(path[len-2]));
        	len-=2;
        	while(len>0){
        		Min_Cost_Path+="|"+graph.EdgeName.get(String.valueOf(path[len])+String.valueOf(path[len-1]));
        		len--;
        	}
        }
		return Min_Cost_Path;
	}
}
