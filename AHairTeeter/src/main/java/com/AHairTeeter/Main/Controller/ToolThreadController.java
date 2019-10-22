package com.AHairTeeter.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.AHairTeeter.Main.ThreadExecutionMethod.ThreadServiceImpl;

import com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl.ToolServiceImpl;
import com.AHairTeeter.Tool.Tool;

@Controller
@RequestMapping("/Thread")
public class ToolThreadController {
	@Autowired	
	public ToolServiceImpl ToolServiceImpl;
	
	@Autowired	
	public ThreadServiceImpl ThreadServiceImpl;
	
	Tool Tool =new Tool();
	
	@RequestMapping(value = "/Testlist", method = RequestMethod.GET)
	public String Testlist(Model model) {
		model.addAttribute("text", "执行多线程Testlist");
		ThreadServiceImpl.middleman(0);
		return "redirect:/AHairTeeter/navigation";
	}
	
	@RequestMapping(value = "/Moeimg", method = RequestMethod.GET)
	public String Moeimg(Model model) {
		model.addAttribute("text", "执行多线程Moeimg");
		ThreadServiceImpl.middleman(21);
		return "redirect:/AHairTeeter/navigation";
	}
	
	@RequestMapping(value = "/SssGif", method = RequestMethod.GET)
	public String SssGif(Model model) {
		model.addAttribute("text", "执行多线程SssGif");
		ThreadServiceImpl.middleman(20);
		return "redirect:/AHairTeeter/navigation";
	}
	
	@RequestMapping(value = "/Xvideos", method = RequestMethod.GET)
	public String Xvideos(Model model) {
		model.addAttribute("text", "执行多线程Xvideos");
		ThreadServiceImpl.middleman(22);
		return "redirect:/AHairTeeter/navigation";
	}
	
	@RequestMapping(value = "/Kr36", method = RequestMethod.GET)
	public String Kr36(Model model) {
		model.addAttribute("text", "执行多线程Kr36");
		ThreadServiceImpl.middleman(24);
		return "redirect:/AHairTeeter/navigation";
	}
	
	
	
	
	
}
