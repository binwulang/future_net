package com.routesearch.route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
class Info{
	
	int distTo;
	int pathTo;
	int id;
	Info(int id){
		this.id=id;
		this.distTo=Dijkstra.INFINTY;
		this.pathTo=0;
	}
	static Comparator<Info> OrderIsdn =  new Comparator<Info>(){  
        public int compare(Info o1, Info o2) {  
            // TODO Auto-generated method stub  
            int numbera = o1.distTo;  
            int numberb = o2.distTo;  
            if(numberb < numbera)return 1;  
            else if(numberb>numbera)return -1;  
            else  return 0;  
        }  
    };
}
class Prior_Path{
	List<Integer> path;
	int inc_count;
	int Cost;
	Set<Integer> disable_inc;
	Prior_Path(List<Integer> path,int inc_count,int Cost){
		this.path=path;
		this.inc_count=inc_count;
		this.Cost=Cost;
		this.disable_inc=new HashSet<>();
	}
	static Comparator<Prior_Path> OrderIsdn =  new Comparator<Prior_Path>(){  
        public int compare(Prior_Path o1, Prior_Path o2) {  
            // TODO Auto-generated method stub  
            int numbera = o1.inc_count;  
            int numberb = o2.inc_count;  
            if(numberb > numbera)return 1;  
            else if(numberb<numbera)return -1;  
            else  return 0;  
        }  
    };
}
public class Solution_Dijstra_BFS extends Thread{
	private List<DirectedEdge> min_path=new ArrayList<>();
	//private List<Integer> min_path2=new ArrayList<>();
	private int Min_Cost=Dijkstra.INFINTY;
	private Graph graph;
	boolean []inc_node;
	private int StartID;
	private int EndID;
	private Set<Integer> IncVertexs;
	static int NO_PRE=0x7fffffff;
	void Dijstra_BFS_SP(PriorityQueue<Prior_Path> path){
		while(!path.isEmpty()){
			Prior_Path prior_path=path.poll();
			int inc_len=IncVertexs.size()-prior_path.inc_count;
			if(prior_path.Cost>Min_Cost||
            		inc_len>IncVertexs.size()-prior_path.disable_inc.size())
				continue;
			boolean visit[]=new boolean[graph.V];
			for(int path_id:prior_path.path)
				visit[path_id]=true;
			int Cost=prior_path.Cost;
			Info[] info=new Info[graph.V];
			for(int i=0;i<info.length;i++)
				info[i]=new Info(i);
			int startID=prior_path.path.get(0);
			info[startID].distTo=0;
			PriorityQueue<Info> queue=new PriorityQueue<>(graph.V,Info.OrderIsdn);
			queue.add(info[startID]);	
			if(inc_len==0){
				while(!queue.isEmpty()){
					Info temp=queue.poll();
					if(temp.id==EndID&&Cost+info[temp.id].distTo<Min_Cost){
						Min_Cost=Cost+info[temp.id].distTo;
						Stack<Integer> newpath=new Stack<>();
						int ID=temp.id;
						while(ID!=startID){
							newpath.push(ID);
							ID=info[ID].pathTo;
						}
						newpath.addAll(prior_path.path);
						//min_path2.addAll(newpath);
						System.out.println(newpath.toString()+Min_Cost);
					}
		        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
		        		if(visit[e.DestinationID]==false&&info[e.DestinationID].distTo>temp.distTo+e.Cost){
		        			queue.remove(info[e.DestinationID]);
		        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
		        			info[e.DestinationID].pathTo=e.SourceID;
		        			queue.add(info[e.DestinationID]);
		        		}
		        	}
				}
			}
			else{
				List<Prior_Path> son_path=new ArrayList<>();
				while(!queue.isEmpty()){
					Info temp=queue.poll();
					if(this.inc_node[temp.id]&&temp.id!=startID&&Cost+info[temp.id].distTo<Min_Cost){
						inc_len--;
						Stack<Integer> newpath=new Stack<>();			
						int ID=temp.id;
						int inc_count=prior_path.inc_count;
						int pre_disable_id=-1;
						while(ID!=startID){
							newpath.push(ID);
							if(this.inc_node[ID]){
								inc_count++;
								if(pre_disable_id==-1&&ID!=temp.id)pre_disable_id=ID;
							}
							ID=info[ID].pathTo;
						}
						newpath.addAll(prior_path.path);
						if(pre_disable_id!=-1){
							for(Prior_Path p:son_path)
								if(p.path.get(0)==pre_disable_id)
									p.disable_inc.add(temp.id);						
						}
						son_path.add(new Prior_Path(newpath,inc_count,Cost+info[temp.id].distTo));
						if(inc_len==0)break;
					}
		        	for(DirectedEdge e:graph.Graph_Map[temp.id]){
		        		if(visit[e.DestinationID]==false&&info[e.DestinationID].distTo>temp.distTo+e.Cost
		        				&&!prior_path.disable_inc.contains(e.DestinationID)&&e.DestinationID!=EndID){
		        			queue.remove(info[e.DestinationID]);
		        			info[e.DestinationID].distTo=temp.distTo+e.Cost;
		        			info[e.DestinationID].pathTo=e.SourceID;
		        			queue.add(info[e.DestinationID]);
		        		}
		        	}
				}
				path.addAll(son_path);
			}
		}
	}
	public void FindPath(int StartID, int EndID, Set<Integer> IncVertexs,
			Graph graph) {
		// TODO Auto-generated method stub
		this.IncVertexs=IncVertexs;
		this.StartID=StartID;;
		this.graph=graph;
		this.EndID=EndID;
		//this.start();
		///*for single thread test
		PriorityQueue<Prior_Path> path=new PriorityQueue<>(100,Prior_Path.OrderIsdn);
    	this.inc_node=new boolean[graph.V];
    	for(int id:IncVertexs)
    		this.inc_node[id]=true;
    	List<Integer> newpath=new ArrayList<>();
    	newpath.add(StartID);
    	path.add(new Prior_Path(newpath,0,0));
		Dijstra_BFS_SP(path);
		//*/
	}
	public String GetResult(){
		String Min_Cost_Path=Route.NO_PATH;
		if(min_path.size()>0){
			Min_Cost_Path=String.valueOf(min_path.get(0).E_Name);
			min_path.remove(0);
			for(DirectedEdge e:min_path)
			Min_Cost_Path+="|"+e.E_Name;
		}
		return Min_Cost_Path;	
	}
	@Override
	public void run() {
		PriorityQueue<Prior_Path> path=new PriorityQueue<>(100,Prior_Path.OrderIsdn);
    	this.inc_node=new boolean[graph.V];
    	for(int id:IncVertexs)
    		this.inc_node[id]=true;
    	List<Integer> newpath=new ArrayList<>();
    	newpath.add(StartID);
    	path.add(new Prior_Path(newpath,0,0));
		Dijstra_BFS_SP(path);		
	}
}
