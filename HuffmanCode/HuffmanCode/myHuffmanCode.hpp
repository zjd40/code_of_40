#pragma once
#include "myFile.hpp"
#include <malloc.h>

#define MAX_CHAR 26

typedef struct {
	unsigned int weight;
	unsigned int parent, lchild, rchild;
}HTNdoe,*HuffmanTree;	//��̬��������洢�շ�����

typedef char **HuffmanCode;	//��̬��������洢�շ��������

unsigned int ch[MAX_CHAR] = { 0 };
char HuffmanCodeChar[MAX_CHAR];

void Read_char(unsigned int *w, int &n)
{
	//���ն˶���һ��Ӣ���ַ���ͳ��ÿ���ַ����ֵ�Ƶ��
	string str;
	int i, count = 0;
	printf("�������ַ�����ȫСдӢ����ĸ����");
	getline(cin, str);
	for (i = 0; i < str.size(); i++)
		if (str[i] >= 'a' && str[i] <= 'z')
			ch[str[i] - 'a']++;
	for (i = 0; i < MAX_CHAR; i++)
		if (ch[i] != 0)
		{
			w[count] = ch[i] * 100 / str.size();	//����w����Ϊunsigned int���ҵȺ��ұ�ʽ�����ȼ������ң�Ϊ������������ȳ�100
													//Ȩ����Ϊ��w=��ǰ�ַ�����/���ַ�����*100%
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
	//���ַ�������Ϣת���ɺշ�������Ϣ
	bool flag = false;	//�ж��Ƿ�ʼ��¼����
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
	//��HT[1..i-1]ѡ��parentΪ0��weight��С��������㣬����ŷֱ�Ϊs1��s2
	unsigned min1 = 100, min2 = 100;	//100ΪȨ��ֵ
	for (int i = 1; i <= n; i++)
	{
		if (HT[i].parent == 0)	//�����ĸ�������ȷʱ��˵���ý����Խ��бȽϣ����򲻿�ʹ��
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
	//���б���ʽ��ӡ�շ������б�
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
	//��ӡ�շ�������HC
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
	//w���n���ַ���Ȩֵ����>0��������շ�����HT�������n���ַ��ĺշ�������HC
	if (n <= 1)
		return;
	int m = 2 * n - 1;
	int i, c, f;
	int start;
	unsigned int s1, s2;
	HuffmanTree p;
	HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNdoe));	//0�ŵ�Ԫδ��
	for (p = HT + 1, i = 1; i <= n; ++i, ++p, ++w)
		*p = { *w,0,0,0 };
	for (; i <= m; ++i, ++p)
		*p = { 0,0,0,0 };
	for (i = n + 1; i <= m; ++i)
	{
		//���շ�����
		//��HT[1..i-1]ѡ��parentΪ0��weight��С��������㣬����ŷֱ�Ϊs1��s2
		Selete(HT, i - 1, s1, s2);
		HT[s1].parent = i;	HT[s2].parent = i;
		HT[i].lchild = s1;	HT[i].rchild = s2;
		HT[i].weight = HT[s1].weight + HT[s2].weight;
	}
	printHuffmanTreeTable(HT + 1, n);

	//---��Ҷ�ӵ���������ÿ���ַ��ĺշ�������---
	HC = (HuffmanCode)malloc((n + 1) * sizeof(char*));	//����n���ַ������ͷָ������
	char* cd = (char*)malloc(n * sizeof(char));	//���������Ĺ����ռ�
	cd[n - 1] = '\0';	//���������
	for (i = 1; i <= n; ++i)
	{
		start = n - 1;	//���������λ��
		for (c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent)	//��Ҷ�ӵ������������
			if (HT[f].lchild == c)
				cd[--start] = '0';
			else
				cd[--start] = '1';
		HC[i] = (char*)malloc((n - start) * sizeof(char));	//Ϊ��i���ַ��������ռ�
		strcpy(HC[i], &cd[start]);	//��cd���Ʊ��루������HC
	}
	free(cd);	//�ͷŹ����ռ�
	printHuffmanCode(HC, n);

	/*
	//-----��ջ�ǵݹ�����շ���������շ�������-----
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
	//�Ӹ��������������ִ�
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
	printf("������01���룺");
	getline(cin, code);
	printf("����Ϊ��");
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
