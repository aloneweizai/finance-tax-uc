/**
 *
 * @author geloin
 * @date 2012-5-5 下午12:05:57
 */
package com.abc.common.util;

import com.abc.application.SpringCtxHolder;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.CmsFileUploadDto;
import com.abc.soa.request.FileListDto;
import com.abc.soa.request.FjDto;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author geloin
 * @date 2012-5-5 下午12:05:57
 */
public class FileOperateUtil extends BaseObject {
    private static final String FILEID="fileid";
    public static final String REALNAME = "realName";
    private static final String STORENAME = "storeName";
    private static final String SIZE = "size";
    private static final String SUFFIX = "suffix";
    private static final String CONTENTTYPE = "contentType";
    private static final String CREATETIME = "createTime";
    public static final String UPLOADDIR = "UPLOADDIR";
    public static final String STOREFILEPATH = "storeFilePath";
    public static final String PREQUALIFICATIONID = "preQualificationId";
    public static final String FILETYPE = "fileType";
    public static final String FILETPATH = "filePath";//全文件路径
	public static final String VITUALNAME = "vitualName";
	public static final String URL = "url";


    /**
     * 将上传的文件进行重命名
     *
     * @author geloin
     * @date 2012-3-29 下午3:39:53
     * @param name
     * @return
     */
    private static String rename(String name) {

        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;

        if (name.indexOf(".") != -1) {
            fileName += name.substring(name.lastIndexOf("."));
        }

        return fileName;
    }

    /**
     * 压缩后的文件名
     *
     * @author geloin
     * @date 2012-3-29 下午6:21:32
     * @param name
     * @return
     */
    private static String zipName(String name) {
        String prefix = "";
        if (name.indexOf(".") != -1) {
            prefix = name.substring(0, name.lastIndexOf("."));
        } else {
            prefix = name;
        }
        return prefix + ".zip";
    }

