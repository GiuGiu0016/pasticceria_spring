package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.dstech.model.Dolce;
import it.dstech.repositories.DolceRepository;
import it.dstech.repositories.RicettaRepository;

@Controller
public class DolceController {

	@Autowired
	private DolceRepository dolceRepository;
	
	@Autowired
	private RicettaRepository ricettaRepository;
	
//	Registrazione dolce e salvataggio nel repository.
	
	@GetMapping("/registraDolce")
	public String registraDolceForm(Model model) {
		//model.addAttribute("listaDolci", dolceRepository.findAll());
		model.addAttribute("listaRicette", ricettaRepository.findAll());
		model.addAttribute("dolce", new Dolce());
		return "add-dolce";
	}
	
    @PostMapping("/adddolce")
    public String addDolce(Dolce dolce, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-dolce";
        }
        dolce.setCostoDolce((dolce.getRicetta().getCostoRicetta()*120)/100);
        dolceRepository.save(dolce);
        model.addAttribute("dolce", dolceRepository.findAll());
        return "index";
    }
    
//  Modifica l'cliente
    
    @GetMapping("/editDolce/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Dolce dolce = dolceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dolce Id:" + id));
        model.addAttribute("dolce", dolce);
        return "update-dolce";
    }
    
    @PostMapping("/updateDolce/{id}")
    public String updateDolce(@PathVariable("id") long id,@RequestBody  Dolce dolce, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	dolce.setId(id);
            return "update-dolce";
        }
        
        dolceRepository.save(dolce);
        model.addAttribute("dolce", dolceRepository.findAll());
        return "index";
    }
    
//  Elimina l'ingrediente
    
    @GetMapping("/deleteDolce/{id}")
    public String deleteDolce(@PathVariable("id") long id, Model model) {
    	Dolce dolce = dolceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dolce Id:" + id));
        dolceRepository.delete(dolce);
        model.addAttribute("dolce", dolceRepository.findAll());
        return "index";
    }
	
}
