package Pojo;

public class MyCartPojo {

	int prodId;
	String prodName;
	String prodCategory;
	String prodPrice;
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	@Override
	public String toString() {
		return "MyCartPojo [prodId=" + prodId + ", prodName=" + prodName + ", prodCategory=" + prodCategory
				+ ", prodPrice=" + prodPrice + "]";
	}
	public MyCartPojo(int prodId, String prodName, String prodCategory, String prodPrice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodCategory = prodCategory;
		this.prodPrice = prodPrice;
	}
	
	
	
}
