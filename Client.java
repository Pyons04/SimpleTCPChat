import java.lang.*;
import java.io.*;
import java.net.*;


class Client{

   public static void main(String args[]){


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
    

while(){
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
}




}catch(IOException a){
      System.out.println("例外処理、入力エラー");
     }

}
}