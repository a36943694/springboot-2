package rlkj.modules.process.upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import rlkj.common.utils.DateUtils;
import rlkj.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 */
@Api(description = "文件上传接口")
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件存储地址
     */
    @Value("${rlkj.uploadpath}")
    private String mySavePath;

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @ApiOperation(value = "单个文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                String originalFileName = file.getOriginalFilename();
                String temp = originalFileName.substring(originalFileName.lastIndexOf("."));
                String saveFileName = DateUtils.format(new Date(),"yyyyMMddHHmmssSS") + temp;
                File saveFile = new File(mySavePath + saveFileName);
                if(!saveFile.getParentFile().exists()){
                    saveFile.getParentFile().mkdir();
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return R.ok().put("path",mySavePath + saveFileName);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                return R.error("上传失败");
            }catch (IOException e){
                e.printStackTrace();
                return R.error("上传失败");
            }
        }else {
            return R.error("上传失败，因为文件为空");
        }
    }

    /**
     * 批量上传
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "批量上传")
    @PostMapping("uploadFiles")
    @ResponseBody
    public R uploadFiles(HttpServletRequest request) throws IOException,InterruptedException{
        File savePath = new File(mySavePath);
        if(!savePath.exists()){
            savePath.mkdirs();
        }

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        List<String> fileNames = new ArrayList<String>();
        for(int i = 0; i <files.size(); ++i){
            Thread.sleep(2);
            file = files.get(i);
            if(!file.isEmpty()){
                try {
                    byte[] bytes =file.getBytes();
                    String temp = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    String saveFileName = DateUtils.format(new Date(),"yyyyMMddHHmmssSS") + temp;
                    File saveFile = new File(savePath,saveFileName);
                    stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                    stream.write(bytes);
                    stream.close();
                    fileNames.add(saveFileName);
                }catch (Exception e){
                    if(stream != null){
                        stream.close();
                        stream = null;
                    }
                    e.printStackTrace();
                    return R.error("第" + i +" 个文件上传有错误" + e.getMessage());
                }
            }else {
                return R.error("第" + i +" 个文件为空");
            }
        }
        return R.ok().put("fileNames",mySavePath + fileNames);
    }


    @ApiOperation(value = "显示图片")
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(mySavePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
