import java.lang.*;
import java.io.*;
import java.net.*;


class Client{

   public static void main(String args[]){


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
    

while(){
     try{
	Socket mysocket = new Socket(host,port_int);//�����IP�A�h���X,�����Ȃ��Ă��悢�i�N���C�A���g���̂݁j
        BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//in�̓T�[�o�[�����M���邽�߂̃��\�b�h

System.out.print("��M�������b�Z�[�W:");
    while(!in.ready()){}
    System.out.println(in.readLine());
    System.out.print("\n");
    in.close();
   
     }
catch(Exception e){
    System.out.print("�ҋ@��\n");
    }
}




}catch(IOException a){
      System.out.println("��O�����A���̓G���[");
     }

}
}