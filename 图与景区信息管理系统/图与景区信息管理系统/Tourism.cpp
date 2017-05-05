#include "stdafx.h"
#include "Tourism.h"

void CreateGraph(ALGraph &G)
{
	InitGraph(G);
	cout << "创建景点图成功" << endl;
	cout << "顶点数目：" << G.vexnum << endl;
	cout << "-------顶点-------" << endl;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
		if (G.vertices[i].data.num != -1)
		{
			cout << G.vertices[i].data.num << "-";
			for (int k = 0; k < strlen(G.vertices[i].data.name); k++)
				cout << G.vertices[i].data.name[k];
			cout << endl;
		}
	cout << "--------边--------" << endl;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
	{
		if (G.vertices[i].data.num != -1)
		{
			ArcNode *p = G.vertices[i].firststarc;
			while (p != NULL)
			{
				if (i<p->adjvex)
					cout << "<v" << G.vertices[i].data.num << ",v" << p->adjvex << ">  " << p->weight << endl;
				p = p->next;
			}
		}
	}
}

void getSpotInfo(ALGraph G)
{
	cout << "-------顶点信息-------" << endl;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
		if (G.vertices[i].data.num != -1)
		{
			cout << G.vertices[i].data.num << "-";
			for (int k = 0; k < strlen(G.vertices[i].data.name); k++)
				cout << G.vertices[i].data.name[k];
			cout << endl;
		}
	int nVex;
	cout << "请输入想要查询的景点编号：";
	cin >> nVex;
	Vex vex = getVex(G, nVex);
	cout << vex.name << endl;
	cout << vex.description << endl;
	cout << "-------周边景区-------" << endl;
	ArcNode *p = G.vertices[nVex].firststarc;
	while (p != NULL)
	{
		cout << vex.name << "->" << G.vertices[p->adjvex].data.name << "   " << p->weight << "米" << endl;
		p = p->next;
	}
}

void Guidance(ALGraph G)
{
	cout << "-------顶点信息-------" << endl;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
		if (G.vertices[i].data.num != -1)
		{
			cout << G.vertices[i].data.num << "-";
			for (int k = 0; k < strlen(G.vertices[i].data.name); k++)
				cout << G.vertices[i].data.name[k];
			cout << endl;
		}
	int nVex;
	cout << "请输入想要查询的景点编号：";
	cin >> nVex;
	Path path = NULL;
	DFSTraver(G, path, nVex);
	pNode *p = path;
	int count = 0;
	while (p != NULL)
	{
		int i = 0;
		if (p->vex[G.vexnum] == -1)
		{
			cout << "路线" << ++count << "：";
			for (; i < G.vexnum - 1 && p->vex[i + 1] != -1; i++)
				cout << G.vertices[p->vex[i]].data.name << " -> ";
			cout << G.vertices[p->vex[i]].data.name << endl;
		}
		p = p->next;
	}
}

void FindShortPath(ALGraph G)
{
	int start_vex, end_vex;
	cout << "-------最短路径-------" << endl;
	cout << "-------顶点-------" << endl;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
		if (G.vertices[i].data.num != -1)
		{
			cout << G.vertices[i].data.num << "-";
			for (int k = 0; k < strlen(G.vertices[i].data.name); k++)
				cout << G.vertices[i].data.name[k];
			cout << endl;
		}
	cout << "请输入起始位置：";
	cin >> start_vex;
	cout << "请输入结束位置：";
	cin >> end_vex;
	int *path = (int*)malloc(G.vexnum * sizeof(int));
	FindShortPath(G, path, start_vex, end_vex);
	cout << "路线" << "：";
	int i = 0;
	for (i = 0; i < G.vexnum && path[i + 1] != -1; i++)
		cout << G.vertices[path[i]].data.name << " -> ";
	cout << G.vertices[path[i]].data.name << endl;
	int path_len = 0;
	for (int j = 0; j < i; j++)
	{
		ArcNode *arc = G.vertices[path[j]].firststarc;
		while (arc != NULL)
		{
			if (arc->adjvex == path[j + 1])
			{
				path_len += arc->weight;
				break;
			}
			arc = arc->next;
		}
	}
	cout << "最短路径长度为：" << path_len << "米" << endl;
}

void DesignPath(ALGraph G)
{
	cout << "-------电路铺设方案-------" << endl;
	bool *visited = (bool*)malloc(G.vexnum * sizeof(bool));
	for (int i = 0; i < G.vexnum; visited[i++] = false);
	int isvisited = 0;
	int start_pos = 0, end_pos = 0, weight = 0, len = 0;
	while (isvisited != G.vexnum)
	{
		int min = INFINITY;
		for (int i = 0; i < G.vexnum; i++)
		{
			ArcNode *arc = G.vertices[i].firststarc;
			while (arc != NULL)
			{
				if (i < arc->adjvex && (!visited[i] || !visited[arc->adjvex]))
				{
					min = min < arc->weight ? min : arc->weight;
					if (min == arc->weight)
					{
						start_pos = i;
						end_pos = arc->adjvex;
						weight = min;
					}
				}
				arc = arc->next;
			}
		}
		cout << G.vertices[start_pos].data.name << " --- " << G.vertices[end_pos].data.name << " ：" << weight << "米" << endl;
		len += weight;
		if (!visited[start_pos])
		{
			visited[start_pos] = true;
			isvisited++;
		}
		if (!visited[end_pos])
		{
			visited[end_pos] = true;
			isvisited++;
		}
	}
	cout << "总长：" << len << "米" << endl;
}