    /**
     * 静态资源上传文件
     *
     * @author husl
     * @date 2015-8-15 下午14:25:47
     * @param request
     * @param fileType  子目录
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> upload(HttpServletRequest request, SubsystemType subsystemType, FileType fileType) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	String filedir = null;
		String fileSuffix = null;
		String uploadedFileName = null;

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		filedir = !CommonUtils.nullOrBlank(BaseObject.getConfig("STATIC_FILE_DIR_PATH")) ? BaseObject.getConfig("STATIC_FILE_DIR_PATH") : "/home/html";

		filedir += subsystemType.subDir + fileType.dir;

		File file = new File(filedir);

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName = null;
		int i = 0;
		if (fileMap != null) {
			for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {
				String storeDirPath = subsystemType.subDir + fileType.dir;
				Map.Entry<String, MultipartFile> entry = it.next();
				Map<String, Object> map = new HashMap<String, Object>();

				MultipartFile mFile = entry.getValue();

				fileName = mFile.getOriginalFilename();
				fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
				if("image".equals(fileType.tag)) {//图片类型校验
					if (mFile.getSize() > 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new Exception("图片不能超过" + SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE") + "K");
					}

					String[] requireSuffix = SpringCtxHolder.getProperty("IMG_UPLOAD_SUFFIX").split(";");
					if (requireSuffix != null) {
						String tmpSuffix = null;
						for (int j = 0; j < requireSuffix.length; j++) {
							tmpSuffix = requireSuffix[j];
							if (tmpSuffix.equals("." + fileSuffix)) {
								break;
							}
							if (j == requireSuffix.length - 1) {
								throw new Exception("图片格式只能为jpg,jpeg,png,bmp中的一种");
							}
						}
					}
				}else if("file".equals(fileType.tag)){//文件类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new Exception("文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
				}else{//其他类型校验
					if (mFile.getSize() > 1024 * 1024 * Long.parseLong(SpringCtxHolder.getProperty("IMG_UPLOAD_SIZE"))) {
						throw new Exception("文件不能超过" + SpringCtxHolder.getProperty("FILE_UPLOAD_SIZE") + "M");
					}
				}

				String filePath = filedir + "/" + uploadedFileName;

				try {
					FileOutputStream outputStream = new FileOutputStream(filePath);
					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
					throw new Exception("上传文件异常");
				}

				map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
				map.put(FileOperateUtil.REALNAME, fileName);
				map.put(FileOperateUtil.SIZE, new File(filePath).length());
				map.put(FileOperateUtil.SUFFIX, fileSuffix);
				map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
				map.put(FileOperateUtil.CREATETIME, new Date());
				map.put(FileOperateUtil.UPLOADDIR, filedir);
				map.put(FileOperateUtil.FILETPATH, fileType.dir);
				map.put(FileOperateUtil.STOREFILEPATH, storeDirPath + uploadedFileName);
				result.add(map);
			}
		}
		return result;
    }

    public static FileListDto voteImgUpload(MultipartHttpServletRequest request){
		String directory = "_voteUploadedImg";
		String fileName = null;
		List<Byte> content = null;
		FjDto fjDto = null;
		List<FjDto> fjDtoList = new ArrayList<>();
		FileListDto fileListDto = null;
		try{

			Map<String, MultipartFile> fileMap = request.getFileMap();
			if(fileMap != null){
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {

					fjDto = new FjDto();
					Map.Entry<String, MultipartFile> entry = it.next();
					//Map<String, Object> map = new HashMap<String, Object>();

					MultipartFile mFile = entry.getValue();
					fileName = mFile.getOriginalFilename();
					content = fileBytesToList(mFile.getBytes());
					fjDto.setFileName(fileName);
					fjDto.setContent(content);
					fjDtoList.add(fjDto);
//					param.put("directory",directory);
//					param.put("fjBo",fjDto);
//					result.add(SoaConnectionFactory.post(request, ConstantsUri.SYSFILEUP, param, FileListDto.class));
				}

				CmsFileUploadDto param = new CmsFileUploadDto();
				param.setDirectory(directory);
				param.setFjBo(fjDtoList);
				fileListDto = SoaConnectionFactory.post(request, ConstantsUri.SYSFILEUP, param, FileListDto.class);

			}

		}catch (Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}

		if(fileListDto!=null){
			if(fileListDto.getDataList()==null){
				fileListDto.setDataList(new ArrayList<>());
			}
		}


//		FjDto


//		String classPath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
//
//		String relativeImgDir = "static/images/voteUploaded/";
//
//		String filedir = classPath + relativeImgDir;
//		String fileSuffix = null;
//		String uploadedFileName = null;
//
//		try {
//			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
//			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
//
//			File file = new File(filedir);
//
//			if (!file.exists()) {
//				file.mkdirs();
//			}
//
//			String fileName = null;
//			if(fileMap != null){
//				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
//
//					Map.Entry<String, MultipartFile> entry = it.next();
//					Map<String, Object> map = new HashMap<String, Object>();
//
//					MultipartFile mFile = entry.getValue();
//					fileName = mFile.getOriginalFilename();
//
//					fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//					uploadedFileName = sdf.format(new Date()) + "." + fileSuffix;
//
//					String filePath = filedir + uploadedFileName;
//
//					FileOutputStream outputStream = new FileOutputStream(filePath);
//					FileCopyUtils.copy(mFile.getInputStream(), outputStream);
//
//					map.put(FileOperateUtil.VITUALNAME, uploadedFileName);
//					map.put(FileOperateUtil.REALNAME, fileName);
//					map.put(FileOperateUtil.SIZE, new File(filePath).length());
//					map.put(FileOperateUtil.SUFFIX, fileSuffix);
//					map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
//					map.put(FileOperateUtil.CREATETIME, new Date());
//					map.put(FileOperateUtil.UPLOADDIR, filedir);
//					map.put(FileOperateUtil.FILETPATH, filePath);
//					map.put(FileOperateUtil.URL, request.getContextPath()+"/images/voteUploaded/"+uploadedFileName);
//					result.add(map);
//				}
//			}
//		} catch(Exception e) {
//			log.error(e.getMessage());
//			e.printStackTrace();
//		}
		return fileListDto;
	}
    
    public static String checkFile(HttpServletRequest request) throws Exception {
    	try {
    		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
    		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
    		String maxFileSize = !CommonUtils.nullOrBlank(BaseObject.getConfig("SYS_FILE_MAX_SIZE")) ? BaseObject.getConfig("SYS_FILE_MAX_SIZE") : "5";
    		if(fileMap != null){
				for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, MultipartFile> entry = it.next();
					MultipartFile mFile = entry.getValue();
					String[] idInfo = entry.getKey().split("_");
					String fileTypeName = idInfo[0];
					String fileName = mFile.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					ArrayList<String> yszlType = new ArrayList<String>(){{add(".bmp"); add(".jpg"); add(".pdf");}};
					if (mFile.getSize() < 1) {
						return "100000010130";
					}
					if ((fileTypeName.indexOf("zxsqs") > -1 && suffix.indexOf(".doc") < 0) ||
							(fileTypeName.indexOf("yszl") > -1 && !yszlType.contains(suffix))) {
						return "100000010129";
					}
//					long test = mFile.getSize() / 1024 / 1024;
//					double size = Math.ceil(Integer.parseInt(test+""));
//					long size1=Long.parseLong(size+"");
//					long maxsize = Long.parseLong(maxFileSize);
//					if ( size1>maxsize ) {
//						return "100000010128";
//					}
				}
			}
    	} catch(Exception e) {
			log.error(e.getMessage());
    		e.printStackTrace();
    		throw new Exception("验证上传文件大小异常");
    	}
		return "";
    }

    /**
     * 下载
     *
     */
    public static void download(HttpServletRequest request,
                                HttpServletResponse response, String filePath, String fileName, String realFileName) throws Exception {
		String contentType = "application/octet-stream";

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String downLoadPath = filePath + fileName;

		long fileLength = new File(downLoadPath).length();
		if (fileLength > 0) {
			
			String agent = request.getHeader("User-Agent");
			boolean isMSIE = ((agent != null && agent.indexOf("MSIE") != -1) || (agent != null &&agent.indexOf("rv:11") != -1));
			if (isMSIE) {
				realFileName = URLEncoder.encode(realFileName, "UTF-8");
				realFileName = realFileName.replace("+", "%20");
			} else {
				realFileName = new String(realFileName.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename=" + realFileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			try {
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
//				bis.close();
//				bos.close();
			}catch(Exception e){
				log.error(e.getMessage());
				e.printStackTrace();
			}finally {
				if(bis!=null) {
					bis.close();
				}
				if(bos!=null) {
					bos.flush();
					bos.close();
				}
			}
		}
    }
    
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    public static void delteFile(String filePath) {
    	File file = new File(filePath);
    	file.delete();
    }

	/**
	 * 返回SOA_URL
	 * @return
	 */
	public static String getSoaURL(){
		String soa_url = BaseObject.getConfig("SOA_DOWN_URL");
		return soa_url;
	}

	/**
	 * 文件内容转换为byteList
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static List fileBytesToList(byte[] bytes) throws Exception {
		Byte[] newBytes = new Byte[bytes.length];
		List list = null;
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = bytes[i];
		}
		list = Arrays.asList(newBytes);
		return list;
	}

}