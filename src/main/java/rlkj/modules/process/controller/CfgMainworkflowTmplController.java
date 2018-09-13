package rlkj.modules.process.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;
import rlkj.modules.process.entity.CfgMainworkflowTmplEntity;
import rlkj.modules.process.service.CfgMainworkflowTmplService;


/**
 * 主流程模版表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "主流程模版")
@RestController
@RequestMapping("process/cfgmainworkflowtmpl")
public class CfgMainworkflowTmplController {
    @Autowired
    private CfgMainworkflowTmplService cfgMainworkflowTmplService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "stepName",value = "步骤名称",dataType = "String",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cfgMainworkflowTmplService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			CfgMainworkflowTmplEntity cfgMainworkflowTmpl = cfgMainworkflowTmplService.selectById(id);

        return R.ok().put("cfgMainworkflowTmpl", cfgMainworkflowTmpl);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody CfgMainworkflowTmplEntity cfgMainworkflowTmpl){
			cfgMainworkflowTmplService.insert(cfgMainworkflowTmpl);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody CfgMainworkflowTmplEntity cfgMainworkflowTmpl){
			cfgMainworkflowTmplService.updateById(cfgMainworkflowTmpl);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			cfgMainworkflowTmplService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
