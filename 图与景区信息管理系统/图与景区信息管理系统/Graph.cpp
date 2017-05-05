#include "stdafx.h"
#include "Graph.h"

vector<string> split(string str, string pattern)
{
	string::size_type pos;
	vector<string> result;
	str += pattern;
	int size = str.size();

	for (int i = 0; i < size; i++)
	{
		pos = str.find(pattern, i);
		if (pos < size)
		{
			string s = str.substr(i, pos - i);
			result.push_back(s);
			i = pos + pattern.size() - 1;
		}
	}
	return result;

}

int getMin(int num1, int num2)
{
	return num1 <= num2 ? num1 : num2;
}

int InsertVex(ALGraph &G)
{
	FILE *fp;
	char str[1024];
	int num = 0;
	if ((fp = fopen("Vex.txt", "rt")) == NULL)
	{
		cout << "无法打开文件" << endl;
		return ERROR;
	}

	//记录各顶点信息
	int vex_num = 0;
	while (fgets(str, 1024, fp)) 
	{
		num = 0;
		vector<string> data = split(str, "|");
		for (int k = 0; k < data[0].length(); k++)
			num = num * 10 + data[0][k] - '0';
		G.vertices[num].data.num = num;									//储存顶点编号信息
		G.vertices[num].data.name = (char*)malloc(data[1].length() * sizeof(char));
		strcpy(G.vertices[num].data.name, data[1].c_str());				//储存顶点名称信息
		G.vertices[num].data.description = (char*)malloc(data[2].length() * sizeof(char));
		strcpy(G.vertices[num].data.description, data[2].c_str());			//储存顶点描述信息
		vex_num++;
	}
	G.vexnum = vex_num;
	fclose(fp);
	return OK;
}

int InsertArc(ALGraph &G)
{
	FILE *fp;
	if ((fp = fopen("Arc.txt", "rt")) == NULL)
	{
		cout << "无法打开文件" << endl;
		return ERROR;
	}
	char str[128];
	int arc_num = 0;
	int num1, num2, len;
	for (arc_num = 0; fgets(str, 128, fp); arc_num++)
	{
		num1 = 0; num2 = 0; len = 0;
		ArcNode *arc = (ArcNode*)malloc(sizeof(ArcNode));	//分配新的弧的节点
		vector<string> data = split(str, "|");
		for (int k = 0; k < data[0].length(); k++)
			num1 = num1 * 10 + data[0][k] - '0';			//记录景点1的编号
		for (int k = 0; k < data[1].length(); k++)
			num2 = num2 * 10 + data[1][k] - '0';
		arc->adjvex = num2;									//记录景点2的编号
		for (int k = 0; k < data[2].length() - 1; k++)		
			len = len * 10 + data[2][k] - '0';
		arc->weight = len;									//vex1到vex2的道路的长度
		arc->next = G.vertices[num1].firststarc;
		G.vertices[num1].firststarc = arc;
		G.vertices[num1].num++;
	}
	G.arcnum = arc_num / 2;
	fclose(fp);
	return OK;
}

void InitGraph(ALGraph &G)
{
	G.arcnum = 0;
	G.vexnum = 0;
	for (int i = 0; i < MAX_VEXTEX_NUM; i++)
	{
		G.vertices[i].data.num = -1;
		G.vertices[i].firststarc = NULL;
		G.vertices[i].num = 0;
	}
	InsertVex(G);
	InsertArc(G);
}

Vex getVex(ALGraph G, int nVex)
{
	return G.vertices[nVex].data;
}

int getArc(ALGraph G, int nVex, Vex vex[])
{
	int i = 0;
	ArcNode *p = G.vertices[nVex].firststarc->next;
	while (!p)
	{
		vex[i++] = G.vertices[p->adjvex].data;
		p = p->next;
	}
	return OK;
}

void DFS(ALGraph G, Path &path, bool *visited, int &nIndex, int nVex)
{
	visited[nVex] = true;
	ArcNode *arc = G.vertices[nVex].firststarc;
	pNode *p = (Path)malloc(sizeof(pNode));
	for (int i = 0; i < nIndex; i++)
		p->vex[i] = path->vex[i];
	p->next = path;
	path = p;
	p->vex[nIndex++] = nVex;
	while (arc != NULL)
	{
		if (!visited[arc->adjvex])
		{
			DFS(G, path, visited, nIndex, arc->adjvex);
			nIndex--;
			visited[arc->adjvex] = false;
		}
		arc = arc->next;
	}
	for (; p->vex[nIndex - 1] < 0; nIndex--);
	p->vex[nIndex] = -1;
}

int DFSTraver(ALGraph G,Path &path, int nVex)
{
	int nIndex = 0;
	bool visited[MAX_VEXTEX_NUM] = { false };
	DFS(G, path, visited, nIndex, nVex);
	return OK;
}

int FindShortPath(ALGraph G, int *path,int start_vex, int end_vex)
{
	int *temp = (int*)malloc(G.vexnum * sizeof(int));
	for (int i = 0; i < G.vexnum; i++)
		temp[i] = INFINITY;
	int path_pos = 0;
	path[path_pos++] = start_vex;
	bool visited[MAX_VEXTEX_NUM] = { false };
	visited[start_vex] = true;
	int min = INFINITY;
	int pos = -1;
	int len = 0;
	int temp_len = 0;
	while (pos != end_vex) 
	{
		for (int i = 0; i < path_pos; i++)
		{
			ArcNode *arc = G.vertices[path[i]].firststarc;
			while (arc != NULL)
			{
				temp[arc->adjvex] = arc->weight;
				min = getMin(min, temp[arc->adjvex]);
				if (min == temp[arc->adjvex])
					pos = arc->adjvex;
				if (pos == end_vex)
					temp_len = len + arc->weight;
				arc = arc->next;
			}
		}
		len += temp[pos];
		if (temp_len != 0 && len >= temp_len)
			pos = end_vex;
		temp_len = 0;
		path[path_pos++] = pos;
	}
	path[path_pos] = -1;
	return OK;
}