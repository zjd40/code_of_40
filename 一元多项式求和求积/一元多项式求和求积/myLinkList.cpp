#include "myLinkList.h"

int Init_LinkList(Linklist &L)
{
	if (!(L = (Linklist)malloc(sizeof(LNode))))
		return ERROR;
	L->index = NULL;
	L->num = NULL;
	L->next = NULL;
	return OK;
}

LNode *Create_LNode(int num, int index)
{
	LNode *p;
	if (!(p = (Linklist)malloc(sizeof(LNode))))
		return ERROR;
	p->index = index;
	p->num = num;
	p->next = NULL;
	return p;
}

int add_LNode(Linklist &L, int index, int num)
{
	if (num == 0)
		return ERROR;
	LNode *p = L;
	while (p->next != NULL && p->next->index < index)	//直到下一个节点的指数比目标指数大，停止循环，往当前位置插入新节点或者把当前节点改变
		p = p->next;
	if (p->next != NULL && p->next->index == index)	//指数相等，系数相加
		p->next->num += num;
	else
	{
		//指数不等，插入新节点
		Create_LNode(num, index)->next = p->next;
		p->next = Create_LNode(num, index);
	}
	return OK;
}

Linklist add_LinkList(Linklist L1, Linklist L2)
{
	LNode *p = L1->next;
	LNode *q = L2->next;
	Linklist L3;
	Init_LinkList(L3);
	while (p != NULL || q != NULL)
	{
		if (q == NULL)
		{
			add_LNode(L3, p->index, p->num);
			p = p->next;
		}
		else
		{
			add_LNode(L3, q->index, q->num);
			q = q->next;
		}
	}
	return L3;
}

Linklist mul_LinkList(Linklist L1, Linklist L2)
{
	if (!L1 || !L2)
		return ERROR;
	LNode *p = L1->next;
	LNode *q = L2->next;
	Linklist L3;
	Init_LinkList(L3);
	while (p != NULL)
	{
		while (q != NULL)
		{
			add_LNode(L3, p->index + q->index, p->num*q->num);
			q = q->next;
		}
		p = p->next;
		q = L2->next;
	}
	return L3;
}

void Input(Linklist L)
{
	int num, index;
	printf("输入系数（若输入0，则停止输入）：");
	while (scanf("%d", &num) && num != 0)
	{
		printf("输入指数：");
		scanf("%d", &index);
		add_LNode(L, index, num);
		printf("输入系数（若输入0，则停止输入）：");
	}
}

bool Print_LNode(int num, int index)
{
	if (num == 0)	//当前一项为0，做标记
		return false;
	if (index == 0)
		printf("%d", num);
	if (index == 1)
		printf("%dx", num);
	if (index != 0 && index != 1)
		if (num == 1)
			printf("x^%d", index);
		else
			if(num==-1)
				printf("-x^%d", index);
		else
			printf("%dx^%d", num, index);
	return true;
}

void Print_LinkList(Linklist L)
{
	int flag = 0;	//标记，判断均不为0
	LNode *p = L;
	while (p->next != NULL)
	{
		p = p->next;
		if (Print_LNode(p->num, p->index))
			flag |= 1;
		if (p->next != NULL && p->next->num > 0)	//若下一项系数为正，则输出“+”
			printf("+");
	}
	if (!flag)	//若标记为0，说明全部项均为0，
		printf("0");
	return;
}