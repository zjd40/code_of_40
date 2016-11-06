#include<iostream>//���������������system()
#include<Windows.h>//�������ߺ���
#include<time.h>//����ʱ�亯�������ڲ��������
#include <conio.h>//����kbhit()����
using namespace std;

#define H 17   //��ͼ��
#define W 22   //��ͼ��

static int point = 0;//����
int i, j, xFood, yFood,xHead,yHead;//��ͼ���꣬ʳ�����꣬��ͷ����
int map[H][W], *food, *head, *body[H*W-1], *tail;//��ͼ��ʳ���ͷ��������β
char direction;//���뷽��
char Right, Left, Front, Back;//��ͷ����
int bodyLength = 1;//����
int flag = 1;//��Ϸ������־

void start();//���ɵ�ͼ
void getFood();//����ʳ��
void draw();//���Ƶ�ͼ
void getPoint();//��ӡ����
void turn();//ת������
void changeDirection();//�ı䷽���
void move();//�ƶ�
bool judge();//�ж���Ϣ

int main()
{
	start();//���ɵ�ͼ
	getFood();//����ʳ��
	while (flag)
	{
		draw();//���Ƶ�ͼ
		getPoint();//��ӡ����
		Sleep(500);//���ߺ���	
		if (_kbhit())
		{
			direction = _getch();//��ȡ�ߵķ���
			changeDirection();//�ı��ߵķ���
		}
		turn();//������ת��
		move();//�ƶ�
		if (judge()) flag = 0;//�ж��Ƿ��ȡʳ����߽�����Ϸ
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
				map[i][j] = 9;//ǽ���
			else
				map[i][j] = 0;//�յر��
		}
	}
	xHead = H / 2;//��ͷ����X
	yHead = W / 2;//��ͷ����Y
	head = &map[xHead][yHead];//��ͷ����
	body[0] = &map[xHead - 1][yHead];//��������
	tail = &map[xHead - 2][yHead];//��β����
	*head = 3;//��ͷ���
	*body[0] = 2;//������
	*tail = 1;//��β���
	Front = '2';//ǰ
	Right = '4';//��
	Left = '6';//��
	Back = '8';//��
	direction = Front;//��ͷ����
}

void getFood()
{
	srand((unsigned)time(NULL));
	do
	{
		xFood = rand() % (H - 2) + 1;
		yFood = rand() % (W - 2) + 1;
	} while (map[xFood][yFood] != 0);
	food = &map[xFood][yFood];//ʳ������
	*food = 4;//ʳ����
}

void draw()
{
	for (i = 0; i < H; i++)
	{
		for (j = 0; j < W; j++)
		{
			switch (map[i][j])
			{
			case 0:cout << "   "; break;//�հ״�
			case 1:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN); cout << " . "; break;//β
			case 2:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN); cout << " o "; break;//��
			case 3:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_BLUE); cout << " O "; break;//ͷ
			case 4:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED); cout << " @ "; break;//ʳ��
			case 9:SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); cout << " # "; break;//ǽ
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
	/*����Ĭ�ϳ�ʼ�������趨*/
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
	direction = Front;//�ı䷽��к���ͷ����Ϊǰ
}

void move()
{
	/*�ӳ����������ƶ�*/
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
	case 0://�����յ�ʱ
		*body[bodyLength] = 1;//����ĩ�˱�Ÿ�Ϊ��β���
		*tail = 0;//��β��Ÿ�Ϊ�յر��
		tail = body[bodyLength];//����ĩ�˸�Ϊ��β
		*head = 3;
		return false;
	case 1://������βʱ
		*body[bodyLength] = 1;//����ĩ�˱�Ÿ�Ϊ��β���
		*tail = 3;//��β��Ÿ�Ϊ��ͷ���
		tail = body[bodyLength];//����ĩ�˸�Ϊ��β
		return false;
	case 2://��������ʱ
	case 9://����ǽʱ
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED);
		cout << "GAME OVER!" << endl;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_RED |FOREGROUND_BLUE);
		cout << "YOUR POINT IS :  ";
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), FOREGROUND_INTENSITY | FOREGROUND_GREEN | FOREGROUND_BLUE);
		cout << point << endl;
		system("pause");
		return true;
	case 4://����ʳ��ʱ
		*head = 3;//��Ÿ�Ϊ��ͷ���
		bodyLength++;//��������
		point++;//�ӷ�
		getFood();//����ʳ��
		return false;
	}
}

/*
*���ߺ���
*#include<windows.h> 
*Sleep(������)
*ʹ��������
*
*�жϼ������뺯��
*#include <conio.h>
*kbhit()
*�жϼ����Ƿ�����,��Ϊtrue����Ϊfalse
*/