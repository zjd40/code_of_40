#include "myHuffmanCode.hpp"

int main()
{
	string s = " ";
	int n = 0;
	while (true)
	{
		printf("\n1---------±àÂë----------\n");
		printf("2---------ÒëÂë----------\n");
		printf("0---------ÍË³ö----------\n");
		printf("ÇëÑ¡Ôñ±àÂë»òÒëÂë£º");
		getline(cin, s);
		if (strcmp(s.c_str(),"1") == 0)
		{
			HuffmanTree HT;
			HuffmanCode HC;
			unsigned int w[26];
			initFile();
			Read_char(w, n);
			HuffmanCoding(HT, HC, w, n);
		}
		if (strcmp(s.c_str(), "2") == 0)
		{
			n = get_Num_of_Line("HuffmanCode.txt") - 1;
			getHuffmanCodeChar();
			translateHuffmanCode(n);
		}
		if (strcmp(s.c_str(), "0") == 0)
			break;
	}
	system("pause");
	return 0;
}