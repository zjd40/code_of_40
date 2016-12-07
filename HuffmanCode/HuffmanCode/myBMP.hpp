#pragma once
#include "myHuffmanCode.hpp"
#include<Windows.h>
#include<stdlib.h>
#include<string.h>


/*定义WORD为两个字节的类型*/
typedef unsigned short WORD;
/*定义DWORD为e四个字节的类型*/
typedef unsigned long DWORD;

/*位图文件头*/
typedef struct BMP_FILE_HEADER
{
	WORD bType; /* 文件标识符 */
	DWORD bSize; /* 文件的大小 */
	WORD bReserved1; /* 保留值,必须设置为0 */
	WORD bReserved2; /* 保留值,必须设置为0 */
	DWORD bOffset; /* 文件头的最后到图像数据位开始的偏移量 */
} BMPFILEHEADER;

/*位图信息头*/
typedef struct BMP_INFO
{
	DWORD bInfoSize; /* 信息头的大小 */
	DWORD bWidth; /* 图像的宽度 */
	DWORD bHeight; /* 图像的高度 */
	WORD bPlanes; /* 图像的位面数 */
	WORD bBitCount; /* 每个像素的位数 */
	DWORD bCompression; /* 压缩类型 */
	DWORD bmpImageSize; /* 图像的大小,以字节为单位 */
	DWORD bXPelsPerMeter; /* 水平分辨率 */
	DWORD bYPelsPerMeter; /* 垂直分辨率 */
	DWORD bClrUsed; /* 使用的色彩数 */
	DWORD bClrImportant; /* 重要的颜色数 */
} BMPINF;

/*彩色表*/
typedef struct RGB_QUAD
{
	WORD rgbBlue; /* 蓝色强度 */
	WORD rgbGreen; /* 绿色强度 */
	WORD rgbRed; /* 红色强度 */
	WORD rgbReversed; /* 保留值 */
};

void getStr(char* filename, char* str)
{
	FILE *fp;
	BMPFILEHEADER fileHeader;
	BMPINF infoHeader;
	if ((fp = fopen(filename, "rb")) == NULL)
	{
		printf("无法打开文件");
		getchar();
		exit(1);
	}

	fseek(fp, 0, 0);
	fread(&fileHeader, sizeof(fileHeader), 1, fp);	//读取BMP文件头文件
	fread(&infoHeader, sizeof(infoHeader), 1, fp);	//读取BMP文件头文件信息

	//计算并输出位图数据的偏移量，图像的大小，宽度和高度，每个像素点所占的字节
	//long size = fileHeader.bSize;
	//long offset = fileHeader.bOffset;
	//long bmpImageSize = infoHeader.bmpImageSize;
	long width = infoHeader.bWidth;
	long height = infoHeader.bHeight;
	//long bitCount = infoHeader.bBitCount;
	//long bytesPerPixel = infoHeader.bBitCount / 8;

	fseek(fp, long(sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER)), 0);//定位到像素起始位置
	fread(str, 1, width * height * 3, fp);		//开始读取数据
	fclose(fp);
}

void myBMP(char* filename, int &n)
{
	HuffmanTree HT;
	HuffmanCode HC;
	double w[256] = { 0 };
	int count = 0;
	FILE *fp;
	WORD c;
	char myc;
	char* str = (char*)malloc(getFileSize(filename));		//分配缓冲区大小
	getStr(filename, str);
	if ((fp = fopen(filename, "rb")) == NULL)
	{
		printf("无法打开文件");
		getchar();
		exit(1);
	}
	fseek(fp, 0, 0);
	//输出每个像素点所占字节中的内容
	c = fgetc(fp);
	while (!feof(fp))
	{
		//printf("%x ", c);
		myc = (char)c;
		Read_char(n, myc);
		c = fgetc(fp);
		//count++;
	}
	//cout << "\n总字节数：" << count << endl;
	fclose(fp);
	getWeight(w, n, getFileSize(filename));
	HuffmanCoding(HT, HC, w, n, str);

	/*
	fpw = fopen("mywolf2.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpw);  //写入文件头
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpw);  //写入文件头信息
	p = buf;
	for (int j = 0; j<h; j++)
	{
	for (int i = 0; i<w * 3; i++)
	{
	fwrite(p++, 1, 1, fpw);
	}
	}
	fclose(fpw);
	*/
}


