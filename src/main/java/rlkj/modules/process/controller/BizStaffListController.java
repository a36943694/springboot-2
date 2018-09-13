package rlkj.modules.process.controller;

import java.util.Arrays;
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
import rlkj.modules.process.entity.BizStaffListEntity;
import rlkj.modules.process.service.BizStaffListService;


/**
 * 工作人员配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "工作人员配置")
@RestController
@RequestMapping("process/bizstafflist")
public class BizStaffListController {
    @Autowired
    private BizStaffListService bizStaffListService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "userName",value = "人员名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "workPosition",value = "1 - 工作负责人;  2 - 施工人员",dataType = "int",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizStaffListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			BizStaffListEntity bizStaffList = bizStaffListService.selectById(id);

        return R.ok().put("bizStaffList", bizStaffList);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody BizStaffListEntity bizStaffList){
			bizStaffListService.insert(bizStaffList);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody BizStaffListEntity bizStaffList){
			bizStaffListService.updateById(bizStaffList);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			bizStaffListService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
