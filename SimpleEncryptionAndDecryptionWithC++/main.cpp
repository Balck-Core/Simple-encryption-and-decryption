#include <iostream>
#include <ostream>
#include <fstream>

using namespace std;
int main () {
    cout << "����������ܵ��ļ��ĵ�ַ" << endl;
    string InputPath;
    getline(cin,InputPath);
    cout << "��������ܺ��ļ�����ĵ�ַ" << endl;
    string OutputPath;
    getline(cin,OutputPath);
    cout << "��������Կ" << endl;
    int keys;
    cin >> keys;
    std::ifstream intputFile(InputPath,std::ios::binary);
    std::ofstream outputFile(OutputPath,std::ios::binary);
    const int buffersize = 1024 * 8;
    char buffer[buffersize];
    int byteRead;

    while ((byteRead = intputFile.readsome(buffer,buffersize)) > 0) {
        for (int i = 0; i < byteRead; ++i) {
            buffer[i] ^= keys;
        }
        outputFile.write(buffer,byteRead);
    }
    intputFile.close();
    outputFile.close();
    cout << "ִ�гɹ�" << endl;
}
