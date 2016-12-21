package cappuccino.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by MainasuK on 2016-12-16.
 */
public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception {
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();

        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
}
