#pragma once
#include "stdafx.h"

#define MAX_VEXTEX_NUM	20			//��󶥵����

typedef struct{
	int num;						//������
	char *name;						//��������
	char *description;				//�������
}Vex;								//������Ϣ

typedef struct ArcNode {
	int adjvex;						//�û���ָ��Ķ����λ��
	int weight;						//Ȩֵ
	struct ArcNode *next;			//ָ����һ������ָ��
}ArcNode;							//������Ϣ

typedef struct VNode {
	Vex data;						//������Ϣ
	int num;						//�ö���ָ�����������ĸ���
	ArcNode *firststarc;			//ָ���һ�������ö���Ļ���ָ��
}VNode, AdjList[MAX_VEXTEX_NUM];	//��Ŷ�������

typedef struct {
	AdjList vertices;				//����ͼ�ڽӱ�
	int vexnum, arcnum;				//��������뻡����
}ALGraph;


typedef struct pNode{
	int vex[MAX_VEXTEX_NUM];
	struct pNode *next;
}pNode, *Path;

void InitGraph(ALGraph &G);												//��ʼ���ڽӱ�

Vex getVex(ALGraph G, int nVex);										//��ѯָ��������Ϣ

int getArc(ALGraph G, int nVex, Vex vex[]);								//��ѯ��ָ�����������ı�

int DFSTraver(ALGraph G, Path &path, int nVex);							//ָ�������ſ�ʼ�����������ͼ

int FindShortPath(ALGraph G, int *path, int start_vex, int end_vex);	//Ѱ�����·��