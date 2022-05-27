package com.spring.board.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String select(TestVo vo, Model model, HttpServletResponse response) {
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
 		String fileName = imgUpdateFile.getOriginalFilename();
 		File f = new File(path+fileName);
 		
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
 				onlyFilename = fileName.substring(0, fileName.indexOf("."));
 				extension = fileName.substring(fileName.indexOf(".")); 
 				fileName = onlyFilename + "_" + timeStr + extension ; 
 			  
 				imgUpdateFile.transferTo(new File(path+fileName));
 			
 			//중복 파일이 없으면
 			} else {
 				imgUpdateFile.transferTo(new File(path+fileName));
 			}
 		}
 		
 		//파일 저장
 		vo.setImg(fileName); 		 		 		
		service.insert(vo);
	
		return "list.do";
	}
	
	@RequestMapping("download.do")
	public void download(TestVo vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//path 설정
 		String path = request.getSession().getServletContext().getRealPath("/files/");
 		System.out.println(path);
 		
 		//파일명 받아오기
 		String fileName = request.getParameter("fileName");
 		
 		//새창 띄우기
 		try {
 			String browser = request.getHeader("User-Agent"); 
            
            //파일 인코딩 
            if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
            	fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            	
            } else {
            	fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }
 		
 		//파일 가져오기
 		String realPath = path + fileName;
        System.out.println(realPath);
        File downFile = new File(realPath);
        
        if (!downFile.exists()) {
            return ;
        }
         
        //파일명 지정        
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(realPath);
 
            int ncount = 0;
            byte[] bytes = new byte[512];
 
            while ((ncount = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
            
        } catch (Exception ex) {
            System.out.println("Exception");
        }
	}
	
	
	@RequestMapping("delete.do")
	public String delete(TestVo vo, HttpServletRequest request) {
		//path 설정
 		String path = request.getSession().getServletContext().getRealPath("/files/");
 		System.out.println(path);
 		 		
 		TestVo img = service.content(vo);
 		File file = new File(path+img.getImg());
 		
 		//파일이 있으면 폴더에서 삭제
 		if(file.exists()) {
 			file.delete();
 		}
 		
 		service.delete(vo);
		
		return "list.do";
	}
}
