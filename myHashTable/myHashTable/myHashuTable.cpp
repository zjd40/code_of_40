#include "myHashTable.h"
#include <fstream>
#include <stdio.h>
#include <vector>
#include <iostream>
using namespace std;

vector<string> split(string str, string pattern)
{
	string::size_type pos;
	vector<string> result;
	str += pattern;
	int size = str.size();

	for (int i = 0; i < size; i++)
	{
		pos = str.find(pattern, i);
		if (pos < size)
		{
			string s = str.substr(i, pos - i);
			result.push_back(s);
			i = pos + pattern.size() - 1;
		}
	}
	return result;
	
}

void getData(string str, string &keyname, string &name)
{
	vector<string> result = split(str, "|");
	keyname = result[0];
	name = result[1];
}

int initHashTable(HashTable &H)
{
	if ((H.elem = (HNode*)malloc(HASHSIZE * sizeof(HNode))))
		return UNSUCCESS;
	H.count = 0;
	H.sizeindex = HASHSIZE;
	for (int i = 0; i < HASHSIZE; i++)
		H.elem = NULL;
	return SUCCESS;
}

int createHashuTable(HashTable &H)
{
	FILE *fp;
	char str[256];
	HNode *p;
	string keyname, name;
	if ((fp = fopen("StudentData.txt", "rt")) == NULL)
	{
		printf("无法打开文件");
		return UNSUCCESS;
	}
	while (fgets(str, 256, fp))
	{
		if (str[0] >= 'A'&&str[0] <= 'Z')
		{
			p = (HNode*)malloc(sizeof(HNode));
			getData(str, keyname, name);
			p->data.KeyName = (char*)malloc(keyname.length() * sizeof(char));
			p->data.Name = (char*)malloc(name.length() * sizeof(char));
			for (int i = 0; i < keyname.length(); i++)
				p->data.KeyName[i] = keyname[i];
			for (int i = 0; i < name.length(); i++)
				p->data.Name[i] = name[i];
			p->next = H.elem[str[0] - 'A'].next;
			H.elem[str[0] - 'A'].next = p;
			H.count++;
		}
	}
	fclose(fp);
	return SUCCESS;
}

int SearchHash(HashTable H, KeyType KeyName)
{
	if (KeyName[0]<'A' || KeyName[0]>'Z')
		return UNSUCCESS;
	HNode *p = NULL;
	p = H.elem[KeyName[0] - 'A'].next;
	while (p)
	{
		if (strcmp(p->data.KeyName, KeyName))
			break;
		p = p->next;
	}
	if (p)
	{
		cout << p->data.KeyName << endl;
		cout << p->data.Name << endl;
		return SUCCESS;
	}
	else
	{
		cout <<"查询失败，对象不存在"<< endl;
		return UNSUCCESS;
	}
}