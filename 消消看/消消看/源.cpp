#include<iostream>
#include<time.h>
#include<Windows.h>
#include<iomanip>
using namespace std;

#define H 18 //高
#define W 23 //宽

void start();//绘制边框
void getMap();//生成数据
void draw();//画图
void printPoint();//打印分数
void getLocal();//获取坐标
int offset(int x, int y, int core);//模拟消除方块
void tran(int x,int y,int core);//消除或恢复方块
void downMove();//方块下移
void leftMove();//方块左移
void getPoint(int core);//计算分数
bool overJudge();//判断游戏结束

int i, j;
int x, y;//目标坐标
int map[H][W];//方块图
static double point = 0;//分数

int main()
{
	int core;
	start();//生成初始图
	getMap();//生成初始数据
	draw();//打印图
	printPoint();//打印分数
	while (1)//循环至游戏结束
	{
		if (overJudge())//判断游戏是否结束
		{
			system("CLS");//清屏
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN);
			cout << "Game Over!" << endl;
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED |FOREGROUND_BLUE);
			cout << "YOUR POINT IS :";
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY |FOREGROUND_GREEN | FOREGROUND_BLUE);
			cout<< point << endl;
			system("pause");
			break;//游戏结束跳出循环
		}
		getLocal();//获取目标坐标
		core = offset(x, y, 1);//模拟消除方块，返还消除个数
		tran(x,y,core);//判断是否满足消除的条件，若满足则将模拟消除的方块消除，否则将其还原
		system("CLS");//清屏
		draw();//打印图
		printPoint();//打印分数
	}
	return 0;
}

void start()
{
	for (j = 1; j < W - 1; j++) map[1][j] = 8;
	for (i = 1; i < H - 1; i++) map[i][1] = 9;
}

void getMap()
{
	srand((unsigned)time(NULL));
	for (i = 2; i < H - 1; i++)
		for (j = 2; j < W - 1; j++)
			map[i][j] = rand() % 4 + 1;
}

void draw()
{
	for (i = 0; i < H-1; i++)
	{
		for (j = 0; j < W-1; j++)
		{
			if ((i == 0) && (j != 0))
			{
				SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
				cout << setw(2) << j-1 << "|";
			}
			else
			{
				if ((j == 0) && (i != 0))
				{
					SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
					cout << setw(3) << i-1;
				}
				else
				{
					switch (map[i][j])
					{
					case 0: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); cout << "   "; break;
					case 1: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED); cout << " @ ";  break;
					case 2: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN); cout << " @ "; break;
					case 3: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_BLUE); cout << " @ "; break;
					case 4: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN); cout << " @ "; break;
					case 8: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); cout << " - "; break;
					case 9: SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); cout << " | "; break;
					}
				}
			}
		}
		cout << endl;
	}
	cout << endl;
}

void printPoint()
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_BLUE);
	cout << "Point :  ";
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN | FOREGROUND_BLUE);
	cout<< point << endl;
}

void getLocal()
{
	cin >> x;
	x++;
	cin >> y;
	y++;
	while (x <= 1 || x >= H - 1 || y <= 1 || y >= W - 1 || map[x][y] == 0)
	{
		system("CLS");
		draw();
		printPoint();
		cin >> x;
		x++;
		cin >> y;
		y++;
	}
}

int offset(int x, int y, int core)
{
	int temp = map[x][y];
	if (map[x + 1][y] == temp&&map[x + 1][y] != 0)
	{
		map[x][y] = 5;//相同的归5
		core = offset(x + 1, y, core);
		map[x + 1][y] = 5;
		core++;
	}//下
	if (map[x - 1][y] == temp&&map[x - 1][y] != 0)
	{
		map[x][y] = 5;
		core = offset(x - 1, y, core);
		map[x - 1][y] = 5;
		core++;
	}//上
	if (map[x][y + 1] == temp&&map[x][y + 1] != 0)
	{
		map[x][y] = 5;
		core = offset(x, y + 1, core);
		map[x][y + 1] = 5;
		core++;
	}//右
	if (map[x][y - 1] == temp&&map[x][y - 1] != 0)
	{
		map[x][y] = 5;
		core = offset(x, y - 1, core);
		map[x][y - 1] = 5;
		core++;
	}//左
	map[x][y] = temp;
	return core;
}

void tran(int x,int y,int core)
{
	if (core>2)
	{
		map[x][y] = 5;
		for (i = 2; i<H - 1; i++)
			for (j = 2; j<W - 1; j++)
				if (map[i][j] == 5) map[i][j] = 0;
		downMove();
		leftMove();
		getPoint(core);

	}
	else
		for (i = 2; i<H - 1; i++)
			for (j = 2; j<W - 1; j++)
				if (map[i][j] == 5) map[i][j] = map[x][y];
	return;
}

void downMove()
{
	int k;
	for (j = 2; j<W - 1; j++)
	{
		k = 1;
		for (i = H - 2; i>2; i--)
			if (map[i][j] == 0)
			{
				if (map[i - 1][j] == 0) k++;
				else
				{
					i--;
					map[i + k][j] = map[i][j];
					map[i][j] = 0;
					i++;
				}
			}
	}
	return;
}

void leftMove()
{
	int k = 1;
	for (j = 2; j<W - 2; j++)
		if (map[H - 2][j] == 0)
		{
			if (map[H - 2][j + 1] == 0) k++;
			else
			{
				j++;
				for (i = 2; i<H - 1; i++)
				{
					map[i][j - k] = map[i][j];
					map[i][j] = 0;
				}
				j--;
			}
		}
	return;
}

void getPoint(int core)
{
	switch (core)
	{
	case 3:point = point + 3 * 100; break;
	case 4:point = point + 4 * 150; break;
	case 5:point = point + 5 * 200; break;
	case 6:point = point + 6 * 300; break;
	default:point = point + core * 500; break;
	}
}

bool overJudge()
{
	int core;
	for (i = 2; i<H - 1; i++)
		for (j = 2; j < W - 1; j++)
		{
			if (map[i][j] != 0)
			{
				core = offset(i, j, 1);
				if (core > 2)
				{
					tran(i,j,0);
					return false;
				}
			}
		}
	return true;
}