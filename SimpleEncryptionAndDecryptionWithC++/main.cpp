#include <iostream>
#include <ostream>
#include <fstream>

using namespace std;
int main () {
    cout << "请输入需加密的文件的地址" << endl;
    string InputPath;
    getline(cin,InputPath);
    cout << "请输入加密后文件输出的地址" << endl;
    string OutputPath;
    getline(cin,OutputPath);
    cout << "请输入密钥" << endl;
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
    cout << "执行成功" << endl;
}
