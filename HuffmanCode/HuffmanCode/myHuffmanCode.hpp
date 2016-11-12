#pragma once
#include <stdio.h>
#include <malloc.h>
#include <iostream>
#include <string>
using namespace std;

typedef struct {
	unsigned int weight;
	unsigned int parent, lchild, rchild;
}HTNdoe,*HuffmanTree;	//动态分配数组存储赫夫曼树

typedef char **HuffmanCode;	//动态分配数组存储赫夫曼编码表

unsigned int ch[26] = { 0 };

void Read_char(unsigned int *w, int &n)
{
	//从终端读入一段英文字符，统计每个字符出现的频率
	string str;
	int i, count = 0;
	getline(cin, str);
	for (i = 0; i < str.size(); i++)
		if (str[i] >= 'a' && str[i] <= 'z')
			ch[str[i] - 'a']++;
	for (i = 0; i < 26; i++)
		if (ch[i] != 0)
		{
			w[count] = ch[i] * 100 / str.size();	//由于w类型为unsigned int，且等号右边式子优先级从左到右，为避免运算错误，先乘100
													//权运算为：w=当前字符个数/总字符个数*100%
			count++;
		}
	n = count;
	return;
}

void Selete(HuffmanTree HT, int n, unsigned int &s1, unsigned int &s2)
{
	//在HT[1..i-1]选择parent为0且weight最小的两个结点，其序号分别为s1和s2
	unsigned min1 = 100, min2 = 100;	//100为权总值
	for (int i = 1; i <= n; i++)
	{
		if (HT[i].parent == 0)	//当结点的父亲明不确时，说明该结点可以进行比较，否则不可使用
		{
			if (HT[i].weight < min2)
			{
				if (HT[i].weight < min1)
				{
					min1 = HT[i].weight;
					s1 = i;
				}
				else
				{
					min2 = HT[i].weight;
					s2 = i;
				}
			}
		}
	}
}

void printHuffmanTreeTable(HuffmanTree p,int m)
{
	//用列表形式打印赫夫曼树列表
	for (int i = 1; i <= m; i++, p++)
		printf("%d:\t%d\t%d\t%d\t%d\n", i, (*p).weight, (*p).parent, (*p).lchild, (*p).rchild);
	printf("\n");
}

void printHuffmanCode(HuffmanCode HC,int n)
{
	//打印赫夫曼编码HC
	for (int i = 1; i <= n; i++)
	{
		printf("%d: ", i);
		for (int j = 0; j < n; j++)
		{
			if (HC[i][j] != '0' && HC[i][j] != '1')
				break;
			printf("%c ", HC[i][j]);
		}
		printf("\n");
	}
}

void HuffmanCoding(HuffmanTree &HT, HuffmanCode &HC, unsigned int *w, int n)
{
	//w存放n个字符的权值（均>0），构造赫夫曼树HT，并求出n个字符的赫夫曼编码HC
	if (n <= 1)
		return;
	int m = 2 * n - 1;
	int i, c, f;
	int start;
	unsigned int s1, s2;
	HuffmanTree p;
	HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNdoe));	//0号单元未用
	for (p = HT + 1, i = 1; i <= n; ++i, ++p, ++w)
		*p = { *w,0,0,0 };
	for (; i <= m; ++i, ++p)
		*p = { 0,0,0,0 };
	p = HT + 1;
	printHuffmanTreeTable(p, m);
	for (i = n + 1; i <= m; ++i)
	{
		//建赫夫曼树
		//在HT[1..i-1]选择parent为0且weight最小的两个结点，其序号分别为s1和s2
		Selete(HT, i - 1, s1, s2);
		HT[s1].parent = i;	HT[s2].parent = i;
		HT[i].lchild = s1;	HT[i].rchild = s2;
		HT[i].weight = HT[s1].weight + HT[s2].weight;
	}
	printHuffmanTreeTable(HT + 1, m);

	//---从叶子到根逆向求每个字符的赫夫曼编码---
	HC = (HuffmanCode)malloc((n + 1) * sizeof(char*));	//分配n个字符编码的头指针向量
	char* cd = (char*)malloc(n * sizeof(char));	//分配求编码的工作空间
	cd[n - 1] = '\0';	//编码结束符
	for (i = 1; i <= n; ++i)
	{
		start = n - 1;	//编码结束符位置
		for (c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent)	//从叶子到根逆向求编码
			if (HT[f].lchild == c)
				cd[--start] = '0';
			else
				cd[--start] = '1';
		HC[i] = (char*)malloc((n - start) * sizeof(char));	//为第i个字符编码分配空间
		strcpy(HC[i], &cd[start]);	//从cd复制编码（串）到HC
	}
	free(cd);	//释放工作空间
	printHuffmanCode(HC, n);

	/*
	//-----无栈非递归遍历赫夫曼树，求赫夫曼编码-----
	HC = (HuffmanCode)malloc((n + 1) * sizeof(char*));
	int a = m, cdlen = 0;
	for (i = 1; i < m; ++i)
		HT[i].weight = 0;
	while (a) 
	{
		if (HT[a].weight == 0)
		{
			HT[a].weight = 1;
			if (HT[a].lchild != 0)
			{
				a = HT[a].lchild;
				cd[cdlen++] = '0';
			}
			else
				if (HT[a].rchild == 0)
				{
					HC[a] = (char*)malloc((cdlen + 1) * sizeof(char));
					cd[cdlen] = '\0';
					strcpy(HC[a], cd);
				}
		}
		else
			if (HT[a].weight == 1)
			{
				HT[a].weight = 2;
				if (HT[a].rchild != 0)
				{
					a = HT[a].rchild;
					cd[cdlen++] = '1';
				}
				else
				{
					HT[a].weight = 0;
					a = HT[a].parent;
					--cdlen;
				}
			}
	}
	*/
}