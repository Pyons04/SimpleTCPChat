import java.lang.*;
import java.io.*;
import java.net.*;

class Server{
   public static void main(String args[]){


 try{

    InputStreamReader c =
          new InputStreamReader(System.in);

System.out.print("port��ݒ肵�܂�:");

       BufferedReader a=
          new BufferedReader(c);
       String port = a.readLine();
       int port_int = Integer.parseInt(port);
       System.out.println(port);

     String data="�T�[�o�[�ւ̐ڑ������B";

     while(true){
     try{
        System.out.print("�T�[�o�[�ƃN���C�A���g�̐ڑ���ҋ@���Ă��܂�\n");
	ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//�ڑ�����܂ł����Ŏ~�܂�
	System.out.print("�T�[�o�[�ƃN���C�A���g�̐ڑ����\�z���܂���\n���M���郁�b�Z�[�W����͂��Ă��������B");
	PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//���b�Z�[�W�𑗐M
        System.out.print("���M���ꂽ���b�Z�[�W��"+data+"�ł�\n");
        out.print(data);
        out.close();
        skt.close();
        srvr.close();

try{   System.out.print("���M��������������͂��Ă��������B");
       InputStreamReader i =
          new InputStreamReader(System.in);
       BufferedReader b=
          new BufferedReader(i);
        data = b.readLine();
       System.out.println(data);
       }catch  (IOException f){
        System.out.println("���̓G���[");
     }



    }
catch(Exception e){
    System.out.print("�G���[���������Ă��܂�\n");
    }
  }

}catch(IOException a){
      System.out.println("��O�����A���̓G���[");
     }

}
}
