//�N���C�A���g�Ƃ��Ă̏������ɍs���A���Ɏ�M�Ɉڂ�B���̖��������݂ɐ؂�ւ���B

import java.lang.*;
import java.io.*;
import java.net.*;


class Client_first{

   public static void main(String args[]){

while(true){
     try{

    InputStreamReader i =
          new InputStreamReader(System.in);
      
		System.out.print("Client���̐ݒ���s���܂��B");
		System.out.print("host��ݒ肵�܂�:");
       			BufferedReader b=
          		new BufferedReader(i);
       		String host = b.readLine();
       		System.out.println(host);

		System.out.print("port��ݒ肵�܂�:");

       		BufferedReader a=
          	new BufferedReader(i);
      		String port = a.readLine();
       		int port_int = Integer.parseInt(port);
       System.out.println(port);
    

     try{
	Socket mysocket = new Socket(host,port_int);//�����IP�A�h���X,�����Ȃ��Ă��悢�i�N���C�A���g���̂݁j
        BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//in�̓T�[�o�[�����M���邽�߂̃��\�b�h

		System.out.print("��M�������b�Z�[�W:");
    		while(!in.ready()){}
    		System.out.println(in.readLine());
    		System.out.print("\n");
    		in.close();
   
     �@}
	catch(Exception e){
    		System.out.print("�ҋ@��\n");
    	}


String data="�T�[�o�[�ւ̐ڑ������B";


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



}catch(IOException a){
      System.out.println("��O�����A���̓G���[");
     }

}

}
}