#include "myCLinkList.h"

int main()
{
	int code;
	CLinklist L;
	Init_CLinkList(L);
	for (int i = n; i > 0; i--)
	{
		printf("������%d��ͬѧ�����룺", i);
		scanf("%d", &code);
		add_LNode(L, i, code);
	}
	print(L);
	system("pause");
	return 0;
}