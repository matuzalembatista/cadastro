package br.com.matuzalem.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.matuzalem.cadastro.dto.CadastroDTO;

@Controller
public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();

    // COLETAR OS DADOS
    @PostMapping("cadastroPost")
    public String doPost(CadastroDTO dto, Model model) {
        cadastros.add(dto);
        return doGet(model);
    }

    // SALVAR OS DADOS EM LISTA
    @RequestMapping("cadastroGet")
    public String doGet(Model model) {
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }

    // REMOVER O ITEM DA LISTA
    @GetMapping("/cadastroDelete")
    public String delete(@RequestParam("name") String name) {
        cadastros.removeIf(cadastro -> name.equals(cadastro.getName()));
        return "redirect:/cadastroGet";
    }




}
