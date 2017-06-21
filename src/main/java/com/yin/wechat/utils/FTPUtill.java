package com.yin.wechat.utils;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.SocketException;
//
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//import org.apache.log4j.Logger;
//
//public class FTPUtill {
//    private static Logger logger = Logger.getLogger(FTPUtill.class);  
//
//	/** 
//     * 获取FTPClient对象 
//     * @param ftpHost FTP主机服务器 
//     * @param ftpPassword FTP 登录密码 
//     * @param ftpUserName FTP登录用户名 
//     * @param ftpPort FTP端口 默认为21 
//     * @return 
//     */  
//    public static FTPClient getFTPClient(String ftpHost, String ftpPassword,  
//            String ftpUserName, int ftpPort) {  
//        FTPClient ftpClient = null;  
//        try {  
//            ftpClient = new FTPClient();  
//            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器  
//            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器  
//            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
//                logger.info("未连接到FTP，用户名或密码错误。");  
//                ftpClient.disconnect();  
//            } else {  
//                logger.info("FTP连接成功。");  
//            }
//        } catch (SocketException e) {  
//            e.printStackTrace();  
//            logger.info("FTP的IP地址可能错误，请正确配置。");  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//            logger.info("FTP的端口错误,请正确配置。");  
//        }  
//        return ftpClient;  
//    }  
//	
//	 /**  
//     * Description: 向FTP服务器上传文件  
//     * @param host FTP服务器hostname  
//     * @param port FTP服务器端口  
//     * @param username FTP登录账号  
//     * @param password FTP登录密码  
//     * @param basePath FTP服务器基础目录 
//     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath 
//     * @param filename 上传到FTP服务器上的文件名  
//     * @param input 输入流  
//     * @return 成功返回true，否则返回false  
//     */    
//    public static boolean uploadFile(String host, int port, String username, String password, String basePath,  
//            String filePath, String filename, InputStream input) {  
//        boolean result = false;  
//        FTPClient ftp = null;  
//        logger.info("开始上传文件到FTP."); 
//        try {  
//            ftp = getFTPClient(host, password,  
//            		username, port);
//            //切换到上传目录  
//            if (!ftp.changeWorkingDirectory(basePath+filePath)) {  
//                //如果目录不存在创建目录  
//                String[] dirs = filePath.split("/");  
//                String tempPath = basePath;  
//                for (String dir : dirs) {  
//                    if (null == dir || "".equals(dir)) continue;  
//                    tempPath += "/" + dir;  
//                    if (!ftp.changeWorkingDirectory(tempPath)) {  
//                        if (!ftp.makeDirectory(tempPath)) {  
//                            return result;  
//                        } else {  
//                            ftp.changeWorkingDirectory(tempPath);  
//                        }  
//                    }  
//                }  
//            }  
//            //设置上传文件的类型为二进制类型  
//            ftp.setFileType(FTP.BINARY_FILE_TYPE);  
//            //上传文件  
//            if (!ftp.storeFile(filename, input)) {  
//                return result;  
//            }  
//            input.close();  
//            ftp.logout();  
//            result = true;  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } finally {  
//            if (ftp.isConnected()) {  
//                try {  
//                    ftp.disconnect();  
//                } catch (IOException ioe) {  
//                }  
//            }  
//        }  
//        return result;  
//    }  
//      
//    /**  
//     * Description: 从FTP服务器下载文件  
//     * @param host FTP服务器hostname  
//     * @param port FTP服务器端口  
//     * @param username FTP登录账号  
//     * @param password FTP登录密码  
//     * @param remotePath FTP服务器上的相对路径  
//     * @param fileName 要下载的文件名  
//     * @param localPath 下载后保存到本地的路径  
//     * @return  
//     */    
//    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,  
//            String fileName, String localPath) {  
//        boolean result = false;  
//        FTPClient ftp = null;  
//        logger.info("开始从FTP下载文件"); 
//        try {  
//            ftp = getFTPClient(host, password,  
//            		username, port);           
//            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录  
//            FTPFile[] fs = ftp.listFiles();  
//            for (FTPFile ff : fs) {  
//                if (ff.getName().equals(fileName)) {  
//                    File localFile = new File(localPath + "/" + ff.getName());  
//  
//                    OutputStream is = new FileOutputStream(localFile);  
//                    ftp.retrieveFile(ff.getName(), is);  
//                    is.close();  
//                }  
//            }  
//  
//            ftp.logout();  
//            result = true;  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } finally {  
//            if (ftp.isConnected()) {  
//                try {  
//                    ftp.disconnect();  
//                } catch (IOException ioe) {  
//                }  
//            }  
//        }  
//        return result;  
//    }  
//	public static void testFtpClient() throws Exception{  
//        //创建ftpClient对象  
//        FTPClient ftpClient= new FTPClient();  
//        //创建ftp链接，默认是21端口  
//        ftpClient.connect("106.15.102.168",21);  
//          
//        //登录ftp服务器，使用用户名和密码  
//        ftpClient.login("leantech", "citsm!@#");  
//          
//        //上传文件  
//        //读取本地文件  
//        FileInputStream inputStream=new FileInputStream(new File("E:\\精宸资料\\tip.png"));  
//          
//        //设置上传的路径  
//        ftpClient.changeWorkingDirectory("/var/ftp");  
//          
//        //修改上传格式  
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);  
//        //第一个参数：服务器端文档名  
//        //第二个参数，上传文档的inputStream  
//        ftpClient.storeFile("rest.png", inputStream);  
//        //关闭链接  
//        ftpClient.logout();            
//          
//    }  
//	public static void main(String[] args) throws Exception {
//		testFtpClient();
//	}
//}
