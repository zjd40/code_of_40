#pragma once
#include "myFile.hpp"
#include <malloc.h>

#define MAX_CHAR 26

typedef struct {
	unsigned int weight;
	unsigned int parent, lchild, rchild;
}HTNdoe,*HuffmanTree;	//动态分配数组存储赫夫曼树

typedef char **HuffmanCode;	//动态分配数组存储赫夫曼编码表

unsigned int ch[MAX_CHAR] = { 0 };
char HuffmanCodeChar[MAX_CHAR];

void Read_char(unsigned int *w, int &n)
{
	//从终端读入一段英文字符，统计每个字符出现的频率
	string str;
	int i, count = 0;
	printf("请输入字符串（全小写英文字母）：");
	getline(cin, str);
	for (i = 0; i < str.size(); i++)
		if (str[i] >= 'a' && str[i] <= 'z')
			ch[str[i] - 'a']++;
	for (i = 0; i < MAX_CHAR; i++)
		if (ch[i] != 0)
		{
			w[count] = ch[i] * 100 / str.size();	//由于w类型为unsigned int，且等号右边式子优先级从左到右，为避免运算错误，先乘100
													//权运算为：w=当前字符个数/总字符个数*100%
			HuffmanCodeChar[count] = i + 'a';
			count++;
		}
	n = count;
	return;
}

void getHuffmanCodeChar()
{
	readFile("HuffmanCode.txt", HuffmanCodeChar, 1);
}

void tranInfo(HuffmanTree &HT, char* str,int n)
{
	//将字符表内信息转换成赫夫曼树信息
	bool flag = false;	//判断是否开始记录数据
	char c[3];
	int x = 0, count = 1;
	unsigned int num = 0;
	for (int i = 0; i < strlen(str); i++)
	{
		if (str[i] == ':')
			flag = true;
		if (str[i] >= '0' && str[i] <= '9' && flag)
			c[x++] = str[i];
		else
		{
			if(x != 0)
			{
				for (int k = 0; k < x; k++)
					num = num * 10 + c[k] - '0';
				switch (count++)
				{
				case 1:HT[n].weight = num; break;
				case 2:HT[n].parent = num; break;
				case 3:HT[n].lchild = num; break;
				case 4:HT[n].rchild = num; break;
				}
				x = 0;
				num = 0;
			}
		}
	}
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

void printHuffmanTreeTable(HuffmanTree p,int n)
{
	//用列表形式打印赫夫曼树列表
	char buf[100];
	for (int i = 1; i <= 2 * n - 1; i++, p++)
	{
		sprintf(buf, "%d:\t%d\t%d\t%d\t%d\n", i, (*p).weight, (*p).parent, (*p).lchild, (*p).rchild);
		printf(buf);
		writeFile("HuffmanTree.txt", buf);
	}
	printf("\n");
	writeFile("HuffmanTree.txt", "\n");
}

void printHuffmanCode(HuffmanCode HC,int n)
{
	//打印赫夫曼编码HC
	char buf[100];
	writeFile("HuffmanCode.txt", HuffmanCodeChar);
	for (int i = 1; i <= n; i++)
	{
		sprintf(buf, "\n%d %c: ", i, HuffmanCodeChar[i - 1]);
		printf(buf);
		writeFile("HuffmanCode.txt", buf);
		for (int j = 0; j < n; j++)
		{
			if (HC[i][j] != '0' && HC[i][j] != '1')
				break;
			sprintf(buf, "%c ", HC[i][j]);
			printf(buf);
			writeFile("HuffmanCode.txt", buf);
		}
	}
	printf("\n");
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
	for (i = n + 1; i <= m; ++i)
	{
		//建赫夫曼树
		//在HT[1..i-1]选择parent为0且weight最小的两个结点，其序号分别为s1和s2
		Selete(HT, i - 1, s1, s2);
		HT[s1].parent = i;	HT[s2].parent = i;
		HT[i].lchild = s1;	HT[i].rchild = s2;
		HT[i].weight = HT[s1].weight + HT[s2].weight;
	}
	printHuffmanTreeTable(HT + 1, n);

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

void  translateHuffmanCode(int n)
{
	//从根出发获得译码的字串
	HuffmanTree Ht = (HuffmanTree)malloc((2 * n) * sizeof(HTNdoe));
	//HuffmanTree p;
	string code;
	int i, count = 2 * n - 1;
	char *str = new char[256];
	for (i = 1; i <= 2 * n - 1; i++)
	{
		readFile("HuffmanTree.txt", str, i);
		tranInfo(Ht, str, i);
	}
	printf("请输入01编码：");
	getline(cin, code);
	printf("译码为：");
	for (i = 0; i < code.size(); i++)
	{
		if (code[i] == '0')
			count = Ht[count].lchild;
		if (code[i] == '1')
			count = Ht[count].rchild;
		if (Ht[count].lchild == 0)
		{
			printf("%c", HuffmanCodeChar[count - 1]);
			count = 2 * n - 1;
		}
	}
	printf("\n");
}
