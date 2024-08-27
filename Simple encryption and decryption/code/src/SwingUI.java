import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SwingUI extends JFrame {

    private JTextField inputFileField;
    private JTextField outputFileField;
    private JTextField keyField;
    private JLabel statusLabel;

    public SwingUI() {
        setTitle("文件加密解密工具");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        inputFileField = new JTextField();
        outputFileField = new JTextField();
        keyField = new JTextField();
        statusLabel = new JLabel(" ");

        JButton selectInputButton = new JButton("选择输入的文件地址");
        selectInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    inputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JButton selectOutputButton = new JButton("选择输出的文件地址");
        selectOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    outputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JButton encryptButton = new JButton("开始加密");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    encryptFile();
                } catch (IOException ex) {
                    statusLabel.setText("错误: " + ex.getMessage());
                }
            }
        });

        add(new JLabel("输入文件:"));
        add(inputFileField);
        add(selectInputButton);
        add(new JLabel("输出文件:"));
        add(outputFileField);
        add(selectOutputButton);
        add(new JLabel("密钥:"));
        add(keyField);
        add(encryptButton);
        add(statusLabel);
    }

    private void encryptFile() throws IOException {
        String input = inputFileField.getText();
        String output = outputFileField.getText();
        int password = Integer.parseInt(keyField.getText());

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output))) {

            byte[] buffer = new byte[1024 * 8];
            int by;
            while ((by = bis.read(buffer)) != -1) {
                for (int i = 0; i < by; i++) {
                    buffer[i] ^= password;
                }
                bos.write(buffer, 0, by);
            }
        }
        statusLabel.setText("加密或解密成功");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwingUI().setVisible(true);
        });
    }
}
