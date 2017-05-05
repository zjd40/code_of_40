#pragma once
#include "stdafx.h"
#include "Graph.h"

void CreateGraph(ALGraph &G);			//读取文件，创建景区景点图

void getSpotInfo(ALGraph G);			//查询指定景点的信息，现实相邻景点的距离

void Guidance(ALGraph G);				//查询指定景点的所有导游路线

void FindShortPath(ALGraph G);			//寻找两点间最短路径

void DesignPath(ALGraph G);				//铺设景点电路