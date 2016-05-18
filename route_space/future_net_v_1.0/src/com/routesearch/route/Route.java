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
	private static final String NO_PATH="NA";
	private static final int MAX_VERTEX=600;
	private Graph CreatGraph(String graphContent){
		Graph graph=new Graph(MAX_VERTEX);
		DirectedEdge edge=null;
		char[] graphcontent=graphContent.toCharArray();
    	int count=0;
    	int index=0;
		for(int i=0;i<graphcontent.length;i++){
			if(graphcontent[i]==','||graphcontent[i]=='\n'){
				count++;
    			switch(count){
    			case 1://Edge name
    				edge=new DirectedEdge();
    				edge.Name=graphContent.substring(index, i);
    				index=i+1;
    				break;
    			case 2://SourceID
    				edge.SourceID=Integer.valueOf(graphContent.substring(index, i));
    				index=i+1;
    				break;
    			case 3://DestinationID
    				edge.DestinationID=Integer.valueOf(graphContent.substring(index, i));
    				index=i+1;
    				break;
    			case 4://Cost
    				edge.Cost=Integer.valueOf(graphContent.substring(index, i));
    				index=i+1;
    				count=0;
    				graph.addEdge(edge);
    				break;
    			}				
			}
		}
		return graph;
	}
/*
 * 创建图
 */
	/*
	private Graph CreatGraph(String graphContent, String condition){
		Graph graph=null;
    	HashMap<String,Set<String>> Graph_Map=new HashMap<>();
    	HashMap<String,Edge> Cost_Table=new HashMap<>();
    	char[] graphcontent=graphContent.toCharArray();
    	String SourceID=null;
    	String DestinationID=null;
    	Integer Cost=null;
    	String key=null;
    	Set<String> nbr_link=null;
    	String E_name=null;
    	int count=0;
    	int index=0;
    	for(int i=0;i<graphcontent.length;i++){
    		if(graphcontent[i]==','||graphcontent[i]=='\n'){
    			count++;
    			switch(count){
    			case 1:
    				E_name=graphContent.substring(index, i);
    				index=i+1;
    				break;
    			case 2://SourceID
    				SourceID=graphContent.substring(index, i);
    				index=i+1;
    				break;
    			case 3://DestinationID
    				if(!Graph_Map.containsKey(SourceID))
    					nbr_link=new HashSet<String>();
    				DestinationID=graphContent.substring(index, i);
    				nbr_link.add(DestinationID);
    				Graph_Map.put(SourceID, nbr_link);
    				index=i+1;
    				break;
    			case 4://Cost
    				key=SourceID+DestinationID;
    				Cost=new Integer(graphContent.substring(index, i));
    				Edge edge=new Edge(E_name,Cost.intValue());
    				Cost_Table.put(key, edge);
    				index=i+1;
    				count=0;
    				break;
    			}
    		}
    	}
    	graph=new Graph(Graph_Map.size(),Cost_Table.size(),Graph_Map,Cost_Table);
		return graph;
	}*/
    /**
     * 你需要完成功能的入口
     * 
     * @author XXX
     * @since 2016-3-4
     * @version V1
     */
    public static String searchRoute(String graphContent, String condition)
    {
    	String path=new String(NO_PATH);
    	Route route=new Route();
    	Graph graph=route.CreatGraph(graphContent);
    	
        return path;
    }

}
