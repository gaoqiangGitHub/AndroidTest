package com.example.androidtest.entity;

import java.io.Serializable;

/**
 * @author alan
 * @version ${date}${time}
 * @description
 */
public class GoodsEntity implements Serializable{
	private static final long serialVersionUID = 2071536785864593905L;
	
	/**
     * id : 1132
     * src : http://developer.api.24tidy.com/upLoads/goods/1376150537.jpg
     * oldid : 47
     * oldtype : diy
     * typeid : 4
     * typecode : coat
     * title : 衬衫-普通
     * memo : 洗涤+熨烫+免费取送
     * goodid : 10001
     * preday : 预计3天
     * status : 1
     * order : 1
     * costing : 1.00
     * price : 20.00
     * perprice : 0
     * disprice : 12
     * editFlag : 0
     */

    private String id;
    private String src;
    private String oldid;
    private String oldtype;
    private String typeid;
    private String typecode;
    private String title;
    private String memo;
    private String goodid;
    private String preday;
    private String status;
    private String order;
    private String costing;
    private String price;
    private String perprice;
    private String disprice;
    private String editFlag;
    
    public GoodsEntity() {}
    
    public GoodsEntity(String title, String memo) {
		this.title = title;
		this.memo = memo;
	}

	public void setId(String id) { this.id = id;}

    public void setSrc(String src) { this.src = src;}

    public void setOldid(String oldid) { this.oldid = oldid;}

    public void setOldtype(String oldtype) { this.oldtype = oldtype;}

    public void setTypeid(String typeid) { this.typeid = typeid;}

    public void setTypecode(String typecode) { this.typecode = typecode;}

    public void setTitle(String title) { this.title = title;}

    public void setMemo(String memo) { this.memo = memo;}

    public void setGoodid(String goodid) { this.goodid = goodid;}

    public void setPreday(String preday) { this.preday = preday;}

    public void setStatus(String status) { this.status = status;}

    public void setOrder(String order) { this.order = order;}

    public void setCosting(String costing) { this.costing = costing;}

    public void setPrice(String price) { this.price = price;}

    public void setPerprice(String perprice) { this.perprice = perprice;}

    public void setDisprice(String disprice) { this.disprice = disprice;}

    public void setEditFlag(String editFlag) { this.editFlag = editFlag;}

    public String getId() { return id;}

    public String getSrc() { return src;}

    public String getOldid() { return oldid;}

    public String getOldtype() { return oldtype;}

    public String getTypeid() { return typeid;}

    public String getTypecode() { return typecode;}

    public String getTitle() { return title;}

    public String getMemo() { return memo;}

    public String getGoodid() { return goodid;}

    public String getPreday() { return preday;}

    public String getStatus() { return status;}

    public String getOrder() { return order;}

    public String getCosting() { return costing;}

    public String getPrice() { return price;}

    public String getPerprice() { return perprice;}

    public String getDisprice() { return disprice;}

    public String getEditFlag() { return editFlag;}
}
