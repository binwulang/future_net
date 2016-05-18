/*
 * Dijkstra.h
 *
 *  Created on: 2016年3月25日
 *      Author: yaobin
 */

#ifndef DIJKSTRA_H_
#define DIJKSTRA_H_
#include "Graph.h"
const int INFINTY=0x7fffffff;
class PD_Info{
public:
	int distTo;
	DirectedEdge *pathTo;
	PD_Info(){
		this->distTo=INFINTY;
		this->pathTo=NULL;
	}
};
class Dijkstra {
public:
	bool operator()(PD_Info o1,PD_Info o2){
		return o1.distTo>o2.distTo;
	}
	Dijkstra();
	virtual ~Dijkstra();
};

#endif /* DIJKSTRA_H_ */
