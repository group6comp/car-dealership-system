public class TestBg {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test BG");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/bg.jpg");
        Image img = icon.getImage();
        BgPanel bg = new BgPanel(img);
        frame.setContentPane(bg);
        
        // Optionally add a single label
        JLabel lbl = new JLabel("Testing BG");
        lbl.setForeground(Color.WHITE);
        bg.add(lbl);

        frame.setVisible(true);
    }
}

