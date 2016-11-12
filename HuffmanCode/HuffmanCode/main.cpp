#include "myHuffmanCode.hpp"

int main()
{
	HuffmanTree HT;
	HuffmanCode HC;
	int n = 0;
	unsigned int w[26];
	Read_char(w, n);
	HuffmanCoding(HT, HC, w, n);
	system("pause");
	return 0;
}