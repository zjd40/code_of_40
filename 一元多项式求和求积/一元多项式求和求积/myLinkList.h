#pragma once
#include<iostream>
#include<stdio.h>
using namespace std;

#define OK 1 
#define ERROR 0 

typedef struct LNode
{
	int index;
	int num;
	struct LNode *next;
}LNode, *Linklist;

int Init_LinkList(Linklist &L);
//初始化线性表

LNode *Create_LNode(int num, int index);
//创建新结点

int add_LNode(Linklist &L, int index, int num);
//往线性表插入数据

Linklist add_LinkList(Linklist L1, Linklist L2);
//线性表相加

Linklist mul_LinkList(Linklist L1, Linklist L2);
//线性表相乘

void Input(Linklist L);
//输入数据

bool Print_LNode(int num, int index);
//输出结点信息

void Print_LinkList(Linklist L);
//输出线性表