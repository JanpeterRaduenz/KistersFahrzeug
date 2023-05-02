package com.example.KistersFahrzeuge.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Fahrzeuge")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Fahrzeug {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private String Bezeichnung;
	
	private int MaxGeschindigkeit;
	
	@Enumerated(EnumType.STRING)
	private FahrzeugType type;
	
	
	private String GeschwindigkeitsEinheit;
	
	private String Farbe;

	public String getBezeichnung() {
		return Bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		Bezeichnung = bezeichnung;
	}

	public int getMaxGeschindigkeit() {
		return MaxGeschindigkeit;
	}

	public void setMaxGeschindigkeit(int maxGeschindigkeit) {
		MaxGeschindigkeit = maxGeschindigkeit;
	}
	
	
	public FahrzeugType getType() {
		return type;
	}
	
	public void setType(FahrzeugType type) {
		this.type = type;
	}

	public String getGeschwindigkeitsEinheit() {
		return GeschwindigkeitsEinheit;
	}

	public void setGeschwindigkeitsEinheit(String geschwindigkeitsEinheit) {
		GeschwindigkeitsEinheit = geschwindigkeitsEinheit;
	}

	public String getFarbe() {
		return Farbe;
	}

	public void setFarbe(String farbe) {
		Farbe = farbe;
	}

}
