#include "myLinkList.h"

#include "myLinkList.h"

int main()
{
	Linklist L1;
	Init_LinkList(L1);
	printf("������L1�����ݣ�\n");
	Input(L1);

	Linklist L2;
	Init_LinkList(L2);
	printf("\n������L2�����ݣ�\n");
	Input(L2);

	system("CLS");

	printf("L1=");
	Print_LinkList(L1);

	printf("\nL2=");
	Print_LinkList(L2);

	printf("\nL1+L2=");
	Print_LinkList(add_LinkList(L1, L2));

	printf("\nL1*L2=");
	Print_LinkList(mul_LinkList(L1, L2));
	printf("\n");

	system("pause");
	return 0;
}