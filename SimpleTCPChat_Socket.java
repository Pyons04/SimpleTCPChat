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

    JFrame frame=new JFrame("Simple TCP Chat!!"); //上部のタイトル
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
    frame.setSize(600,600);
    Container contentPane=frame.getContentPane();//必要ない
    //JPanel panel=new JPanel();//新しいパネルオブジェクト
    //panel.setLayout(new FlowLayout());//Layoutの設定（部品の乗せ方の設定、上下左右）
    //panel.setBackground(Color.White);
    //frame.getContentPane().add(panel);//Paneにpanelを追加

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
    JTextArea textarea = new JTextArea(message);//デフォルト文字
    int height = textarea.getMaximumSize().height;
    textarea.setPreferredSize(new Dimension(300, 200));//textareaのサイズ設定

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
//Connectボタンのアクションリスナー
		button1.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
		System.out.print("ボタンが押された\n");


    String ipadress = ip.getText();//ipアドレスをstring型の変数として取得
    String port_number = port.getText();//ポート番号をstring型の変数として取得

   System.out.println(ipadress+"取得したhostip");
   System.out.println(port_number+"取得したportnumber");
   int port_int = Integer.parseInt(port_number);
   

    boolean status_guest = guest_button.isSelected();//guestにチェックが入っているかどうか
    boolean status_host = host_button.isSelected();//hostにチェックが入っているかどうか
if(status_guest){      //もしゲストになっていたら受信モード
  //ConnectボタンのActionListnerの設定
  //while(true){   //受信側は常にループして受信を繰り返しておき、送信側は一回送信が完了したら処理を停止する。
     		try{
			Socket mysocket = new Socket(ipadress,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
        		BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド


		System.out.print("guest側・受信モードになっています。\n");
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
    				System.out.print("エラーが発生しています\n");
            textarea.append("受信モードでエラーが発生しています\n");
    			}

        //}

      }

      else{//guestにチェックが入っていなければ送信モードになります。
        System.out.print("host側・送信モードになっています。\n");
     try{
        ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//接続するまでここで止まる
        System.out.print("サーバーに接続しました\n");
        PrintWriter out=new PrintWriter(skt.getOutputStream(),true);
        String send_message = text_field_under_the_scrollpanel.getText();
        textarea.append("OUTGOING:"+send_message+"\n");
        out.print(send_message);
        out.close();
        skt.close();
        srvr.close();
     }
catch(Exception b){
    System.out.print("エラーが発生しています\n");
    textarea.append("送信モードでエラーが発生しています\n");
    }
      }
 //Connectボタンの通信の設定終わり
   }
  });
//Connectボタンのアクションリスナー終わり

    //button.addActionListener(this);
    pan4_on_leftPanel.add(button1);

    button2 =new JButton();
    button2.setText("<html><u>D</u>isconnect</html>");
   //button2.setPreferredSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
    //button.addActionListener(this);
    pan4_on_leftPanel.add(button2);
    leftPanel.add(pan4_on_leftPanel);   


    ButtonGroup group1;//Host_ButtonとGest_buttunは片方しか選択できないようにする。
    group1 = new ButtonGroup();
    group1.add(host_button);
    group1.add(guest_button);

    label_offline=new JLabel("Offline");
    leftPanel.add(label_offline);

    splitpane.setLeftComponent(leftPanel);
    splitpane.setRightComponent(rightPanel);

    frame.getContentPane().add(splitpane, BorderLayout.CENTER);
    frame.setVisible(true);//windowを見せるor見せない。一番最後が良い（必須）
  }

    public void actionPerformed(ActionEvent event){
      //Icon img4=new ImageIcon("steak.png");
      //icon3.setIcon(img4);
   }
  public static void main (String [] args){
   SimpleTCPChat_Socket sAction3=new SimpleTCPChat_Socket();
  }
}