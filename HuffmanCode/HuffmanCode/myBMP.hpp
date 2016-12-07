#pragma once
#include "myHuffmanCode.hpp"
#include<Windows.h>
#include<stdlib.h>
#include<string.h>


/*����WORDΪ�����ֽڵ�����*/
typedef unsigned short WORD;
/*����DWORDΪe�ĸ��ֽڵ�����*/
typedef unsigned long DWORD;

/*λͼ�ļ�ͷ*/
typedef struct BMP_FILE_HEADER
{
	WORD bType; /* �ļ���ʶ�� */
	DWORD bSize; /* �ļ��Ĵ�С */
	WORD bReserved1; /* ����ֵ,��������Ϊ0 */
	WORD bReserved2; /* ����ֵ,��������Ϊ0 */
	DWORD bOffset; /* �ļ�ͷ�����ͼ������λ��ʼ��ƫ���� */
} BMPFILEHEADER;

/*λͼ��Ϣͷ*/
typedef struct BMP_INFO
{
	DWORD bInfoSize; /* ��Ϣͷ�Ĵ�С */
	DWORD bWidth; /* ͼ��Ŀ�� */
	DWORD bHeight; /* ͼ��ĸ߶� */
	WORD bPlanes; /* ͼ���λ���� */
	WORD bBitCount; /* ÿ�����ص�λ�� */
	DWORD bCompression; /* ѹ������ */
	DWORD bmpImageSize; /* ͼ��Ĵ�С,���ֽ�Ϊ��λ */
	DWORD bXPelsPerMeter; /* ˮƽ�ֱ��� */
	DWORD bYPelsPerMeter; /* ��ֱ�ֱ��� */
	DWORD bClrUsed; /* ʹ�õ�ɫ���� */
	DWORD bClrImportant; /* ��Ҫ����ɫ�� */
} BMPINF;

/*��ɫ��*/
typedef struct RGB_QUAD
{
	WORD rgbBlue; /* ��ɫǿ�� */
	WORD rgbGreen; /* ��ɫǿ�� */
	WORD rgbRed; /* ��ɫǿ�� */
	WORD rgbReversed; /* ����ֵ */
};

void getStr(char* filename, char* str)
{
	FILE *fp;
	BMPFILEHEADER fileHeader;
	BMPINF infoHeader;
	if ((fp = fopen(filename, "rb")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}

	fseek(fp, 0, 0);
	fread(&fileHeader, sizeof(fileHeader), 1, fp);	//��ȡBMP�ļ�ͷ�ļ�
	fread(&infoHeader, sizeof(infoHeader), 1, fp);	//��ȡBMP�ļ�ͷ�ļ���Ϣ

	//���㲢���λͼ���ݵ�ƫ������ͼ��Ĵ�С����Ⱥ͸߶ȣ�ÿ�����ص���ռ���ֽ�
	//long size = fileHeader.bSize;
	//long offset = fileHeader.bOffset;
	//long bmpImageSize = infoHeader.bmpImageSize;
	long width = infoHeader.bWidth;
	long height = infoHeader.bHeight;
	//long bitCount = infoHeader.bBitCount;
	//long bytesPerPixel = infoHeader.bBitCount / 8;

	fseek(fp, long(sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER)), 0);//��λ��������ʼλ��
	fread(str, 1, width * height * 3, fp);		//��ʼ��ȡ����
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
	char* str = (char*)malloc(getFileSize(filename));		//���仺������С
	getStr(filename, str);
	if ((fp = fopen(filename, "rb")) == NULL)
	{
		printf("�޷����ļ�");
		getchar();
		exit(1);
	}
	fseek(fp, 0, 0);
	//���ÿ�����ص���ռ�ֽ��е�����
	c = fgetc(fp);
	while (!feof(fp))
	{
		//printf("%x ", c);
		myc = (char)c;
		Read_char(n, myc);
		c = fgetc(fp);
		//count++;
	}
	//cout << "\n���ֽ�����" << count << endl;
	fclose(fp);
	getWeight(w, n, getFileSize(filename));
	HuffmanCoding(HT, HC, w, n, str);

	/*
	fpw = fopen("mywolf2.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpw);  //д���ļ�ͷ
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpw);  //д���ļ�ͷ��Ϣ
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


