package com.spring.board.test;

import org.springframework.web.multipart.MultipartFile;

public class TestVo {

	MultipartFile imgUploadFile; //실제 파일
	String img; //파일명
	
	public MultipartFile getImgUploadFile() {
		return imgUploadFile;
	}
	public void setImgUploadFile(MultipartFile imgUploadFile) {
		this.imgUploadFile = imgUploadFile;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	@Override
	public String toString() {
		return "TestVo [imgUploadFile=" + imgUploadFile + ", img=" + img + ", getImgUploadFile()=" + getImgUploadFile()
				+ ", getImg()=" + getImg() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
