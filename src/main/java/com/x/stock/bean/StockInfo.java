package com.x.stock.bean;


/**
 * Title: Message
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 15:27
 */
public class StockInfo extends StockBase {

    /**
     * 2020-09-28 14:20:00
     */
    private String day;

    /**
     * 15.300
     */
    private float open;

    private float high;

    private float low;

    private float close;
    /**
     * 516911
     */
    private long volume;

    private float maPrice5;

    private long maVolume5;


    public StockInfo(String day,float open,float high,float low,float close,long volume,float maPrice5,long maVolume5) {
        this.day = day;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.maPrice5 = maPrice5;
        this.maVolume5 = maVolume5;
    }

    public StockInfo(int id, String code, String codeFull, String name) {
        super(id, code, codeFull, name);
    }

    public StockInfo(int id, String code, String name) {
        super(id, code, name);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public float getMaPrice5() {
        return maPrice5;
    }

    public void setMaPrice5(float maPrice5) {
        this.maPrice5 = maPrice5;
    }

    public long getMaVolume5() {
        return maVolume5;
    }

    public void setMaVolume5(long maVolume5) {
        this.maVolume5 = maVolume5;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "day='" + day + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", maPrice5=" + maPrice5 +
                ", maVolume5=" + maVolume5 +
                '}';
    }

    public void initBase(StockBase stock) {
        this.id = stock.getId();
        this.code = stock.getCode();
        this.codeFull = stock.getCodeFull();
        this.name = stock.getName();
    }
}
