package com.example.demo.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transactions { 
	@Id
	@GeneratedValue
	private Integer id;
	@Column(columnDefinition = "bigint default 00")
	private Long racno = 0L;
	private TransactionType trtype;
	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "acno")
	@JsonIgnore
	private Users user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Long getRacno() {
		return racno;
	}
	public void setRacno(Long racno) {
		this.racno = racno;
	}
	public TransactionType getTrtype() {
		return trtype;
	}
	public void setTrtype(TransactionType trtype) {
		this.trtype = trtype;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", racno=" + racno + ", trtype=" + trtype + ", amount=" + amount + ", time="
				+ time + ", user=" + user + "]";
	}
	
	
	
}
