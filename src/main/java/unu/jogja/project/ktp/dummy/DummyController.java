/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unu.jogja.project.ktp.dummy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ELFANDIRA ADE
 */
@Controller
public class DummyController {

    DummyJpaController dummyController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();

    @RequestMapping("/read")
    @ResponseBody
    public List<Dummy> getDummy() {
        try {
            data = dummyController.findDummyEntities();
        } catch (Exception e) {
        }
        return data;
    }

    @RequestMapping("/create")
    public String createDummy() {
        return "dummy/create";
    }

    @PostMapping(value = "/newdata", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String newDummy(@RequestParam("file") MultipartFile file, HttpServletRequest data) throws ParseException, Exception {

        Dummy dumdata = new Dummy();
        String id = data.getParameter("id");
        int iid = Integer.parseInt(id);
        String tanggal = data.getParameter("");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] image = file.getBytes();
        dumdata.setId(iid);
        dumdata.setTanggal(date);
        dumdata.setGambar(image);

        dummyController.create(dumdata);
        return "dummy/create";
    }
}
