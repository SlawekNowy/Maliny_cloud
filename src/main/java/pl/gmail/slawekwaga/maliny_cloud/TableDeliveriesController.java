package pl.gmail.slawekwaga.maliny_cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gmail.slawekwaga.maliny_cloud.db.Klienci;
import pl.gmail.slawekwaga.maliny_cloud.db.KlienciRepo;
import pl.gmail.slawekwaga.maliny_cloud.views.*;

@Controller
public class TableDeliveriesController {

    private ViewDeliveryRepo viewDeliveryRepo;

    @GetMapping("/table_deliveries")
    public String tableClients(Model model,
                            @RequestParam(defaultValue = "1000") int entries_per_page,
                            @RequestParam(defaultValue = "1") int goto_page,
                            @RequestParam(required = false) String controlAction,
                            @RequestParam(defaultValue = "1") int curPage) {
        //action parameter name is reserved for submit form
        //TODO add page controls and work with them

        model.addAttribute("entries_per_page",entries_per_page);


        int newPage =curPage ;
        if (controlAction != null) {
            switch (controlAction) {
                case "first":
                    newPage=1;
                    break;
                case "previous":
                    newPage=curPage-1;
                    break;
                case "next":
                    newPage=curPage+1;
                    break;
                case "goto_button":
                    newPage=goto_page;
                    //last is handled after pagination
            }
        }


        model.addAttribute("pageNum",newPage);


        // first paginate

        //update the variables based on action.




                Pageable test= PageRequest.of(newPage-1,entries_per_page, Sort.by("idDostawy"));
                Page<ViewDelivery> pageObj = viewDeliveryRepo.findAll(test);
                model.addAttribute("data", pageObj);
                model.addAttribute("totalPages",pageObj.getTotalPages());
                if (controlAction!=null) {
                    if (controlAction.equals("last")|| newPage>=pageObj.getTotalPages()) {
                        model.addAttribute(pageObj.getTotalPages());
                    }
                }

        return "table_deliveries";
    }

    @Autowired
    public void setViewDeliveryRepo(ViewDeliveryRepo viewDeliveryRepo) {
        this.viewDeliveryRepo = viewDeliveryRepo;
    }
}
