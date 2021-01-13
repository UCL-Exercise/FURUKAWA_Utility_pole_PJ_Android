package Util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import Util.impl.CharItem;
import Util.impl.DataItem;
import Util.impl.Item;

public class SendUtil {
    //
    private SendUtil(){}
    //
    private List<Item>itemList=null;
    private int gpNo=1;
    //
    public static SendUtil builderSendUtil(){
        SendUtil util=new SendUtil();
        util.itemList=new ArrayList<Item>();
        return util;
    }
    //
    public void add(long value){
        this.itemList.add(new CharItem(value));
    }
    public void add(int value){
        this.itemList.add(new CharItem(value));
    }
    public void add(String value){
        try{
            this.itemList.add(new CharItem(value));
        }catch(Exception e){
            //
        }
    }
    public void add(File file){
        this.add(file,null);
    }
    public void add(File file,List<String>strList){
        try{
            List<Item> subItemList= new ArrayList<Item>();
            subItemList.add(new CharItem(file.getName()));//ファイル名
            subItemList.add(new DataItem(new FileInputStream(file),file.length()));//ファイルデータ
            if(strList!=null){
                for(String str:strList){
                    subItemList.add(new CharItem(str));//付加属性文
                }
            }
            //GP設定
            for(Item dto:subItemList){
                dto.setGp(this.gpNo);
            }
            //
            this.itemList.addAll(subItemList);
            gpNo++;
        }catch(Exception e){
            //
        }
    }
    //
    public void sendToSocketChannel(SocketChannel socketChannel)throws IOException{
        sendToOutStream(socketChannel.socket().getOutputStream());
    }
    //
    public void sendToOutStream(OutputStream dos)throws IOException{
        //項目数 4BTYE
        byte[] itemCount=ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.itemList.size()).array();
        dos.write(itemCount);
        //項目毎
        int preGp=0;
        int nexGp=0;
        for(int i=0;i<this.itemList.size();i++){
            //
            Item dto=this.itemList.get(i);
            //
            nexGp=0;
            if(i+1<this.itemList.size()){
                nexGp=this.itemList.get(i+1).getGp();
            }

            //G標識
            byte flg=0x00;
            if(dto.getGp()>0){
                if(dto.getGp() == nexGp){
                    //同じGroup
                    flg=0x01;
                }else{
                    //Group 終了
                    flg=0x03;
                }
            }else{
                //
            }
            //
            if(dto instanceof CharItem){
                flg = (byte)(flg << 4 | 0x01);
                dos.write(flg);
                //
                byte[] length=ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt((int)dto.getLength()).array();
                dos.write(length);
            }else if(dto instanceof DataItem){
                flg = (byte)(flg << 4 | 0x02);
                dos.write(flg);
                //
                byte[] length=ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(dto.getLength()).array();
                dos.write(length);
            }else{
                //
            }
        }
        dos.flush();
        //項目毎の内容
        for(Item dto:this.itemList){
            if(dto instanceof CharItem){
                dos.write(((CharItem)dto).getData());
                dos.flush();
            }else{
                //
            }
        }
        //
        byte[] bytes = new byte[1024];
        int length = 1024;
        DataInputStream dis = null;
        for(Item dto:this.itemList){
            if(dto instanceof DataItem){
                DataItem dataDto=(DataItem)dto;
                dis = new DataInputStream(dataDto.getInputstream());
                while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
                    dos.write(bytes, 0, length);
                    dos.flush();
                }
                dos.flush();
                dis.close();
            }else{
                //
            }
        }
    }
}
