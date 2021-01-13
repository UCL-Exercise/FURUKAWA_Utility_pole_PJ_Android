package Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception{
        // TODO 自動生成されたメソッド・スタブ
        SendUtil util =SendUtil.builderSendUtil();
        util.add("sdfsdf1");
        util.add("sdfsdf2");
        util.add("sdfsdf3");
        util.add("sdfsdf4");
        //
        List<String>cc=new ArrayList<String>();
        cc.add("youkenin");
        //
        util.add(new File("C:/git/jmall_workspace/FileUpload/test1.txt"),cc);
        util.add(new File("C:/git/jmall_workspace/FileUpload/test2.txt"),cc);
        //
        //DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:/git/jmall_workspace/FileUpload/test.txt"));
        //
        ByteArrayOutputStream arrayOutputStream =new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(arrayOutputStream);
        util.sendToOutStream(dos);
        //
        ReceUtil util1 =ReceUtil.builderReceUtil();
        //
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOutputStream.toByteArray()));
        util1.parseHead(dis);
        DataOutputStream dos1 = new DataOutputStream(new FileOutputStream("C:/git/jmall_workspace/FileUpload/test1_1.txt"));
        util1.parseFile(dis, 0, dos1);
//
        DataOutputStream dos2 = new DataOutputStream(new FileOutputStream("C:/git/jmall_workspace/FileUpload/test2_1.txt"));
        util1.parseFile(dis, 1, dos2);

        //
        dos.close();
        dis.close();
    }

}
