package it.dstech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Ingrediente;
import it.dstech.model.Ricetta;
import it.dstech.repositories.IngredienteRepository;
import it.dstech.repositories.RicettaRepository;

@Controller
public class RicettaCotroller {

	@Autowired
	private RicettaRepository ricettaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository; 
	
//	Registrazione ricetta e salvataggio nel repository.
	
	@GetMapping("/registraRicetta")
	public String registraRicettaForm(Model model) {
		model.addAttribute("listaIngredienti", ingredienteRepository.findAll());
		model.addAttribute("ricetta", new Ricetta());
		return "add-ricetta";
	}
	
	@GetMapping("/addIngrediente")
	public String addIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "add-ingrediente";
	}
	
    @PostMapping("/addricetta")
    public String addRicetta(Ricetta ricetta, int[] ingredienteId, BindingResult result, Model model) {
    	
    	List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
    	
    	for (Ingrediente ingrediente : ingredienteRepository.findAll()) {
    		for (int idChecked : ingredienteId) {
    			if(ingrediente.getId() == idChecked) {
    				ingredienti.add(ingrediente);
    			}
    		}
    	}
    	ricetta.setListaIngredienti(ingredienti);
    	
    	if (result.hasErrors()) {
            return "add-ricetta";
        }
        double costo = 0;
        for (Ingrediente ingrediente : ricetta.getListaIngredienti()) {
        	for (Ingrediente ingredienteRepo : ingredienteRepository.findAll()) {
        		if(ingrediente.getNome().equals(ingredienteRepo.getNome())) {
        			costo += ingrediente.getCostoing();
        		}else {
        			return "create-ingrediente";
        		}
        	}
		}
        ricetta.setCostoRicetta((costo*110)/100);
        ricettaRepository.save(ricetta);
        model.addAttribute("ricetta", ricettaRepository.findAll());
        return "index";
    }
    
    
    
//  Modifica l'ricetta
    
    @GetMapping("/editRicetta/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Ricetta ricetta = ricettaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ricetta Id:" + id));
        model.addAttribute("ricetta", ricetta);
        return "update-ricetta";
    }
    
    @PostMapping("/updateRicetta/{id}")
    public String updateDolce(@PathVariable("id") long id,@RequestBody  Ricetta ricetta, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	ricetta.setId(id);
            return "update-ricetta";
        }
        
        ricettaRepository.save(ricetta);
        model.addAttribute("ricetta", ricettaRepository.findAll());
        return "index";
    }
    
//  Elimina l'ricetta
    
    @GetMapping("/deleteRicetta/{id}")
    public String deleteDolce(@PathVariable("id") long id, Model model) {
    	Ricetta ricetta = ricettaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dolce Id:" + id));
        ricettaRepository.delete(ricetta);
        model.addAttribute("ricetta", ricettaRepository.findAll());
        return "index";
    }
    
    
	
}
