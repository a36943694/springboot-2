package rlkj.modules.process.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;
import rlkj.modules.process.entity.BizMainworkflowInstEntity;
import rlkj.modules.process.entity.BizSafecontrolListEntity;
import rlkj.modules.process.entity.BizWorktaskListEntity;
import rlkj.modules.process.service.BizMainworkflowInstService;
import rlkj.modules.process.service.BizSafecontrolListService;
import rlkj.modules.process.service.BizWorktaskListService;


/**
 * 主流程实例表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "主流程实例")
@RestController
@RequestMapping("process/bizmainworkflowinst")
public class BizMainworkflowInstController {
    @Autowired
    private BizMainworkflowInstService bizMainworkflowInstService;

    @Autowired
    private BizSafecontrolListService bizSafecontrolListService;

    @Autowired
    private BizWorktaskListService bizWorktaskListService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "stepName",value = "步骤名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "state",value = "状态(0 - 未开始;  1 - 进行中;  2 - 已完成;  3 - 异常)",dataType = "int",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizMainworkflowInstService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			BizMainworkflowInstEntity bizMainworkflowInst = bizMainworkflowInstService.selectById(id);

        return R.ok().put("bizMainworkflowInst", bizMainworkflowInst);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody BizMainworkflowInstEntity bizMainworkflowInst){
			bizMainworkflowInstService.insert(bizMainworkflowInst);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody BizMainworkflowInstEntity bizMainworkflowInst){
			bizMainworkflowInstService.updateById(bizMainworkflowInst);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			bizMainworkflowInstService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 通过工作票id获取工作任务清单和安全措施清单
     * @param workSheetId
     * @return
     */
    @ApiOperation(value = "通过工作票id获取工作任务清单和安全措施清单")
    @GetMapping("/getSafeAndWorkList")
    public R getSafeAndWorkList(@RequestParam("workSheetId") Integer workSheetId){
        List<BizSafecontrolListEntity> safeList = bizSafecontrolListService.getListByWorkSheetId(workSheetId);
        List<BizWorktaskListEntity> workList = bizWorktaskListService.getListByWorkSheetId(workSheetId);

        return  R.ok().put("safeList",safeList).put("workList",workList);

    }
}
