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
//��ʼ�����Ա�

int add_LNode(CLinklist &L, int num, int code);
//�����Ա��������

void print(CLinklist L);
//�����ϯ�߱��