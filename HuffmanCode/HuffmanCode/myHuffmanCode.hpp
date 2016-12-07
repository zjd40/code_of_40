#pragma once
#include "myFile.hpp"
#include <malloc.h>

#define MAX_CHAR 256
#define MIN_CHAR -128

typedef struct {
	double weight;
	unsigned int parent, lchild, rchild;
}HTNdoe,*HuffmanTree;	//��̬��������洢�շ�����

typedef struct Table {
	int tap;
	double num;
};

typedef char **HuffmanCode;					//��̬��������洢�շ��������
char HuffmanCodeChar[MAX_CHAR - MIN_CHAR];	//��¼�����ַ�
Table ch[MAX_CHAR - MIN_CHAR];				//��¼�����ַ����ִ���

void Init()
{
	for (int i = 0; i < MAX_CHAR - MIN_CHAR; i++)
	{
		HuffmanCodeChar[i] = '\0';	//��ʼ��ch���HuffmanCodeChar��
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
	ch[c - MIN_CHAR].num++;	//����ÿ���ַ����ִ���
}

void getWeight(double *w, int n,int count)
{
	for (int i = 0; i < n; i++)
		w[i] = ch[HuffmanCodeChar[i] - MIN_CHAR].num / count * 100;	//Ȩ����Ϊ��w=��ǰ�ַ�����/���ַ�����*100%
}

void tranInfo(HuffmanTree &HT, char* str,int n)
{
	//���ַ�������Ϣת���ɺշ�������Ϣ
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
	//��HT[1..i-1]ѡ��parentΪ0��weight��С��������㣬����ŷֱ�Ϊs1��s2
	double min1 = 100.00, min2 = 100.00;	//100ΪȨ��ֵ
	for (int i = 1; i <= n; i++)
		if (HT[i].parent == 0)	//�����ĸ�������ȷʱ��˵���ý����Խ��бȽϣ����򲻿�ʹ��
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
	//���б���ʽ��ӡ�շ������б�
	char buf[100];
	printf("\n�շ�������Ϊ��\n\n");
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
	//��ӡ�շ�������HC
	char buf[100];
	writeFile("HuffmanCode.txt", HuffmanCodeChar);
	printf("�շ���b�����Ϊ��\n");
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
	printf("size of BMP��\t %d\n", getFileSize("myImage.bmp"));
	printf("size of txt��\t %d\n", getFileSize("code.txt"));
}

void HuffmanCoding(HuffmanTree &HT, HuffmanCode &HC, double *w, int n, string str)
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
	setRecord(HC, str, n);

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

void  translateHuffmanCode(const char* HuffmanTablefilename, int n, string code)
{
	//�Ӹ��������������ִ�
	HuffmanTree Ht = (HuffmanTree)malloc((2 * n) * sizeof(HTNdoe));
	int i, count = 2 * n - 1;
	char *str = new char[256];;
	for (i = 1; i <= 2 * n - 1; i++)	//��ȡ�շ�������
	{
		readFile(HuffmanTablefilename, str, i);
		tranInfo(Ht, str, i);
	}
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
