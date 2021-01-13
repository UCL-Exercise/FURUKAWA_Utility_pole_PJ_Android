package Util.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CharItem extends Item{
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public CharItem(){
    }
    public CharItem(long value){
        ByteBuffer bbuff=ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(value);
        byte[] bytes=bbuff.array();
        this.setData(bytes);
        this.setLength(bytes.length);
    }
    public CharItem(int value){
        ByteBuffer bbuff=ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value);
        byte[] bytes=bbuff.array();
        this.setData(bytes);
        this.setLength(bytes.length);
    }
    public CharItem(String value)throws Exception{
        ByteBuffer bbuff=ByteBuffer.wrap(value.getBytes("UTF-8")).order(ByteOrder.BIG_ENDIAN);
        byte[] bytes=bbuff.array();
        this.setData(bytes);
        this.setLength(bytes.length);
    }
}
