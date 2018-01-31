import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

public class SimpleTCPChat_Socket implements ActionListener{
  JLabel labelnorth,labelsouth,label_ip,label_port;
  JLabel label_offline;
  JButton button1,button2;
  JTextField ip,port,text_field_under_the_scrollpanel;
  JRadioButton host_button,guest_button;
  JTextArea ta,textarea;
  JScrollPane pane;
  JPanel leftPanel,rightPanel;

   public SimpleTCPChat_Socket(){

    JFrame frame=new JFrame("Simple TCP Chat!!"); //�㕔�̃^�C�g��
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����{�^���������ƃv���O�����������ŏI������B
    frame.setSize(600,600);
    Container contentPane=frame.getContentPane();//�K�v�Ȃ�
    //JPanel panel=new JPanel();//�V�����p�l���I�u�W�F�N�g
    //panel.setLayout(new FlowLayout());//Layout�̐ݒ�i���i�̏悹���̐ݒ�A�㉺���E�j
    //panel.setBackground(Color.White);
    //frame.getContentPane().add(panel);//Pane��panel��ǉ�

    JSplitPane splitpane = new JSplitPane();
    //frame.getContentPane().add(splitpane);
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

    JPanel pan1_on_leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel pan2_on_leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel pan3_on_leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel pan4_on_leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    String message="";
    JTextArea textarea = new JTextArea(message);//�f�t�H���g����
    int height = textarea.getMaximumSize().height;
    textarea.setPreferredSize(new Dimension(300, 200));//textarea�̃T�C�Y�ݒ�

    JScrollPane scrollpane = new JScrollPane(textarea);
    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    rightPanel.add(scrollpane,BorderLayout.CENTER);

    text_field_under_the_scrollpanel=new JTextField("Put your message to this text filed!",100);
    //t.addActionListener(this);
    rightPanel.add(text_field_under_the_scrollpanel,BorderLayout.SOUTH);

    ip=new JTextField("",10);
    //t.addActionListener(this);
    label_ip=new JLabel("Host IP");
    pan1_on_leftPanel.add(label_ip);
    pan1_on_leftPanel.add(ip);
    leftPanel.add(pan1_on_leftPanel);

    port=new JTextField("",10);
    //t.addActionListener(this);
    label_port=new JLabel("PORT");
    pan2_on_leftPanel.add(label_port);
    pan2_on_leftPanel.add(port);
    leftPanel.add(pan2_on_leftPanel);

    host_button = new JRadioButton("<html><u>H</u>ost</html>",true);
    //host_button.setText("<html><u>H</u>ost</html>");
    pan3_on_leftPanel.add(host_button);

    guest_button = new JRadioButton();
    guest_button.setText("<html><u>G</u>uest</html>");
    pan3_on_leftPanel.add(guest_button);
    leftPanel.add(pan3_on_leftPanel);

    pan4_on_leftPanel.setLayout(new GridLayout(1,2));

    //pan4_on_leftPanel.setLayout(new BorderLayout());
    button1 =new JButton();
    button1.setText("<html><u>C</u>onnect</html>");
    //button1.setPreferredSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
//Connect�{�^���̃A�N�V�������X�i�[
		button1.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
		System.out.print("�{�^���������ꂽ\n");


    String ipadress = ip.getText();//ip�A�h���X��string�^�̕ϐ��Ƃ��Ď擾
    String port_number = port.getText();//�|�[�g�ԍ���string�^�̕ϐ��Ƃ��Ď擾

   System.out.println(ipadress+"�擾����hostip");
   System.out.println(port_number+"�擾����portnumber");
   int port_int = Integer.parseInt(port_number);
   

    boolean status_guest = guest_button.isSelected();//guest�Ƀ`�F�b�N�������Ă��邩�ǂ���
    boolean status_host = host_button.isSelected();//host�Ƀ`�F�b�N�������Ă��邩�ǂ���
if(status_guest){      //�����Q�X�g�ɂȂ��Ă������M���[�h
  //Connect�{�^����ActionListner�̐ݒ�
  //while(true){   //��M���͏�Ƀ��[�v���Ď�M���J��Ԃ��Ă����A���M���͈�񑗐M�����������珈�����~����B
     		try{
			Socket mysocket = new Socket(ipadress,port_int);//�����IP�A�h���X,�����Ȃ��Ă��悢�i�N���C�A���g���̂݁j
        		BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//in�̓T�[�o�[�����M���邽�߂̃��\�b�h


		System.out.print("guest���E��M���[�h�ɂȂ��Ă��܂��B\n");
    			//while(!in.ready()){}
    		//System.out.println(in.readLine());
        String new_message;
        new_message=in.readLine();
        System.out.println(new_message+"\n");
        textarea.setForeground(Color.blue);
        textarea.append("INCOMING:"+new_message+"\n");
        in.close();
    		 }
			catch(Exception a){
    				System.out.print("�G���[���������Ă��܂�\n");
            textarea.append("��M���[�h�ŃG���[���������Ă��܂�\n");
    			}

        //}

      }

      else{//guest�Ƀ`�F�b�N�������Ă��Ȃ���Α��M���[�h�ɂȂ�܂��B
        System.out.print("host���E���M���[�h�ɂȂ��Ă��܂��B\n");
     try{
        ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//�ڑ�����܂ł����Ŏ~�܂�
        System.out.print("�T�[�o�[�ɐڑ����܂���\n");
        PrintWriter out=new PrintWriter(skt.getOutputStream(),true);
        String send_message = text_field_under_the_scrollpanel.getText();
        textarea.append("OUTGOING:"+send_message+"\n");
        out.print(send_message);
        out.close();
        skt.close();
        srvr.close();
     }
catch(Exception b){
    System.out.print("�G���[���������Ă��܂�\n");
    textarea.append("���M���[�h�ŃG���[���������Ă��܂�\n");
    }
      }
 //Connect�{�^���̒ʐM�̐ݒ�I���
   }
  });
//Connect�{�^���̃A�N�V�������X�i�[�I���

    //button.addActionListener(this);
    pan4_on_leftPanel.add(button1);

    button2 =new JButton();
    button2.setText("<html><u>D</u>isconnect</html>");
   //button2.setPreferredSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
    //button.addActionListener(this);
    pan4_on_leftPanel.add(button2);
    leftPanel.add(pan4_on_leftPanel);   


    ButtonGroup group1;//Host_Button��Gest_buttun�͕Е������I���ł��Ȃ��悤�ɂ���B
    group1 = new ButtonGroup();
    group1.add(host_button);
    group1.add(guest_button);

    label_offline=new JLabel("Offline");
    leftPanel.add(label_offline);

    splitpane.setLeftComponent(leftPanel);
    splitpane.setRightComponent(rightPanel);

    frame.getContentPane().add(splitpane, BorderLayout.CENTER);
    frame.setVisible(true);//window��������or�����Ȃ��B��ԍŌオ�ǂ��i�K�{�j
  }

    public void actionPerformed(ActionEvent event){
      //Icon img4=new ImageIcon("steak.png");
      //icon3.setIcon(img4);
   }
  public static void main (String [] args){
   SimpleTCPChat_Socket sAction3=new SimpleTCPChat_Socket();
  }
}