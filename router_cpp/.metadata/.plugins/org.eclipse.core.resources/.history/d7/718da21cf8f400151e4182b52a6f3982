/*
 * SolutionDijstraDFS.h
 *
 *  Created on: 2016年3月24日
 *      Author: yaobin
 */

#ifndef SRC_SOLUTIONDIJSTRADFS_H_
#define SRC_SOLUTIONDIJSTRADFS_H_
#include <set>
#include "Graph.h"
class Solution_Dijstra_DFS {
	vector<int> min_path ;
	int Min_Cost;
	set<int> *IncVertexs;
	Graph *graph;
	bool *inc_node;
public:
	Solution_Dijstra_DFS();
	virtual ~Solution_Dijstra_DFS();
	vector<int> * GetPath();
	void DijstraDFS_SP1(int StartID, int EndID,int Cost,vector<int>* path,int IncNode_Count);
	void DijstraDFS_SP2(int StartID, int EndID,int Cost,vector<int>* path);
	void FindPath(int StartID, int EndID,set<int>* IncVertexs,Graph *graph);
};


#endif /* SRC_SOLUTIONDIJSTRADFS_H_ */
