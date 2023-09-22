package br.com.matuzalem.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    // REENVIAR O ITEM EDITADO
    @PostMapping("cadastroUpdate")
    public String update(CadastroDTO updatedCadastro) {
        // Encontre o cadastro original na lista e atualize os dados com base no email
        for (int i = 0; i < cadastros.size(); i++) {
            CadastroDTO cadastro = cadastros.get(i);
            if (updatedCadastro.getName().equals(cadastro.getName())) {
                cadastros.set(i, updatedCadastro);
                break; // Parar após encontrar o cadastro
            }
        }
        return "redirect:/cadastroGet";
    }

    // EDITAR O ITEM DA LISTA
    @GetMapping("/cadastroPost")
    public ModelAndView edit(@RequestParam("email") String email) {
        ModelAndView mv = new ModelAndView("edit");

        // Encontre o cadastro com o email fornecido
        CadastroDTO cadastroToEdit = cadastros.stream()
                .filter(cadastro -> email.equals(cadastro.getEmail()))
                .findFirst()
                .orElse(null);

        if (cadastroToEdit != null) {
            mv.addObject("cadastro", cadastroToEdit);
        } else {
            // Lidar com o caso em que o email não corresponde a nenhum cadastro
            // Você pode redirecionar para uma página de erro ou tratar de outra forma
        }

        return mv;
    }

}
