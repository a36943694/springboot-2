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
import rlkj.modules.process.entity.CfgWorktypeEntity;
import rlkj.modules.process.service.CfgWorktypeService;


/**
 * 工作类型配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "工作类型配置")
@RestController
@RequestMapping("process/cfgworktype")
public class CfgWorktypeController {
    @Autowired
    private CfgWorktypeService cfgWorktypeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "name",value = "类型名称",dataType = "String",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cfgWorktypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			CfgWorktypeEntity cfgWorktype = cfgWorktypeService.selectById(id);

        return R.ok().put("cfgWorktype", cfgWorktype);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody CfgWorktypeEntity cfgWorktype){
			cfgWorktypeService.insert(cfgWorktype);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody CfgWorktypeEntity cfgWorktype){
			cfgWorktypeService.updateById(cfgWorktype);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			cfgWorktypeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
