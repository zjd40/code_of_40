#include <iostream>
#include <sstream>
#include <string>
#include<cmath>
using namespace std;

#define STACK_INIT_SIZE 100
#define STACKINCREMENT	10

#define ERROR	0
#define OK		1

typedef struct
{
	char *base;
	char *top;
	int stacksize;
}SqStack;

 extern char table[8][8];

int Init_Stack(SqStack &S);
//��ʼ��ջ

int push(SqStack &S, char e);
//���ջ��Ԫ��

int pop(SqStack &S, char &e);
//ɾ��ջ��Ԫ��

char getTop(SqStack S);
//��ȡջ��Ԫ��

bool In(char c);
//�ж��Ƿ�Ϊ���������

char Precede(char c1,char c2);
//��ȡ��������ȼ�

double stack_to_double(SqStack &S);
//������ջ���ַ�ת��Ϊ����

void double_to_stack(SqStack &S, double num);
//������ת��Ϊ�ַ���������ջ��

double operate(double x, char c, double y);
//�������

int EvaluateExpression();
//���ʽ����
