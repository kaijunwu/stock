package com.x.stock.bean;

/**
 * Title: Stock
 * ProjectName demo
 * Description:
 * author wukaijun
 * date 2020/9/28 23:23
 */
public class StockBase {
    protected int id;
    protected String code;
    protected String codeFull;
    protected String name;

    public StockBase(){

    }

    public StockBase(int id,String code,String codeFull,String name){
        this.id = id;
        this.code = code;
        this.codeFull = codeFull;
        this.name = name;
    }

    public StockBase(int id,String code,String name){
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCodeFull() {
        return codeFull;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCodeFull(String codeFull) {
        this.codeFull = codeFull;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
