#include<iostream>
using namespace std;

#define OK 1 
#define ERROR 0 

#define n 7

typedef struct LNode
{
	int num;
	int code;
	struct LNode *next;
}LNode, *CLinklist;

int Init_CLinkList(CLinklist &L);
//初始化线性表

int add_LNode(CLinklist &L, int num, int code);
//往线性表插入数据

void print(CLinklist L);
//输出退席者编号