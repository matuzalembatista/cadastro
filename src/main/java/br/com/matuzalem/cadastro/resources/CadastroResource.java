package br.com.matuzalem.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.matuzalem.cadastro.dto.CadastroDTO;

@Controller
public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();

    @PostMapping("cadastroPost")
    public String doPost(CadastroDTO dto, Model model) {
        cadastros.add(dto);
        return doGet(model);
        
    }

    @RequestMapping("cadastroGet")
    public String doGet( Model model) {
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }
}
