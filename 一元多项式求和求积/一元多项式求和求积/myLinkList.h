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
//��ʼ�����Ա�

LNode *Create_LNode(int num, int index);
//�����½��

int add_LNode(Linklist &L, int index, int num);
//�����Ա��������

Linklist add_LinkList(Linklist L1, Linklist L2);
//���Ա����

Linklist mul_LinkList(Linklist L1, Linklist L2);
//���Ա����

void Input(Linklist L);
//��������

bool Print_LNode(int num, int index);
//��������Ϣ

void Print_LinkList(Linklist L);
//������Ա