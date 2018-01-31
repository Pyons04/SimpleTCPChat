import java.lang.*;
import java.io.*;
import java.net.*;

class Server{
   public static void main(String args[]){


 try{

    InputStreamReader c =
          new InputStreamReader(System.in);

System.out.print("portを設定します:");

       BufferedReader a=
          new BufferedReader(c);
       String port = a.readLine();
       int port_int = Integer.parseInt(port);
       System.out.println(port);

     String data="サーバーへの接続完了。";

     while(true){
     try{
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

try{   System.out.print("送信したい文字を入力してください。");
       InputStreamReader i =
          new InputStreamReader(System.in);
       BufferedReader b=
          new BufferedReader(i);
        data = b.readLine();
       System.out.println(data);
       }catch  (IOException f){
        System.out.println("入力エラー");
     }



    }
catch(Exception e){
    System.out.print("エラーが発生しています\n");
    }
  }

}catch(IOException a){
      System.out.println("例外処理、入力エラー");
     }

}
}
