// ͼ�뾰����Ϣ����ϵͳ.cpp : �������̨Ӧ�ó������ڵ㡣
//

#include "stdafx.h"
#include "Tourism.h"


int main()
{
	bool isRunning = true;
	bool created_Graph = false;
	ALGraph G;
	while (isRunning)
	{
		int o;
		cout << "----------������Ϣ����ϵͳ----------" << endl;
		cout << "1.������������ͼ" << endl;
		cout << "2.��ѯ������Ϣ" << endl;
		cout << "3.���ξ��㵼��" << endl;
		cout << "4.�������·��" << endl;
		cout << "5.�����·�滮" << endl;
		cout << "0.�˳�" << endl;
		cout << "������������(0��5)��";
		cin >> o;
		if ((o == 2 || o == 3 || o == 4 || o == 5) && !created_Graph)
		{
			cout << "���ȴ�����������ͼ" << endl << endl;
			std::system("pause");
			std::system("cls");
			continue;
		}
		switch (o)
		{
		case 1:
			CreateGraph(G);
			created_Graph = true;
			break;
		case 2:
			getSpotInfo(G);
			break;
		case 3:
			Guidance(G);
			break;
		case 4:
			FindShortPath(G);
			break;
		case 5:
			DesignPath(G);
			break;
		case 0:
			cout << "�˳�ϵͳ��" << endl;
			isRunning = false;
			break;
		default:
			break;
		}
		std::system("pause");
		std::system("cls");
	}
    return 0;
}

