//クライアントとしての処理を先に行い、次に受信に移る。この役割を交互に切り替える。

import java.lang.*;
import java.io.*;
import java.net.*;


class Client_first{

   public static void main(String args[]){

while(true){
     try{

    InputStreamReader i =
          new InputStreamReader(System.in);
      
		System.out.print("Client側の設定を行います。");
		System.out.print("hostを設定します:");
       			BufferedReader b=
          		new BufferedReader(i);
       		String host = b.readLine();
       		System.out.println(host);

		System.out.print("portを設定します:");

       		BufferedReader a=
          	new BufferedReader(i);
      		String port = a.readLine();
       		int port_int = Integer.parseInt(port);
       System.out.println(port);
    

     try{
	Socket mysocket = new Socket(host,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
        BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド

		System.out.print("受信したメッセージ:");
    		while(!in.ready()){}
    		System.out.println(in.readLine());
    		System.out.print("\n");
    		in.close();
   
     　}
	catch(Exception e){
    		System.out.print("待機中\n");
    	}


String data="サーバーへの接続完了。";


System.out.print("サーバーとクライアントの接続を待機しています\n");
	ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//接続するまでここで止まる
	System.out.print("サーバーとクライアントの接続を構築しました\n送信するメッセージを入力してください。");
	PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//メッセージを送信
        System.out.print("送信されたメッセージは"+data+"です\n");
        out.print(data);
        out.close();
        skt.close();
        srvr.close();



}catch(IOException a){
      System.out.println("例外処理、入力エラー");
     }

}

}
}