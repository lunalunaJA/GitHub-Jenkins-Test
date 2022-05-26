package com.spring.board.presentation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.test.TestServiceImpl;
import com.spring.board.test.TestVo;



@Controller
public class TestController {

	@Autowired
	private TestServiceImpl service;
	
	@RequestMapping("list.do")
	public String select(TestVo vo, Model model) {
		//System.out.println("확인중======>" + vo.toString());
		model.addAttribute("li", service.select());
		
		return "list.jsp";
	}
	
	@RequestMapping("upload.do")
	public String insert() {		
		return "upload.jsp";
	}
	
	@RequestMapping("uploadOk.do")
	public String uploadOk(TestVo vo, HttpServletRequest request) throws Exception {
		
		//path 설정
 		String path = request.getSession().getServletContext().getRealPath("/files/");
 		System.out.println(path);
 		
 		//실제 저장할 파일
 		MultipartFile imgUpdateFile = vo.getImgUploadFile();
 		String fileNmae = imgUpdateFile.getOriginalFilename();
 		File f = new File(path+fileNmae);
 		
 		//파일 자르기
 		String onlyFilename = "";
 		String extension = "";
 		
 		//날짜 구분하기
 		long time = System.currentTimeMillis();
 		SimpleDateFormat dayTime = new SimpleDateFormat("HHmmss");
 		String timeStr = dayTime.format(time);

 		
 		//만약 파일이 있으면
 		if(!imgUpdateFile.isEmpty()) {
 			//파일 중복 체크
 			//만약 동일한 이름의 파일이 있으면
 			if(f.exists()) {
 				onlyFilename = fileNmae.substring(0, fileNmae.indexOf("."));
 				extension = fileNmae.substring(fileNmae.indexOf(".")); 
 				fileNmae = onlyFilename + "_" + timeStr + extension ; 
 			  
 				imgUpdateFile.transferTo(new File(path+fileNmae));
 			
 			//중복 파일이 없으면
 			} else {
 				imgUpdateFile.transferTo(new File(path+fileNmae));
 			}
 		}
 		
 		//파일 저장
 		vo.setImg(fileNmae); 		 		 		
		service.insert(vo);
	
		return "list.do";
	}
}
