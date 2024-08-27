import java.io.*;
import java.util.Scanner;

public class tools {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需加密的文件地址");
        String input = sc.nextLine();
        System.out.println("请输入文件输出路径");
        String output = sc.nextLine();
        System.out.println("请输入解密密钥");
        int password = sc.nextInt();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
        int key = password;
        byte[] buffer = new byte[1024*8];
        int by;
        while ((by = bis.read(buffer)) != -1) {
            for (int i = 0; i < by; i++) {
                buffer[i] ^= password;
            }
            bos.write(buffer, 0, by);
        }
        bis.close();
        bos.close();
        sc.close();
        System.out.println("执行成功");
    }
}
