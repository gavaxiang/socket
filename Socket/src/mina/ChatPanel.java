package mina;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang.math.RandomUtils;

public class ChatPanel extends javax.swing.JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
    private JLabel headLabel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JButton exitB;
    private JButton clearMsgB;
    private JButton sentB;
    private JButton connectB;
    private JTextArea messageText;
    private JTextField nameText;
    private JLabel nameLabel;
    private JTextArea messageArea;
    private JPanel southPanel;
    private SimpleMinaClient client = null;
    private boolean connected = false;
    private String username = null;


    public void connect(){
        if(client.connect()){
            username = nameText.getText().trim();
            if(username == null || "".equals(username)){
                username = "Guest" + RandomUtils.nextInt(1000);
                nameText.setText(username);
            }
            connected = true;
            dealUIWithFlag();
        }else{
            connected = false;   
            dealUIWithFlag();
            showMsg("Connect Failure。。。");
        }
    }
    
    
    public void showMsg(String msg){
        messageArea.append(msg);
        messageArea.append("\n");
        messageArea.selectAll();
        messageArea.lostFocus(null, this);
    }
    
    
    public void sentMsg(){
        String message = username + ":" + messageText.getText();
        client.sentMsg(message);
        messageText.setText("");
        messageText.requestFocus();
    }
    
    public void dealUIWithFlag(){
        if(connected){
            nameText.setEnabled(false);
            connectB.setEnabled(false);
            sentB.setEnabled(true);
            clearMsgB.setEnabled(true);
            exitB.setEnabled(true);
        }else{
            nameText.setEnabled(true);
            connectB.setEnabled(true);
            sentB.setEnabled(false);
            clearMsgB.setEnabled(false);
            exitB.setEnabled(false);
        }
    }
    
    
    public void closeTheClient(){
        if(client.close()){
            showMsg("disconnect...");
            connected = false;
            dealUIWithFlag();
        }else{
            showMsg("not Disconnect...");
        }
    }
    

    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new ChatPanel());
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public ChatPanel() {
        super();
        client = new SimpleMinaClient(this);
        initGUI();
        dealUIWithFlag();
    }
    
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(400, 339));
            this.setLayout(null);
            {
                northPanel = new JPanel();
                BorderLayout northPanelLayout = new BorderLayout();
                northPanel.setLayout(northPanelLayout);
                this.add(northPanel);
                northPanel.setBounds(0, 0, 400, 188);
                {
                    headLabel = new JLabel();
                    northPanel.add(headLabel, BorderLayout.NORTH);
                    headLabel.setText("Welcome (Ip:port --> 127.0.0.1:8888)");
                    headLabel.setPreferredSize(new java.awt.Dimension(397, 19));
                }
                {
                    jScrollPane1 = new JScrollPane();
                    northPanel.add(jScrollPane1, BorderLayout.CENTER);
                    jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 169));
                    {
                        messageArea = new JTextArea();
                        jScrollPane1.setViewportView(messageArea);
                        messageArea.setPreferredSize(new java.awt.Dimension(398, 145));
                        messageArea.setEditable(false);
                        messageArea.setLineWrap(true);
                        messageArea.setWrapStyleWord(true);
                    }
                }
            }
            {
                southPanel = new JPanel();
                this.add(southPanel);
                southPanel.setBounds(0, 194, 400, 145);
                southPanel.setLayout(null);
                {
                    nameLabel = new JLabel();
                    southPanel.add(nameLabel);
                    nameLabel.setText("Name:");
                    nameLabel.setBounds(10, 12, 49, 15);
                }
                {
                    nameText = new JTextField();
                    southPanel.add(nameText);
                    nameText.setText("Guest");
                    nameText.setBounds(71, 8, 96, 21);
                }
                {  
                    jScrollPane2 = new JScrollPane();
                    southPanel.add(jScrollPane2);
                    jScrollPane2.setBounds(15, 37, 364, 69);
                    {
                        messageText = new JTextArea();
                        jScrollPane2.setViewportView(messageText);
                        messageText.setBounds(101, 72, 362, 75);
                        messageText.setPreferredSize(new java.awt.Dimension(362, 54));
                        messageText.setLineWrap(true);
                        messageText.setWrapStyleWord(true);
                    }
                }
                {
                    connectB = new JButton();
                    southPanel.add(connectB);
                    connectB.setText("Login");
                    connectB.setBounds(179, 8, 93, 23);
                    connectB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("connectB.actionPerformed, event="+evt);
                            //TODO add your code for connectB.actionPerformed
                            connect();
                        }
                    });
                }
                {
                    sentB = new JButton();
                    southPanel.add(sentB);
                    sentB.setText("Send");
                    sentB.setBounds(233, 116, 77, 23);
                    sentB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("sentB.actionPerformed, event="+evt);
                            //TODO add your code for sentB.actionPerformed
                            sentMsg();
                        }
                    });
                }
                {
                    clearMsgB = new JButton();
                    southPanel.add(clearMsgB);
                    clearMsgB.setText("Clear");
                    clearMsgB.setBounds(304, 116, 77, 23);
                    clearMsgB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("clearMsgB.actionPerformed, event="+evt);
                            //TODO add your code for clearMsgB.actionPerformed
                            messageText.setText("");
                        }
                    });
                }
                {
                    exitB = new JButton();
                    southPanel.add(exitB);
                    exitB.setText("Logout");
                    exitB.setBounds(284, 9, 95, 23);
                    exitB.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("exitB.actionPerformed, event="+evt);
                            //TODO add your code for exitB.actionPerformed
                            closeTheClient();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}