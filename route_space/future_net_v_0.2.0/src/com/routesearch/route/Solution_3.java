package com.routesearch.route;

import java.util.*;
import java.util.concurrent.*;

public class Solution_3 implements PathSolution{
	private String Min_Cost_Path=Route.NO_PATH;
	boolean Check_Dijkstra_Path(Info[]info,int StartID,int EndID,Set<Integer> IncVertexs,Graph graph){
		Stack<Integer> path=new Stack<>();
		path.push(EndID);
    	int ID=info[EndID].pathTo;
    	path.push(ID);
    	while(ID!=StartID){
    		ID=info[ID].pathTo;
    		path.push(ID);
    	}
    	if(path.containsAll(IncVertexs)){
    		int pre_id=path.pop();
    		int id=path.pop();
    		Min_Cost_Path=graph.EdgeName.get(String.valueOf(pre_id)+String.valueOf(id));
    		pre_id=id;
    		while(path.size()>0){
    			id=path.pop();
    			Min_Cost_Path+="|"+graph.EdgeName.get(String.valueOf(pre_id)+String.valueOf(id));
    			pre_id=id;
    		}
    		return true;
    	}
    	else return false;
	}
	@Override
	public String FindPath(int StartID, int EndID, Set<Integer> IncVertexs,Graph graph) {
		// TODO Auto-generated method stub
		//��ע:�����費��Ҫ�Ѷ�StartID�ı���Ҳ�����̳߳�����ִ�У�����
		Info[]start_info=Dijkstra.DijkstraSP(StartID, graph);
		if(start_info[EndID].distTo==Route.INFINTY)return Min_Cost_Path;
		for(Integer id:IncVertexs){//�м���һ���㶼���ܵ���
			if(start_info[id.intValue()].distTo==Route.INFINTY)
				return Min_Cost_Path;
		}
		//��ע:�˴��費��Ҫ���ж�?���ǵ�����һ���߳�ִ�У�
		if(Check_Dijkstra_Path(start_info,StartID,EndID,IncVertexs,graph))return Min_Cost_Path;//Dijkstra����������·�����ð���������
		ExecutorService pool=Executors.newFixedThreadPool(IncVertexs.size()+1);
		Dijkstra_Thread[] dijkstra_thread=new Dijkstra_Thread[IncVertexs.size()+1];
		dijkstra_thread[0]=new Dijkstra_Thread(EndID,graph);
		pool.submit(dijkstra_thread[0]);
		int count=1;
		for(Integer id:IncVertexs){
			dijkstra_thread[count]=new Dijkstra_Thread(id.intValue(),graph);
			pool.submit(dijkstra_thread[count]);
			count++;
		}
		pool.shutdown();
		try {
			pool.awaitTermination(8, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Min_Cost_Path;
	}

}
