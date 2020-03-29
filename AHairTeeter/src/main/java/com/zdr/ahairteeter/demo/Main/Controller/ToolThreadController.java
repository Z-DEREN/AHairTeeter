package com.zdr.ahairteeter.demo.Main.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.ThreadServiceImpl;
import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolServiceImpl.ToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
@RequestMapping("/Thread")
public class ToolThreadController {
	@Autowired
	public ToolServiceImpl ToolServiceImpl;
	@Autowired
	public ThreadServiceImpl ThreadServiceImpl;

	@RequestMapping(value = "/Testlist", method = RequestMethod.GET)
	public Object Testlist(ModelAndView model) {
		model.addObject("text", "执行多线程Testlist");
		ThreadServiceImpl.middleman(0);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/Moeimg", method = RequestMethod.GET)
	public Object Moeimg(ModelAndView model) {
		model.addObject("text", "执行多线程Moeimg");
		ThreadServiceImpl.middleman(21);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/SssGif", method = RequestMethod.GET)
	public Object SssGif(ModelAndView model) {
		model.addObject("text", "执行多线程SssGif");
		ThreadServiceImpl.middleman(20);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/Xvideos", method = RequestMethod.GET)
	public Object Xvideos(ModelAndView model) {
		model.addObject("text", "执行多线程Xvideos");
		ThreadServiceImpl.middleman(22);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/Kr36", method = RequestMethod.GET)
	public Object Kr36(ModelAndView model) {
		model.addObject("text", "执行多线程Kr36");
		ThreadServiceImpl.middleman(24);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/ChangKu", method = RequestMethod.GET)
	public Object ChangKu(ModelAndView model) {
		model.addObject("text", "执行多线程ChangKu");
		ThreadServiceImpl.middleman(23);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

	@RequestMapping(value = "/alone_use_way")
	public ModelAndView alone_use_way(ModelAndView model) {
		model.addObject("text", "进入alone_use_way");
		model.setViewName("main/alone_use_way/alone_use_way");
		return model;
	}

	@RequestMapping(value = "/typeurl")
	public ModelAndView XvideosMORE(@RequestParam(value = "typenum", required = false) String typenum,
			@RequestParam(value = "Xvideosurl", required = false) String Xvideosurl, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		System.out.println(typenum);
		switch (typenum) {
		case "GIFSSS":
			ThreadServiceImpl.alone_use_way("20", Xvideosurl);
			model.addObject("text", "执行GIFSSS推荐");
			break;
		case "volvo_alone":
			ThreadServiceImpl.alone_use_way("22", Xvideosurl);
			model.addObject("text", "执行Xvideos推荐");
			break;
			
		case "volvo_listings_alone":
			ThreadServiceImpl.alone_use_way("22_1", Xvideosurl);
			model.addObject("text", "执行Xvideos搜索");
			break;
			
		case "video_Reco_multi":
			ThreadServiceImpl.alone_use_way("22_2", Xvideosurl);
			model.addObject("text", "执行Xvideos大量推荐");
			break;
		case "video_Reco_alone":
			ThreadServiceImpl.alone_use_way("22_3", Xvideosurl);
			model.addObject("text", "执行Xvideos单独");
			break;
		case "KUkuManHua":
			ThreadServiceImpl.alone_use_way("25", Xvideosurl);
			model.addObject("text", "执行漫画下载");
			break;
		default:
			break;
		}
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}

}
