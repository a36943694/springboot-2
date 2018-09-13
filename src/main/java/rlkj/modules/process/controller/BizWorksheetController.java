package rlkj.modules.process.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rlkj.common.utils.DealStrSub;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;
import rlkj.common.utils.ShiroUtils;
import rlkj.modules.process.entity.*;
import rlkj.modules.process.service.*;
import rlkj.modules.process.vo.BizWorksheetVO;


/**
 * 工作票配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "工作票配置")
@RestController
@RequestMapping("process/bizworksheet")
public class BizWorksheetController {
    @Autowired
    private BizWorksheetService bizWorksheetService;

    @Autowired
    private BizWorktaskListService bizWorktaskListService;
    @Autowired
    private BizSafecontrolListService bizSafecontrolListService;
    @Autowired
    private BizStaffListService bizStaffListService;

    @Autowired
    private BizMainworkflowInstService bizMainworkflowInstService;
    @Autowired
    private CfgMainworkflowTmplService cfgMainworkflowTmplService;
    @Autowired
    private BizSubworkflowInstService bizSubworkflowInstService;
    @Autowired
    private BizStaffSignService bizStaffSignService;

    @PostMapping("upload")
    @ApiOperation("上传工作票")
    @Transactional
    public R uploadFile(@RequestParam(value = "file", required = true) MultipartFile file){
        String textFileName = file.getOriginalFilename();
        BizWorksheetVO bizWorksheetVO = null;
        try
        {
            if(textFileName.endsWith(".doc"))
            {
                //处理doc业务
                bizWorksheetVO =   DocDataMining(file);
            }
            else if(textFileName.endsWith(".docx"))
            {
                //处理docx
                bizWorksheetVO =   DocxDataMining(file);
            }
//            else if(textFileName.endsWith(".pdf"))
//            {
//                paserPDFFileByIText("F:\\"+textFileName);
//            }

        }catch (Exception ex)
        {
            R.error(ex.toString());
        }

        return R.ok().put("BizWorksheetVO",bizWorksheetVO);
    }

    /**
     * 使用IText API解析
     * @param filePath 待解析pdf文档路径
     * @return 解析得到的pdf文本字符串
     * @throws Exception
     */
    public String paserPDFFileByIText(String filePath) throws Exception {
        TextExtractionStrategy strategy = null;

        PdfReader reader = new PdfReader(filePath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuffer buffer = new StringBuffer();

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            buffer.append(strategy.getResultantText());
        }

        return buffer.toString();
    }




    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "sheetNo",value = "工作票编号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "workplace",value = "工作单位",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "workGroup",value = "班组",dataType = "String",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizWorksheetService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			BizWorksheetEntity bizWorksheet = bizWorksheetService.selectById(id);

        return R.ok().put("bizWorksheet", bizWorksheet);
    }

    /**
     * 通过工作票id获取工作票信息
     */
    @GetMapping("/getInfoBySheetNo/{sheetNo}")
    @ApiOperation(value = "通过工作票id获取工作票信息")
    public R getInfoBySheetNo(@PathVariable("sheetNo") String sheetNo){
        BizWorksheetEntity bizWorksheet = bizWorksheetService.queryBySheetNo(sheetNo);

        return R.ok().put("bizWorksheet", bizWorksheet);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody BizWorksheetEntity bizWorksheet){
			bizWorksheetService.insert(bizWorksheet);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody BizWorksheetEntity bizWorksheet){
			bizWorksheetService.updateById(bizWorksheet);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			bizWorksheetService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    private  BizWorksheetVO DocxDataMining(MultipartFile file)
    {
        try {
            InputStream fis = file.getInputStream();
            //创建一个map对象存放word中的内容
            List<String> wordMap = Lists.newArrayList();
            // 得到word文档的信息
            XWPFDocument xwpf = new XWPFDocument(fis);
            //得到段落信息
            List<XWPFParagraph> listParagraphs = xwpf.getParagraphs();
            for(int i = 0;i<listParagraphs.size();i++)
            {
                XWPFParagraph xwpfParagraph = listParagraphs.get(i);
                wordMap.add(xwpfParagraph.getParagraphText());
            }
            //获取常规字段 BizWorksheetEntity
            BizWorksheetEntity bizWorksheetEntity = GetWorksheet(wordMap);
            if (bizWorksheetEntity != null)
            {
                bizWorksheetService.insert(bizWorksheetEntity);
            }
            // 得到word中的表格
            Iterator<XWPFTable> it = xwpf.getTablesIterator();
            List<BizWorktaskListEntity> bizWorktaskListEntityList = Lists.newArrayList();
            List<BizSafecontrolListEntity> bizSafecontrolListEntityList = Lists.newArrayList();
            Integer SafeControlType =1;
            Integer tempseq =1;
            Boolean hadWorktask =false;
            Boolean hasSafeControl =false;
            outterLoop :   while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                // 读取每一行数据
                for (int i = 0; i < rows.size(); i++) {
                    XWPFTableRow row = rows.get(i);
                    // 读取每一列数据
                    List<XWPFTableCell> cells = row.getTableCells();
                    if(cells.get(0).getText().contains("应拉断路器"))
                    {
                        hadWorktask =false;
                    }
                    if(hadWorktask && !hasSafeControl)
                    {
                        BizWorktaskListEntity bizWorktaskListEntity= new BizWorktaskListEntity();
                        bizWorktaskListEntity.setSeq(bizWorktaskListEntityList.size()+1);
                        bizWorktaskListEntity.setWorkAdressDevices(cells.get(0).getText());
                        bizWorktaskListEntity.setContent(cells.get(1).getText());
                        bizWorktaskListEntity.setCreateTime(new Date());
                        bizWorktaskListEntity.setWorksheetId(bizWorksheetEntity.getId());
                        bizWorktaskListEntityList.add(bizWorktaskListEntity);
                    }
                    if(cells.get(0).getText().contains("工作地点保留带电部分或注意事项"))
                    {
                        tempseq= 1;
                        SafeControlType =1;
                        hasSafeControl = false;
                    }
                    if(cells.get(0).getText().contains("工作地点及设备双重名称"))
                    {
                        hadWorktask =true;
                    }
                    if (cells.get(0).getText().contains("应装接地线、应合接地刀闸")) {
                        SafeControlType =2;
                        tempseq= 1;
                    }
                    if (cells.get(0).getText().contains("应挂标示牌及防止二次回路误碰等措施")) {
                        SafeControlType =3;
                        tempseq=1;
                    }
                    if(hasSafeControl && !hadWorktask
                    && !cells.get(0).getText().contains("应拉断路器（开关）、隔离开关（刀闸）")
                    && !cells.get(0).getText().contains("应装接地线、应合接地刀闸")
                    && !cells.get(0).getText().contains("应挂标示牌及防止二次回路误碰等措施")
                    &&cells.get(0).getText().replace(" ","").trim().length()>0)
                    {
                        BizSafecontrolListEntity bizSafecontrolListEntity = new BizSafecontrolListEntity();
                        bizSafecontrolListEntity.setContent(cells.get(0).getText());
                        bizSafecontrolListEntity.setSeq(tempseq);
                        //bizSafecontrolListEntity.setSubworkflowInstId(SafeControlType);
                        bizSafecontrolListEntity.setCreateTime(new Date());
                        bizSafecontrolListEntity.setWorksheetId(bizWorksheetEntity.getId());
                        bizSafecontrolListEntityList.add(bizSafecontrolListEntity);
                        tempseq ++;
                    }
                    if (cells.get(0).getText().contains("应拉断路器") && cells.get(0).getText().contains("隔离开关")) {
                        hasSafeControl = true;
                        SafeControlType =1;
                        tempseq= 1;
                    }
                }
                xwpf.close();
            }
            bizWorktaskListService.insertBatch(bizWorktaskListEntityList);
            //获取安全清单
            bizSafecontrolListService.insertBatch(bizSafecontrolListEntityList);
            //获取工作人员列表
            List<BizStaffListEntity> bizStaffListEntityList = GetStaffListEntity(wordMap,bizWorksheetEntity.getId());
            if(bizStaffListEntityList != null && bizStaffListEntityList.size()>0)
            {
                bizStaffListService.insertBatch(bizStaffListEntityList);
            }
            BizWorksheetVO bizWorksheetVO = new BizWorksheetVO();
            BeanUtils.copyProperties(bizWorksheetEntity, bizWorksheetVO);
            return  bizWorksheetVO;

        }catch (Exception ex)
        {

        }

        return  null;
    }

    //doc 数据抽取
    private  BizWorksheetVO DocDataMining(MultipartFile file)
    {
        //第1步 ,插入工作票 worksheetEntity

        //第2步 ,插入人员列表 StaffListEntity

        // 第3步 ,插入工作任务清单 WorktaskListEntity 要拆分，以逗号拆分

        // 第4步,插入主流程  BizMainworkflowInstEntity 从CfgMainworkflowTmplEntity 取相关数据 ，工作票导入完成，工作许可 正在进行中

        //第5步 ,插入安全措施列表 BizSafecontrolListEntity 不拆分

        //第6步  插入子流程实列表 BizSubworkflowInstEntity   安全清单拆分 ，工作内容

        // 第7步 插入点名表

        try {
            //创建一个map对象存放word中的内容
            List<String> wordMap = Lists.newArrayList();
            InputStream fis = file.getInputStream();
            //使用HWPF组件中WordExtractor类从Word文档中提取文本或段落
            WordExtractor wordExtractor = new WordExtractor(fis);
            POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
            //获取段落内容
            for (String words : wordExtractor.getParagraphText()) {

                if (!words.equals("\\u0007")) {
                    if (words != null) {
                        wordMap.add(words);
                    }
                }
            }
            //获取常规字段 BizWorksheetEntity
            //第1步 ,插入工作票 worksheetEntity
            BizWorksheetEntity bizWorksheetEntity = GetWorksheet(wordMap);
            if (bizWorksheetEntity != null)
            {
                bizWorksheetService.insert(bizWorksheetEntity);
            }

            //获取工作人员列表
            //第2步 ,插入人员列表 StaffListEntity
            List<BizStaffListEntity> bizStaffListEntityList = GetStaffListEntity(wordMap,bizWorksheetEntity.getId());
            if(bizStaffListEntityList != null && bizStaffListEntityList.size()>0)
            {

                bizStaffListService.insertBatch(bizStaffListEntityList);
            }

            HWPFDocument hwpf = new HWPFDocument(pfs);
            // 得到文档的读取范围
            Range range = hwpf.getRange();
            TableIterator it = new TableIterator(range);
           // TableIterator its = new TableIterator(range);
            // 获取工作内容 BizWorktaskListEntity
            // 第3步 ,插入工作任务清单 WorktaskListEntity 要拆分，以逗号拆分
            List<BizWorktaskListEntity> bizWorktaskListEntityArrayList =  GetWorktaskListFromTable(it,bizWorksheetEntity.getId());
            bizWorktaskListService.insertBatch(bizWorktaskListEntityArrayList);


            // 第4步,插入主流程  BizMainworkflowInstEntity 从CfgMainworkflowTmplEntity 取相关数据 ，工作票导入完成，工作许可 正在进行中
           List<CfgMainworkflowTmplEntity> cfgMainworkflowTmplEntityList =cfgMainworkflowTmplService.selectList(null);
           List<BizMainworkflowInstEntity> bizMainworkflowInstEntityList = Lists.newArrayList();

           for(CfgMainworkflowTmplEntity  cfgworkflow : cfgMainworkflowTmplEntityList)
           {
               BizMainworkflowInstEntity bizMainworkflowInstEntity =new BizMainworkflowInstEntity();
               bizMainworkflowInstEntity.setCreateTime(new Date());
               bizMainworkflowInstEntity.setWorksheetId(bizWorksheetEntity.getId());
               bizMainworkflowInstEntity.setStepName(cfgworkflow.getStepName());
               bizMainworkflowInstEntity.setSeq(cfgworkflow.getSeq());
               bizMainworkflowInstEntity.setCreateUserId(new Long(ShiroUtils.getUserId()).intValue());
               if(cfgworkflow.getSeq() == 1) {
                   bizMainworkflowInstEntity.setState(2);
               }
               else if(cfgworkflow.getSeq() == 2) {
                   bizMainworkflowInstEntity.setState(1);
               }
               else
               {
                   bizMainworkflowInstEntity.setState(0);
               }
               bizMainworkflowInstEntityList.add(bizMainworkflowInstEntity);
           }
           bizMainworkflowInstService.insertBatch(bizMainworkflowInstEntityList);

            BizMainworkflowInstEntity bizMainworkflowInstEntityTemp =  bizMainworkflowInstEntityList.stream().filter(u -> u.getStepName().equals("工作监护及过程管控")).findFirst().get();
            BizMainworkflowInstEntity bizMainworkflowInstEntityStaffSign =  bizMainworkflowInstEntityList.stream().filter(u -> u.getStepName().equals("班前会")).findFirst().get();
            //第5步 ,插入安全措施列表 BizSafecontrolListEntity 不拆分
            //获取安全清单BizSafecontrolListEntity
            List<BizSafecontrolListEntity> bizSafecontrolListEntityList = GetSafeControlFromTable(wordMap,bizWorksheetEntity.getId());
            bizSafecontrolListService.insertBatch(bizSafecontrolListEntityList);

            //第6步  插入子流程实列表 BizSubworkflowInstEntity   安全清单拆分 ，工作内容
            List<BizSubworkflowInstEntity> bizSubworkflowInstEntityList = Lists.newArrayList();

            //工作任务
            for(int i =0; i< bizWorktaskListEntityArrayList.size(); i++)
            {
                BizSubworkflowInstEntity  bizSubworkflowInstEntity = new BizSubworkflowInstEntity();
                bizSubworkflowInstEntity.setWorksheetId(bizWorksheetEntity.getId());
                bizSubworkflowInstEntity.setMainFlowId(bizMainworkflowInstEntityTemp.getId());
               // bizSubworkflowInstEntity.setSafecontrolId(bizWorktaskListEntityArrayList.get(i).getId());
                //工作清单
                 bizSubworkflowInstEntity.setWorktaskId(bizWorktaskListEntityArrayList.get(i).getId());
                //ids ++;
                //排序
                bizSubworkflowInstEntity.setSeq(i+1);
                //子流程步骤
                //bizSubworkflowInstEntity.setSubStepName();
                //0 未开始 1 进行中 2 已完成 3 异常
                bizSubworkflowInstEntity.setState(0);
                //工作内容
                bizSubworkflowInstEntity.setWorkPrompt(bizWorktaskListEntityArrayList.get(i).getContent());
                //工作内容关键字
                bizSubworkflowInstEntity.setSafePoint(null);
                //
                bizSubworkflowInstEntity.setCreateTime(new Date());
                bizSubworkflowInstEntity.setCreateUserId(new Long(ShiroUtils.getUserId()).intValue());

                bizSubworkflowInstEntityList.add(bizSubworkflowInstEntity);
            }
            //bizSubworkflowInstEntityList.clear();
            //安全措施
            bizSafecontrolListEntityList.stream().forEach(e ->{
                List<String> splitWorkList = getSubStringByWork(e,"、");
               // splitWorkList.stream().forEach(v -
                 for(int i=0; i<splitWorkList.size(); i++)
                {
                    BizSubworkflowInstEntity  bizSubworkflowInstEntity = new BizSubworkflowInstEntity();
                    bizSubworkflowInstEntity.setWorksheetId(bizWorksheetEntity.getId());
                    bizSubworkflowInstEntity.setMainFlowId(bizMainworkflowInstEntityTemp.getId());
                    bizSubworkflowInstEntity.setSafecontrolId(e.getId());
                    //工作清单
                   // bizSubworkflowInstEntity.setWorksheetId();
                    //ids ++;
                    //排序
                    bizSubworkflowInstEntity.setSeq(i+1);
                    //子流程步骤
                    //bizSubworkflowInstEntity.setSubStepName();
                    //0 未开始 1 进行中 2 已完成 3 异常
                    bizSubworkflowInstEntity.setState(0);
                    //工作内容
                    bizSubworkflowInstEntity.setWorkPrompt(splitWorkList.get(i));
                    //工作内容关键字
                    bizSubworkflowInstEntity.setSafePoint(null);
                    //
                    bizSubworkflowInstEntity.setCreateTime(new Date());
                    bizSubworkflowInstEntity.setCreateUserId(new Long(ShiroUtils.getUserId()).intValue());
                    bizSubworkflowInstEntityList.add(bizSubworkflowInstEntity);
                }
            });
            bizSubworkflowInstService.insertBatch(bizSubworkflowInstEntityList);


          // 第7步 插入点名表

           // bizStaffSignService
          //  getSubStringByWork(bizSafecontrolListEntityList,"、");
            List<BizStaffSignEntity> bizStaffSignEntityList = Lists.newArrayList();
            bizStaffListEntityList.stream().forEach(e -> {
                BizStaffSignEntity bizStaffSignEntity =new BizStaffSignEntity();
                //主流程流程id 开班前会
                bizStaffSignEntity.setSubworkflowId(bizMainworkflowInstEntityStaffSign.getId());
                //工作票ID
                bizStaffSignEntity.setStaffId(bizWorksheetEntity.getId());
                bizStaffSignEntity.setCreateTime(new Date());
                bizStaffSignEntityList.add(bizStaffSignEntity);
            });
            bizStaffSignService.insertBatch(bizStaffSignEntityList);

            BizWorksheetVO bizWorksheetVO = new BizWorksheetVO();
            BeanUtils.copyProperties(bizWorksheetEntity, bizWorksheetVO);
            return  bizWorksheetVO;

        }catch (Exception ex){
            ex.toString();
        }
        return  null;
    }

    /**
     * @Author wuning
     * @Description 获取人员列表
     * @Date 14:01 2018/9/1
     * @Param [wordList, bizWorksheetId]
     * @return java.util.List<rlkj.modules.process.entity.BizStaffListEntity>
     **/
    private List<BizStaffListEntity> GetStaffListEntity(List<String> wordList,Integer bizWorksheetId)
    {
        List<BizStaffListEntity> bizStaffListEntityList =Lists.newArrayList() ;
        Boolean hadStafflist =false;
        if(wordList != null)
        {
            List<String> filteredStaff = wordList.stream().filter(x -> x.contains("工作负责人") && x.contains("班组")).collect(Collectors.toList());
            if(filteredStaff != null && filteredStaff.size()>0)
            {
                BizStaffListEntity  bizStaffListEntity = new BizStaffListEntity();
                bizStaffListEntity.setCreateTime(new Date());
                bizStaffListEntity.setWorkPosition(1);
                bizStaffListEntity.setWorksheetId(bizWorksheetId);
                bizStaffListEntity.setUserName(DealStrSub.getSubUtilSimple(filteredStaff.get(0).replace(")","")
                        .replace("）","").replace(" ",""),"监护人(.*?)班组"));
                bizStaffListEntityList.add(bizStaffListEntity);
            }
            StringBuffer StaffS = new StringBuffer();
            for(int i=0; i<wordList.size(); i++)
            {
                String Content = wordList.get(i);
                if(hadStafflist)
                {
                    if(Content.contains("共") && Content.contains("人") &&
                            DealStrSub.getSubUtilSimple(Content,"共(.*?)人")!= null
                            &&DealStrSub.getSubUtilSimple(Content,"共(.*?)人").trim().length()<4
                    )
                    {
                        hadStafflist = false;
                        break;
                    }
                    else
                    {
                        if(hadStafflist)
                        {
                            StaffS.append(wordList.get(i).trim());
                        }
                    }
                }
                if(Content.contains("工作班人员"))
                {
                    hadStafflist = true;
                }
            }

            if(StaffS != null )
            {
                    if(StaffS.toString().endsWith("等"))
                    {
                        StaffS = StaffS.deleteCharAt(StaffS.length() - 1);
                    }
                    String[] strs1 = StaffS.toString().split("、");
                    String[] strs2 = StaffS.toString().split("，");
                    String[] strs3 = StaffS.toString().split(",");
                    if(strs1.length >0){
                        BindStaffList(strs1,bizStaffListEntityList,bizWorksheetId);
                    }
                    else if(strs2.length >0){
                        BindStaffList(strs2,bizStaffListEntityList,bizWorksheetId);
                    }
                    else if(strs3.length >0){
                        BindStaffList(strs3,bizStaffListEntityList,bizWorksheetId);
                    }

            }
        }
        return  bizStaffListEntityList;
    }

    private void BindStaffList(String[] strs,List<BizStaffListEntity> bizStaffListEntityList,Integer bizWorksheetId)
    {
        for (int i =0 ; i<strs.length; i++)
        {
            BizStaffListEntity bizStaffListEntity = new BizStaffListEntity();
            bizStaffListEntity.setWorksheetId(bizWorksheetId);
            bizStaffListEntity.setCreateTime(new Date());
            bizStaffListEntity.setUserName(strs[i].trim());
            bizStaffListEntity.setWorkPosition(2);
            bizStaffListEntityList.add(bizStaffListEntity);
        }

    }

    /**
     * @Author wuning
     * @Description 获取工作票
     * @Date 14:01 2018/9/1
     * @Param [wordlist]
     * @return rlkj.modules.process.entity.BizWorksheetEntity
     **/
    private BizWorksheetEntity GetWorksheet(List<String>  wordlist) {
        BizWorksheetEntity bizWorksheetEntity = new BizWorksheetEntity();
        String regex_all="%s(.*?)%s(.*?)%s";
        List<String> filteredCompany = wordlist.stream().filter(x -> x.contains("单位") && x.contains("编号")).collect(Collectors.toList());
        if(filteredCompany.size() == 1)
        {
            List<String> lists =getSubUtil(filteredCompany.get(0).trim(),String.format(regex_all,"单位","编号","([0-9]{5})"));
            if(lists.size() ==1)
            {
                bizWorksheetEntity.setWorkplace(lists.get(0).trim().replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
            }
            bizWorksheetEntity.setSheetNo(filteredCompany.get(0).substring( filteredCompany.get(0).indexOf("编号")+2).trim()
                    .replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
            bizWorksheetEntity.setCreateTime(new Date());
            bizWorksheetEntity.setState(0);
        }
        List<String> filteredGrooupName = wordlist.stream().filter(x -> x.contains("班组")).collect(Collectors.toList());
        if(filteredGrooupName.size() > 0)
        {
            bizWorksheetEntity.setWorkGroup(filteredGrooupName.get(0).substring( filteredGrooupName.get(0).indexOf("班组")+2).trim()
                    .replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
        }
        StringBuilder workstationstr= new StringBuilder();
        Boolean hasworkstation =false;
        for(int i=0; i<wordlist.size(); i++)
        {
            String contents = wordlist.get(i);
            if(contents.contains("工作任务")&& contents.contains("4"))
            {
                hasworkstation = false;
                break;
            }
            if(hasworkstation)
            {
                workstationstr.append(contents.trim().replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , ""));
            }
            if((contents.contains("配电站名称及设备双重名称") && contents.contains("3"))
                    ||contents.contains("工作变电站名称"))
            {
                hasworkstation = true;
            }

        }
        if(workstationstr != null)
        {
            bizWorksheetEntity.setWorkStation(workstationstr.toString().trim());
        }

        List<String> filteredBeginEndTime = wordlist.stream().filter(x -> x.contains("年") && x.contains("月")&& x.contains("日")).collect(Collectors.toList());

        if(filteredBeginEndTime.size() > 0)
        {
            for(int i = 0; i<filteredBeginEndTime.size(); i++)
            {
                String tempString = filteredBeginEndTime.get(i);
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("yyyy年MM月dd日hh时mm分");
                try {
                    if (tempString.contains("自")) {
                        tempString = tempString.replace("自", "").trim().replaceAll(" ", "");
                        Date date2 = format.parse(tempString);
                        System.out.println(date2);
                        bizWorksheetEntity.setPlanStartTime(date2);
                    }
                    if (tempString.contains("至")) {
                        tempString = tempString.replace("至", "").trim().replaceAll(" ", "");
                        Date date2 = format.parse(tempString);
                        System.out.println(date2);
                        bizWorksheetEntity.setPlanEndTime(date2);
                        break;
                    }

            }catch (Exception ex)
            {
            }
            }
        }

        return bizWorksheetEntity;
    }

    /**
     * @Author wuning
     * @Description //获取工作任务列表
     * @Date 14:01 2018/9/1
     * @Param [tableIterator, bizWorksheetId]
     * @return java.util.List<rlkj.modules.process.entity.BizWorktaskListEntity>
     **/
    private List<BizWorktaskListEntity> GetWorktaskListFromTable(TableIterator tableIterator,Integer bizWorksheetId)
    {
        List<BizWorktaskListEntity> bizWorktaskListEntityList = Lists.newArrayList();
        List<BizWorktaskListEntity> bizWorktaskListEntityListtemp = Lists.newArrayList();
        List<String> worktaskStringList = Lists.newArrayList();
        //迭代文档中的表格
        outterLoop : while (tableIterator.hasNext()) {
            Table tb = tableIterator.next();
            //迭代行，默认从0开始
            for (int i = 0; i < tb.numRows(); i++) {
                TableRow tr = tb.getRow(i);
                //迭代列，默认从0开始
                for (int j = 0; j < tr.numCells(); j++) {
                    //取得单元格
                    TableCell td = tr.getCell(j);
                    String  worktask = null;
                    //取得单元格的内容
                    for(int k=0;k<td.numParagraphs();k++){
                        Paragraph para =td.getParagraph(k);
                        if(k==0) {
                            worktask =para.text();
                        }
                        else
                        {
                            worktask = worktask +para.text()+"";
                        }
                        if(para.text().contains("应拉断路器") )
                        {
                            break outterLoop; //
                        }
                    }
                    if(worktask.trim().length()>0) {
                        worktaskStringList.add(worktask);
                    }
                }
            }
        }
        if(worktaskStringList != null && worktaskStringList.size() > 0 &&
                (worktaskStringList.get(0).contains("工作地点及设备双重名称")
                || worktaskStringList.get(1).contains("工作内容"))) {
            worktaskStringList.remove(0);
            worktaskStringList.remove(0);
        }
        //排序，两个为一组
        int seq =1;
        for(int i= 0; i < worktaskStringList.size(); i++)
        {
            String contents  = worktaskStringList.get(i);
            BizWorktaskListEntity bizWorktaskListEntity = new BizWorktaskListEntity();
            bizWorktaskListEntity.setCreateTime(new Date());
            bizWorktaskListEntity.setWorksheetId(bizWorksheetId);
            if(i == 0)
            {
                bizWorktaskListEntity.setSeq(1);
                bizWorktaskListEntity.setWorkAdressDevices(contents.trim());
                bizWorktaskListEntity.setContent(worktaskStringList.get(i+1).trim());
                bizWorktaskListEntityList.add(bizWorktaskListEntity);
                seq++;
            }
            else
            {
                if( i%2 == 0 && i+1 < worktaskStringList.size())
                {
                    bizWorktaskListEntity.setSeq(seq);
                    bizWorktaskListEntity.setWorkAdressDevices(contents.trim());
                    bizWorktaskListEntity.setContent(worktaskStringList.get(i+1).trim());
                    bizWorktaskListEntityList.add(bizWorktaskListEntity);
                    seq++;
                }
            }
        }
        for(int i = 0; i<bizWorktaskListEntityList.size();i++)
        {
            BizWorktaskListEntity bizWorktaskListEntity =bizWorktaskListEntityList.get(i);
            String str = bizWorktaskListEntity.getContent().trim();
            String[] strArray = str.split("，");
            if(strArray.length>0)
            {
               for(int j = 0 ; j<strArray.length; j++) {
                    BizWorktaskListEntity bizWorktaskListEntityTemp = new BizWorktaskListEntity();
                    BeanUtils.copyProperties(bizWorktaskListEntity, bizWorktaskListEntityTemp);
                    bizWorktaskListEntityTemp.setContent(strArray[j].replace(",","").replace("，","").trim());
                    bizWorktaskListEntityListtemp.add(bizWorktaskListEntityTemp);

                }
            }
        }
        return bizWorktaskListEntityListtemp;
    }

    /**
     * @Author wuning
     * @Description //获取安全措施列表
     * @Date 14:02 2018/9/1
     * @Param [wordList, bizWorksheetId]
     * @return java.util.List<rlkj.modules.process.entity.BizSafecontrolListEntity>
     **/
    private List<BizSafecontrolListEntity> GetSafeControlFromTable(List<String> wordList,Integer bizWorksheetId)
    {
        List<String> tempWordList = Lists.newArrayList();
        Boolean booleanaddword = false;
        String temp="";
        List<BizSafecontrolListEntity>  bizSafecontrolListEntityList= Lists.newArrayList();
        for (int i = 0; i < wordList.size(); i++)
        {
            if (!wordList.equals("\u0007")) {
                if (booleanaddword) {
                    if (wordList.get(i).endsWith("\n")) {
                        temp = temp + wordList.get(i).trim();
                    } else {
                        if (temp.length() > 0) {
                            temp = temp.trim() + wordList.get(i).trim();
                            tempWordList.add(temp);
                            temp = "";
                        } else {
                            String centerwords = wordList.get(i).trim();
                            if(centerwords != null && centerwords.length()>0 && !centerwords.contains("执行")
//                                    && !centerwords.contains("应装接地线、应合接地刀闸")
//                                    && !centerwords.contains("应设遮栏、应挂标示牌及防止二次回路误碰等措施")
                            ) {
                                tempWordList.add(centerwords);
                            }

                        }
                    }
                }
                if (wordList.get(i).contains("应拉断路器") && wordList.get(i).contains("隔离开关")) {
                    booleanaddword = true;
                }
                if (wordList.get(i).contains("应装接地线、应合接地刀闸") && tempWordList.size()>0) {

                    for(int j=0; j<tempWordList.size()-1; j++)
                    {
                        BizSafecontrolListEntity bizSafecontrolListEntity = new BizSafecontrolListEntity();
                        bizSafecontrolListEntity.setContent(tempWordList.get(j).trim());
                        bizSafecontrolListEntity.setSeq(j+1);
                        bizSafecontrolListEntity.setSafeControlType(1);
                        bizSafecontrolListEntity.setCodeType(1);
                        bizSafecontrolListEntity.setCreateTime(new Date());
                        bizSafecontrolListEntity.setWorksheetId(bizWorksheetId);
                        bizSafecontrolListEntityList.add(bizSafecontrolListEntity);
                    }
                    tempWordList.clear();
                }
                if (wordList.get(i).contains("应设遮栏") && wordList.get(i).contains("应挂标示牌及防止二次回路误碰等措施")
                        && tempWordList.size()>0) {
                    for(int j=0; j<tempWordList.size()-1; j++)
                    {
                        BizSafecontrolListEntity bizSafecontrolListEntity = new BizSafecontrolListEntity();
                        bizSafecontrolListEntity.setContent(tempWordList.get(j).trim());
                        bizSafecontrolListEntity.setSeq(j+1);
                        bizSafecontrolListEntity.setSafeControlType(2);
                        bizSafecontrolListEntity.setCodeType(2);
                        bizSafecontrolListEntity.setCreateTime(new Date());
                        bizSafecontrolListEntity.setWorksheetId(bizWorksheetId);
                        bizSafecontrolListEntityList.add(bizSafecontrolListEntity);
                    }
                    tempWordList.clear();

                }
                if (wordList.get(i).contains("已执行栏目及接地编号由工作许可人填写") && tempWordList.size()>0 ) {
                    for(int j=0; j<tempWordList.size(); j++)
                    {
                        BizSafecontrolListEntity bizSafecontrolListEntity = new BizSafecontrolListEntity();
                        bizSafecontrolListEntity.setContent(tempWordList.get(j).trim());
                        bizSafecontrolListEntity.setSeq(j+1);
                        bizSafecontrolListEntity.setSafeControlType(3);
                        bizSafecontrolListEntity.setCodeType(3);
                        bizSafecontrolListEntity.setCreateTime(new Date());
                        bizSafecontrolListEntity.setWorksheetId(bizWorksheetId);
                        bizSafecontrolListEntityList.add(bizSafecontrolListEntity);
                    }
                    tempWordList.clear();
                    booleanaddword = false;
                }
            }


        }
        return bizSafecontrolListEntityList;
    }
    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap,String rgex){
        List<String> list = Lists.newArrayList();
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * @Author wuning
     * @Description 字符串分割
     * @Date 14:48 2018/9/8
     * @Param []
     * @return void
     **/
    public  static  List<String> getSubStringByWork( BizSafecontrolListEntity bizSafecontrolListEntity,String keyWork )
    {

        ArrayList<String> tempString = Lists.newArrayList();
            String str =bizSafecontrolListEntity.getContent();
            String[] strArray = str.split(keyWork);

            if(bizSafecontrolListEntity.getSafeControlType() ==1 || bizSafecontrolListEntity.getSafeControlType() ==2) {
                if (strArray.length > 0) {
                    String chineseWord = removehead(strArray[strArray.length - 1].trim());
                    String regex = "([\\u4e00-\\u9fa5]+)";
                    String strword = "";
                    Matcher matcher = Pattern.compile(regex).matcher(chineseWord);
                    while (matcher.find()) {
                        strword = matcher.group(0);
                    }
                    for (int i = 0; i < strArray.length; i++) {

                        if (i < strArray.length - 1) {
                            tempString.add(removehead(strArray[i].trim()) + strword + "");
                        } else {
                            tempString.add(removehead(strArray[i].trim()));
                        }
                    }
                }
           }
       return tempString;
    }
    private static   String removehead(String tempword)
    {
        if(tempword.startsWith("拉开"))
        {
            tempword = tempword.substring( 2);
        }
        if(tempword.startsWith("合上"))
        {
            tempword = tempword.substring( 2);
        }
        return tempword;
    }

    /**
     * @Author wuning
     * @Description 上传作业票
     * @Date 15:00 2018/9/6
     * @Param [file]
     * @return rlkj.common.utils.R
     **/
    @PostMapping("jobslipupload")
    @ApiOperation("上传作业票")
    @Transactional
    public  R jobSlipUploadFile(@RequestParam(value = "file", required = true) MultipartFile file){
        String textFileName = file.getOriginalFilename();
        BizWorksheetVO bizWorksheetVO = null;
        try
        {
            if(textFileName.endsWith(".doc"))
            {
                //处理doc业务
                bizWorksheetVO =   jobSlipDOCDataMining(file);
            }
            else if(textFileName.endsWith(".docx"))
            {

            }
//

        }catch (Exception ex)
        {
            R.error(ex.toString());
        }

        return R.ok().put("BizWorksheetVO",bizWorksheetVO);
    }

    //数据抽取
    private BizWorksheetVO jobSlipDOCDataMining(MultipartFile file)
    {
        try {
                //创建一个map对象存放word中的内容
                List<String> wordMap = Lists.newArrayList();
                InputStream fis = file.getInputStream();
                //使用HWPF组件中WordExtractor类从Word文档中提取文本或段落
                WordExtractor wordExtractor = new WordExtractor(fis);
                POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
                //获取段落内容
                for (String words : wordExtractor.getParagraphText()) {

                    if (!words.equals("\\u0007")) {
                        if (words != null) {
                            wordMap.add(words);
                        }
                    }
                }
                //获取常规字段 BizWorksheetEntity
                BizWorksheetEntity bizWorksheetEntity = gettest(wordMap);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                // 得到文档的读取范围
                Range range = hwpf.getRange();

                BizWorksheetVO bizWorksheetVO = new BizWorksheetVO();
                BeanUtils.copyProperties(bizWorksheetEntity, bizWorksheetVO);
                return  bizWorksheetVO;

            }catch (Exception ex){
                ex.toString();
            }
            return  null;

    }


    private BizWorksheetEntity gettest(List<String> wordlist)
    {
        BizWorksheetEntity bizWorksheetEntity = new BizWorksheetEntity();
        String regex_all="%s(.*?)%s(.*?)%s";
        List<String> filteredCompany = wordlist.stream().filter(x -> x.contains("工程名称") && x.contains("编号")).collect(Collectors.toList());
        if(filteredCompany.size() == 1)
        {
            List<String> lists =getSubUtil(filteredCompany.get(0).trim(),String.format(regex_all,"工程名称","编号","([0-9]{5})"));
            if(lists.size() ==1)
            {
                bizWorksheetEntity.setWorkplace(lists.get(0).trim().replaceAll( "：" , ""));
            }
            bizWorksheetEntity.setSheetNo(filteredCompany.get(0).substring( filteredCompany.get(0).indexOf("编号")+2).trim()
                    .replaceAll( "：" , ""));
            bizWorksheetEntity.setCreateTime(new Date());
            bizWorksheetEntity.setState(0);
        }

        StringBuilder workstationstr= new StringBuilder();
        Boolean hasworkstation =false;
        for(int i=0; i<wordlist.size(); i++)
        {
            String contents = wordlist.get(i);
            if(contents.contains("工作任务")&& contents.contains("4"))
            {
                hasworkstation = false;
                break;
            }
            if(hasworkstation)
            {
                workstationstr.append(contents.trim().replaceAll( ":" , ""));
            }
            if((contents.contains("配电站名称及设备双重名称") && contents.contains("3"))
                    ||contents.contains("工作变电站名称"))
            {
                hasworkstation = true;
            }

        }
        if(workstationstr != null)
        {
            bizWorksheetEntity.setWorkStation(workstationstr.toString().trim());
        }






        return bizWorksheetEntity;
    }


}
