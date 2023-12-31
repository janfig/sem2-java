package com.example.lab4.controllers;


import com.example.lab4.beans.Pracownik;
import com.example.lab4.dao.PracownikDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao; //wstrzyknięcie dao z pliku XML

    /* Wynikiem działania metody jest przekazanie danych w modelu do
     * strony widoku addForm.jsp, która wyświetla formularz
     * wprowadzania danych, a „command” jest zastrzeżonym atrybutem
     * żądania, umożliwiającym wyświetlenie danych obiektu pracownika
     * w formularzu.
     */
    @RequestMapping("/addForm")
    public String showform(Model m) {
        m.addAttribute("command", new Pracownik());
        return "addForm"; //przekiekrowanie do addForm.jsp
    }

    /* Metoda obsługuje zapis pracownika do BD. @ModelAttribute
     * umozliwia pobranie danych z żądania do obiektu pracownika.
     * Jawnie wskazano RequestMethod.POST (domyślnie jest to GET)
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr) {
        dao.save(pr);
        //przekierowanie do endpointa o URL: /viewAll
        return "redirect:/viewAll";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model m) {
        Pracownik p = dao.getPracownikById(id);
        //przekierowanie do endpointa o URL: /viewAll
        m.addAttribute("command", p);
        return "editForm"; //przekiekrowanie do addForm.jsp
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("pr") Pracownik pr) {
        dao.update(pr);
        //przekierowanie do endpointa o URL: /viewAll
        return "redirect:/viewAll";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/viewAll";
    }

    /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */
    @RequestMapping("/viewAll")
    public String viewAll(Model m) {
        List<Pracownik> list = dao.getAll();
        m.addAttribute("list", list);
        return "viewAll"; //przejście do widoku viewAll.jsp
    }
}
