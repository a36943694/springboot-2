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
import rlkj.modules.process.entity.CfgWorkmoudleEntity;
import rlkj.modules.process.service.CfgWorkmoudleService;


/**
 * 工作模型配置表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "工作模型配置")
@RestController
@RequestMapping("process/cfgworkmoudle")
public class CfgWorkmoudleController {
    @Autowired
    private CfgWorkmoudleService cfgWorkmoudleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "按条件获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "safePoint",value = "工作检查内容关键字",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "checkMode",value = "1 - 图片;  2 - 视频;  3 - 手工",dataType = "String",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cfgWorkmoudleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "通过id获取详细信息")
    public R info(@PathVariable("id") Integer id){
			CfgWorkmoudleEntity cfgWorkmoudle = cfgWorkmoudleService.selectById(id);

        return R.ok().put("cfgWorkmoudle", cfgWorkmoudle);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public R save(@RequestBody CfgWorkmoudleEntity cfgWorkmoudle){
			cfgWorkmoudleService.insert(cfgWorkmoudle);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public R update(@RequestBody CfgWorkmoudleEntity cfgWorkmoudle){
			cfgWorkmoudleService.updateById(cfgWorkmoudle);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody Integer[] ids){
			cfgWorkmoudleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
