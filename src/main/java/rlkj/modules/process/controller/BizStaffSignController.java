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

import rlkj.modules.process.entity.BizStaffSignEntity;
import rlkj.modules.process.service.BizStaffSignService;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;



/**
 * 人员签到表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@Api(description = "人员签到")
@RestController
@RequestMapping("process/bizstaffsign")
public class BizStaffSignController {
    @Autowired
    private BizStaffSignService bizStaffSignService;

    /**
     * 列表
     */
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5")})
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizStaffSignService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id获取签到详细信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
			BizStaffSignEntity bizStaffSign = bizStaffSignService.selectById(id);

        return R.ok().put("bizStaffSign", bizStaffSign);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody BizStaffSignEntity bizStaffSign){
			bizStaffSignService.insert(bizStaffSign);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public R update(@RequestBody BizStaffSignEntity bizStaffSign){
			bizStaffSignService.updateById(bizStaffSign);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			bizStaffSignService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
