package Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import Util.impl.CharItem;
import Util.impl.DataItem;
import Util.impl.Item;

public class ReceUtil {
    //
    private ReceUtil(){}
    //
    private List<Item>itemList=null;
    //
    private List<List<Item>>itemGpList=null;
    //
    public static ReceUtil builderReceUtil(){
        ReceUtil util=new ReceUtil();
        util.itemList=new ArrayList<Item>();
        //
        util.itemGpList=new ArrayList<List<Item>>();
        return util;
    }
    //
    public void parseHead(SocketChannel socketChannel)throws Exception{
        this.parseHead(socketChannel.socket().getInputStream());
    }
    public void parseHead(InputStream dis)throws Exception{
        //head数 4BTYE
        byte[] itemCountB=new byte[4];
        dis.read(itemCountB);
        int itemCount=ByteBuffer.wrap(itemCountB).getInt();
        //
        int gpNo=1;
        List<Item> subItemList=new ArrayList<Item>();
        //
        for(int i=0;i<itemCount;i++){
            //先頭(1バイト)
            byte[] flg=new byte[1];
            dis.read(flg);
            //
            Item dto=null;
            int kbn=flg[0] & 0x0F;
            if(kbn == (int)0x01){
                dto=new CharItem();
                byte[] lengthB=new byte[4];
                dis.read(lengthB);
                dto.setLength(ByteBuffer.wrap(lengthB).getInt());
            }else if(kbn == (int)0x02){
                dto=new DataItem();
                byte[] lengthB=new byte[8];
                dis.read(lengthB);
                dto.setLength(ByteBuffer.wrap(lengthB).getLong());
            }else{
                //
            }
            //
            subItemList.add(dto);
            if(flg[0] >> 4 == 0x01){
                dto.setGp(gpNo);
            }else if(flg[0] >> 4 == 0x03){
                dto.setGp(gpNo);
                this.itemList.addAll(subItemList);
                this.itemGpList.add(subItemList);
                subItemList=new ArrayList<Item>();
                gpNo++;
            }else{
                //
                this.itemList.addAll(subItemList);
                subItemList=new ArrayList<Item>();
            }
            //
        }
        //
        for(Item dto:this.itemList){
            if(dto instanceof CharItem){
                byte[] data=new byte[(int)dto.getLength()];
                dis.read(data);
                ((CharItem) dto).setData(data);
            }else{
                //
            }
        }
        //
    }
    public List<Item>parseFile(SocketChannel socketChannel,int index,OutputStream dos)throws IOException{
        return this.parseFile(socketChannel.socket().getInputStream(), index, dos);
    }
    public List<Item>parseFile(InputStream dis,int index,OutputStream dos)throws IOException{
        if(index<0 || index > this.itemGpList.size()-1){
            //error
            return null;
        }else{
            for(int i=0;i<=index;i++){
                if(i<index){
                    this.parseFile(dis, this.itemGpList.get(i));
                }else{
                    this.parseFile(dis, this.itemGpList.get(i), dos);
                }
            }
            return this.itemGpList.get(index);
        }
    }
    //
    private void parseFile(InputStream dis,List<Item>itemList,OutputStream dos)throws IOException{
        for(Item dto:itemList){
            if(dto instanceof CharItem){
                //
            }else if(dto instanceof DataItem){
                DataItem dataDto=(DataItem)dto;
                if(dataDto.isReadFlg()){
                    //
                }else{
                    dataDto.setReadFlg(true);
                    long meLength=dataDto.getLength();
                    byte[] bytes = null;
                    long readLength=0;
                    //
                    if((meLength-readLength)<1024){
                        bytes = new byte[(int)(meLength-readLength)];
                    }else{
                        bytes = new byte[1024];
                    }
                    //
                    int length = bytes.length;
                    //
                    while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
                        readLength+=length;
                        if(dos!=null){
                            dos.write(bytes, 0, length);
                            dos.flush();
                        }
                        if(meLength<=readLength){
                            break;
                        }else{
                            if((meLength-readLength)<1024){
                                bytes = new byte[(int)(meLength-readLength)];
                            }else{
                                bytes = new byte[1024];
                            }
                        }
                    }
                }
            }else{
                //
            }
        }
    }
    private void parseFile(InputStream dis,List<Item>itemList)throws IOException{
        this.parseFile(dis,itemList,null);
    }//
}
