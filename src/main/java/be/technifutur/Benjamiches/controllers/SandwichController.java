package be.technifutur.Benjamiches.controllers;

import be.technifutur.Benjamiches.model.dto.SandwichDTO;
import be.technifutur.Benjamiches.services.SandwichService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sandwich")
public class SandwichController {

    private final SandwichService sandwichServ;

    public SandwichController(SandwichService sandwichServ) {
        this.sandwichServ = sandwichServ;
    }

    @GetMapping("/all")
    public List<SandwichDTO> getAllSandwiches() {
        return sandwichServ.getAll();
    }

    @GetMapping("/filter_diet/{diet}")
    public List<SandwichDTO> getSandwichesByDiet(@PathVariable long diet) {
        return sandwichServ.getByDiet(diet);
    }
}
