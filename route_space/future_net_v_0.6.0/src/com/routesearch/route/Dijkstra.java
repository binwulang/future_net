package com.routesearch.route;

import java.util.Comparator;

class PD_Info{
	
	int distTo;
	DirectedEdge pathTo;
	int id;
	boolean first;
	PD_Info(int id){
		this.id=id;
		this.distTo=Dijkstra.INFINTY;
		this.pathTo=null;
		this.first=false;
	}
}

public class Dijkstra {
	static final int INFINTY=0x7fffffff;
	static Comparator<PD_Info> OrderIsdn =  new Comparator<PD_Info>(){  
        public int compare(PD_Info o1, PD_Info o2) {  
            // TODO Auto-generated method stub  
            int numbera = o1.distTo;  
            int numberb = o2.distTo;  
            if(numberb < numbera)return 1;  
            else if(numberb>numbera)return -1;  
            else  return 0;  
        }  
    };
    static Comparator<PD_Info> OrderIsdn2 =  new Comparator<PD_Info>(){  
        public int compare(PD_Info o1, PD_Info o2) {  
            // TODO Auto-generated method stub  
        	if(o1.first==true&&o2.first==false)return -1;
        	if(o1.first==false&&o2.first==true)return 1;
            int numbera = o1.distTo;  
            int numberb = o2.distTo;  
            if(numberb < numbera)return 1;  
            else if(numberb>numbera)return -1;  
            else  return 0;  
        }  
    };
}
