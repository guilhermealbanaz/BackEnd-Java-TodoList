package br.com.guilhermealbnz.todolist.controllerExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstRoute")
public class MyFirstController {

    /**
     * Métodos de acesso do HTTP
     * GET - Buscar uma informação
     * POST - Adicionar um dado/informação
     * PUT - Alterar um dado/informação
     * DELETE - Deletar um dado/informação
     * PATCH - Alterar somente uma parte do dado/informação
     */

    // Método funcionalidade de uma classe
    @GetMapping("/firstMethod")
    public String getFirstMessage() {
        return "Funcionou";
    }
}
