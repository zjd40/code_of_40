// 图与景区信息管理系统.cpp : 定义控制台应用程序的入口点。
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
		cout << "----------景点信息管理系统----------" << endl;
		cout << "1.创建景区景点图" << endl;
		cout << "2.查询景点信息" << endl;
		cout << "3.旅游景点导航" << endl;
		cout << "4.搜索最短路径" << endl;
		cout << "5.铺设电路规划" << endl;
		cout << "0.退出" << endl;
		cout << "请输入操作编号(0—5)：";
		cin >> o;
		if ((o == 2 || o == 3 || o == 4 || o == 5) && !created_Graph)
		{
			cout << "请先创建景区景点图" << endl << endl;
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
			cout << "退出系统！" << endl;
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

