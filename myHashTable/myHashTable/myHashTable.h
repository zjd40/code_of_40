#pragma once
#include "myStudentData.h"
#include <iostream>
using namespace std;

#define ElemType StudentData
#define KeyType char*
#define SUCCESS 1			//成功
#define UNSUCCESS 0			//失败
#define DUPLICATE -1		//重复

#define HASHSIZE 26			//哈希表的容量为26（大写英文字母的个数）

typedef struct HNode
{
	ElemType data;
	struct  HNode *next;
}HNode;

typedef struct
{
	HNode *elem;	//数据元素存储基址
	int count;		//当前数据元素个数
	int sizeindex;	//hashsize[sizeindex]为当前容量
}HashTable;

int initHashTable(HashTable &H);	//初始化哈希表

int createHashuTable(HashTable &H);	//创建一个哈希查找表

int SearchHash(HashTable H, KeyType KeyName);	//通过关键词在哈希表查找相应数据