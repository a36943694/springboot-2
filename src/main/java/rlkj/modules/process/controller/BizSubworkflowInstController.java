package rlkj.modules.process.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rlkj.modules.process.entity.BizSubworkflowInstEntity;
import rlkj.modules.process.service.BizSubworkflowInstService;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;


/**
 * 子流程实例表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@Api(description = "子流程实例")
@RestController
@RequestMapping("process/bizsubworkflowinst")
public class BizSubworkflowInstController {
    @Autowired
    private BizSubworkflowInstService bizSubworkflowInstService;

    /**
     * 列表
     */
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5")})
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizSubworkflowInstService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id获取子流程详细信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
			BizSubworkflowInstEntity bizSubworkflowInst = bizSubworkflowInstService.selectById(id);

        return R.ok().put("bizSubworkflowInst", bizSubworkflowInst);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public R save(@RequestBody BizSubworkflowInstEntity bizSubworkflowInst){
			bizSubworkflowInstService.insert(bizSubworkflowInst);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public R update(@RequestBody BizSubworkflowInstEntity bizSubworkflowInst){
			bizSubworkflowInstService.updateById(bizSubworkflowInst);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			bizSubworkflowInstService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    @ApiOperation(value = "通过安全策略id获取安全子流程列表")
    @GetMapping("/getSafeListBySafeId")
    public R getSafeListBySafeId(@RequestParam Integer safecontrolId){
        List<BizSubworkflowInstEntity> safeList = bizSubworkflowInstService.getSafeListBySafeId(safecontrolId);
        return R.ok().put("safeList",safeList);
    }

    @ApiOperation(value = "通过工作清单id获取安全子流程列表")
    @GetMapping("/getWorkListByWorkId")
    public R getWorkListByWorkId(@RequestParam Integer worktaskId){
        List<BizSubworkflowInstEntity> workList = bizSubworkflowInstService.getWorkListByWorkId(worktaskId);
        return R.ok().put("workList",workList);
    }
}
