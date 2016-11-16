#pragma once
#include <string>
#include <iostream>
#include <stdio.h>
using namespace std;

void initFile()
{
	//初始化赫夫曼树表和赫夫曼编码表
	FILE *fp = NULL;
	fp = fopen("HuffmanTree.txt", "wt");
	fclose(fp);
	fp = fopen("HuffmanCode.txt", "wt");
	fclose(fp);
}

 void readFile(const char* filename, char* str, int n)
{
	//从文件中读取信息
	FILE *fp = NULL;
	if ((fp = fopen(filename, "rt")) == NULL)
	{
		printf("无法打开文件");
		getchar();
		exit(1);
	}
	for (int i = 0; i < n; i++)
		fgets(str, 32, fp);
	fclose(fp);
}

void writeFile(const char* filename, char* str)
{
	//从文件中写入信息
	FILE *fp = NULL;
	if ((fp = fopen(filename, "at")) == NULL)
	{
		printf("无法打开文件");
		getchar();
		exit(1);
	}
	fputs(str, fp);
	fclose(fp);
}

int get_Num_of_Line(const char* filename)
{
	//返还文件中行数
	FILE *fp = NULL;
	char str[256];
	int count = 0;
	if ((fp = fopen(filename, "rt")) == NULL)
	{
		printf("无法打开文件");
		getchar();
		exit(1);
	}
	while (fgets(str, 256, fp) != NULL)
		count++;
	return count;
}


