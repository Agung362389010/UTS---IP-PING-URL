import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends JFrame {
    private JButton btnCheck;
    private JTextArea txtResult;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        // Inisialisasi tombol dan area teks
        btnCheck = new JButton("Check");
        txtResult = new JTextArea(14, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Computer Name & IP Address");

        // Membuat label judul dengan format dan margin atas dan bawah
        JLabel lblTitle = new JLabel("Computer Name & IP Address", SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Margin atas dan bawah

        // Label untuk instruksi dan tombol "Check"
        JLabel lblCheck = new JLabel("Check Host Name & IP Address");

        // Menambahkan listener untuk tombol "Check"
        btnCheck.addActionListener(this::btnCheckActionPerformed);

        // Mengatur layout BorderLayout
        setLayout(new java.awt.BorderLayout());

        // Menambahkan label judul ke bagian utara
        add(lblTitle, java.awt.BorderLayout.NORTH);

        // Panel untuk menampung label instruksi dan tombol "Check"
        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        buttonPanel.add(lblCheck);
        buttonPanel.add(btnCheck);
        add(buttonPanel, java.awt.BorderLayout.CENTER);

        // JScrollPane untuk menangani area teks dan menambahkan margin kiri dan kanan
        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Margin kiri dan kanan
        add(scrollPane, java.awt.BorderLayout.SOUTH);

        // Menetapkan ukuran form menjadi 500x380
        setSize(500, 380);

        // Menempatkan form di tengah layar
        setLocationRelativeTo(null);
        // Menampilkan form
        setVisible(true);
    }

    private void btnCheckActionPerformed(ActionEvent evt) {
        try {
            // Mendapatkan informasi tentang nama komputer dan alamat IP localhost
            InetAddress localHost = InetAddress.getLocalHost();
            txtResult.append("Nama Komputer: " + localHost.getHostName() + "\n");
            txtResult.append("IP Address: " + localHost.getHostAddress() + "\n");
        } catch (UnknownHostException e) {
            // Menangani exception jika terjadi kesalahan saat mendapatkan informasi
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Memulai aplikasi GUI Swing di EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(Main::new);
    }
}
