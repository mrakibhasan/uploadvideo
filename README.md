# uploadvideo
This project is a web service that will allow a user to log in and upload a short video written by spring mvc.

Follow to run this project:
1. After checkout run in server (Here I have used Tomcat)
2. To test web service used postman.
3. Use this Url to invoke this service, here this service receives only post method:  http://localhost:8080/video-upload-service/upload
4. In this project spring security have used to authorize user,here I have used Basic encoding authentication.
5. From postman used Authorization Tab--> select type-->Basic Auth-> username: rakib password: rakib123 From header tab encoding username and password will be visible
6. From Body Tab: select form-data: put key "file" and select video file less than 15mbs.
7. Hit send button and expect relevan response.

Assumption:
1.Only Mp4 video file allow to upload all other files will give bad request response
2. Right now only two user allow to upload file  username: rakib password: rakib123 and  username: hasan password: hasan123
3. Video size should be less than 15 mbs.

Limitation: 
This web service does not return video duration and bit rate. I have used Xuggler to retrieve duration and bitrate but it 
is showing some dependency problem though I have used maven dependency but It does not come up in runtime. (I have commented this java code)

please change the file location to be uploaded.or use the following to upload in working directory
        private static final String workingDir = System.getProperty("user.dir");
 The full file path location will log to the console.
