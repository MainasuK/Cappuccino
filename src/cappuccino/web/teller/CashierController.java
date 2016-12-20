package cappuccino.web.teller;

import cappuccino.protocol.CMKCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.security.Principal;

/**
 * Created by MainasuK on 2016-12-16.
 */
@Controller
@RequestMapping("/cashier")
public class CashierController {

    private CMKCommodityRepository CMKCommodityRepository;

    @Autowired
    public CashierController(CMKCommodityRepository CMKCommodityRepository) {
        this.CMKCommodityRepository = CMKCommodityRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String cashier(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("commodities", CMKCommodityRepository.commodities());
        model.addAttribute("currentCommodity", CMKCommodityRepository.currentCommodity());
        model.addAttribute("totalQuantity", CMKCommodityRepository.totalQuantity());
        model.addAttribute("totalPrice", CMKCommodityRepository.totalPrice());

        return "teller/cashier";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear() {
        CMKCommodityRepository.clear();
        return "redirect: /cashier";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(String pay) {

        try {
            BigDecimal decimal = new BigDecimal(pay);
            BigDecimal change = decimal.subtract(CMKCommodityRepository.totalPrice());
            if (change.doubleValue() >= 0) {
                CMKCommodityRepository.pay();
            } else {
                // TODO:
            }

        } catch (NumberFormatException exception) {
            // TODO:
        }

        return "redirect: /cashier";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String scan(String search) {

        System.out.println(search);
        CMKCommodityRepository.save(search);

        return "redirect: /cashier";
    }
}
