package com.thyago.app;

import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculadoraController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam double a, @RequestParam double b,
                           @RequestParam String op, Model model) {
        String resultado = switch (op) {
            case "+" -> formatar(a + b);
            case "-" -> formatar(a - b);
            case "*" -> formatar(a * b);
            case "/" -> b == 0 ? "Nao da para dividir por zero" : formatar(a / b);
            default -> "Operacao invalida";
        };
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("op", op);
        model.addAttribute("resultado", resultado);
        return "index";
    }

    private String formatar(double valor) {
        return BigDecimal.valueOf(valor).stripTrailingZeros().toPlainString();
    }
}
