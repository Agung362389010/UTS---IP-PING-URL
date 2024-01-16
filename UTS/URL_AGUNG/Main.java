import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {
    private JTextField txtInputText;
    private JButton btnExtract;
    private JTextArea txtResult;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        // Inisialisasi komponen GUI
        txtInputText = new JTextField(15);
        btnExtract = new JButton("Extract");
        txtResult = new JTextArea(14, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Extract URL");

        // Label judul "Extract URL" dengan format dan margin atas dan bawah
        JLabel lblTitle = new JLabel("Extract URL", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // Margin atas dan bawah

        // Label untuk input dan hasil ekstraksi URL
        JLabel lblInput = new JLabel("Input Text");
        JLabel lblResult = new JLabel("Result");

        // Menambahkan listener untuk tombol "Extract"
        btnExtract.addActionListener(this::btnExtractActionPerformed);

        // Mengatur layout BorderLayout
        setLayout(new BorderLayout());

        // Menambahkan label judul ke bagian utara
        add(lblTitle, BorderLayout.NORTH);

        // Panel input untuk menampung label input, teks input, dan tombol "Extract"
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(lblInput);
        inputPanel.add(txtInputText);
        inputPanel.add(btnExtract);
        add(inputPanel, BorderLayout.CENTER);

        // JScrollPane untuk menangani area teks hasil ekstraksi dan menambahkan margin kiri dan kanan
        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Margin kiri dan kanan
        add(lblResult, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.SOUTH);

        // Menetapkan ukuran form menjadi 500x380
        setSize(500, 380);

        // Menempatkan form di tengah layar
        setLocationRelativeTo(null);
        // Menampilkan form
        setVisible(true);
    }

    private void btnExtractActionPerformed(ActionEvent evt) {
        // Mendapatkan teks input dari pengguna
        String inputText = txtInputText.getText();
        // Pola regex untuk mencocokkan URL dalam teks
        String regex = "(https?://\\S+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        // Mencari dan menampilkan URL yang ditemukan
        while (matcher.find()) {
            txtResult.append("URL: " + matcher.group(1) + "\n");
        }
    }

    public static void main(String[] args) {
        // Memulai aplikasi GUI Swing di EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(Main::new);
    }
}
