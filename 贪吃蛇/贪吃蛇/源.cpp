#include<iostream>//包含输入输出流及system()
#include<Windows.h>//包含休眠函数
#include<time.h>//包含时间函数，用于产生随机数
#include <conio.h>//包含kbhit()函数
using namespace std;

#define H 17   //地图高
#define W 22   //地图宽

static int point = 0;//分数
int i, j, xFood, yFood,xHead,yHead;//地图坐标，食物坐标，蛇头坐标
int map[H][W], *food, *head, *body[H*W-1], *tail;//地图，食物，蛇头，蛇身，蛇尾
char direction;//输入方向
char Right, Left, Front, Back;//蛇头方向
int bodyLength = 1;//蛇身长
int flag = 1;//游戏继续标志

void start();//生成地图
void getFood();//产生食物
void draw();//绘制地图
void getPoint();//打印分数
void turn();//转动方向
void changeDirection();//改变方向感
void move();//移动
bool judge();//判断信息

int main()
{
	start();//生成地图
	getFood();//产生食物
	while (flag)
	{
		draw();//绘制地图
		getPoint();//打印分数
		Sleep(500);//休眠函数	
		if (_kbhit())
		{
			direction = _getch();//获取蛇的方向
			changeDirection();//改变蛇的方向
		}
		turn();//朝方向转动
		move();//移动
		if (judge()) flag = 0;//判断是否获取食物或者结束游戏
		system("CLS");
	}
	return 0;
}

void start()
{
	for (i = 0; i < H; i++)
	{
		for (j = 0; j < W; j++)
		{
			if ((i == 0) || (i == H - 1) || (j == 0) || (j == W - 1))
				map[i][j] = 9;//墙编号
			else
				map[i][j] = 0;//空地编号
		}
	}
	xHead = H / 2;//蛇头坐标X
	yHead = W / 2;//蛇头坐标Y
	head = &map[xHead][yHead];//蛇头坐标
	body[0] = &map[xHead - 1][yHead];//蛇身坐标
	tail = &map[xHead - 2][yHead];//蛇尾坐标
	*head = 3;//蛇头编号
	*body[0] = 2;//蛇身编号
	*tail = 1;//蛇尾编号
	Front = '2';//前
	Right = '4';//右
	Left = '6';//左
	Back = '8';//后
	direction = Front;//蛇头方向
}

void getFood()
{
	srand((unsigned)time(NULL));
	do
	{
		xFood = rand() % (H - 2) + 1;
		yFood = rand() % (W - 2) + 1;
	} while (map[xFood][yFood] != 0);
	food = &map[xFood][yFood];//食物坐标
	*food = 4;//食物标号
}

void draw()
{
	for (i = 0; i < H; i++)
	{
		for (j = 0; j < W; j++)
		{
			switch (map[i][j])
			{
			case 0:cout << "   "; break;//空白处
			case 1:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN); cout << " . "; break;//尾
			case 2:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN); cout << " o "; break;//身
			case 3:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_BLUE); cout << " O "; break;//头
			case 4:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED); cout << " @ "; break;//食物
			case 9:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); cout << " # "; break;//墙
			}
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
		}
		cout << endl;
	}
	cout << endl;
}

void getPoint()
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_BLUE);
	cout << "Point :  ";
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN | FOREGROUND_BLUE);
	cout << point << endl;
}

void turn()
{
	/*根据默认初始方向编号设定*/
	switch (direction)
	{
	case '2':xHead++; break;
	case '4':yHead--; break;
	case '6':yHead++; break;
	case '8':xHead--; break;
	}
}

void changeDirection()
{
	if (direction == Right)
	{
		Right = Back;
		Back = Left;
		Left = Front;
		Front = direction;
	}
	if (direction == Left)
	{
		Left = Back;
		Back = Right;
		Right = Front;
		Front = direction;
	}
	direction = Front;//改变方向感后蛇头方向为前
}

void move()
{
	/*延长并让蛇身移动*/
	for (i = bodyLength; i > 0; i--)
		body[i] = body[i - 1];
	body[0] = head;
	*body[0] = 2;
	head = &map[xHead][yHead];
}

bool judge()
{
	switch (map[xHead][yHead])
	{
	case 0://遇到空地时
		*body[bodyLength] = 1;//蛇身末端编号改为蛇尾编号
		*tail = 0;//蛇尾编号改为空地编号
		tail = body[bodyLength];//蛇身末端改为蛇尾
		*head = 3;
		return false;
	case 1://遇到蛇尾时
		*body[bodyLength] = 1;//蛇身末端编号改为蛇尾编号
		*tail = 3;//蛇尾编号改为蛇头编号
		tail = body[bodyLength];//蛇身末端改为蛇尾
		return false;
	case 2://遇到蛇身时
	case 9://遇到墙时
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED);
		cout << "GAME OVER!" << endl;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED |FOREGROUND_BLUE);
		cout << "YOUR POINT IS :  ";
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN | FOREGROUND_BLUE);
		cout << point << endl;
		system("pause");
		return true;
	case 4://遇到食物时
		*head = 3;//编号改为蛇头编号
		bodyLength++;//蛇身增长
		point++;//加分
		getFood();//生成食物
		return false;
	}
}

/*
*休眠函数
*#include<windows.h> 
*Sleep(毫秒数)
*使程序休眠
*
*判断键盘输入函数
*#include <conio.h>
*kbhit()
*判断键盘是否输入,是为true，否为false
*/