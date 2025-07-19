package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.domain.Color;
import mall.service.ColorService;

@Controller
public class ColorController {
	
	@Autowired
	ColorService colorService;
	
	@GetMapping("/color/list")
	@ResponseBody
	public List<Color> selectAll(){
		return colorService.selectAll();
	}
}
