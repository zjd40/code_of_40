#pragma once
#include "stdafx.h"
#include "Graph.h"

void CreateGraph(ALGraph &G);			//��ȡ�ļ���������������ͼ

void getSpotInfo(ALGraph G);			//��ѯָ���������Ϣ����ʵ���ھ���ľ���

void Guidance(ALGraph G);				//��ѯָ����������е���·��

void FindShortPath(ALGraph G);			//Ѱ����������·��

void DesignPath(ALGraph G);				//���辰���·