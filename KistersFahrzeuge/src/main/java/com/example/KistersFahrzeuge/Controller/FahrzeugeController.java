package com.example.KistersFahrzeuge.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.KistersFahrzeuge.Model.Fahrzeug;
import com.example.KistersFahrzeuge.Model.FahrzeugType;
import com.example.KistersFahrzeuge.Repository.FahrzeugeRepository;

@RestController
public class FahrzeugeController {
	
	@Autowired
	private FahrzeugeRepository FahrzeugRepo;
	
	private int calcKnoten(int geschwindigkeit) {
		return (int) ((geschwindigkeit * 2) - (geschwindigkeit * 0.1));
	}
	
	@PostMapping("/addFahrzeug")
	public ResponseEntity<Fahrzeug> addFahrzeug(@RequestBody Fahrzeug fahrzeug){
		if (fahrzeug.getType() == FahrzeugType.AUTO) {
			fahrzeug.setGeschwindigkeitsEinheit("km/h");
		}
		if (fahrzeug.getType() == FahrzeugType.SCHIFF) {
			fahrzeug.setMaxGeschindigkeit(calcKnoten(fahrzeug.getMaxGeschindigkeit()));
			fahrzeug.setGeschwindigkeitsEinheit("Knoten");
			fahrzeug.setFarbe(null);
		}
		
		Fahrzeug fahrzeugObj = FahrzeugRepo.save(fahrzeug);
		
		return new ResponseEntity<Fahrzeug>(fahrzeugObj, HttpStatus.OK);
	}
	
	@GetMapping("/viewAllFahrzeug")
	public ResponseEntity<List<Fahrzeug>> viewAllFahrzeug(){
		try {
			List<Fahrzeug> FahrzeugList = new ArrayList<>();
			FahrzeugRepo.findAll().forEach(FahrzeugList::add);
			
			if (FahrzeugList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(FahrzeugList, HttpStatus.OK);
		}
		catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/viewFahrzeugById/{Id}")
	public ResponseEntity<Fahrzeug> viewFahrzeugById(@PathVariable int Id){
      Optional<Fahrzeug> FahrzeugData = FahrzeugRepo.findById(Id);
		
		if (FahrzeugData.isPresent()) {
			return new ResponseEntity<>(FahrzeugData.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/updateFahrzeugById/{Id}")
	public ResponseEntity<Fahrzeug> updateFahrzeugById(@PathVariable int Id, @RequestBody Fahrzeug newFahrzeugData){
       Optional<Fahrzeug> OldFahrzeugData = FahrzeugRepo.findById(Id);
		
		if (OldFahrzeugData.isPresent()) {
			Fahrzeug updatedFahrzeugData = OldFahrzeugData.get();
			updatedFahrzeugData.setBezeichnung(newFahrzeugData.getBezeichnung());
			updatedFahrzeugData.setMaxGeschindigkeit(newFahrzeugData.getMaxGeschindigkeit());
			if (newFahrzeugData.getType() == FahrzeugType.SCHIFF) {
				updatedFahrzeugData.setGeschwindigkeitsEinheit("Knoten");
				updatedFahrzeugData.setFarbe(null);
			}
			if (newFahrzeugData.getType() == FahrzeugType.AUTO) {
				updatedFahrzeugData.setGeschwindigkeitsEinheit("km/h");
				updatedFahrzeugData.setFarbe(newFahrzeugData.getFarbe());
			}
				
			Fahrzeug FahrzeugObj = FahrzeugRepo.save(updatedFahrzeugData);
			return new ResponseEntity<>(FahrzeugObj, HttpStatus.OK);
			}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@DeleteMapping("/DeleteFahrzeug/{Id}")
	public ResponseEntity<Object> DeleteFahrzeug(@PathVariable int Id){
		FahrzeugRepo.deleteById(Id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
