package rlkj.modules.process.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;
import rlkj.modules.process.entity.EveninfEntity;
import rlkj.modules.process.service.EveninfService;


/**
 * 告警信息表
 *
 * @author ${author}
 * @email ${email}
 * @date 2018-08-29 14:31:42
 */
@Api(description = "告警信息")
@RestController
@RequestMapping("process/eveninf")
public class EveninfController {
    @Autowired
    private EveninfService eveninfService;

    /**
     * 列表
     */
    @ApiOperation(value = "按条件获取告警信息列表")
    @GetMapping( "/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "params"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "int",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "每页数量",dataType = "int",paramType = "query",defaultValue = "5"),
            @ApiImplicitParam(name = "eventCode",value = "告警编码",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status",value = "处理状态:  0 - 未处理;  1 - 已处理",dataType = "int",paramType = "query")})
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eveninfService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "通过id获取告警信息详细信息")
    @GetMapping( value = "/info/{id}" )
    public R info(@PathVariable("id") Integer id){
			EveninfEntity eveninf = eveninfService.selectById(id);

        return R.ok().put("eveninf", eveninf);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增告警信息")
    @PostMapping("/save")
    public R save(@RequestBody EveninfEntity eveninf){
			eveninfService.insert(eveninf);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改告警信息")
    @PutMapping("/update")
    public R update(@RequestBody EveninfEntity eveninf){
			eveninfService.updateById(eveninf);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除告警信息")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			eveninfService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
