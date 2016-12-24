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
	double integer = 0;	//整数部分
	double decimal = 0;	//小数部分
	int index = 1;		//整数数位的辅助指数
	int count = 0;		//小数位数记录变量
	char c;
	pop(S, c);
	while (c != '.' && c != '#')	//读取并整理小数部分数据
	{
		decimal = decimal / 10 + c - '0';
		count++;
		pop(S, c);
	}
	if (c == '.')	//判断是否有小数部分
	{
		pop(S, c);
		while (c != '#')	//读取并整理小数部分数据
		{
			integer += (c - '0') * pow(10, index);
			index++;
			pop(S, c);
		}
	}
	else
		decimal *= pow(10, count);	//没有小数点，小数数据放大count倍

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
	printf("请输入表达式：\n");
	c = getchar();
	while (c != '=' || getTop(OPTR) != '#')
	{
		if (!In(c))
		{
			if ((c >= '0' && c <= '9') || c == '.')
				push(OPND, c);
			else
			{	//当输入的字符不合法，表达式错误
				printf("输入字符不合法，表达式错误\n");
				return ERROR;
			}
			c = getchar();
		}
		else
		{
			if (getTop(OPND) == '.')	//当小数点后无数据，表达式错误
			{
				printf("数据小数点符号不合法，表达式错误\n");
				return ERROR;
			}
			switch (Precede(getTop(OPTR), c))
			{
			case '<':
				if (getTop(OPND) != '#')
				{
					if (c == '(')	//当数据接‘（’时，自动在其之间补上‘*’表示相乘
						push(OPTR, '*');
					push(OPND, '#');	//数据之间以‘#’作为隔断符
				}
				else
				{
					if (c == '+' || c == '-' || c == '*' || c == '/')	//当两个操作符不合法接在一起（如++，-+等）时，操作匹配错误
					{
						printf("操作符匹配错误，表达式错误\n");
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
			case 0:	//当操作符无法匹配，表达式错误
				printf("操作符匹配错误，表达式错误\n");
				return ERROR;
			}
		}
	}
	printf("%lf\n", stack_to_double(OPND));
	return OK;
}