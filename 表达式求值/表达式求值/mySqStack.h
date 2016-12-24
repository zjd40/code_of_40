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
//初始化栈

int push(SqStack &S, char e);
//添加栈顶元素

int pop(SqStack &S, char &e);
//删除栈顶元素

char getTop(SqStack S);
//获取栈顶元素

bool In(char c);
//判断是否为运算符集合

char Precede(char c1,char c2);
//获取运算符优先级

double stack_to_double(SqStack &S);
//将数据栈中字符转化为数据

void double_to_stack(SqStack &S, double num);
//将数据转化为字符存入数据栈中

double operate(double x, char c, double y);
//运算操作

int EvaluateExpression();
//表达式运算
