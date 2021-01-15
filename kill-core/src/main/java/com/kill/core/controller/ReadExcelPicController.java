package com.kill.core.controller;

import com.kill.core.service.IReadExcelPicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/01/13 17:00:41
 */
@RestController
@RequestMapping("/read")
@AllArgsConstructor
public class ReadExcelPicController {

    private IReadExcelPicService readExcelPicService;


    @GetMapping("/excel/pic")
    public String readExcelPic(){
        return readExcelPicService.read();
    }


}
