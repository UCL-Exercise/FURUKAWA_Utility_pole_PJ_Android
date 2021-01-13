package Util.impl;

import java.io.InputStream;

public class DataItem extends Item{
    private InputStream inputstream;
    private boolean readFlg;
    public InputStream getInputstream() {
        return inputstream;
    }
    public void setInputstream(InputStream inputstream) {
        this.inputstream = inputstream;
    }
    public boolean isReadFlg() {
        return readFlg;
    }
    public void setReadFlg(boolean readFlg) {
        this.readFlg = readFlg;
    }
    //
    public DataItem(InputStream dis,long length){
        this.inputstream=dis;
        this.readFlg=false;
        this.setLength(length);
    }
    public DataItem(){

    }
}
