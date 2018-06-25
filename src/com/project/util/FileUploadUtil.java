package com.project.util;


import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUploadUtil {
    private static FileUploadUtil fileUploadUtil;

    // 图片的自动编号
    private int picNo = 0;

    // 单例
    public static synchronized FileUploadUtil getInstance() {
        if (null == fileUploadUtil) {
            fileUploadUtil = new FileUploadUtil();
        }

        return fileUploadUtil;
    }

    /**
     *
     * @Title: getNewFileName
     * @Description:
     * @param @param fileName
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String getNewFileName(String fileName) {
        StringBuffer buff = new StringBuffer();
        String str = DateUtil.getCurrentDateTimeToStr();
        long m = Long.parseLong((str)) + picNo;
        picNo++;
        buff.append(m).append(
                FileUploadUtil.getInstance().getExtention(fileName));
        return buff.toString();
    }

    /**
     *
     * 〈获取文件格式eg:0001.jpg--->jpg〉
     *
     * @return String
     * @Modify [方勇]
     */
    public String getExtention(String fileName) {
        String uploadName = "";
        if (!"".equals(fileName) && null != fileName) {
            int pos = fileName.lastIndexOf(".");
            uploadName = fileName.substring(pos);
        }
        return uploadName;
    }


    /**
     * springmvc专用, 保存上传的文件。
     *
     * @param mFile
     *            文件要保存的路径。相对于webroot
     * @param newfileName
     *            新文件名,可以为空。定义保存后，承现的文件名。如果不带有后缀名，将使用上传源文件的后缀名。 返回文件的相对路径
     */
    public String saveFileUpload(MultipartFile mFile, String relativeDir, String newfileName) {
        String url = "";
        try {

            if (newfileName != null) {
                if (newfileName != "") {
                    newfileName = newfileName.concat(getExtention(mFile.getOriginalFilename()));
                }
            } else {
                newfileName = mFile.getOriginalFilename();
            }

            //request.getSession().getServletContext().getRealPath("")
            String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            url= relativeDir+"/"+newfileName;
            File file = new File(realPath+"/"+url);
            if(!file.exists()){
                file.mkdirs();
            }
            mFile.transferTo(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 
     * @param mFile				上传的文件
     * @param relativeDir		文件要保存的路径。相对于Root　　　格式： "upload/commodity/picture"
     * @param newfileName		新的文件名
     * @param realPath			request.getSession().getServletContext().getRealPath("");
     * @return
     */
    public String saveFileUpload(MultipartFile mFile, String relativeDir, String newfileName, String realPath) {
        String url = "";
        try {

            if (newfileName != null) {
                if (newfileName != "") {
                   // newfileName = newfileName+".jpg";
                }
            } else {
                newfileName = mFile.getOriginalFilename();
            }

            String directory = realPath + "/" + relativeDir;

            File file = new File(directory + "/" + newfileName);
            if(!file.exists()){
                file.mkdirs();
            }
            mFile.transferTo(file);
//            mFile.transferTo(new File(file,""));
            url = relativeDir + "" + newfileName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

//    public String uploadFile(MultipartFile file,HttpServletRequest request, String dictoryInfo,String olddictoryInfo){
//        if(file!=null && !file.isEmpty() && file.getSize()>0){
//            String path = "";
//            File dictoryinfo = new File(getDataFilePath(request)+File.separatorChar+dictoryInfo);
//            File olddictoryinfo = new File(getDataFilePath(request)+File.separatorChar+olddictoryInfo);
//            try {
//                if(!dictoryinfo.exists()){
//                    dictoryinfo.mkdirs();//不存在则创建
//                }
//                if(olddictoryinfo.exists()){
//                    olddictoryinfo.delete();//存在则删除
//                }
//                //path = FileUtil.getFileName(file.getOriginalFilename());
//                file.transferTo(new File(dictoryinfo,path));
//                logger.info("文件上传成功："+dictoryinfo+"/"+path);
//            } catch (Exception e) {
//                throw new RuntimeException("保存文件失败,"+e.getMessage(),e);
//            }
//            return dictoryInfo+"/"+path;}
//        else{
//            return null;
//        }
//    }

}
