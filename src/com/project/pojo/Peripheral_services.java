package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 周边服务
 * @author Administrator
 *
 */
@Entity
@Table(name = "peripheral_services")
public class Peripheral_services extends BaseEntity {
	private int id;
	private int wifi=0;			//wifi
	private int equipment=0;	//器材
	private int locker_room=0;	//更衣室
	private int lockers=0;		//储物柜
	private int shower=0;		//淋浴
	private int vip_room=0;		//贵宾室
	private int equipment_shop=0;   //装备店
	private int food=0;			//食品
	private int parking_lot=0;	//停车场
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	@Column(name = "wifi")
	public int getWifi() {
		return wifi;
	}
	@Column(name = "equipment")
	public int getEquipment() {
		return equipment;
	}
	@Column(name = "locker_room")
	public int getLocker_room() {
		return locker_room;
	}
	@Column(name = "lockers")
	public int getLockers() {
		return lockers;
	}
	@Column(name = "shower")
	public int getShower() {
		return shower;
	}
	@Column(name = "vip_room")
	public int getVip_room() {
		return vip_room;
	}
	@Column(name = "equipment_shop")
	public int getEquipment_shop() {
		return equipment_shop;
	}
	@Column(name = "food")
	public int getFood() {
		return food;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setWifi(int wifi) {
		this.wifi = wifi;
	}
	public void setEquipment(int equipment) {
		this.equipment = equipment;
	}
	public void setLocker_room(int locker_room) {
		this.locker_room = locker_room;
	}
	public void setLockers(int lockers) {
		this.lockers = lockers;
	}
	public void setShower(int shower) {
		this.shower = shower;
	}
	public void setVip_room(int vip_room) {
		this.vip_room = vip_room;
	}
	public void setEquipment_shop(int equipment_shop) {
		this.equipment_shop = equipment_shop;
	}
	public void setFood(int food) {
		this.food = food;
	}
	
	@Column(name = "parking_lot")
	public int getParking_lot() {
		return parking_lot;
	}
	public void setParking_lot(int parking_lot) {
		this.parking_lot = parking_lot;
	}
	
	
	
}
