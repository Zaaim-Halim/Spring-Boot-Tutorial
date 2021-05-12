package com.halim.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.halim.model.MyFiles;


@Controller
public class HomeController {
	
	@Value("${extern.resoures.path}")
	private String path;
	
	private static MyFiles files = new MyFiles();
	
	@GetMapping({"/","/index"})
	public String index(Model model)
	{
		return "index";
	}
    @PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file,Model model)
	{
		if(file.isEmpty())
		{
			throw  new RuntimeException("please provide a valide file");
		}
		
		InputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(file.getInputStream());
			byte[] b = in.readAllBytes();
			String fullPath = decideFullPath(file);
			out = new BufferedOutputStream(new FileOutputStream(fullPath));
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	    
		model.addAttribute("files", files);
		return "index";
	}

	private String decideFullPath(MultipartFile file) {
		String filename = file.getOriginalFilename();
		int index = filename.indexOf('.');
		String extension = filename.substring(index+1, filename.length()).toUpperCase();
		if("PDF".equals(extension)) {
			files.setPdf(filename);
			return path + File.separator + "pdf" + File.separator + filename;
		}	
		else if ("PNG".equals(extension) || "JPG".equals(extension))
		{   files.setImage(filename);
			return path + File.separator + "images" + File.separator + filename;
		}
			
		else if ("MP4".equals(extension) || "FLV".equals(extension))
		{
			files.setVideo(filename);
			return path + File.separator + "videos" + File.separator+ filename;
		}
			
		else 
			throw new RuntimeException("extension not supported :"+extension);
		
	}
}
