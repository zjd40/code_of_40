#pragma once
#include "stdafx.h"

#define MAX_VEXTEX_NUM	20			//最大顶点个数

typedef struct{
	int num;						//景点编号
	char *name;						//景点名字
	char *description;				//景点介绍
}Vex;								//顶点信息

typedef struct ArcNode {
	int adjvex;						//该弧所指向的顶点的位置
	int weight;						//权值
	struct ArcNode *next;			//指向下一条弧的指针
}ArcNode;							//弧的信息

typedef struct VNode {
	Vex data;						//顶点信息
	int num;						//该顶点指向的其他顶点的个数
	ArcNode *firststarc;			//指向第一条依附该顶点的弧的指针
}VNode, AdjList[MAX_VEXTEX_NUM];	//存放顶点数组

typedef struct {
	AdjList vertices;				//无向图邻接表
	int vexnum, arcnum;				//顶点个数与弧个数
}ALGraph;


typedef struct pNode{
	int vex[MAX_VEXTEX_NUM];
	struct pNode *next;
}pNode, *Path;

void InitGraph(ALGraph &G);												//初始化邻接表

Vex getVex(ALGraph G, int nVex);										//查询指定顶点信息

int getArc(ALGraph G, int nVex, Vex vex[]);								//查询与指定顶点相连的边

int DFSTraver(ALGraph G, Path &path, int nVex);							//指定顶点编号开始深度优先搜索图

int FindShortPath(ALGraph G, int *path, int start_vex, int end_vex);	//寻找最短路径