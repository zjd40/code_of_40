#include "myHashTable.h"
#include <string>

int main()
{
	char key[128];
	HashTable H;
	initHashTable(H);
	createHashuTable(H);
	cout << "请输入要查询的姓名的大写拼音字母：";
	cin >> key;
	SearchHash(H, key);
	system("pause");
	return 0;
}