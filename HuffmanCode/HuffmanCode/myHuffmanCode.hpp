#pragma once
#include "myFile.hpp"
#include <malloc.h>

#define MAX_CHAR 256
#define MIN_CHAR -128

typedef struct {
	double weight;
	unsigned int parent, lchild, rchild;
}HTNdoe,*HuffmanTree;	//动态分配数组存储赫夫曼树

typedef struct Table {
	int tap;
	double num;
};

typedef char **HuffmanCode;					//动态分配数组存储赫夫曼编码表
char HuffmanCodeChar[MAX_CHAR - MIN_CHAR];	//记录编码字符
Table ch[MAX_CHAR - MIN_CHAR];				//记录编码字符出现次数

void Init()
{
	for (int i = 0; i < MAX_CHAR - MIN_CHAR; i++)
	{
		HuffmanCodeChar[i] = '\0';	//初始化ch表和HuffmanCodeChar表
		ch[i].num = 0;
		ch[i].tap = 0;
	}
}

void Read_char(int &n, char c)
{
	if (ch[c - MIN_CHAR].num == 0)
	{
		ch[c - MIN_CHAR].tap = n;
		HuffmanCodeChar[n++] = c;
	}
	ch[c - MIN_CHAR].num++;	//计算每种字符出现次数
}

void getWeight(double *w, int n,int count)
{
	for (int i = 0; i < n; i++)
		w[i] = ch[HuffmanCodeChar[i] - MIN_CHAR].num / count * 100;	//权运算为：w=当前字符个数/总字符个数*100%
}

void tranInfo(HuffmanTree &HT, char* str,int n)
{
	//将字符表内信息转换成赫夫曼树信息
	bool flag = false;
	char c[16];
	int x = 0, count = 1, decimal = 1;
	unsigned int num;
	for (int i = 0; i < strlen(str); i++)
	{
		if (str[i] == ':')
			flag = true;
		if (flag && ((str[i] >= '0' && str[i] <= '9') || str[i] == '.'))
			c[x++] = str[i];
		else
		{
			if(x != 0)
			{
				for (int k = 0; k < x; k++)
					if (count != 1)
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
	double min1 = 100.00, min2 = 100.00;	//100为权总值
	for (int i = 1; i <= n; i++)
		if (HT[i].parent == 0)	//当结点的父亲明不确时，说明该结点可以进行比较，否则不可使用
			if (HT[i].weight < min2)
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

void printHuffmanTreeTable(HuffmanTree p,int n)
{
	//用列表形式打印赫夫曼树列表
	char buf[100];
	printf("\n赫夫曼树表为：\n\n");
	for (int i = 1; i <= 2 * n - 1; i++, p++)
	{
		sprintf(buf, "%d:\t%lf\t%d\t%d\t%d\n", i, (*p).weight, (*p).parent, (*p).lchild, (*p).rchild);
		printf(buf);
		writeFile("HuffmanTree.txt", buf);
	}
	printf("\n");
}

void printHuffmanCode(HuffmanCode HC,int n)
{
	//打印赫夫曼编码HC
	char buf[100];
	writeFile("HuffmanCode.txt", HuffmanCodeChar);
	printf("赫夫曼b编码表为：\n");
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
	printf("\n\n");
}

void setRecord(HuffmanCode HC, string str, int n)
{
	printf("wait.............\n");
	/*
	for (int i = 0; i < str.size(); i++)
		for (int j = 0; j < n; j++)
			if (str[i] == HuffmanCodeChar[j])
			{
				writeFile("code.txt", HC[ch[str[i] - MIN_CHAR].tap + 1]);
				break;
			}*/
	string code;
	for (int i = 0; i < str.size(); i++)
		code += HC[ch[str[i] - MIN_CHAR].tap + 1];
	writeFile("code.txt", code.c_str());
	printf("OK!\n");
	printf("size of BMP：\t %d\n", getFileSize("myImage.bmp"));
	printf("size of txt：\t %d\n", getFileSize("code.txt"));
}

void HuffmanCoding(HuffmanTree &HT, HuffmanCode &HC, double *w, int n, string str)
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
	setRecord(HC, str, n);

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

void  translateHuffmanCode(const char* HuffmanTablefilename, int n, string code)
{
	//从根出发获得译码的字串
	HuffmanTree Ht = (HuffmanTree)malloc((2 * n) * sizeof(HTNdoe));
	int i, count = 2 * n - 1;
	char *str = new char[256];;
	for (i = 1; i <= 2 * n - 1; i++)	//获取赫夫曼树表
	{
		readFile(HuffmanTablefilename, str, i);
		tranInfo(Ht, str, i);
	}
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
