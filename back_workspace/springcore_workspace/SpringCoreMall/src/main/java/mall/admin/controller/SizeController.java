package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.domain.Size;
import mall.service.SizeService;

@Controller
public class SizeController {
	@Autowired
	SizeService sizeService;
	
	@GetMapping("/size/list")
	@ResponseBody
	public List<Size> selectAll(){
		return sizeService.selectAll();
	}
}
