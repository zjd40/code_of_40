
#include "myCLinkList.h"

int Init_CLinkList(CLinklist &L)
{
	L = NULL;
	return OK;
}

int add_LNode(CLinklist &L, int num, int code)
{
	LNode *p;
	if (!(p = (LNode*)malloc(sizeof(LNode))))
		return ERROR;
	p->num = num;
	p->code = code;
	if (L == NULL)
		L = p;
	p->next = L->next;
	L->next = p;
	return OK;
}

void print(CLinklist L)
{
	LNode *p = L;
	LNode *q;
	int i;
	int m = 20;
	while (p->next != p)
	{
		for (i = 1; i < m; i++)
			p = p->next;
		printf("%d ->", p->next->num);
		m = p->next->code;
		q = p->next;
		p->next = q->next;
		free(q);
	}
	printf("%d\n", p->num);
	return;
}
