package com.ccty.service.common.util;

import cn.hutool.core.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    //文件下载
    public static boolean downLoadFileNm(HttpServletResponse response, String filePath, String fileName) throws Exception {
        File file = new File(filePath);
        if(!file.exists()){
            throw new Exception("文件不存在!");
        }
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setContentLength((int)file.length());
            writeOutPutStrem(response.getOutputStream(),file);
        } catch (IOException e) {
            throw new IOException(e.getMessage(),e);
        }
        return true;
    }

    public static void writeOutPutStrem(OutputStream out, File sourceFile) throws IOException {

        FileChannel fileChannel = null;

        try {
            fileChannel = new FileInputStream(sourceFile).getChannel();

            int byteSize = 10 * 1024 , bufferSize = 50 * 1024;
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            byte[] barray = new byte[byteSize];

            int nRead,nGet;
            while(fileChannel.read(buffer)!=-1){
                buffer.flip();
                while(buffer.hasRemaining()){
                    nGet = Math.min(buffer.remaining(),byteSize);
                    buffer.get(barray,0,nGet);
                    out.write(barray);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage(),e);
        }

    }

    public static boolean downFileNm(HttpServletResponse response,String filePath,String fileName) throws Exception {
        File file = new File(filePath);
        if(!file.exists()){
            throw new Exception("文件不存在!");
        }

        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("multipart/form-data");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentLength((int)file.length());

        InputStream in = null;
//        BufferedInputStream bis = null;

        OutputStream out=response.getOutputStream();
//        BufferedOutputStream bos= null;
        int barray = 10 * 1024,buffer = 50 * 1024;
        try {
            in = new FileInputStream(file);
            //接第三方接口返回的byte数组
//            byte[] bytes = new byte[barray];
//            in = new ByteArrayInputStream(bytes);

            //自己手写缓冲流
//            bis = new BufferedInputStream(in,buffer);
//            bos = new BufferedOutputStream(out,buffer);

            IoUtil.copy(in,out,buffer);

            // 大型文件对应的数组应该大一些，小文件的数组应当小一些，在实践中不断尝试和优化。
//            byte[] bytes = new byte[barray];
//            while (bis.read(bytes) != -1) {
//                bos.write(bytes);
//            }
        } catch (IOException e) {
            throw new IOException(e.getMessage(),e);
        }catch (Exception e) {
            throw new Exception(e.getMessage(),e);
        }finally {
            IoUtil.close(in);
        }

        return true;
    }

    /**
     * 批量创建目录
     * @param args
     */
    public static void bathCreateFolder(String[] args){
        for(String folder : args){
            File dir = null;
            try {
                dir = new File(folder);
                if (!dir.exists() || dir.isFile()) {
                    dir.mkdirs();
                    logger.info("创建文件夹成功:"+dir.getAbsolutePath());
                }else{
                    logger.info("文件夹路径已存在"+dir.getAbsolutePath());
                }
            }catch(Exception e){
                logger.info("创建文件夹操作出错"+folder,e);
                continue;
            }
        }
    }
}
