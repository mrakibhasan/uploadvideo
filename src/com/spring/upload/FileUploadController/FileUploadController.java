package com.spring.upload.FileUploadController;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.spring.upload.model.FileBucket;
import com.xuggle.xuggler.IContainer;



@RestController
public class FileUploadController {

	private static String UPLOAD_LOCATION="C:/mytemp/";
	private static final Logger logger=LogManager.getLogger(FileUploadController.class);
	

	@RequestMapping(value="/upload", method = RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileBucket> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
		
		String  mimeType =file.getOriginalFilename().split("\\.")[1];
		logger.info("The file extension: "+mimeType);
		String fileName = file.getOriginalFilename();
		logger.info("The file Name is: "+fileName);
		FileBucket fileInfo=new FileBucket();
		logger.info(file.getContentType());
		if (!file.isEmpty()) {
			try {
				if(!mimeType.equals("mp4")) {
					String fileFormatError=fileName+" is not mp4 type ";
					logger.error("The file Format error: "+fileFormatError);
					fileInfo.setFileName(fileFormatError);
					return new ResponseEntity<FileBucket>(fileInfo, HttpStatus.BAD_REQUEST);
				}
				
				FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_LOCATION + file.getOriginalFilename()));
				logger.info("File Location: "+UPLOAD_LOCATION + file.getOriginalFilename());
				fileInfo.setFileName(fileName);
				fileInfo.setFileSize(file.getSize());
				
				// uncomment if you want file duration and bit rate. In my case I have some issue with Icontainer jar file though I have added maven dependency
				/*IContainer container = IContainer.make();
				String fileLocation=UPLOAD_LOCATION + file.getOriginalFilename();
				int result = container.open(fileLocation, IContainer.Type.READ, null);
				if (result<0) {
					System.out.println("could not open");
				}
				long duration = container.getDuration();
				System.out.println(duration);
				logger.info("File Duration: "+duration);*/
				
				HttpHeaders headers=new HttpHeaders();
				headers.add("file Upload Succesfully", fileName);
				return new ResponseEntity<FileBucket>(fileInfo,headers,HttpStatus.OK);
			}catch(Exception ex) {
				return new ResponseEntity<FileBucket>(HttpStatus.BAD_REQUEST);
			}

		} else {			

			return new ResponseEntity<FileBucket>(HttpStatus.BAD_REQUEST);
		}
	}



}
