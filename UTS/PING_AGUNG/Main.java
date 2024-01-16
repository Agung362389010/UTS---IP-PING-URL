import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private JLabel labelIpAddress;
    private JTextField txtIPAddress;
    private JButton btnStart;
    private JTextArea txtTerminal;

    public Main() {
        // Inisialisasi komponen GUI
        txtIPAddress = new JTextField(15);
        btnStart = new JButton("Start Ping");
        txtTerminal = new JTextArea(14, 10);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ping Program");

        // Menambahkan listener untuk tombol "Start Ping"
        btnStart.addActionListener(evt -> btnStartActionPerformed());

        setLayout(new BorderLayout());

        // Menambahkan judul label "Ping Program" dengan margin atas
        JLabel titleLabel = new JLabel("PING PROGRAM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Panel untuk menampung komponen-komponen GUI terkait alamat IP
        JPanel panel = new JPanel();
        panel.add(new JLabel("IP Address"));
        panel.add(txtIPAddress);
        panel.add(btnStart);
        add(panel, BorderLayout.CENTER);

        // Menambahkan area terminal dengan menggunakan JScrollPane
        JScrollPane scrollPane = new JScrollPane(txtTerminal);
        scrollPane.setBorder(new EmptyBorder(0, 10, 10, 10));
        add(scrollPane, BorderLayout.SOUTH);

        // Menetapkan ukuran form
        setSize(500, 380);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnStartActionPerformed() {
        // Mendapatkan alamat IP dari input pengguna
        String ipAddress = txtIPAddress.getText();

        try {
            // Mencoba untuk membuat objek InetAddress dari alamat IP
            InetAddress inetAddress = InetAddress.getByName(ipAddress);

            // Melakukan ping sebanyak 5 kali
            for (int i = 0; i < 5; i++) {
                long startTime = System.currentTimeMillis();
                // Mengecek apakah host dapat dicapai dalam batas waktu 2000 ms
                boolean reachable = inetAddress.isReachable(2000);
                long endTime = System.currentTimeMillis();

                if (reachable) {
                    // Jika host dapat dicapai, menampilkan informasi ping yang berhasil
                    long pingTime = endTime - startTime;
                    txtTerminal.append("PING " + ipAddress + " Berhasil - Waktu Respons: " + pingTime + " ms\n");
                } else {
                    // Jika host tidak dapat dicapai, menampilkan pesan ping gagal
                    txtTerminal.append("PING ke " + ipAddress + " Gagal\n");
                }
            }
        } catch (IOException e) {
            // Menangani exception yang mungkin terjadi, seperti IOException
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Memulai aplikasi GUI Swing
        SwingUtilities.invokeLater(Main::new);
    }

}
