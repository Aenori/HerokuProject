package wcsdata.xmen.controller.crud_html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wcsdata.xmen.entity.CerebookMedia;
import wcsdata.xmen.repository.CerebookMediaRepository;
import wcsdata.xmen.services.HostingService;
import wcsdata.xmen.services.MediaService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("allElements", mediaService.getMediaList());
        model.addAttribute("categories", CerebookMedia.Type.class.getEnumConstants());

        return "media/getAll";
    }

    @PostMapping("")
    public String create(
            @RequestParam("mediaFile") MultipartFile file,
            @RequestParam("mediaType") String type,
            RedirectAttributes redirectAttributes
    ) {
        String filename = file.getOriginalFilename();

        try {
            mediaService.uploadMedia(
                    filename,
                    file.getInputStream(),
                    file.getSize()
            );
        }
        catch(IOException e) {
            redirectAttributes.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/media";
    }
}
