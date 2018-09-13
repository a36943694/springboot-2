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

import rlkj.modules.process.entity.CfgCodeEntity;
import rlkj.modules.process.service.CfgCodeService;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;



/**
 * 字典表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@Api(description = "字典表")
@RestController
@RequestMapping("process/cfgcode")
public class CfgCodeController {
    @Autowired
    private CfgCodeService cfgCodeService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取列表（分页）")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "codeType",value = "类型",dataType = "int",paramType = "query")})
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cfgCodeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 通过类型获取字典列表
     */
    @ApiOperation(value = "通过类型获取列表")
    @GetMapping("/getListByType")
    public R getListByType(@RequestParam Integer type){
        List<CfgCodeEntity> result = cfgCodeService.getListByType(type);

        return R.ok().put("result", result);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id获取详细信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
			CfgCodeEntity cfgCode = cfgCodeService.selectById(id);

        return R.ok().put("cfgCode", cfgCode);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public R save(@RequestBody CfgCodeEntity cfgCode){
			cfgCodeService.insert(cfgCode);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public R update(@RequestBody CfgCodeEntity cfgCode){
			cfgCodeService.updateById(cfgCode);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			cfgCodeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
