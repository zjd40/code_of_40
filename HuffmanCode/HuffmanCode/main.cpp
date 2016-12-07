#include "myHuffmanCode.hpp"
#include "myBMP.hpp"

int main()
{
	int n = 0;
	while (true)
	{
		printf("1---------�����ͱ���---------\n");
		printf("2---------�����ͱ���---------\n");
		printf("3---------����������---------\n");
		printf("4---------����������---------\n");
		printf("0------------�˳�------------\n");
		printf("��ѡ���������룺");
		string s;
		getline(cin, s);
		if (strcmp(s.c_str(), "1") == 0)
		{
			HuffmanTree HT;
			HuffmanCode HC;
			double w[26] = { 0 };
			string str;
			initFile();
			Init();
			printf("�������ַ�����ȫСдӢ����ĸ����");
			getline(cin, str);
			for (int i = 0; i < str.size(); i++)
				Read_char(n, str[i]);
			getWeight(w, n, str.size());
			HuffmanCoding(HT, HC, w, n, str);
		}
		if (strcmp(s.c_str(), "2") == 0) 
		{
			HuffmanTree HT;
			HuffmanCode HC;
			const char* filename = "test.txt";
			int count = 0;
			int length = 0;
			double w[26] = { 0 };
			char* str = (char*)malloc(getFileSize(filename)*sizeof(char));
			//printf("������Ҫ������ļ�����");
			//scanf("%c", &filename);
			readFile(filename, str);
			for (int i = 0; i < getFileSize(filename); i++)
				Read_char(n, str[i]);
			getWeight(w, n, getFileSize(filename));
			HuffmanCoding(HT, HC, w, n, str);
		}
		if (strcmp(s.c_str(), "3") == 0)
		{
			string code;
			printf("������01���룺");
			getline(cin, code);
			n = get_Num_of_Line("HuffmanCode.txt") - 1;
			readFile("HuffmanCode.txt", HuffmanCodeChar, 1);
			translateHuffmanCode("HuffmanTree.txt", n, code);
		}
		if (strcmp(s.c_str(), "4") == 0)
		{
			const char* filename = "code.txt";
			char* str = (char*)malloc(getFileSize(filename) * sizeof(char));
			cout << strlen(str) << endl;
			cin >> n;
			readFile(filename, str);
			n = get_Num_of_Line("HuffmanCode.txt") - 1;
			readFile("HuffmanCode.txt", HuffmanCodeChar, 1);
			string code = str;
			translateHuffmanCode("HuffmanTree.txt", n, code);
		}
		if (strcmp(s.c_str(), "6") == 0)
		{
			initFile();
			myBMP("myImage.bmp", n);
		}
		if (strcmp(s.c_str(), "0") == 0)
			break;
		printf("\n");
	}
	system("pause");
	return 0;
}