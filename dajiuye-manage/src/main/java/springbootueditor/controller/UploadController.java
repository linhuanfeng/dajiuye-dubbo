package springbootueditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/all")
    public String upload(){
        return "FileUpload";
    }
}
