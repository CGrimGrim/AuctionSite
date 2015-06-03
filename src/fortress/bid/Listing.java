package fortress.bid;

import java.time.*;

public class Listing {
	
	private int id;
	private int sellerid;
	private String itemName;
	private String description;
	private byte condition;
	private byte category;
	private double reserveAmount;
	private LocalDate endDate;
	private LocalTime endTime;
	private byte status;
	
	public Listing(int id, int sid, String itname, String desc, byte cond, byte cat, double reserveAmt, LocalDate endD, LocalTime endT, byte stat){
		this.id = id;
		sellerid = sid;
		itemName = itname;
		description = desc;
		condition = cond;
		category = cat;
		reserveAmount = reserveAmt;
		endDate = endD;
		endTime = endT;
		status = stat;
		
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getCondition() {
		return condition;
	}

	public void setCondition(byte condition) {
		this.condition = condition;
	}

	public byte getCategory() {
		return category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	public double getReserveAmount() {
		return reserveAmount;
	}

	public void setReserveAmount(double reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
