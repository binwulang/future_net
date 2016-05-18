package com.routesearch.route;

import java.util.Set;

public interface PathSolution {
	public abstract String FindPath(int StartID,int EndID,Set<Integer> IncVertexs,Graph graph);
}
