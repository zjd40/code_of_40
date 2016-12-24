#pragma once
#include "myStudentData.h"
#include <iostream>
using namespace std;

#define ElemType StudentData
#define KeyType char*
#define SUCCESS 1			//�ɹ�
#define UNSUCCESS 0			//ʧ��
#define DUPLICATE -1		//�ظ�

#define HASHSIZE 26			//��ϣ�������Ϊ26����дӢ����ĸ�ĸ�����

typedef struct HNode
{
	ElemType data;
	struct  HNode *next;
}HNode;

typedef struct
{
	HNode *elem;	//����Ԫ�ش洢��ַ
	int count;		//��ǰ����Ԫ�ظ���
	int sizeindex;	//hashsize[sizeindex]Ϊ��ǰ����
}HashTable;

int initHashTable(HashTable &H);	//��ʼ����ϣ��

int createHashuTable(HashTable &H);	//����һ����ϣ���ұ�

int SearchHash(HashTable H, KeyType KeyName);	//ͨ���ؼ����ڹ�ϣ�������Ӧ����