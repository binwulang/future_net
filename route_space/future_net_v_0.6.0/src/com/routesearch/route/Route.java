/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

import java.util.*;

public final class Route
{
	static int StartID;
	static int EndID;
	static String NO_PATH="NA";
	static Set<Integer> IncVertexs=new HashSet<>();
	static final int MAX_VERTEX=600;
    /**
     * 你需要完成功能的入口
     * 
     * @author XXX
     * @since 2016-3-4
     * @version V1
     */
    @SuppressWarnings("deprecation")
	public static String searchRoute(String graphContent, String condition)
    {
    	if(graphContent==null||condition==null)return null;
    	Graph graph=Graph.CreatGraph(graphContent);//创建图
    	Graph.AnalysisCondition(condition);
    	String path=null;
    	if(graph.V<=30){//search in a 
    		Solution_DFS solution=new Solution_DFS();
        	solution.FindPath(StartID, EndID, IncVertexs, graph);
        	try {
    			solution.join(9500);
    			solution.stop();
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	path=solution.GetResult();      		
    	}
    	else{
    		if(IncVertexs.size()<=20){
	        	Solution_Dijstra_DFS2 solution=new Solution_Dijstra_DFS2();
	        	solution.FindPath(StartID, EndID, IncVertexs, graph);
	        	try {
	    			solution.join(3500);
	    			solution.stop();
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	path=solution.GetResult(); 	
    		}
    		else{
    			
	        	Solution_Dijstra_DFS2 solution=new Solution_Dijstra_DFS2();
	        	solution.FindPath(StartID, EndID, IncVertexs, graph);
	        	try {
	    			solution.join(3500);
	    			solution.stop();
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	path=solution.GetResult();
	        	
    			for(int i=0;i<graph.visit.length;i++)
    				graph.visit[i]=false;
    			
	        	Solution_Dijstra_DFS3 solution3=new Solution_Dijstra_DFS3();
	        	solution3.FindPath(StartID, EndID, IncVertexs, graph);
	        	try {
	        		solution3.join(3500);
	        		solution3.stop();
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	if(solution3.Min_Cost<solution.Min_Cost)
	        		path=solution3.GetResult();
    		}
    	}

        return path;
    }

}
