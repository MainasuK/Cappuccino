package cappuccino.web.stockkeeper;

import cappuccino.protocol.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private CommodityRepository commodityRepository;

    @Autowired
    public WarehouseController(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String warehouse(Model model, Principal principal) {
        model.addAttribute("commodities", commodityRepository.commodities());
        model.addAttribute("username", principal.getName());
        return "stockKeeper/warehouse";
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public String disable(Integer commodityID, Boolean flag) {

        if (flag) {
            commodityRepository.disable(commodityID);
        } else {
            commodityRepository.enable(commodityID);
        }

        return "redirect: /warehouse";
    }

    @RequestMapping(value = "/procurement", method = RequestMethod.POST)
    public String procurement(Integer commodityID, String quantity) {
        try {
            Integer q = Integer.parseInt(quantity);
            commodityRepository.addQuantity(commodityID, q);

        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            // TODO:
        }

        return "redirect: /warehouse";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String barCode, String name, Double price) {
        commodityRepository.add(barCode, name, price);

        return "redirect: /warehouse";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Integer commodityID, String barCode, String name, Double price) {

        commodityRepository.modify(commodityID, barCode, name, price);

        return "redirect: /warehouse";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, String keyword) {
        model.addAttribute("commodities", commodityRepository.search(keyword));
        model.addAttribute("isSearching", true);

        return "stockKeeper/warehouse";
    }

}
