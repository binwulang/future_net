
#include "route.h"
#include "../lib/lib_record.h"
#include <stdio.h>
#include <sys/time.h>
#include <unistd.h>
#include <math.h>
#include <set>
#include "Graph.h"
#include "SolutionDFS.h"
#include "SolutionDijstraDFS.h"
#include <pthread.h>
int StartID;
int EndID;
set<int> IncVertexs;
Graph *graph;
Solution_Dijstra_DFS solution;
pthread_cond_t condition;
pthread_mutex_t lock;
Graph* CreatGraph(char **topo,int edge_num);
void AnalysisCondition(char *demand);
void* run(void *argv){
	pthread_mutex_lock(&lock);
	pthread_setcancelstate(PTHREAD_CANCEL_DISABLE,NULL);
	solution.FindPath(StartID,EndID,&IncVertexs,graph);
	pthread_cond_signal(&condition);
	pthread_mutex_unlock(&lock);
	return NULL;
}
//你要完成的功能总入口
void search_route(char *topo[5000], int edge_num, char *demand)
{
	graph=CreatGraph(topo,edge_num);
	AnalysisCondition(demand);
	pthread_t thread_id;

	pthread_mutex_init(&lock,NULL);
	pthread_cond_init(&condition,NULL);
	pthread_mutex_lock(&lock);
	struct timespec outtime;
	struct timeval now;
	gettimeofday(&now, NULL);
	 outtime.tv_sec = now.tv_sec + 9;
	 //outtime.tv_nsec = now.tv_usec * 1000;
	pthread_create(&thread_id,NULL,run,NULL);
	pthread_cond_timedwait(&condition,&lock,&outtime);
	pthread_mutex_unlock(&lock);
	pthread_cancel(thread_id);
	vector<int> *path=solution.GetPath();
	if(path->size()){
		for(int id:*path)
			record_result(id);
	}
    delete graph;
}

int charToint(char*begin,char*end){
	if(begin==NULL||end==NULL)return -1;
	if(begin==end)return *begin-'0';
	int num=0;
	int len=0;
	while(end>=begin){
		num+=(*end-'0')*pow(10,len++);
		end--;
	}
	return num;
}

Graph* CreatGraph(char **topo,int edge_num)
{
	Graph* graph=new Graph();
	 int edge_name;
	 int SourceID;
	 int DestinationID;
	int Cost;
	for(int i=0;i<edge_num;i++){
		char*p=topo[i];
		char*q=p;
		int count=0;
		while(*p!='\n'&&*p!='\0'&&*p!='\r'){
			if(*p==','){
				count++;
				switch(count){
				case 1:
					edge_name=charToint(q,p-1);
					q=p+1;
					break;
				case 2:
					SourceID=charToint(q,p-1);
					q=p+1;
					break;
				case 3:
					DestinationID=charToint(q,p-1);
					q=p+1;
					break;
				}
			}
			p++;
		}
		if(count==3){
			Cost=charToint(q,p-1);
			DirectedEdge e(SourceID,DestinationID,edge_name,Cost);
			graph->addEdge(e);
			count=0;
		}
	}
	return graph;
}


void AnalysisCondition(char *demand){
	if(demand==NULL)return;
	char*p=demand;
	char*q=p;
	int count=0;
	while(*p!='\n'){
		if(*p==','){
			count++;
			if(count==1){
				StartID=charToint(q,p-1);
				q=p+1;
			}
			else if(count==2){
				EndID=charToint(q,p-1);
				q=p+1;
				break;
			}
		}
		p++;
	}
	/*
	if(*p=='\n'){
		EndID=charToint(q,p-1);
		return;//无条件顶点
	}*/
	count=0;
	while(*p!='\n'&&*p!='\0'&&*p!='\r'){
		if(*p=='|'){
			IncVertexs.insert(charToint(q,p-1));
			q=p+1;
		}
		p++;
	}
	IncVertexs.insert(charToint(q,p-1));
}