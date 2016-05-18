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
    public static String searchRoute(String graphContent, String condition)
    {
    	if(graphContent==null||condition==null)return null;
    	Graph graph=Graph.CreatGraph(graphContent);//创建图
    	Graph.AnalysisCondition(condition);
    	String path=NO_PATH;
    	if(graph.Vertexs.size()<=30){
    		Solution_DFS solution=new Solution_DFS();
        	solution.FindPath(StartID, EndID, IncVertexs, graph);
        	try {
    			solution.join(9700);
    			solution.stop();
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	path=solution.GetResult();      		
    	}
    	else{
        	Solution_Dijstra_DFS solution=new Solution_Dijstra_DFS();
        	solution.FindPath(StartID, EndID, IncVertexs, graph);
        	try {
    			solution.join(9700);
    			solution.stop();
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	path=solution.GetResult();    		
    	}
        return path;
    }

}
