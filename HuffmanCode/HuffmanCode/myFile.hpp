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
	fp = fopen("code.txt", "wt");
	fclose(fp);
}

int getFileSize(const char* filename)
{
	FILE *fp;
	int filesize;
	if ((fp = fopen(filename, "rt")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}
	fseek(fp, 0, SEEK_END);
	filesize = ftell(fp);
	fclose(fp);
	return filesize;
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
		fgets(str, 256, fp);
	fclose(fp);
}

 void readFile(const char* filename, char* buf)
 {
	 FILE *fp;
	 if ((fp = fopen(filename, "rt")) == NULL)
	 {
		 printf("�޷����ļ�");
		 getchar();
		 exit(1);
	 }
	 fread(buf, 1, getFileSize(filename) * sizeof(char), fp);
	 fclose(fp);
 }

void writeFile(const char* filename, const char* str)
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





