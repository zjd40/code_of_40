#include "mySqStack.h"

char table[8][8] = {
	{ 0, '+', '-', '*', '/', '(', ')', '=' },
	{ '+', '>', '>', '<', '<', '<', '>', '>' },
	{ '-', '>', '>', '<', '<', '<', '>', '>' },
	{ '*', '>', '>', '>', '>', '<', '>', '>' },
	{ '/', '>', '>', '>', '>', '<', '>', '>' },
	{ '(', '<', '<', '<', '<', '<', '=', 0 },
	{ ')', '>', '>', '>', '>', 0, '>', '>' },
	{ '#', '<', '<', '<', '<', '<', 0,'=' } };

int Init_Stack(SqStack &S)
{
	if (!(S.base = (char*)malloc(STACK_INIT_SIZE * sizeof(char))))
		return ERROR;
	S.top = S.base;
	S.stacksize = STACK_INIT_SIZE;
	return OK;
}

int push(SqStack &S, char e)
{
	if (S.top - S.base >= S.stacksize)
	{
		if (!(S.base = (char*)realloc(S.base, (S.stacksize + STACKINCREMENT) * (sizeof(char)))))
			return ERROR;
		S.top = S.base + S.stacksize;
		S.stacksize += STACKINCREMENT;
	}
	*S.top++ = e;
	return OK;
}

int pop(SqStack &S, char &e)
{
	if (S.top == S.base)
		return ERROR;
	e = *--S.top;
	return OK;
}

char getTop(SqStack S)
{
	if (S.top == S.base)
		return ERROR;
	return *(S.top - 1);
}

bool In(char c)
{
	if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '=')
		return true;
	else
		return false;
}

char Precede(char c1,char c2)
{
	int i, j;
	for (i = 0; i < 8; i++)
		if (table[i][0] == c1)
			break;
	for (j = 0; j < 8; j++)
		if (table[0][j] == c2)
			break;
	return table[i][j];
}

double stack_to_double(SqStack &S)
{
	double integer = 0;	//��������
	double decimal = 0;	//С������
	int index = 1;		//������λ�ĸ���ָ��
	int count = 0;		//С��λ����¼����
	char c;
	pop(S, c);
	while (c != '.' && c != '#')	//��ȡ������С����������
	{
		decimal = decimal / 10 + c - '0';
		count++;
		pop(S, c);
	}
	if (c == '.')	//�ж��Ƿ���С������
	{
		pop(S, c);
		while (c != '#')	//��ȡ������С����������
		{
			integer += (c - '0') * pow(10, index);
			index++;
			pop(S, c);
		}
	}
	else
		decimal *= pow(10, count);	//û��С���㣬С�����ݷŴ�count��

	return integer / 10 + decimal / 10;
}

void double_to_stack(SqStack &S, double num)
{
	string str;
	ostringstream oss;
	oss << num;
	str = oss.str();
	for (int i = 0; i < str.length(); i++)
		push(S, str[i]);
}

double operate(double x, char c, double y)
{
	switch (c)
	{
	case '*':return x * y;
	case '/':return x / y;
	case '+':return x + y;
	case '-':return x - y;
	}
}

int EvaluateExpression()
{
	char c;
	double a, b;
	char theta;
	SqStack OPTR;	Init_Stack(OPTR);	push(OPTR, '#');
	SqStack OPND;	Init_Stack(OPND);	push(OPND, '#');
	printf("��������ʽ��\n");
	c = getchar();
	while (c != '=' || getTop(OPTR) != '#')
	{
		if (!In(c))
		{
			if ((c >= '0' && c <= '9') || c == '.')
				push(OPND, c);
			else
			{	//��������ַ����Ϸ������ʽ����
				printf("�����ַ����Ϸ������ʽ����\n");
				return ERROR;
			}
			c = getchar();
		}
		else
		{
			if (getTop(OPND) == '.')	//��С����������ݣ����ʽ����
			{
				printf("����С������Ų��Ϸ������ʽ����\n");
				return ERROR;
			}
			switch (Precede(getTop(OPTR), c))
			{
			case '<':
				if (getTop(OPND) != '#')
				{
					if (c == '(')	//�����ݽӡ�����ʱ���Զ�����֮�䲹�ϡ�*����ʾ���
						push(OPTR, '*');
					push(OPND, '#');	//����֮���ԡ�#����Ϊ���Ϸ�
				}
				else
				{
					if (c == '+' || c == '-' || c == '*' || c == '/')	//���������������Ϸ�����һ����++��-+�ȣ�ʱ������ƥ�����
					{
						printf("������ƥ����󣬱��ʽ����\n");
						return ERROR;
					}
				}
				push(OPTR, c);
				c = getchar();
				break;
			case '=':
				pop(OPTR, c);
				c = getchar();
				break;
			case '>':
				pop(OPTR, theta);
				b = stack_to_double(OPND);
				a = stack_to_double(OPND);
				push(OPND, '#');
				double_to_stack(OPND, operate(a, theta, b));
				break;
			case 0:	//���������޷�ƥ�䣬���ʽ����
				printf("������ƥ����󣬱��ʽ����\n");
				return ERROR;
			}
		}
	}
	printf("%lf\n", stack_to_double(OPND));
	return OK;
}