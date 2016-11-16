#pragma once
#include <string>
#include <iostream>
#include <stdio.h>
using namespace std;

void initFile()
{
	//��ʼ���շ�������ͺշ��������
	FILE *fp = NULL;
	fp = fopen("HuffmanTree.txt", "wt");
	fclose(fp);
	fp = fopen("HuffmanCode.txt", "wt");
	fclose(fp);
}

 void readFile(const char* filename, char* str, int n)
{
	//���ļ��ж�ȡ��Ϣ
	FILE *fp = NULL;
	if ((fp = fopen(filename, "rt")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}
	for (int i = 0; i < n; i++)
		fgets(str, 32, fp);
	fclose(fp);
}

void writeFile(const char* filename, char* str)
{
	//���ļ���д����Ϣ
	FILE *fp = NULL;
	if ((fp = fopen(filename, "at")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}
	fputs(str, fp);
	fclose(fp);
}

int get_Num_of_Line(const char* filename)
{
	//�����ļ�������
	FILE *fp = NULL;
	char str[256];
	int count = 0;
	if ((fp = fopen(filename, "rt")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}
	while (fgets(str, 256, fp) != NULL)
		count++;
	return count;
}


