#include "myHashTable.h"
#include <string>

int main()
{
	char key[128];
	HashTable H;
	initHashTable(H);
	createHashuTable(H);
	cout << "������Ҫ��ѯ�������Ĵ�дƴ����ĸ��";
	cin >> key;
	SearchHash(H, key);
	system("pause");
	return 0;
}